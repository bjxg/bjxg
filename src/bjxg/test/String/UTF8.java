/* 
 * @(#)UTF8.java    Created on Sep 30, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.String;

import com.huawei.eidc.slee.security.MD5;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Sep 30, 2009 11:09:50 AM $
 */
public class UTF8 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><BODY><RESULTCODE>0</RESULTCODE><RESULTMSG>处理成功</RESULTMSG>";
        String str2 = null;
        try {
            System.out.println(MD5.md5(str2) + str);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
