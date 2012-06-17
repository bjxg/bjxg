/* 
 * @(#)EnumTest.java    Created on Mar 10, 2010
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.enumtest;

import java.io.File;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;

import bjxg.test.object.ObjectUtils;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Mar 10, 2010 5:40:17 PM $
 */
public class EnumTest {
    private UserState userState = UserState.OWING;
    private Date date = null;
    private int n = 1;
    private String[] strs = new String[] { "a" };
    private FileItem[] f;

    public void setF(FileItem[] f) {
        this.f = f;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EnumTest et = new EnumTest();

        System.out.println(et.userState.getDescription());

        // ObjectUtils.setProperty(et, "userState", "NORMAL");
        System.out.println(et.userState.getDescription());
        ObjectUtils.setProperty(et, "strs", "23ss2");
        Object[] o = new FileItem[] { new DiskFileItem("", "", true, "", 1, new File("s")) };
        ObjectUtils.setProperty(et, "f", o);
        System.out.println(et.strs[0]);
        System.out.println(et.f.getClass());
        System.out.println(FileItem.class);
    }
}
