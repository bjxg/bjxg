package bjxg.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.zdsoft.keel.util.*;;;


public class Tools {
	public static SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
	static int n=0;
public synchronized static String  newId(){
	Date now=new Date();
	 n++;
	 if(n>999999){n=1;}	return f.format(now)+n;	
}
public synchronized static String  newId2(){
	Date now=new Date();return f.format(now) ;	
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start =System.currentTimeMillis();
		 
for(int i=0;i<1000000;i++){
	 newId2();
	 //System.out.println(newId());
	//DateUtils.date2String(new Date(),   "yyyyMMddHHmmss" );
}
long end =System.currentTimeMillis();
System.out.println((end-start));
	}

}
