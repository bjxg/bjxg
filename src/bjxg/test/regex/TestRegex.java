/* 
 * @(#)TestRegex.java    Created on Sep 16, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Sep 16, 2009 1:20:17 PM $
 */
public class TestRegex {
    public static void main(String[] args) {
        String a = "afd\r/dev/1/s /dev/2/s\r/dev/3/s\r";

        Pattern p = Pattern.compile("(/dev/[^\\s]*)\\r");
        Matcher m = p.matcher(a);
        System.out.println("0s023".replaceFirst("^0*", ""));
        while (m.find()) {
            // System.out.print(m.group(0)); // 0���������ʽ���������û����v��(?<!c)���ַ� �����
            // System.out.println();
            System.out.print(m.group(1)); // ����ֻҪ������1�����ּ��ɡ����
            break;
            // 3434

            // a3434bd
        }

    }
}
