/* 
 * @(#)IntTest.java    Created on Sep 14, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.number;

import java.text.NumberFormat;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Sep 14, 2009 5:15:24 PM $
 */
public class IntTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(1 == 120 % 69);
        // TODO Auto-generated method stub
        Integer f = formatInt("s");
        int n = f == null ? 0 : f;
        System.out.println(n);
        Double i = 5d;
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumIntegerDigits(Integer.MAX_VALUE);
        format.setMaximumFractionDigits(0);
        format.setMinimumFractionDigits(0);

        String strRate = format.format(i);
        System.out.println(strRate);
    }

    public static Integer formatInt(String value) {
        Integer result = null;
        try {
            result = new Integer(value);
        }
        catch (Exception e) {
        }

        return result;
    }
}
