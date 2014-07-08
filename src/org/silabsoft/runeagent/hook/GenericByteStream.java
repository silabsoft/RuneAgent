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
public interface GenericByteStream {

    @ByteStreamMeta(
            name = "p1isaac",
            parameters = "#"
    )
    public void p1isaac(int value);

    @ByteStreamMeta(
            name = "p1",
            parameters = "#"
    )
    public void p1(int value);

    @ByteStreamMeta(
            name = "sp1",
            parameters = "#"
    )
    public void sp1(int value);

    @ByteStreamMeta(
            name = "np1",
            parameters = "#"
    )
    public void np1(int value);

    @ByteStreamMeta(
            name = "p2",
            parameters = "#"
    )
    public void p2(int value);

    @ByteStreamMeta(
            name = "ip2",
            parameters = "#"
    )
    public void ip2(int value);

    @ByteStreamMeta(
            name = "isp2",
            parameters = "#"
    )
    public void isp2(int value);

    @ByteStreamMeta(
            name = "sp2",
            parameters = "#"
    )
    public void sp2(int value);

    @ByteStreamMeta(
            name = "p4",
            parameters = "#"
    )
    public void p4(int value);

    @ByteStreamMeta(
            name = "ip4",
            parameters = "#"
    )
    public void ip4(int value);

    @ByteStreamMeta(
            name = "sp4",
            parameters = "#"
    )
    public void sp4(int value);

    @ByteStreamMeta(
            name = "p8",
            parameters = "#"
    )

    public void p8(int value);

    @ByteStreamMeta(
            name = "pjstr",
            parameters = "\"\""
    )
    public void pjstr(String value);

}
