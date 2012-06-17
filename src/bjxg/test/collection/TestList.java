/* 
 * @(#)TestList.java    Created on Sep 1, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.collection;

import java.util.Iterator;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Sep 1, 2009 1:06:30 PM $
 */
public class TestList extends Thread {
    public static java.util.List<String> slist = new java.util.ArrayList<String>();

    /**
     * @param slist
     */
    public static void create() {
        slist.add("a");
        slist.add("B");
        slist.add("C");
        for (int i = 0; i < 100; i++) {
            slist.add(String.valueOf(i));
        }
    }

    static void remove(java.util.List<String> slist) {
        slist.remove(0);
    }

    @Override
    public void run() {

        super.run();

        for (int i = 0; i < 100; i++) {
            System.out.println(slist.get(0) + "-------------");
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            slist.remove(0);
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // TODO Auto-generated method stub

        java.util.List<String> slist2 = new java.util.ArrayList<String>();
        TestList.create();
        slist2.addAll(slist);
        System.out.println(slist2.size());
        // Collections.copy(slist2, slist);
        System.out.println(slist2);
        TestList t = new TestList();
        t.start();
        for (Iterator<String> it = slist.iterator(); it.hasNext();) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(it.next() + "==");
        }

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
