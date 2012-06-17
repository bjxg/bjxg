package bjxg.test.db;

import java.sql.Connection;   
import java.sql.DriverManager;   
import java.sql.ResultSet;   
import java.sql.SQLException;   
import java.sql.Statement;   
  
import org.h2.tools.Server;   
  
public class TestH2 {   
    private Server server;   
    private String port = "9094";   
    private String dbDir = "./h2db/sample";   
    private String user = "zhoujiang";   
    private String password = "123456";   
  
    public void startServer() {   
        try {   
            System.out.println("��������h2...");   
            server = Server.createTcpServer(   
                    new String[] { "-tcpPort", port }).start();   
        } catch (SQLException e) {   
            System.out.println("����h2���?" + e.toString());   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
            throw new RuntimeException(e);   
        }   
    }   
  
    public void stopServer() {   
        if (server != null) {   
            System.out.println("���ڹر�h2...");   
            server.stop();   
            System.out.println("�رճɹ�.");   
        }   
    }   
  
    public void useH2() {   
        try {   
            Class.forName("org.h2.Driver");   
            Connection conn = DriverManager.getConnection("jdbc:h2:" + dbDir,   
                    user, password);   
            Statement stat = conn.createStatement();   
            // insert data   
            stat.execute("CREATE TABLE TEST_BIN(MMS OTHER)");  
            
            stat.execute("INSERT INTO TEST VALUES('Hello World')");  
            //conn.createBlob().getBinaryStream().;
            
            // use data   
            ResultSet result = stat.executeQuery("select name from test ");   
            int i = 1;   
            while (result.next()) {   
            
                System.out.println(i++ + ":" + result.getString("name"));   
            }   
            result.close();   
            stat.close();   
            conn.close();   
        } catch (Exception e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
    }   
  
    public static void main(String[] args) {   
    	TestH2 h2 = new TestH2();   
        h2.startServer();   
        h2.useH2();   
        h2.stopServer();   
        System.out.println("==END==");   
    }   
}  
