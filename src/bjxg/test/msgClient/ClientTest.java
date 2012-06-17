/* 
 * @(#)ClientTest.java    Created on 2011-4-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.msgClient;

import net.zdsoft.keel.util.StringUtils;
import net.zdsoft.message.client.MessageClient;
import net.zdsoft.message.client.MessageException;
import net.zdsoft.message.client.MessageHandler;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2011-4-15 ÏÂÎç02:55:37 $
 */
public class ClientTest implements Runnable, MessageHandler {
    public MessageClient messageClient;

    /**
     * @param args
     */
    public static void main(String[] args) {
        ClientTest client = new ClientTest();
        client.start();
        String msg = "0034sp_yuyao                      aa                              100000213732231865       10657307452901       1servicecod1linkid  1sms";

        for (int n = 5500; n < 100000; n++) {
            msg.replaceFirst("aa", "bb");
            client.messageClient.sendMessage("sp_smsg01", msg);
            msg.replaceFirst("bb", "aa");
            client.messageClient.sendMessage("sp_smsg01", msg);
        }
        if (true) {
            return;
        }
        for (int n = 5500; n < 100000; n++) {
            String phone = "138" + StringUtils.enoughZero(n + "", 8);
            String mtId = StringUtils.enoughZero(n + "", 32);
            String m = "5010" + mtId + "0510T01000000032" + mtId
                    + "T02000000016/opt/mmsg/testmmT03000000004²ÊÐÅ²âÊÔT0400000000810657061T05000000000T06000000023";
            msg = m + phone + "," + phone;
            client.messageClient.sendMessage("gw_mms", msg);
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(n + "--" + msg);
        }
    }

    public void start() {
        try {
            messageClient = new MessageClient(this);
            messageClient.connect("192.168.1.176", 5800, "etoh2_web_mms", "etoh2_web_mms");
        }
        catch (MessageException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void kickedOutByServer() {
        // TODO Auto-generated method stub

    }

    @Override
    public void messageReceived(byte[] arg0, String arg1, String arg2) {
        System.out.println(arg2);
    }

    @Override
    public void messageSubmitted(byte[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (;;) {
            try {
                Thread.sleep(10000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
