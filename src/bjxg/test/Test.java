/* 
 * @(#)Test.java    Created on Sep 2, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test;

import java.util.Random;

import net.zdsoft.keel.util.ArrayUtils;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Sep 2, 2009 6:18:18 PM $
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] ss = new String[2];
        ss[0] = "s0";
        ss[1] = "s1";
        System.out.println(ArrayUtils.toString(ss, ""));

        // TODO Auto-generated method stub
        java.util.List<String> slist = new java.util.ArrayList<String>();
        java.util.List<String> slist2 = new java.util.ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            String s = String.valueOf(i);
            if (new Random().nextInt(3) == 1) {
                i++;
                s = String.valueOf(i);
                i--;
            }

            slist.add(s);

            slist2.add(s);
        }

        System.out.println(slist);
        System.out.println(slist2);
        for (int i = 0; i < slist.toArray(new String[0]).length; i++) {
            System.out.print(slist.toArray(new String[0])[i]);
            System.out.println(slist2.toArray(new String[0])[i]);
        }
    }
}
