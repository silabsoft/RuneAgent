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
public interface RT5DummyVariableByteStream {

    @ByteStreamMeta(
            methodName = "p1isaac",
            parameters = "#,#"
    )
    public void p1isaac(byte b, int value);

    @ByteStreamMeta(
            methodName = "p1",
            parameters = "#,#"
    )

    public void p1(byte b, int value);

    @ByteStreamMeta(
            methodName = "sp1",
            parameters = "#,#"
    )
    public void sp1(byte b, int value);

    @ByteStreamMeta(
            methodName = "np1",
            parameters = "#,#"
    )
    public void np1(int i, int x);

    @ByteStreamMeta(
            methodName = "p2",
            parameters = "#,#"
    )
    public void p2(int i, byte b);

    @ByteStreamMeta(
            methodName = "ip2",
            parameters = "#,#"
    )
    public void ip2(int i, int x);

    @ByteStreamMeta(
            methodName = "isp2",
            parameters = "#,#"
    )
    public void isp2(int a, int b);

    @ByteStreamMeta(
            methodName = "sp2",
            parameters = "#,#"
    )

    public void sp2(int a, int b);

    @ByteStreamMeta(
            methodName = "p3",
            parameters = "#,#"
    )
    public void p3(int a, int b);

    @ByteStreamMeta(
            methodName = "ip4",
            parameters = "#,#"
    )
    public void ip4(byte b, int i);

    @ByteStreamMeta(
            methodName = "p4",
            parameters = "#,#"
    )
    public void p4(int a, int b);

    @ByteStreamMeta(
            methodName = "sp4",
            parameters = "#,#"
    )
    public void sp4(int i, byte b);

    @ByteStreamMeta(
            methodName = "isp4",
            parameters = "#,#"
    )
    public void isp4(int i, byte b);

    @ByteStreamMeta(
            methodName = "p8",
            parameters = "#,#"
    )
    public void p8(long l, byte b);

}
