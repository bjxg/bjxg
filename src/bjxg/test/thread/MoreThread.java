package bjxg.test.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import bjxg.test.Tools;

public class MoreThread {
	static Set<String> setAll =new HashSet<String>();
	 /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ScheduledThreadPoolExecutor tp = new ScheduledThreadPoolExecutor(100);

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tp.execute(new ThreadOne3());
        }
        System.out.println(setAll.size());
    }

}

class ThreadOne3 extends Thread {
    public Set<String> set =new HashSet<String>();
    @Override
    public void run() {
    	for(int i=0;i<100000;i++){
    		set.add(Tools.newId() );
    	}
    	  try {
              Thread.sleep(10000);
          }
          catch (InterruptedException e) {
              // TODO Ato-generated catch block
              e.printStackTrace();
          }
    	for(String s:set){
    		if(MoreThread.setAll.contains(s)){
    			System.out.println(s);
    		}else{
    			MoreThread.setAll.add(s);
    		}
    	}
    	System.out.println(set.size()+"-"+MoreThread.setAll.size());
    }//
 
}
