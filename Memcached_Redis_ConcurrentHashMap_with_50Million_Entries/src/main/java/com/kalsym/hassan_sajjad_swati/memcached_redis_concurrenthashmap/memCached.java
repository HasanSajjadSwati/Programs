//Before using this program you should increase the HeapSize of both your IDE and MAVEN
//Minimum RAM Recommened for 50Million Entries is 16GB with Allocation of 12GB HeapSize
//Install memcached before running

package com.kalsym.hassan_sajjad_swati.memcached_redis_concurrenthashmap;

import com.google.common.base.Stopwatch;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;

public class memCached {

    public static void main(String[] args) throws IOException {
        int size = 50000000;
        long index = 10000000000L;
        Stopwatch stopWatch = Stopwatch.createUnstarted();
        
        try{
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1",11211));
            System.out.println("[Connection to server sucessfully]");
            System.out.println("[Starting Insertion]");
            for(int i = 0; i < size; i++){
                
                if(i==0)
                    stopWatch.start();
                
                if(i==1000)
                    System.out.println("[Inserted 1000 Entries in " + stopWatch.toString() + "]");
                
                
                SomeObject someObject = new SomeObject("Hello",true,100);
                Future<Boolean> set = mcc.set(String.valueOf(index), 3600, someObject.toString());
                System.out.println(index++);
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
        finally{
            stopWatch.stop();
            System.out.println("[Inserted Entries in " + stopWatch.toString() + "]");
        }
        
    }
    
}
