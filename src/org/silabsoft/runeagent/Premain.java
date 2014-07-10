/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import org.silabsoft.runeagent.gui.AgentFrameGeneric;

/**
 *
 * @author unsignedbyte
 */
public class Premain {
    
    public static void premain(String s, Instrumentation instrumentation) {
        
        AgentFrameGeneric f = new AgentFrameGeneric();
        RuneAgent agent = new RuneAgent(instrumentation);
        double currentVersion = getCurrentVersion();
        if (currentVersion != -1) {
            if (agent.VERSION != currentVersion) {
                JOptionPane.showMessageDialog(f,
                        "Please update RuneAgent at http://silabsoft.org",
                        "RuneAgent out of Date",
                        JOptionPane.WARNING_MESSAGE);
                try {
                    Desktop.getDesktop().browse(new URL("http://silabsoft.org/rune-agent-downloads/").toURI());
                } catch (MalformedURLException ex) {
                   
                } catch (URISyntaxException ex) {
                   
                } catch (IOException ex) {
                   
                }
            }
        }
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
    
    public static double getCurrentVersion() {
        try {
            double version = 0;
            URL url = new URL("http://silabsoft.org/runeagent/version.dat");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            version = Double.parseDouble(in.readLine());
            in.close();
            return version;
        } catch (Exception x) {
            x.printStackTrace();
            return -1;
        }
    }
}
