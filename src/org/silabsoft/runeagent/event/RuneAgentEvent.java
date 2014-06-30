/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.event;

/**
 *
 * @author unsignedbyte
 */
public class RuneAgentEvent {

    private final RuneAgentEventTypes type;
    private final Object source;

    public RuneAgentEvent(RuneAgentEventTypes type, Object source) {
        this.type = type;
        this.source = source;
    }

    public RuneAgentEventTypes getType() {
        return type;
    }

    public Object getSource() {
        return source;
    }
}


