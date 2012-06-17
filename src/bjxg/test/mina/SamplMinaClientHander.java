/* 
 * @(#)SamplMinaClientHander.java    Created on 2010-12-28
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2010-12-28 ����10:27:54 $
 */
public class SamplMinaClientHander extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * ���ͻ��˽��ܵ���Ϣʱ
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        // �������趨�˷���������Ϣ������һ��һ�ж�ȡ������Ϳ���תΪString:
        String s = (String) message;

        // Writer the received data back to remote peer
        System.out.println("�������������յ���Ϣ: " + s);

        // ���Խ���Ϣ���͸��ͻ���
        session.write(s);

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        String s = (String) message;

        // Writer the received data back to remote peer
        System.out.println("��������������Ϣ: " + s);

        // ���Խ���Ϣ���͸��ͻ���
        session.write(s);
    }

    /**
     * ��һ���ͻ��˱��ر�ʱ
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("one client Disconnect");

    }

    @Override
    public void sessionCreated(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("one client sessionCreated");
    }

    @Override
    public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * ��һ���ͻ������ӽ���ʱ
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {

        System.out.println("incomming client:" + session.getRemoteAddress());
        session.write("00361012341234567890              26");

    }

}
