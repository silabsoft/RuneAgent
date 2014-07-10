/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.gui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.silabsoft.runeagent.hook.ByteStreamMeta;

/**
 *
 * @author unsignedbyte
 */
public class ByteStreamMetaJListCellRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof ByteStreamMeta) {
            ByteStreamMeta bsm = (ByteStreamMeta) value;
            if (bsm.displayName() == null) {
                setText(bsm.methodName());
            } else {
                setText(bsm.displayName().equals("") ? bsm.methodName() : bsm.displayName());
            }
            setToolTipText(bsm.methodName());
        }
        return this;
    }
}
