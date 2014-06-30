/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import org.silabsoft.runeagent.gui.AgentFrameGeneric;
import org.silabsoft.runeagent.transformer.AgentTransformer;

/**
 *
 * @author unsignedbyte
 */
public class Premain {

    public static void premain(String s, Instrumentation instrumentation) {

        AgentFrameGeneric f = new AgentFrameGeneric();
        RuneAgent agent = new RuneAgent(instrumentation);
        agent.setAgentFrame(f);
        f.setVisible(true);
        try {
            agent.getEngine().eval(new FileReader(s));
        } catch (ScriptException ex) {
            f.logString(ex.getMessage());
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            f.logString("Config not Found");
            ex.printStackTrace();
        }

    }
}
