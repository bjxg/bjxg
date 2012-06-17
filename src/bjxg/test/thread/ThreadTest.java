/* 
 * @(#)ThreadTest.java    Created on Nov 14, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author ccy
 * @version $Revision: 1.0 $, $Date: Nov 14, 2009 2:46:05 PM $
 */
public class ThreadTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ScheduledThreadPoolExecutor tp = new ScheduledThreadPoolExecutor(100);

        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tp.execute(new ThreadOne());
        }
    }

}

class ThreadOne extends Thread {
    public static int id = 0;
    Map<Integer, String> map = new HashMap<Integer, String>();

    @Override
    public void run() {
        super.run();
        System.out.print("runing...=");
        System.out.println(id);
        map.put(1, "" + id);
        // for (int i = 0; i > -1; i++) {
        // map.put(i, String.valueOf(i));
        // }
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start > 1000 * 20) {
            Date d = new Date();
        }
    }//

    public ThreadOne() {

        ThreadOne.id++;
        System.out.print("start...=");
        System.out.println(id);
    }
}
