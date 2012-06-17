/* 
 * @(#)PriorityQueueTest.java    Created on 2010-10-20
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.collection;

import java.util.PriorityQueue;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2010-10-20 上午10:11:53 $
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
        minPQ.add(Integer.MAX_VALUE - 5);
        minPQ.add(Integer.MAX_VALUE - 4);
        minPQ.add(Integer.MAX_VALUE - 3);
        minPQ.add(Integer.MAX_VALUE - 2);
        minPQ.add(Integer.MAX_VALUE - 1);
        while (!minPQ.isEmpty()) {
            System.out.println(Integer.MAX_VALUE - minPQ.remove());
        }
    }
}
