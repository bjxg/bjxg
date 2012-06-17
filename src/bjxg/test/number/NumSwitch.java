/* 
 * @(#)NumSwitch.java    Created on 2011-6-22
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.number;

import net.zdsoft.keel.util.StringUtils;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2011-6-22 上午11:27:45 $
 */
public class NumSwitch {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StringBuilder hexStr = new StringBuilder("6B 3D FE 00 69 7A 00 19");
        StringBuilder binStr = new StringBuilder();

        for (int n = 0; n < hexStr.length(); n++) {
            String hex = hexStr.substring(n, n + 1);
            if (" ".equals(hex)) {
                continue;
            }
            String bin = Integer.toBinaryString(Integer.valueOf(hex, 16));
            bin = StringUtils.enoughZero(bin, 4);
            binStr.append(bin);
            System.out.println(hex + " " + bin);
        }
        System.out.println(binStr.toString());
        StringBuilder id = new StringBuilder();
        int tem = Integer.valueOf(binStr.substring(0, 4), 2);
        id.append(tem > 9 ? tem : "0" + tem);// 月
        tem = Integer.valueOf(binStr.substring(4, 9), 2);
        id.append(tem > 9 ? tem : "0" + tem);// 日
        tem = Integer.valueOf(binStr.substring(9, 14), 2);
        id.append(tem > 9 ? tem : "0" + tem);// 时
        tem = Integer.valueOf(binStr.substring(14, 20), 2);
        id.append(tem > 9 ? tem : "0" + tem);// 分析
        tem = Integer.valueOf(binStr.substring(20, 26), 2);
        id.append(tem > 9 ? tem : "0" + tem).append("-");// 秒
        tem = Integer.valueOf(binStr.substring(26, 48), 2);
        // id.append(StringUtils.enoughZero(String.valueOf(tem), 9));
        id.append(tem).append("-");
        tem = Integer.valueOf(binStr.substring(48, 64), 2);
        // id.append(StringUtils.enoughZero(String.valueOf(tem), 6));
        id.append(tem);
        System.out.println(id.toString());
    }
}
