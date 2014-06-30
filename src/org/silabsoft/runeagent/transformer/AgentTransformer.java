/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import org.silabsoft.runeagent.RuneAgent;
import org.silabsoft.runeagent.event.RuneAgentEvent;
import org.silabsoft.runeagent.event.RuneAgentEventTypes;
import org.silabsoft.runeagent.gui.AgentFrameGeneric;

/**
 *
 * @author unsignedbyte
 */
public abstract class AgentTransformer implements ClassFileTransformer {

    protected final RuneAgent agent;
    private static AgentTransformer transformer;

    public AgentTransformer(RuneAgent agent) {
        this.agent = agent;
        this.transformer = this;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        return classfileBuffer;
    }

    public RuneAgent getAgent() {
        return agent;
    }


}
