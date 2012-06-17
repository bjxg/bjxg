package bjxg.test.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client{
	 static Socket server;
	 
	 public static void 
	 main(String[] args)
	 throws Exception
	 {
	  server=new Socket
	  ("gs.edu88.com",20202);
	  BufferedReader in=new BufferedReader(new 
	  InputStreamReader(server.getInputStream()));
	  PrintWriter out=new
	  PrintWriter(server.getOutputStream());
	  BufferedReader wt=new BufferedReader(new 
	  InputStreamReader(System.in));
	  out.println("00140182000001");
	  while(true)
	  {System.out.println(1);
	   String str=wt.readLine();
	   System.out.println(2);
	   if(str.startsWith("00140182")){
		   out.println(str);
	   }
	   System.out.println(str);
	   System.out.println(2);
	   out.flush();
	   if(str.equals("end"))
	   {
	    break;
	   }
	   Thread.sleep(2000);
	   System.out.println(in.readLine());
	   System.out.println(3);
	  }
	  server.close();
	 }
	}
