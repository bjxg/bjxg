/* 
 * @(#)CreateXxtPwd.java    Created on 2011-6-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.String;

import net.zdsoft.keel.util.DateUtils;
import net.zdsoft.keel.util.SecurityUtils;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2011-6-15 下午05:02:43 $
 */
public class CreateXxtPwd {
    public static void main(String[] args) {
        String ip = "124.160.76.74";
        String user = "admin";
        String today = DateUtils.currentDate2StringByDay();
        String correctKey = SecurityUtils.encodeBySHA1(today + user + ip);
        System.out.println();
        System.out.println(correctKey);
    }

}
