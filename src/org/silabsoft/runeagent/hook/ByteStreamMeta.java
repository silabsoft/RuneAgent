/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.silabsoft.runeagent.hook;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author unsignedbyte
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ByteStreamMeta {
    
    public String name() ;
    public String parameters() ;
}
