/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.util;

import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.TargetLostException;

/**
 * This class is simply for renaming delete to remove due to javascripts delete
 * operator
 *
 * @author unsignedbyte
 */
public class JavaScriptInstructionListWrapper {

    private final InstructionList instructionList;

    public JavaScriptInstructionListWrapper(InstructionList instructionList) {
        this.instructionList = instructionList;
    }

    public void remove(Instruction from, Instruction to) throws TargetLostException {
        this.instructionList.delete(from, to); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(InstructionHandle from, InstructionHandle to) throws TargetLostException {
        this.instructionList.delete(from, to); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(Instruction i) throws TargetLostException {
        this.instructionList.delete(i); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(InstructionHandle ih) throws TargetLostException {
        this.instructionList.delete(ih); //To change body of generated methods, choose Tools | Templates.
    }

    public InstructionList getInstructionList() {
        return instructionList;
    }

}
