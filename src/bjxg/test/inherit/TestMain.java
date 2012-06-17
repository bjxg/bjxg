/* 
 * @(#)TestMain.java    Created on Oct 16, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.inherit;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Oct 16, 2009 3:07:44 PM $
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SubA.map.put("a", "av");
        SubB.map.put("b", "bv");

        System.out.println(SubB.map);
    }

}
