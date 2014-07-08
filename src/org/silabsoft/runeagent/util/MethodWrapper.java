/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.util;

import org.apache.bcel.generic.Type;

/**
 *
 * @author Silabsoft
 */
public class MethodWrapper extends Identifiable{

    private final String methodName;
    private final String signature;
    private final boolean doLogging;
    private final String returnType;
    public MethodWrapper(String identity, String methodName,String signature,boolean doLogging,String returnType) {
        super(identity);
        this.methodName = methodName;
        this.signature = signature;
        this.doLogging = doLogging;
        this.returnType =returnType;
    }

    public MethodWrapper(String identity, String methodName,String signature,boolean doLogging) {
       this(identity,methodName,signature,doLogging,null);

    }
    public String getMethodName() {
        return methodName;
    }

    public String getSignature() {
        return signature;
    }

    public boolean doLogging() {
        return doLogging;
    }

    public String getReturnType() {
        return returnType;
    }


}
