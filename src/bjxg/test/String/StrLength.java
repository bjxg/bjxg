/* 
 * @(#)StrLength.java    Created on Oct 19, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.String;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Oct 19, 2009 12:55:26 PM $
 */
public class StrLength {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 2000;
        String s = "";
        StringBuilder sb = new StringBuilder();
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            s = s + "s";
        }
        System.out.println(s);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            sb.append("--");
        }
        try {
            Thread.sleep(100000);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(sb.toString());
        System.out.println(System.currentTimeMillis() - start);

    }

}
