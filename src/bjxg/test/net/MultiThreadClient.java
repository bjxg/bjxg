package bjxg.test.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadClient {
    
    public static void main(String[] args) {
        int numTasks = 1;
        
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < numTasks; i++) {
            exec.execute(createTask(i));
        }

    }

    // 定义一个简单的任务
    private static Runnable createTask(final int taskID) {
        return new Runnable() {
            private Socket socket = null;
            private int port=20202;

            public void run() {
                System.out.println("Task " + taskID + ":start");
                try {                    
                    socket = new Socket("gs.edu88.com", port);
                    // 发送关闭命令
                    OutputStream socketOut = socket.getOutputStream();
                    System.out.println(0);
                    socketOut.write("00140182000001".getBytes());
                    socketOut.flush();
                    System.out.println(1);
                    // 接收服务器的反馈
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    String msg = null;
                    System.out.println(2);
                    try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    while ((msg = br.readLine()) != null)
                        System.out.println(msg);
                } catch (IOException e) {                    
                    e.printStackTrace();
                }
                System.out.println(-1);
            }

        };
    }
}