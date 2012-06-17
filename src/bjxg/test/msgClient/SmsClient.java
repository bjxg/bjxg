/* 
 * @(#)SmsClient.java    Created on 2011-7-10
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.msgClient;

import net.zdsoft.keel.util.StringUtils;
import net.zdsoft.message.client.MessageClient;
import net.zdsoft.message.client.MessageException;
import net.zdsoft.message.client.MessageHandler;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2011-7-10 下午02:12:21 $
 */
public class SmsClient implements Runnable, MessageHandler {
    public MessageClient messageClient;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SmsClient client = new SmsClient();
        client.start();
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        String content = "测4测试测试测试测试测试测试测试测试测试测试测试测试试试试试试试试70字";
        for (int n = 1; n < 50 * 10000; n++) {
            String msg = "004002010007sp_test020032mtid050011133123455780300011040001006001310659244301712000011070001H2100011080006linkid220001109content270001026000102800010";
            String tem = StringUtils.enoughZero(content.length() + "", 4);
            msg = msg.replaceFirst("content", tem + content);
            String phone = "156" + StringUtils.enoughZero(n + "", 8);
            String mtId = StringUtils.enoughZero("2235" + n + "", 32);
            msg = msg.replaceFirst("mtid", mtId);
            msg = msg.replaceFirst("13312345578", phone);

            client.messageClient.sendMessage("etoh2_web_gw_yd", msg);
            try {
                Thread.sleep(1);
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
            messageClient.connect("server.sms", 5500, "etoh2_web_sms", "etoh2_web_sms");
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
                Thread.sleep(1200 * 1000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
