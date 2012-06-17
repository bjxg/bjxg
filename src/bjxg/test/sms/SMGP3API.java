/* 
 * @(#)SMGP3API.java    Created on 2011-6-2
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.sms;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.simpleteam.Decimal;
import com.simpleteam.Hex;
import com.simpleteam.IntegerTimestamp;
import com.simpleteam.SimpleSequence;
import com.simpleteam.TimeConstant;
import com.simpleteam.adapter.stream.SocketAdapter;
import com.simpleteam.connection.ConnectionException;
import com.simpleteam.connection.protocol.smgp.SMGP3Connection;
import com.simpleteam.log.EventID;
import com.simpleteam.log.LogMode;
import com.simpleteam.log.LogRequests;
import com.simpleteam.log.SimpleLog;
import com.simpleteam.packet.BufferException;
import com.simpleteam.packet.smgp.SMGP3Deliver;
import com.simpleteam.packet.smgp.SMGP3Submit;
import com.simpleteam.packet.smgp.SMGPActiveTest;
import com.simpleteam.packet.smgp.SMGPActiveTestResponse;
import com.simpleteam.packet.smgp.SMGPDeliverResponse;
import com.simpleteam.packet.smgp.SMGPExit;
import com.simpleteam.packet.smgp.SMGPExitResponse;
import com.simpleteam.packet.smgp.SMGPLogin;
import com.simpleteam.packet.smgp.SMGPLoginMode;
import com.simpleteam.packet.smgp.SMGPLoginResponse;
import com.simpleteam.packet.smgp.SMGPMessageType;
import com.simpleteam.packet.smgp.SMGPPacket;
import com.simpleteam.packet.smgp.SMGPRequestID;
import com.simpleteam.packet.smgp.SMGPStatus;
import com.simpleteam.packet.smgp.SMGPSubmitResponse;
import com.simpleteam.tag.TagNode;
import com.simpleteam.transactor.authenticate.SMGPAuthenticate;

/**
 * <p>
 * Title: SimpleTeam
 * </p>
 * <p>
 * Description: SMGP3 API
 * </p>
 * <p>
 * Copyright: All rights reserved by www.simpleteam.com. Copyright (c) 2000 ~ 2009
 * </p>
 * <p>
 * Company: www.simpleteam.com
 * </p>
 * 
 * @author Forest Luo
 * @version 3.0
 */

public class SMGP3API {
    // Class Name
    private final static String CLASS_NAME = "SMGP3API";

    /**
     * Whether authenticated.
     */
    private boolean authenticated;
    /**
     * Simple sequence.
     */
    private SimpleSequence sequence;

    /**
     * Connection.
     */
    private SMGP3Connection connection;
    /**
     * Authenticate.
     */
    private SMGPAuthenticate authenticate;

    /**
     * Default construction.
     * 
     * @param enterpriseCode
     *            Enterprise code.
     * @param serviceCode
     *            Service code.
     * @param account
     *            Account of user.
     * @param password
     *            Password of user.
     * @return <p>
     *         No results returned.
     *         </p>
     */
    public SMGP3API(String enterpriseCode, String serviceCode, String account, String password) {
        // Create sequence.
        sequence = new SimpleSequence();
        // Create connection.
        connection = new SMGP3Connection();

        // Create node.
        TagNode node = new TagNode("authenticate");
        // Add node.
        node.addNode("account", account);
        // Add node.
        node.addNode("password", password);
        // Add node.
        node.addNode("service_code", serviceCode);
        // Add node.
        node.addNode("enterprise_code", enterpriseCode);
        // Create authenticate.
        authenticate = new SMGPAuthenticate(node);
    }

    /**
     * Clear.
     * 
     * @param None
     *            No parameters needed.
     * @return <p>
     *         No results returned.
     *         </p>
     */
    @Override
    public void finalize() {
        // Clear sequence.
        sequence = null;
        // Clear connection.
        connection = null;
        // Clear authenticate.
        authenticate = null;
    }

    /**
     * Get next sequence.
     * 
     * @param None
     *            No parameters needed.
     * @return <p>
     *         Next sequence.
     *         </p>
     */
    public int nextSequence() {
        // Return result.
        return sequence != null ? sequence.nextInteger() : 0;
    }

    /**
     * Whether there is available.
     * 
     * @param None
     *            No parameters needed.
     * @return <p>
     *         Return true, if packet available.
     *         </p>
     */
    public synchronized boolean available() throws IOException {
        try {
            // Return result.
            return connection != null ? connection.available() : false;
        }
        catch (BufferException e) {
            throw new IOException(e.getMessage());
        }
        catch (ConnectionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Read packet.
     * 
     * @param None
     *            No parameters needed.
     * @return <p>
     *         Read packet.
     *         </p>
     */
    public synchronized SMGPPacket readPacket() throws IOException {
        try {
            // Return result.
            return connection != null ? (SMGPPacket) connection.readPacket() : null;
        }
        catch (BufferException e) {
            throw new IOException(e.getMessage());
        }
        catch (ConnectionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Write packet.
     * 
     * @param packet
     *            Packet for writing.
     * @return <p>
     *         No results returned.
     *         </p>
     */
    public synchronized void writePacket(SMGPPacket packet) throws IOException {
        try {
            // Return result.
            if (connection != null) {
                connection.writePacket(packet);
            }
        }
        catch (BufferException e) {
            throw new IOException(e.getMessage());
        }
        catch (ConnectionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Login session.
     * 
     * <p>
     * It depends on session type and gateway type. It must be overrided.
     * </p>
     * 
     * @param None
     *            No parameters needed.
     * @return <p>
     *         Return true, if logined successfully.
     *         </p>
     */
    private boolean login() {
        // Create timestamp.
        IntegerTimestamp timestamp = new IntegerTimestamp();
        // Create login
        SMGPLogin login = new SMGPLogin();
        // Set sequence.
        login.sequence = 1;
        // Set default version.
        login.version = 0x30;
        // Set timestamp.
        login.timestamp = timestamp.getTimestamp();
        // Set source address.
        login.client_id = authenticate.getAccount();
        // Get authenticate.
        login.authenticator_client = authenticate.getAuthenticate(timestamp);
        // Set login_mode.
        login.login_mode = SMGPLoginMode.TRANSCEIVER;
        // Check validation.
        if (!login.isValid()) {
            // Log event.
            if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                SimpleLog.log(CLASS_NAME, "login").message("invalid SMGP3 login !").end();
            }
            return false;
        }

        try {
            // Write packet.
            connection.writePacket(login);
            // Read packet.
            SMGPPacket smgp = (SMGPPacket) connection.readPacket();
            // Check result.
            if (smgp == null) {
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                    SimpleLog.log(CLASS_NAME, "login").message("fail to read packet !").end();
                }
                return false;
            }
            // Check command
            if (smgp.command != SMGPRequestID.LOGIN_RESPONSE) {
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                    SimpleLog.log(CLASS_NAME, "login").message("invalid login response packet !").end();
                }
                return false;
            }
            // Check sequence.
            if (smgp.sequence != login.sequence) {
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                    SimpleLog.log(CLASS_NAME, "login").message("sequence is not according to previous !").end();
                }
            }
            // Get login response.
            SMGPLoginResponse response = (SMGPLoginResponse) smgp;
            // Check result.
            if (response.status != SMGPStatus.SUCCESS) {
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                    SimpleLog.log(CLASS_NAME, "login").begin().append(SMGPStatus.toString(response.status))
                            .append(" !").end();
                }
                return false;
            }
            // Set authenticated.
            authenticated = true;
            // Return true.
            return true;
        }
        catch (Exception e) {
            // Log event.
            if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                SimpleLog.log(CLASS_NAME, "login").exception(e, false).end();
            }
        }
        // Return false.
        return false;
    }

    /**
     * Connect.
     * 
     * @param remoteHost
     *            Address of remote host.
     * @param remotePort
     *            Port of remote host.
     * @return <p>
     *         Return true, if successfully done.
     *         </p>
     */
    public boolean connect(String remoteHost, int remotePort) {
        try {
            // Socket.
            Socket socket = new Socket(InetAddress.getByName(remoteHost), remotePort);
            // Create socket adapter.
            SocketAdapter adapter = new SocketAdapter(socket);
            // Set adapter timeout.
            adapter.setTimeout(5 * TimeConstant.SECOND);
            // Open.
            connection.open(adapter);
            // Return login.
            return login();
        }
        catch (Exception e) {
            // Log event.
            if (LogRequests.isRequested(EventID.SMGP_PACKET | EventID.EXCEPTION)) {
                SimpleLog.log(CLASS_NAME, "connect").exception(e, false).end();
            }
        }
        // Return false.
        return false;
    }

    /**
     * Logout session.
     * 
     * <p>
     * It depends on session type and gateway type. It must be overrided.
     * </p>
     * 
     * @param None
     *            No parameters needed.
     * @return <p>
     *         Return true, if logouted successfully.
     *         </p>
     */
    private boolean logout() {
        // Create exit.
        SMGPExit exit = new SMGPExit();
        // Set sequence.
        exit.sequence = nextSequence();

        try {
            // Write packet.
            connection.writePacket(exit);
            // Read packet.
            SMGPPacket smgp = (SMGPPacket) connection.readPacket();
            // Check result.
            if (smgp == null) {
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                    SimpleLog.log(CLASS_NAME, "logout").message("fail to read packet !").end();
                }
                return false;
            }
            // Check command.
            if (smgp.command == SMGPRequestID.EXIT) {
                // Create response.
                SMGPExitResponse response = new SMGPExitResponse(smgp.sequence);
                // Write response.
                connection.writePacket(response);
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.INFORMATION)) {
                    SimpleLog.log(CLASS_NAME, "logout").message("exit request was received !").end();
                }
            }
            else if (smgp.command == SMGPRequestID.EXIT_RESPONSE) {
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.INFORMATION)) {
                    SimpleLog.log(CLASS_NAME, "logout").message("exit response was received !").end();
                }
            }
            else {
                // Log event.
                if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                    SimpleLog.log(CLASS_NAME, "logout").message("invalid exit response packet !").end();
                }
                return false;
            }
            // Set authenticated.
            authenticated = false;
            // Return true.
            return true;
        }
        catch (Exception e) {
            // Log event.
            if (LogRequests.isRequested(EventID.SESSION | EventID.EXCEPTION)) {
                SimpleLog.log(CLASS_NAME, "logout").exception(e, false).end();
            }
        }
        // Return false.
        return false;
    }

    /**
     * Close.
     * 
     * @param None
     *            No parameters needed.
     * @return <p>
     *         No results returned.
     *         </p>
     */
    public void close() {
        // Check status.
        if (!connection.isClosed()) {
            // Check terminated.
            if (!connection.isTerminated()) {
                // Check authenticated.
                if (!authenticated) {
                    /*
                     * //Log event. if(LogRequests.isRequested(EventID.SMGP3_PACKET | EventID.INFORMATION))
                     * SimpleLog.log(CLASS_NAME,"close"). message("session is not authenticated !").end();
                     */
                }
                // Logout session.
                else if (!logout()) {
                    /*
                     * //Log event. if(LogRequests.isRequested(EventID.SMGP3_PACKET | EventID.INFORMATION))
                     * SimpleLog.log(CLASS_NAME,"close"). message("fail to logout !").end();
                     */
                }
            }

            try {
                // Close connection.
                connection.close();
            }
            catch (Exception e) {
                // Log event.
                if (LogRequests.isRequested(EventID.SMGP_PACKET | EventID.EXCEPTION)) {
                    SimpleLog.log(CLASS_NAME, "close").exception(e, false).end();
                }
            }
            // Log event.
            if (LogRequests.isRequested(EventID.SMGP_PACKET | EventID.INFORMATION)) {
                SimpleLog.log(CLASS_NAME, "close").message("connection was closed !").end();
            }
        }
        else {
            // Log event.
            if (LogRequests.isRequested(EventID.SMGP_PACKET | EventID.INFORMATION)) {
                SimpleLog.log(CLASS_NAME, "close").message("connection has already been closed !").end();
            }
        }
    }

    /**
     * Main.
     * 
     * <p>
     * It is better to sperate submit and deliver processing, although this example give a mixed processing.
     * </p>
     * 
     * @param args
     *            Arguments.
     * @return <p>
     *         No results returned.
     *         </p>
     */
    public static void main(String[] args) {
        // Account.
        String account = "333";
        // Password.
        String password = "0555";
        // Service code.
        String serviceCode = "99999";
        // Enterprise code.
        String enterpriseCode = "3001";

        // Get current directory.
        String userDirectory = System.getProperty("user.dir");
        new File(userDirectory);
        // Create directory.
        File logDirectory = new File(userDirectory, "logs");
        // Check result.
        if (!logDirectory.exists() && !logDirectory.mkdir()) {
            System.out.println("SMGP3API.main : fail to create log directory !");
            return;
        }
        // Log mode.
        int logMode = LogMode.LOG_ALL;
        // Check args.
        if (args.length > 1) {
            // Check value.
            logMode = Decimal.parseInteger(args[1]);
            // Print.
            System.out.println("SMGP3API.main : log mode(0x" + Hex.toString(logMode) + ") !");
        }
        // Initialize log.
        if (!SimpleLog.initialize(logDirectory, logMode)) {
            System.out.println("SMGP3API.main : fail to initialize log !");
            return;
        }

        try {
            // Create API.
            SMGP3API smgp3Api = new SMGP3API(enterpriseCode, serviceCode, account, password);
            // Connect.
            if (smgp3Api.connect("127.0.0.1", 9890)) {
                // Print.
                System.out.println("SMGP3API.main : connection was built !");

                // Count.
                int totalCount = 1, sendCount = 0, reportCount = 0;
                // Do while.
                while (reportCount < totalCount) {
                    // Check send count.
                    if (sendCount < totalCount) {
                        // Create submit.
                        SMGP3Submit submit = new SMGP3Submit(smgp3Api.nextSequence());

                        // Message Type
                        submit.msg_type = SMGPMessageType.INFORMATION_ON_DEMAND;
                        // Need Report
                        submit.need_report = 1;
                        // Service ID
                        submit.service_id = "HELP";
                        // Fee Type
                        submit.fee_type = "01";
                        // Fixed Fee
                        submit.fixed_fee = "000000";
                        // Fee Code
                        submit.fee_code = "000000";
                        // Message Format
                        submit.msg_fmt = 0;
                        // Valid Time
                        submit.valid_time = null;
                        // AT Time
                        submit.at_time = null;
                        // Source Terminal ID
                        submit.src_term_id = serviceCode;
                        // Charge Terminal ID
                        submit.charge_term_id = null;
                        // Destination Terminal ID Count
                        submit.dest_term_id_count = 1;
                        // Destination Terminal ID
                        submit.dest_term_id = new String[] { "13501026991" };
                        // Message Content
                        submit.msg_content = "Hello World !".getBytes("US-ASCII");
                        // Message Length
                        submit.msg_length = submit.msg_content.length;
                        // Reserved Field
                        submit.reserve = null;

                        // Write packet.
                        smgp3Api.writePacket(submit);
                        // Add send count and clear flag.
                        sendCount++;
                    }

                    // Check available.
                    if (smgp3Api.available()) {
                        // Read packet.
                        SMGPPacket packet = smgp3Api.readPacket();
                        // Check instance.
                        if (packet instanceof SMGP3Deliver) {
                            // Get deliver.
                            SMGP3Deliver deliver = (SMGP3Deliver) packet;
                            // Create deliver response.
                            SMGPDeliverResponse response = new SMGPDeliverResponse(packet.sequence);
                            // Set status.
                            response.status = 0;
                            // Set response.
                            response.msg_id = deliver.msg_id;
                            // Write response.
                            smgp3Api.writePacket(response);// Do response as soon as possible.

                            // Add report count.
                            reportCount++;
                            // /////////////////////////////////////////////////////////////////////////
                            //
                            // Do process of deliver.
                            //
                            // <p>You can add your own processing here !</p>
                            //
                            // Check is_report.
                            if ((deliver.is_report & 0x0f) == 1) {
                                // Print.
                                System.out.println("SMGP3API.main : report/status(" + deliver.stat + ")");
                            }
                            else {
                                // Print.
                                System.out.println("SMGP3API.main : normal/source(" + deliver.src_term_id + ")");
                            }
                            //
                            // /////////////////////////////////////////////////////////////////////////
                        }
                        // Check instance.
                        else if (packet instanceof SMGPActiveTest) {
                            // Create activetest response.
                            SMGPActiveTestResponse response = new SMGPActiveTestResponse(packet.sequence);
                            // Write response.
                            smgp3Api.writePacket(response);
                        }
                        // Check command.
                        else if (packet instanceof SMGPSubmitResponse) {
                            // Create submit response.
                            SMGPSubmitResponse response = (SMGPSubmitResponse) packet;
                            // Print.
                            System.out.println("SMGP3API.main : submit/result(" + response.msg_id + ")");

                            // /////////////////////////////////////////////////////////////////////////
                            //
                            // Do process of submit response.
                            //
                            // <p>You can add your own processing here !</p>
                            //
                            //
                            // /////////////////////////////////////////////////////////////////////////
                        }
                    }
                }
            }
            else {
                // Print.
                System.out.println("SMGP3API.main : fail to connect !");
            }
            // Close.
            smgp3Api.close();
            // Print.
            System.out.println("SMGP3API.main : connection was closed !");
        }
        catch (Exception e) {
            // Print.
            System.out.println("SMGP3API.main : " + e.getMessage());
            System.out.println("SMGP3API.main : unexpected exit !");
        }

        // Uninitialize log.
        SimpleLog.uninitialize();

        // Exit
        System.exit(0);
    }
}
