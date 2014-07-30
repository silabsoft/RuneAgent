/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.transformer;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.MethodGen;
import org.silabsoft.runeagent.util.ClassModifier;

/**
 *
 * @author unsignedbyte
 * 
 * Used to remove finalization on methods to allow the ability of overriding the methods.
 */
public class DeFinalizerTransformer extends ClassModifier {

    public DeFinalizerTransformer(String identity, String className, String interfaceName) {
        super(identity, className, interfaceName);
    }

    @Override
    public byte[] transform(ClassGen classGen, AgentTransformer transformer) {
        super.transform(classGen, transformer);
        for (Method m : classGen.getMethods()) {
            if (m.isFinal()) {
                m.isFinal(false);
                classGen.replaceMethod(m, m);
            }
        }
        return classGen.getJavaClass().getBytes();
    }

}
