/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.util;

/**
 *
 * @author unsignedbyte
 */
public class TransformerScript extends Identifiable {

    private final String script;

    public TransformerScript(String identity, String script) {
        super(identity);
        this.script = script;
    }

    public String getScript() {
        return script;
    }

}
