/* 
 * @(#)Validators.java    Created on Mar 25, 2010
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.util;

import java.util.regex.Pattern;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Mar 25, 2010 3:24:29 PM $
 */
public class Validators {
    public static final String IP_REGEX = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
    public static final String http_regexp = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";

    /**
     * 字符串是否是一个合法的IP地址
     * 
     * @param 字符串
     * @return 是返回true，否为false
     */
    public static boolean isIp(String ip) {
        return ip != null && Pattern.compile(IP_REGEX).matcher(ip).matches();
    }

    public static boolean isIp2(String ip) {
        return ip != null && ip.matches(IP_REGEX);
    }

    /**
     * 是否是固定范围内的数字的字符串
     * 
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean isNumber(String str, int min, int max) {
        if (!isNumber(str)) {
            return false;
        }

        int number = Integer.parseInt(str);
        return number >= min && number <= max;
    }

    /**
     * 是否是大于等于某个数字的字符串
     * 
     * @param str
     *            字符串
     * @param min
     *            基准数值
     * @return 是返回true，否为false
     */
    public static boolean isNumber(String str, int min) {
        if (!isNumber(str)) {
            return false;
        }

        int number = Integer.parseInt(str);
        return number >= min;
    }

    public static boolean isNumber(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                return false;
            }
        }
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Validators.isIp2("192.168.1.11");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}
