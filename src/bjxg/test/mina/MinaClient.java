/* 
 * @(#)MinaClient.java    Created on 2010-12-28
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2010-12-28 上午10:22:19 $
 */
public class MinaClient {
    public static void main(String[] args) throws Exception {

        // Create TCP/IP connection
        NioSocketConnector connector = new NioSocketConnector();

        // 创建接受数据的过滤器
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();

        // 设定这个过滤器将一行一行(/r/n)的读取数据
        chain.addLast("myChin", new ProtocolCodecFilter(new TextLineCodecFactory()));

        // 服务器的消息处理器：一个SamplMinaServerHander对象
        connector.setHandler(new SamplMinaClientHander());

        // set connect timeout
        connector.setConnectTimeout(30);

        // 连接到服务器：
        ConnectFuture cf = connector.connect(new InetSocketAddress("121.61.118.228", 10101));

        // Wait for the connection attempt to be finished.
        cf.awaitUninterruptibly();

        cf.getSession().getCloseFuture().awaitUninterruptibly();
        connector.getHandler().messageSent(cf.getSession(), "00361012341234567890              26");
        connector.dispose();
    }
}
