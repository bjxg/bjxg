/* 
 * @(#)SnmpUtilSendTrap.java    Created on Nov 24, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.snmp;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * ��������������̷���Trap��Ϣ
 * 
 * @author zhanjia
 * 
 */
public class SnmpUtilSendTrap {

    private Snmp snmp = null;

    private Address targetAddress = null;

    public void initComm() throws IOException {

        // ���ù����̵�IP�Ͷ˿�
        // 211.137.82.166/4050
        // udp:192.168.0.197/162
        // 221.182.1.133
        targetAddress = GenericAddress.parse("udp:192.168.0.197/162");
        System.out.println(targetAddress);
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        System.out.println(snmp.getUSM());
        transport.listen();

    }

    /**
     * ������̷���Trap����
     * 
     * @throws IOException
     */
    public void sendPDU() throws IOException {

        // ���� target
        CommunityTarget target = new CommunityTarget();
        target.setAddress(targetAddress);

        // ͨ�Ų��ɹ�ʱ�����Դ���
        target.setRetries(2);
        // ��ʱʱ��
        target.setTimeout(1500);
        // snmp�汾
        target.setVersion(SnmpConstants.version2c);

        // ���� PDU
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(".1.3.6.1.2.3377.10.1.1.1.1"), new OctetString("SnmpTrap")));
        pdu.add(new VariableBinding(new OID(".1.3.6.1.2.3377.10.1.1.1.2"), new OctetString("内存使用量:862MB")));
        pdu.add(new VariableBinding(SnmpConstants.sysUpTime, new TimeTicks(new UnsignedInteger32(1000).toLong())));
        pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(".1.2.156.112546.16.0.1")));

        pdu.setType(PDU.TRAP);

        // ��Agent����PDU��������Response
        ResponseEvent respEvnt = snmp.send(pdu, target);
        System.out.println("---------");
        System.out.println(respEvnt);
        // ����Response
        if (respEvnt != null && respEvnt.getResponse() != null) {
            Vector<VariableBinding> recVBs = respEvnt.getResponse().getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                VariableBinding recVB = recVBs.elementAt(i);
                // System.out.println(recVB.getOid() + " : " + recVB.getVariable());
            }
        }
    }

    public static void main(String[] args) {
        try {
            SnmpUtilSendTrap util = new SnmpUtilSendTrap();
            util.initComm();
            util.sendPDU();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
