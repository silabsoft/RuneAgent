/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.runeagent.hook;

/**
 *
 * @author unsignedbyte
 */
public interface DummyVariablesByteStream {

    @ByteStreamMeta(
            methodName = "p1isaac",
            parameters = "6,#"
    )
    public void p1isaac(byte b ,int value);

    @ByteStreamMeta(
            methodName = "p1",
            parameters = "#"
    )
    public void p1(int value);

    @ByteStreamMeta(
            methodName = "sp1",
            parameters = "#,#"
    )
    public void sp1(int i,int value);

    @ByteStreamMeta(
            methodName = "np1",
            parameters = "#,#"
    )
    public void np1(int i,int value);

    @ByteStreamMeta(
            methodName = "p2",
            parameters = "#"
    )
    public void p2(int value);

    @ByteStreamMeta(
            methodName = "ip2",
            parameters = "true,#"
    )
    public void ip2(boolean i,int value);

    @ByteStreamMeta(
            methodName = "isp2",
            parameters = "#"
    )
    public void isp2(int i,int value);

    @ByteStreamMeta(
            methodName = "sp2",
            parameters = "#,#"
    )
    public void sp2(int i,int value);

    @ByteStreamMeta(
            methodName = "p4",
            parameters = "#,#"
    )
    public void p4(int value);

    @ByteStreamMeta(
            methodName = "ip4",
            parameters = "#"
    )
    public void ip4(int value);

    @ByteStreamMeta(
            methodName = "sp4",
            parameters = "#"
    )
    public void sp4(int value);

    @ByteStreamMeta(
            methodName = "p8",
            parameters = "#"
    )

    public void p8(long value);

    @ByteStreamMeta(
            methodName = "pjstr",
            parameters = "\"\""
    )
    public void pjstr(String value);

}
