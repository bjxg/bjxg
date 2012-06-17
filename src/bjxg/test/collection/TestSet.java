/* 
 * @(#)TestSet.java    Created on Aug 20, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.collection;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Aug 20, 2009 10:13:35 AM $
 */
public class TestSet {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TreeSet<String> set = new TreeSet<String>();

        set.add("10637062");
        set.add("106370619434");
        set.add("10637063132");
        set.add("10637063");
        set.add("1063706404");
        set.add("10637061");
        System.out.println(set.contains("10637061"));
        System.out.println(set);
        System.out.println(set.pollLast());
        System.out.println(set.pollLast());
        for (Iterator<String> it = set.iterator(); it.hasNext();) {
            String s = it.next();
            if ("c".equals(s)) {
                it.remove();
            }
            else if ("a".equals(s)) {
                it.remove();
            }
            else if ("d".equals(s)) {
                it.remove();
            }
        }

        System.out.println(set);

        String a;
        String b;
        String c;
        a = b = c = "c";
        System.out.println(a + b + c);

    }
}
