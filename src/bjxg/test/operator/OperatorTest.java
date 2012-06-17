/* 
 * @(#)OperatorTest.java    Created on Oct 29, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.operator;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Oct 29, 2009 3:42:43 PM $
 */
public class OperatorTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(2 == 2 ? 2 : getInt());
        System.out.println(2 == 3 ? 2 : getInt());
    }

    static int getInt() {
        System.out.println(1);
        return 1;
    }
}
