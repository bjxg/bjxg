/* 
 * @(#)TestTime.java    Created on Aug 18, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Aug 18, 2009 3:19:58 PM $
 */
public class TestTime {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        System.out.println(sdf.format(new Date()));
    }

}
