/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.gui;

import java.lang.reflect.Method;
import javax.script.ScriptEngine;
import javax.swing.DefaultListModel;
import org.silabsoft.runeagent.hook.ByteStreamMeta;

/**
 *
 * @author unsignedbyte
 */
public class SubOutstreamPanel extends GenericOutStreamPanel {

    public SubOutstreamPanel(ScriptEngine engine) {
        super(engine);
    }

    @Override
    public void addOutStreamObject(Object o) {
        this.outStreamObject = o;
        this.engine.put("stream", o);
        DefaultListModel model = new DefaultListModel();
        for (Class i : o.getClass().getSuperclass().getInterfaces()) {
            for (Method m : i.getMethods()) {
                ByteStreamMeta bsm = m.getAnnotation(ByteStreamMeta.class);
                if (bsm != null) {
                    model.addElement(bsm);
                }
            }
        }
        jList1.setModel(model);
        jList1.setCellRenderer(new ByteStreamMetaJListCellRenderer());
        logEvent(eventLog, "Outstream object set.");
    }

}
