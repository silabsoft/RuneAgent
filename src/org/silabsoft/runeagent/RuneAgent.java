/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.silabsoft.runeagent.event.RuneAgentEvent;
import org.silabsoft.runeagent.event.RuneAgentEventTypes;
import org.silabsoft.runeagent.gui.AgentFrame;
import org.silabsoft.runeagent.listener.RuneAgentEventListener;
import org.silabsoft.runeagent.transformer.AgentTransformer;

/**
 *
 * @author unsignedbyte
 */
public class RuneAgent {

    private List<RuneAgentEventListener> listeners;
    private AgentFrame agentFrame;
    private final ScriptEngine engine;
    private final Instrumentation instrumentation;
    private final HashMap<String, Object> modifiedObjects;

    public RuneAgent(Instrumentation i) {
        this.listeners = new ArrayList<RuneAgentEventListener>();
        this.modifiedObjects = new HashMap<String, Object>();
        this.instrumentation = i;
        ScriptEngineManager factory = new ScriptEngineManager();
        engine = factory.getEngineByName("JavaScript");
        engine.put("runeAgent", this);

    }

    public HashMap<String, Object> getModifiedObjects() {
        return modifiedObjects;
    }


    public void addRuneAgentListener(RuneAgentEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeRuneAgentListener(RuneAgentEventListener listener) {
        this.listeners.remove(listener);
    }

    public void fireRuneAgentEvent(RuneAgentEvent event) {
        for (RuneAgentEventListener listener : listeners) {
            listener.onRuneAgentEvent(event);
        }
    }

    public void setAgentFrame(AgentFrame af) {
        if (agentFrame != null) {
            removeAgentFrame(af);
        }
        if (af instanceof RuneAgentEventListener) {
            this.addRuneAgentListener((RuneAgentEventListener) af);
        }
        this.agentFrame = af;
        this.fireRuneAgentEvent(new RuneAgentEvent(RuneAgentEventTypes.AGENT_FRAME_ADDED, af));
    }

    public AgentFrame getAgentFrame() {
        return this.agentFrame;
    }

    private void removeAgentFrame(AgentFrame af) {
        this.fireRuneAgentEvent(new RuneAgentEvent(RuneAgentEventTypes.AGENT_FRAME_REMOVED, af));
        if (af instanceof RuneAgentEventListener) {
            this.removeRuneAgentListener((RuneAgentEventListener) af);
        }
        agentFrame = null;
    }

    public void addTransformer(AgentTransformer transformer) {
        System.out.println(this.instrumentation.isRetransformClassesSupported());
        this.instrumentation.addTransformer(transformer);

        this.fireRuneAgentEvent(new RuneAgentEvent(RuneAgentEventTypes.AGENT_TRANSFORMER_ADDED, transformer));
    }

    public ScriptEngine getEngine() {
        return engine;
    }

    public RuneAgent getAgent() {
        return this;
    }
}
