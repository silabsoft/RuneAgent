/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.transformer;

import java.io.ByteArrayInputStream;
import java.security.ProtectionDomain;
import javax.swing.JTextArea;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ObjectType;
import org.silabsoft.runeagent.RuneAgent;
import org.silabsoft.runeagent.event.TransformerEvent;
import org.silabsoft.runeagent.gui.GenericOutStreamPanel;
import org.silabsoft.runeagent.util.ClassModifier;

/**
 *
 * @author unsignedbyte
 */
public class RuneTekFourTransformer extends AgentTransformer {

    public static GenericOutStreamPanel panel;

    public RuneTekFourTransformer(RuneAgent agent) {
        super(agent);
        panel = new GenericOutStreamPanel(agent.getEngine());
        agent.getAgentFrame().addTab(panel, "OutStream");
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
        ClassModifier m = modifiers.get(className.replaceAll("/", "."));

        if (m != null) {
            agent.fireRuneAgentEvent(new TransformerEvent(this.getClass().getName(), "Found: " + m.getIdentity() + " => " + m.getClassName()));
            try {
                byte[] data = m.transform(new ClassGen(new ClassParser(new ByteArrayInputStream(classFileBuffer), null).parse()), this);
                agent.getModifiedObjects().put(m.getIdentity(), new ObjectType(m.getClassName()));
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                agent.fireRuneAgentEvent(new TransformerEvent(this.getClass().getName(), "Error Transforming: " + m.getIdentity() + " => " + e));
            }
        }
        return classFileBuffer;
    }

    public void addClassModifier(ClassModifier cm) {
        this.modifiers.put(cm.getClassName(), cm);
    }

    public static void setOutStream(Object o) {

        panel.addOutStreamObject(o);
    }
    public static void log(String s){
        panel.logEvent((JTextArea) panel.getOutStreamLogComponent(), s);
    }
}
