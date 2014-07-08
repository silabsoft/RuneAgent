/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.util;

import java.util.HashMap;

/**
 *
 * @author unsignedbyte
 */
public class TransformerScript extends Identifiable {

    private final String script;
    private final HashMap<String, Object> scriptObjects;

    public TransformerScript(String identity, String script) {
        super(identity);
        this.script = script;
        this.scriptObjects = new HashMap<String, Object>();
    }

    public String getScript() {
        return script;
    }

    public void addScriptObject(String s, Object o) {
        scriptObjects.put(s, o);
    }

    public HashMap<String, Object> getScriptObjects() {
        return scriptObjects;
    }
    public Object get(String s){
       return scriptObjects.get(s);
    }
}
