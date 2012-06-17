/* 
 * @(#)TestListRemove.java    Created on Jan 7, 2010
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Jan 7, 2010 3:21:16 PM $
 */
public class TestListRemove {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> slist = new ArrayList<String>();
        slist.add("a");
        slist.add("b");
        slist.add("c");
        slist.add("d");
        for (int i = 0; i < slist.size(); i++) {
            System.out.println("------------" + i + "-" + slist.size());
            if (slist.get(i).equals("b") || slist.get(i).equals("c")) {
                slist.remove(i);
                i--;
            }
        }
        System.out.println(slist);
        System.out.println("------------");
        for (int i = 0; i < slist.size(); i++) {
            // System.out.println(slist.get(i) + "-" + i);
            if ("a".equals(slist.get(i))) {
                // slist.remove(i);
            }
        }
        Iterator<String> it = slist.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
            if ("a".equals(s)) {
                it.remove();
            }
        }

        System.out.println(slist);
        System.out.println(slist.size());
    }
}
