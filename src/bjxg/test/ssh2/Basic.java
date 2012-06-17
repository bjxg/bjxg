package bjxg.test.ssh2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Basic {
    public static void main(String[] args) {
        String hostname = "192.168.1.178";
        String username = "root";
        String password = "developer2";
        password = "zdsoft";
        try {
            /* Create a connection instance */

            Connection conn = new Connection(hostname);

            /* Now connect */

            conn.connect();

            /*
             * Authenticate. If you get an IOException saying something like
             * "Authentication method password not supported by the server at this
             * stage." then please check the FAQ.
             */

            boolean isAuthenticated = conn.authenticateWithPassword(username,
                    password);

            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }

            /* Create a session */

            Session sess = conn.openSession();

            sess.execCommand("top -b -n 1");

            System.out
                    .println("Here is some information about the remote host:");

            /*
             * This basic example does not handle stderr, which is sometimes
             * dangerous (please read the FAQ).
             */

            InputStream stdout = new StreamGobbler(sess.getStdout());

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(stdout));
            int cpuUsed = 0;
            String str = null;
            String[] strArray = null;

            while ((str = br.readLine()) != null) {
                int m = 0;
                System.out.println(str);
                if (str.indexOf(" R ") != -1) {// 只分析正在运行的进程，top进程本身除外 &&

                    strArray = str.split(" ");
                    for (String tmp : strArray) {
                        if (tmp.trim().length() == 0) {
                            continue;
                        }
                        if (++m == 9) {// 第9列为CPU的使用百分比(RedHat

                            cpuUsed += Double.parseDouble(tmp);

                        }

                    }

                }
            }

            System.out.println(cpuUsed);

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }

            /* Show exit status, if available (otherwise "null") */

            System.out.println("ExitCode: " + sess.getExitStatus());

            /* Close this session */

            sess.close();

            /* Close the connection */

            conn.close();

        }
        catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
}
