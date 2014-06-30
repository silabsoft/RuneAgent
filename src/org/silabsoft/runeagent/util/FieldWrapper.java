/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.util;

/**
 *
 * @author Silabsoft
 */
class FieldWrapper extends Identifiable{

    private final String fieldName;
    private final String fieldType;
    private final String cast;
    private boolean staticField;

    public FieldWrapper(String identity, String fieldName, String type,String cast) {
        super(identity);
        this.fieldName = fieldName;
        this.fieldType = type;
        this.cast = cast;

    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getCastType(){
        return cast;
    }
    public String getSuffixMethodName() {
        String s = identity.substring(0, 1);
        String e = identity.substring(1);
        return s.toUpperCase() + e;
    }

    @Override
    public String toString() {
        return "FieldWrapper{" + "identity=" + identity + ", fieldName=" + fieldName + ", fieldType=" + fieldType + ", cast=" + cast + '}';
    }

    boolean isStatic() {
        return staticField;
    }

    public void setStaticField(boolean staticField) {
        this.staticField = staticField;
    }
    
}
