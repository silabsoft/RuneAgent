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
import static org.apache.bcel.Constants.INVOKEVIRTUAL;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.POP;
import org.apache.bcel.generic.Type;
import org.silabsoft.runeagent.util.ClassModifier;

/**
 *
 * @author unsignedbyte
 */
public class RT5StreamObjectHook extends ClassModifier {

    private final String streamFieldName;
    private final String isaacAttatchMethod;

    public RT5StreamObjectHook(String identity, String className,String interfaceName, String streamFieldName, String isaacAttatchMethod) {
        super(identity, className, interfaceName);
        this.streamFieldName = streamFieldName;
        this.isaacAttatchMethod = isaacAttatchMethod;
    }

    @Override
    public byte[] transform(ClassGen classGen, AgentTransformer transformer) {
        super.transform(classGen, transformer);

        for (Method m : classGen.getMethods()) {
            MethodGen mg = new MethodGen(m, classGen.getClassName(), classGen.getConstantPool());
            InstructionList il = mg.getInstructionList();
            if (il == null) {
                continue;
            }
            for (InstructionHandle h : il.getInstructionHandles()) {
                Instruction x = h.getInstruction();
                if (x instanceof INVOKEVIRTUAL) {
                    INVOKEVIRTUAL iv = (INVOKEVIRTUAL) x;
                    if (iv.getMethodName(mg.getConstantPool()).equals(isaacAttatchMethod)) {
                        Instruction y = findStreamField(mg,h.getPrev());
                        if (y == null) {
                            throw new RuntimeException("can't find the outstreamObject");
                        }
                        if (y instanceof GETFIELD || y instanceof GETSTATIC) {
                            insertTransformerHook(classGen, mg, x ,y, transformer);
                            break;
                        }
                    }
                }
            }

        }
        return classGen.getJavaClass().getBytes();
    }

    private void insertTransformerHook(ClassGen classGen, MethodGen mg,Instruction inser, Instruction instruction, AgentTransformer transformer) {
      
        InstructionList init = new InstructionList();
        InstructionFactory factory = new InstructionFactory(classGen, classGen.getConstantPool());

        InstructionList methodInstruct = mg.getInstructionList();
        if (instruction instanceof GETSTATIC) {
         

            init.append(instruction);
        } else {
            init.append(new ALOAD(0));
            init.append(instruction);
        }
        init.append(factory.createInvoke(transformer.getClass().getCanonicalName(), "setOutStream", Type.VOID, new Type[]{ObjectType.OBJECT}, Constants.INVOKESTATIC));
        methodInstruct.append(inser, init);
        mg.setInstructionList(methodInstruct);
        mg.setMaxLocals();
        mg.setMaxStack();
        transformer.getAgent().getModifiedObjects().put("OutStream Hook", true);
        classGen.replaceMethod(mg.getMethod(),mg.getMethod());
    }

    private Instruction findStreamField(MethodGen mg, InstructionHandle prev) {
        if (prev == null) {
            return null;
        }
        Instruction x = prev.getInstruction();
        if (x instanceof GETSTATIC) {
            GETSTATIC gs = (GETSTATIC) x;
            if (gs.getFieldName(mg.getConstantPool()).equals(streamFieldName)) {
                return x;
            }
        }
        if (x instanceof GETFIELD) {
            GETFIELD gs = (GETFIELD) x;
            if (gs.getFieldName(mg.getConstantPool()).equals(streamFieldName)) {
                 return x;
            }
        }
        return findStreamField(mg, prev.getPrev());
    }
}
