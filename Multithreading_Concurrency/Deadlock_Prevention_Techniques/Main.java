// package Multithreading_Concurrency.Deadlock_Prevention_Techniques;
import java.util.*;

// a deadlock is a situation in multithreaded applications where two or more threads are blocked forever
//, each waiting for the other to reduce the lock

// real life analogy -> train
// coding example -> Bank transfer

/*
//[1] deadlock show example
class BankAccount{
    private final String name;
    private int balance;
    public BankAccount(String name, int balance){
        this.name = name;
        this.balance = balance;
    }
    public String getName(){ return name; }
    public synchronized void deposit(int amount){
        balance += amount;
    }
    public synchronized void withdraw(int amount){
        balance -= amount;
    }
    public int getBalance(){ return balance; }
}

class TransferTask implements Runnable{
    private final BankAccount from;
    private final BankAccount to;
    private final int amount;

    public TransferTask(BankAccount from, BankAccount to, int amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run(){
        synchronized(from){ //object monitor lock on from object.
            System.out.println(Thread.currentThread().getName() + " locked "+from.getName());
            try{ Thread.sleep(100);} catch (InterruptedException ignored){}
            synchronized(to){
                System.out.println(Thread.currentThread().getName() + " locked "+to.getName());

                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transferred "+amount+" from "+ from.getName()+ " to "+ to.getName());
            }
        }
    }
}
*/

/*
//[2] Lock ordering deadlock prevention.
// deadlock prevention technique
//1. lock ordering (IMPORTANT REMEMBER)
// ensure that all threads acquire locks in a predefined global order, regardless of theri exec context
// condition broken = circular wait.
// If threads always acquire multiple locks in the same sequence (say A → B → C), the possibility of 
// forming a cycle is eliminated, because no thread will ever wait on a lock held by another while holding 
// a higher-order lock.

class LockOrderingSimple{
    static class Resource{
        int id;
        int value;
        public Resource(int id, int value){
            this.id = id;
            this.value = value;
        }
    }
    public static void main(String[] args) throws Exception {
        Resource r1 = new Resource(101, 500);
        Resource r2 = new Resource(102, 500);

        Runnable t1 = () -> transfer(r1, r2, 100);
        Runnable t2 = () -> transfer(r2, r1, 100);

        Thread a1 = new Thread(t1, "T1");
        Thread b1 = new Thread(t2, "T2");

        a1.start();
        b1.start();

        a1.join();
        b1.join();
        System.out.println("Finished");
    }
    public static void transfer(Resource a, Resource b, int amount){
        Resource[] locks = new Resource[]{a,b};
        Arrays.sort(locks, (x,y) ->Integer.compare(x.id, y.id)); //first r1 then r2. depends on id not paramter ordering.
        
        synchronized(locks[0]){ // this and the above sort fixed the indexes even when paramter order in method is different.
            System.out.println(Thread.currentThread().getName() + " locked " + locks[0].id);
            try{ Thread.sleep(100);} catch (InterruptedException ignored){}
            synchronized(locks[1]){
                System.out.println(Thread.currentThread().getName() + " locked " + locks[1].id);
                System.out.println("transferred " + amount + " from "+ a.id + " to " + b.id);
            }
        }
    }
}
*/

/*
//[3] using trylock() deadlock prev technique
// breaks hold and wait.
class TryLockWithTimeout {

    static class Resource {
        final int id;
        final ReentrantLock lock = new ReentrantLock();

        public Resource(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {

        // Create two shared resources
        Resource r1 = new Resource(1);
        Resource r2 = new Resource(2);

        // Thread 1: tries to lock r1 then r2
        Runnable task1 = () -> tryTransfer(r1, r2);

        // Thread 2: tries to lock r2 then r1 (reverse order)
        Runnable task2 = () -> tryTransfer(r2, r1);

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }

    public static void tryTransfer(Resource a, Resource b) {
        boolean acquiredA = false;
        boolean acquiredB = false;

        try {
            // Try locking first resource with timeout
            acquiredA = a.lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (acquiredA) {
                System.out.println(Thread.currentThread().getName() + " locked Resource " + a.id);

                // Simulate some work
                Thread.sleep(50);

                // Try locking second resource with timeout
                acquiredB = b.lock.tryLock(100, TimeUnit.MILLISECONDS);
                if (acquiredB) {
                    System.out.println(Thread.currentThread().getName() + " locked Resource " + b.id);
                    System.out.println("Transfer successful between " + a.id + " and " + b.id);
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not lock Resource " + b.id + " - backing off");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not lock Resource " + a.id + " - backing off");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Always release acquired locks
            if (acquiredB) b.lock.unlock();
            if (acquiredA) a.lock.unlock();
        }
    }
}
*/
public class Main {
    public static void main(String[] args) throws Exception{
        /*
        BankAccount accountA = new BankAccount("A", 1000);
        BankAccount accountB = new BankAccount("B", 1000);

        //Thread 1 = A->B
        Thread t1 = new Thread(new TransferTask(accountA, accountB, 100),"T1");

        // Thread 2 = B->A (opposite order)
        Thread t2 = new Thread(new TransferTask(accountB, accountA, 100), "T2");

        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        //output:
        // T1 locked A
        // T2 locked B
        // infinite state
        // deadlock.
        */
        //four conditions for deadlock must simultaneous:
        // 1. mutual exclusion
        // 2. hold and wait
        // 3. no preemption
        // 4. circular wait.
        // for prevention: break at least 1 condition. thats it.

        // dining philosophers problem:
        // 5 sit aound circular table.
        // each needs 2 fork to eat
        // if ebvry picks the one on left, no one can pick up right one
        // deadlock.
        // total 5 forks.
        
        //[2] lock ordering deadlock prevention.
        // LockOrderingSimple.main(args);
    }
}
