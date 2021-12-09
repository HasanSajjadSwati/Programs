
//Before using this program you should increase the HeapSize of both your IDE and MAVEN
//Minimum RAM Recommened for 50Million Entries is 16GB with Allocation of 12GB HeapSize

package com.kalsym.hassan_sajjad_swati.memcached_redis_concurrenthashmap;

import com.google.common.base.Stopwatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class Redis {

    public static void main(String[] args){
        int size = 50000000;
        long index = 10000000000L;
        SetParams ex = new SetParams();
        Stopwatch stopWatch = Stopwatch.createUnstarted();
        try
        {
           Jedis jedis = new Jedis("localhost");
           System.out.println("[Connection to server sucessfully]");
           System.out.println("[Starting Insertion]");
           for (int i = 0; i < size;i++){
                if(i==0)
                    stopWatch.start();
            
                if(i == 1000)
                {
                    System.out.println("[Inserted 1000 Entries in " + stopWatch.toString() + "]");
                    System.out.println(stopWatch.toString());
                }
                
                SomeObject someObject = new SomeObject("Hello",false,100);
                jedis.set(String.valueOf(index), someObject.toString(),ex.ex(3600));
                index++;
            }   
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            stopWatch.stop();
            System.out.println("[Entries Inserted in " + stopWatch.toString() + "]");
        }
    }
        
}

    