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
public class Identifiable {
    protected final String identity;

    public Identifiable(String identity) {
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }
    
}
