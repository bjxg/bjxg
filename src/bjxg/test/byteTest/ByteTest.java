/* 
 * @(#)ByteTest.java    Created on 2011-6-21
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.byteTest;

import java.nio.ByteBuffer;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2011-6-21 下午04:16:11 $
 */
public class ByteTest {
    public static void main(String[] args) {
        int len = 10;
        byte[] bs = "1".getBytes();
        byte[] bs2 = { 0x40 };
        // String s = new String(bs2);
        int i = (bs2[0] & 0xff);
        System.out.println(i);
        System.out.println(bs);
        ByteBuffer bb = ByteBuffer.allocate(len);
        bb.position(0);
        bb.put("1234567890".getBytes());

    }

    public static int byte2int(byte b[], int offset) {
        return b[offset + 3] & 0xff | (b[offset + 2] & 0xff) << 8 | (b[offset + 1] & 0xff) << 16
                | (b[offset] & 0xff) << 24;
    }
}
