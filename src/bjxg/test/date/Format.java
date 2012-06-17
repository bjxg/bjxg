/* 
 * @(#)Format.java    Created on Sep 11, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.zdsoft.keel.util.DateUtils;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Sep 11, 2009 1:24:39 PM $
 */
public class Format {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        System.out.println(sdf.format(new Date()).toString());
        String s = sdf.format(new Date()).toString();
        System.out.println(s);
        Date d = DateUtils.string2Date(s + "  13:27", "yyyy-MM-dd HH:mm");
        System.out.println(d);
    }
}
