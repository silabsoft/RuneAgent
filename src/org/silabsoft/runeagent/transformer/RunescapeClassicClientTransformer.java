/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.transformer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;
import org.silabsoft.runeagent.util.ClassModifier;

/**
 *
 * @author unsignedbyte
 */
public class RunescapeClassicClientTransformer extends ClassModifier {

    private final ObjectType stream;

    public RunescapeClassicClientTransformer(String identity, String className, String interfaceName, String stream) {
        super(identity, className, interfaceName);
        this.stream = new ObjectType(stream);
    }

    @Override
    public byte[] transform(ClassGen classGen, AgentTransformer transformer) {
        super.transform(classGen, transformer);
        boolean found = false;
        boolean finished = false;
        for (Method m : classGen.getMethods()) {
            MethodGen mg = new MethodGen(m, classGen.getClassName(), classGen.getConstantPool());
            InstructionList il = mg.getInstructionList();
            if (il == null) {
                continue;
            }
            for (InstructionHandle h : il.getInstructionHandles()) {
                Instruction x = h.getInstruction();
                if (x instanceof NEW) {
                    Type lct = ((NEW) x).getLoadClassType(mg.getConstantPool());
                    if (lct.equals(stream)) {
                        found = true;
                    }
                }
                if (x instanceof GETFIELD && found && !finished) {
                    GETFIELD pf = ((GETFIELD) x);
                    if (pf.getFieldType(mg.getConstantPool()).equals(stream)) {
                        insertTransformerHook(classGen, mg, x, transformer);
                        finished = true;
                    }
                }
            }

        }
        try {
            classGen.getJavaClass().dump("Test.class");
        } catch (IOException ex) {
            Logger.getLogger(RunescapeClassicClientTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classGen.getJavaClass().getBytes();
    }

    private void insertTransformerHook(ClassGen classGen, MethodGen mg, Instruction instruction, AgentTransformer transformer) {

        InstructionList init = new InstructionList();
        InstructionFactory factory = new InstructionFactory(classGen, classGen.getConstantPool());
        MethodGen mgn = mg;
        InstructionList methodInstruct = mgn.getInstructionList();
        init.append(instruction);
        init.append(factory.createInvoke(transformer.getClass().getCanonicalName(), "setOutStream", Type.VOID, new Type[]{ObjectType.OBJECT}, Constants.INVOKESTATIC));
        init.append(new ALOAD(0));
        methodInstruct.insert(instruction, init);
        mgn.setMaxLocals();
        mgn.setMaxStack();
        transformer.getAgent().getModifiedObjects().put("OutStream Hook", true);
        classGen.replaceMethod(mg.getMethod(), mgn.getMethod());
        System.out.println("called");
    }
}
