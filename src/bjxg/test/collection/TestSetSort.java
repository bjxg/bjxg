/* 
 * @(#)TestSetSort.java    Created on Aug 4, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import net.zdsoft.keel.util.Validators;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Aug 4, 2009 5:09:56 PM $
 */
public class TestSetSort {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String serviceIds = "16,15,12,17";
        Set<Integer> hset = new HashSet<Integer>();
        Set<Integer> set = new LinkedHashSet<Integer>();
        for (String s : serviceIds.split(",")) {
            System.out.print(s);
            if (Validators.isNumeric(s)) {
                set.add(Integer.parseInt(s));
            }
        }

        hset = set;
        System.out.print(set);
        System.out.print(hset);

    }

}
