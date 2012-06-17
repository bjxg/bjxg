/* 
 * @(#)UUIDTest.java    Created on 2010-7-17
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.String;

import java.util.UUID;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2010-7-17 下午10:13:27 $
 */
public class UUIDTest {
    public static void main(String[] args) {

        UUID.randomUUID();

        System.out.println(UUIDTest.getUUID());

    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

}
