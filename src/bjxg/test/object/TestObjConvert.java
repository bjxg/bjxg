/* 
 * @(#)TestObjConvert.java    Created on Sep 3, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.object;

import java.util.Date;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Sep 3, 2009 2:31:31 PM $
 */
public class TestObjConvert {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Date d = (Date) getObj();

        System.out.print("d=");
        System.out.println(d);
        System.out.println(Date.class.cast(new Date()));

    }

    public static Object getObj() {

        return null;
    }
}
