/* 
 * @(#)RateTest.java    Created on 2010-10-19
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.zdsoft.keel.util.concurrent.AbstractRunnableTask;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2010-10-19 下午10:47:44 $
 */
public class RateTest {
    ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(RateTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ScheduledThreadPoolExecutor tp = new ScheduledThreadPoolExecutor(200);
        tp.scheduleWithFixedDelay(new AbstractRunnableTask("mms send task id=") {
            @Override
            public void processTask() throws Exception {
                System.out.println(new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);

        try {
            for (int i = 0; i < 300; i++) {
                tp.execute(new ThreadOne2());
            }
            tp.setMaximumPoolSize(210);
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1000);
                    tp.setMaximumPoolSize(210);
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(ThreadOne2.id);
                ThreadOne2.id = 0;
            }
        }
        catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class ThreadOne2 extends Thread {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ThreadOne2.class);

    public static int id = 0;
    Map<Integer, String> map = new HashMap<Integer, String>();

    @Override
    public void run() {

        while (true) {
            ++id;
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }//
}
