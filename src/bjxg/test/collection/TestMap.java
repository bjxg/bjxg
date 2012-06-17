/* 
 * @(#)TestMap.java    Created on Aug 17, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.collection;

import java.util.Collections;
import java.util.TreeMap;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Aug 17, 2009 4:33:48 PM $
 */
public class TestMap {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("1", "");
        treeMap.put("13", "");
        treeMap.put("12", "");
        treeMap.put("130", "");
        System.out.print(treeMap.lastKey());
        System.out.print(treeMap.keySet());
        // TODO Auto-generated method stub
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        String key = "k";
        String value = "v";
        map.put("1", "a");
        map.put(key, value);
        map.put("r", "w");
        System.out.print(map);
        Collections.unmodifiableCollection(map.values());
        map.put("w", "w");
        // map.putAll(null);
        System.out.print(map);

    }
}
