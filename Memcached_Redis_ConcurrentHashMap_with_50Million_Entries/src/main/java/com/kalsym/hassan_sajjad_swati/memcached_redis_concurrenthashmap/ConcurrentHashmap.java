
//Before using this program you should increase the HeapSize of both your IDE and MAVEN
//Minimum RAM Recommened for 50Million Entries is 16GB with Allocation of 12GB HeapSize

package com.kalsym.hassan_sajjad_swati.memcached_redis_concurrenthashmap;

import com.google.common.base.Stopwatch;

public class ConcurrentHashmap {

    public static void main(String[] args){
        int size = 50000000;
        long index = 10000000000L;
        Stopwatch stopWatch = Stopwatch.createUnstarted();
        System.out.println("[Memory Before Insertion = " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) +"]");
        System.out.println("[Starting Insertion]");
        try{
            ExpiringMap map = new ExpiringMap(3600);
            for(int i = 0; i < size ; i++){
                
                SomeObject someObject = new SomeObject("Hello",true,1);
                
                if(i == 0)
                    stopWatch.start();
              
                if(i == 1000)
                    System.out.println("[Inserted 1000 entries in " + stopWatch.toString() + "]");
  
                map.put(index, someObject);
                index++;
            }
            
        }catch (OutOfMemoryError e){
            System.out.println(e);
        }
        finally{
            stopWatch.stop();
            System.out.println("[Inserted " +index+ " entries in " + stopWatch.toString() + "]");
            System.out.println("[Memory After = " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + "]");
            
        }
    }
    
    
}
