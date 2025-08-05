// package Multithreading_Concurrency.Thread_Safety_Synchronization;

// say T1 and T2, doing independent tasks, or async tasks
// what if working on same data, and write in same data at same time
// incorrect data
// here comes thread safety
// thread safety means that a piece of code, object or method behaves correctly and
// predictably when accessed by multiple threads at the same time, without corrupting
// data or producing incorrect results

// thread safety ensure correctness under concurrent access
// example TUF+ purchase counter
// say coupon code for 50 users.
// EOD 49 users used
// A and B trying to apply that code
// both read 1 coupon left
// both get true and end up using it. which is an issue.
//why does it fail?

//race condition with naive counter example

import java.util.concurrent.atomic.AtomicInteger;

class PurchaseCounter{
    private int count = 0;
    public void Increment(){
        // read, increment value.
        count++; // not thread-safe.
    }
    public int getCount(){
        return count;
    }
}

//how to fix the issue?
//synchronized keywork (methods vs blocks, works on object monitor lock)
// concept
// synchronized keywork acquires an object monitor lock, allowing only one thread at a time
// fix it via -> synchronized method/block
// sync method -> simple but locks the whole method
// sync block -> more fine-grained, locks only critical section.

/*
//[2] fix using synchronized method-
class PurchaseCounterSyncMethod{
    private int count = 0;
    public synchronized void Increment(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
*/

//[3] fix using synchronized block
class PurchaseCounterSyncBlock{
    private int count = 0;
    public void Increment(){
        synchronized(this){
            //whatever op u want one thread to do at a time
            // where data might get corrupted.
            count++;
        }
    }
    public int getCount(){
        return count;
    }
}


// monitor lock explained
// - every object in java has an intrinsic lock (monitor)
// - synchronized acquires the lock on that object
// - only one thread can hold lock at that time.


//volatile keyword
// ensures visibility, and not atomicity (single operation)
// volatile keyword only ensures that the latest value of count is visible across threads
// count++ is still not atomic.
// use it only when -> onlyy one thread writes, others only read.
// when T1 updates count, the cache might not be updated, T2 might read old value.
// volatile ensure thats u read and write to the main memory.
// core guarantees of volatile
// visibility -> changes made by one thread are immediately visible to another thread
// no caching -> value is always read from and written to main memory(not cached copy in cpu register)
// no atomicity -> doesnt make operations atomic.


//atomic variables (Atomic Integer, Atomic Boolean) (Compare and swap)
// both of them uses compare-and-swap method at hardware level. it is lock free, and highly performant
//CAS concept-
// think of it like "If value is what I expect, then set it to new value "
// prevents race condition without locking.

class PurchaseAtomicCounter{
    private AtomicInteger likes = new AtomicInteger(0);

    public void increment(){
        int prev,next;
        do{
            prev = likes.get();
            next = prev+1;
        } while(!likes.compareAndSet(prev, next));
    }

    public int getCount(){
        return likes.get();
    }
}
public class Main {
    public static void main(String[] args) throws InterruptedException{

        /*
        //[1] without thread safety
        PurchaseCounter counter = new PurchaseCounter();
        Runnable task = () -> {
            for(int i=0;i<1000;i++){
                counter.Increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //wait for threads to finish, then print final count.
        System.out.println(counter.getCount());
        // variable ans, should get 2000, get less than that
        */

        /*
        //[2] with sync method thread safety-
        PurchaseCounterSyncMethod counter = new PurchaseCounterSyncMethod();
        Runnable task = () -> {
            for(int i=0;i<1000;i++){
                counter.Increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //wait for threads to finish, then print final count.
        System.out.println(counter.getCount());
        */

        /*
        //[3] using sync block.
        PurchaseCounterSyncBlock counter = new PurchaseCounterSyncBlock();
        Runnable task = () -> {
            for(int i=0;i<1000;i++){
                counter.Increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //wait for threads to finish, then print final count.
        System.out.println(counter.getCount());
        */


        //[4] using atomic variables
        PurchaseAtomicCounter counter = new PurchaseAtomicCounter();
        Runnable task = () -> {
            for(int i=0;i<1000;i++){
                counter.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2= new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());
        //pros -> high performance, non blocking
        // cons -> may fall under high contention. (too many retries).
        // what enables the atomicity?
        //the magic is in the hardware-level lock-free instructions and java's unsafe class under the hood
        // only one thread can win, and this is guaranteed by the atomicity at the hardware level.
        

    }    
}
