// package Multithreading_Concurrency.Thread_Pools_and_Executors;
// import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.*;

//[1] without thread pools
//why should we not create threads manually in real world systems?
class RideMatchingService {
    public void MatchRide(String riderId){
        Thread matchThread = new Thread(() ->{
            System.out.println("Matching rider "+riderId+" to a driver.. Please wait");
            try{
            Thread.sleep(3000);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println("Success match for: "+riderId); 
        });
        matchThread.start();
    }
}
// imagine if everytime a rider is req a ride, millions of riders requesting
// a lot of resources consuming then in above case.
// every thread consumes memory.
//reasons why to not create threads manually-
// thread explosion -> OS cannot handle that many requests at the same time
// memory issues -> every thread takes ~1Mb of memory stack by default
// thread leaks -> created but never terminated properly
// context switching overload -> CPU sepends more time switching between threads than doing really work.


//better approach -> use thread pools
// use a pool of worker threads, that are reused
// real life analogy -> instead of hiring new chefs everyday,
// use existing chefs effieciently to handle multiple orders as they come in.

//introduction to executor framework
// this framework exposes methods to manage thread pool
//[2] using executor framework, thread pool
class EmailService{
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void sendEmail(String recipient){
        executor.execute(() -> {
            System.out.println("Sending email to "+recipient+" on "+Thread.currentThread().getName());
            try{
                Thread.sleep(3000);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println("Email sent to recipient");
        });        
    }

    public static void main(String[] args) {
        for(int i=1;i<=25;i++){
            sendEmail("user" + i +"@gmail.com");
        }
        executor.shutdown();
    }
}
// executor in the above, took a runnable. runnable always had a call function
// executor framework:
// in java framework, its a high level replacement for manually managing threads
// it decouples:
// task submission (what u want to do)
// task execution (how and when it runs)
// use newFixedThreadPool()

/*
//[3] using Future and Submit, when we need return value.
//methods to submit tasks- (how do we submit tasks?) IMP.
// how do we get return value?
// Future and Submit example
class FutureExample{
    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future = executor.submit(() ->{
            Thread.sleep(1000);
            return 42;
        });
        System.out.println("Doing other work");
        Integer result = future.get();
        System.out.println("Result: "+result);
        executor.shutdown();
    }
}
// theory:
// execute(Runnable)
// executor.execute(()->task)
// fire and forget
// no result tracking

// submit(Runnable/callable)
// executor.submit(()->task)
// u want a return value.
// want to cancel or track task progress.
*/

//shutting down executors
// shutdown() -> no new tasks, waits for current tasks to complete
// shutdownNow() -> attempts to shut down all tasks immediately.

//thread starvation and fairness
// when long running tasks hog the threads, smaller/critical tasks get delayed
// fix -> use priority queues, or separate pools.


//when to use which type of thread pool?
// fixed, Cached vs Scheduled Thread Pool.
// fixed -> reuses N threads, use when predictable and limited load
//Cached -> creates new threads as needed, reuses idle ones. use when Bursty load
// Scheduled -> supports dekays, repeating tasks. use when periodic tasks.

class CachedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 1000; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " running on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed");
            });
        }
        executor.shutdown();
    }
}
/*
The idea behind newCachedThreadPool is to create short lived threads so that the same can be reused instead of creating new threads.

Still let's assume that there is a scenario where the number of threads is equivalent to Integer.MAX_VALUE; but still the number of CPU cores available will be say 4.

As per the below mentioned code :

public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
}
The newCachedThreadPool() method internally creates a new ThreadPoolExecutor with maximumPoolSize as Integer.MAX_VALUE and keepAlive time as 60second.

The javadocs for keepAlive suggests :

keepAliveTime when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.

Which means that if the number of threads is more than the available CPU cores, the excess thread will be live for only 60secs before being marked terminated.

So there should not be any such scenario where newCachedThreadPool() caused the system resources to be fully consumed.
*/

class SessionCleaner{
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = ()->System.out.println("cleaning up expired sessions");
        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}


public class Main {
    public static void main(String[] args) {
        /*
        //[1] without thread pool
        RideMatchingService rs1 = new RideMatchingService();
        RideMatchingService rs2 = new RideMatchingService();
        rs1.MatchRide("raj");
        System.out.println("T1 running");
        rs2.MatchRide("sj");
        System.out.println("T2 running");
        */
        // [2] using Executor framework.
        // EmailService.main(args);


        /*
        //[3] using Future and Submit, thread pool with when u need to return value
        try{
            FutureExample.main(args);
        } catch (Exception e){
            e.printStackTrace();
        }
        */
        // CachedThreadPoolExample.main(args);
        SessionCleaner.main(args);
    }
}
