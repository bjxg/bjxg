/* 
 * @(#)SunFtpClient.java    Created on 2011-4-12
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.ftp;

import sun.net.ftp.FtpClient;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2011-4-12 ÏÂÎç05:30:53 $
 */
public class SunFtpClient {

    public static void main(String[] args) {
        try {
            FtpClient client = new FtpClient("211.137.75.219", 21);
            client.login("hbxxt", "2010hbxj");
            System.out.println(client.pwd());
            client.cd("SEND");

            String cmd = "DELE BOSS270_OrdSub_20110227113633_LXXT001\r\n";

            client.sendServer(cmd);
            // client.sendServer("PASV");
            // Thread.sleep(10 * 1000);
            // client.sendServer("PASV");
            // client.cd("SEND");
            // System.out.println(client.nameList("."));
            // System.out.println(client.("/home/hbxxt/SEND/BOSS270_OrdSub_20101230151155_LNXT001"));
            /*
             * BufferedReader list = new BufferedReader(new InputStreamReader(client.nameList(".")));
             * 
             * List<String> nameList = new ArrayList<String>(); String filename = ""; while ((filename =
             * list.readLine()) != null) { nameList.add(filename); } System.out.println(nameList);
             */
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
