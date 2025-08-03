// package Multithreading_Concurrency.Create_Manage_Threads;
import java.util.*;
import java.util.concurrent.Callable;
//problem statemnt
// place order-> send sms -> send email -> send ETA
import java.util.concurrent.FutureTask;

/*
// [1] without threads, how will we do it?
// OrderService class
class OrderService {

    // Main method simulates placing an order and executing tasks sequentially
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Placing order...\n");

        // Send SMS and simulate the delay of 2 seconds
        sendSMS();
        System.out.println("Task 1 done.\n");

        // Send Email and simulate the delay of 3 seconds
        sendEmail();
        System.out.println("Task 2 done.\n");

        // Calculate ETA (Estimated Time of Arrival) with a delay of 5 seconds
        String eta = calculateETA();
        System.out.println("Order placed. Estimated Time of Arrival: " + eta);
        System.out.println("Task 3 done.\n");
    }

    // Method to simulate sending SMS with a 2-second delay
    private static void sendSMS() {
        try{
            Thread.sleep(2000); // Delay of 2 seconds
            System.out.println("SMS Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate sending Email with a 3-second delay
    private static void sendEmail(){
        try{
            Thread.sleep(3000); // Delay of 3 seconds
            System.out.println("Email Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate calculating the ETA with a 5-second delay
    private static String calculateETA() {
        try {
            Thread.sleep(5000); // Delay of 5 seconds
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "25 minutes"; // Returning the calculated ETA
    }
}
*/

//[2] using thread class (start()/run()) --> fire and forget
/*
class SMSThread extends Thread{
    public void run(){
        try{
            Thread.sleep(2000); // Simulating SMS sending delay
            System.out.println("SMS Sent!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EmailThread extends Thread{
    public void run(){ // its a void method, no return type.
        try{
            Thread.sleep(3000);
            System.out.println("Email sent!");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
// how to do ETA, when we have to return a value?
*/

/*
//[3] using runnable interface (start()/run()) --> fire and forget
// still will be using Thread class as well. neww Thread(p).start()
class SMSThreadRunnable implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(7000);
            System.out.println("SMS send using Thread");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class EmailThreadRunnable implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(3000);
            System.out.println("Email send successfully");
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
*/

/*
//[4] using Callable and Future for result oriented tasks-
//fire and forget-> means we have void run. thats why we couldnt do eta calculation till now.
// we use Callable and Future for result oriented tasks -> wait for results.
// callable is a functional interface, part of java.util.concurrent
// it allows:
// returning a result
// throw exceptions

class SMSThreadRunnable implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(7000);
            System.out.println("SMS send using Thread");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class EmailThreadRunnable implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(3000);
            System.out.println("Email send successfully");
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
class ETACalculator implements Callable<String>{
    private final String location;
    public ETACalculator(String location){
        this.location = location;
    }
    @Override
    public String call() throws Exception{
        System.out.println("[" + Thread.currentThread().getName() + "] Calculating ETA to:" + location);
        Thread.sleep(5000); //simulate ETA calculation
        return "ETA to "+location+": 20 min";
    }
}
*/


//[5] easy way without needing to create classes and everything. 2 line change
class OrderService {

    // Main method simulates placing an order and executing tasks sequentially
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Placing order...\n");

        // Send SMS and simulate the delay of 2 seconds
        Thread thread = new Thread(() -> sendSMS());
        thread.start();
        System.out.println("Task 1 started.\n");

        Thread thread2 = new Thread(() -> sendEmail());
        thread2.start();
        System.out.println("Task 2 started.\n");

        // For tasks that return a value, use Callable with FutureTask and lambda
        FutureTask<String> etaTask = new FutureTask<>(() -> calculateETA());
        Thread thread3 = new Thread(etaTask);
        thread3.start();
        String eta = etaTask.get(); // Waits for result
        System.out.println("Order placed. Estimated Time of Arrival: " + eta);
        System.out.println("Task 3 done.\n");
    }
    // Method to simulate sending SMS with a 2-second delay
    private static void sendSMS() {
        try{
            Thread.sleep(2000); // Delay of 2 seconds
            System.out.println("SMS Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate sending Email with a 3-second delay
    private static void sendEmail(){
        try{
            Thread.sleep(3000); // Delay of 3 seconds
            System.out.println("Email Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate calculating the ETA with a 5-second delay
    private static String calculateETA() {
        try {
            Thread.sleep(5000); // Delay of 5 seconds
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "25 minutes"; // Returning the calculated ETA
    }
}

public class Main {
    public static void main(String[] args) {
        /*
        //[1] without threads, how will we do it?
        try{
            OrderService.main(args); //takes total 10 seconds.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 10 seconds!. this is where multithreading comes in.
        // assume 8 core machine
        // 1 thread-> send sms
        // 2nd thread-> send email
        // 3rd thread-> calculate ETA

        // all in parallel.
        // total time will be max of 2, 3 and 5 seconds.
        // so total time will be 5 seconds.
        // this is how multithreading works.

        // multithreading is done using the Thread class.
        */ 

        /*
        //[2] using thread class.
        SMSThread smsThread = new SMSThread();
        EmailThread emailThread = new EmailThread();
        System.out.println("Task started");
        smsThread.start(); // starts the SMS thread
        System.out.println("Task 1 ongoing");
        emailThread.start(); // starts the Email thread
        System.out.println("Task 2 going on");

        // how do i wait till the tasks are completed?
        try{
            smsThread.join(); // waits for the SMS thread to finish
            emailThread.join(); // waits for the Email thread to finish
            System.out.println("Tasks done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        /*
        //[3] using Runnable interface
        Thread thread1 = new Thread(new SMSThreadRunnable());
        Thread thread2 = new Thread(new EmailThreadRunnable());
        System.out.println("Task starting");
        thread1.start();
        System.out.println("Task 1 started");
        thread2.start();
        System.out.println("Task 2 started");
        try{
            thread1.join();
            thread2.join();
            System.out.println("Both done");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        */

        /*
        //[4] using callable and future
        Thread thread1 = new Thread(new SMSThreadRunnable());
        Thread thread2 = new Thread(new EmailThreadRunnable());
        // Thread thread3 = new Thread(new ETACalculator("BLR")); // cant do this
        // Thread wants a runnable
        // we gave it a callable above.
        // Here Futureclass is used.
        FutureTask etaThreadRunnable = new FutureTask<>(new ETACalculator("BLR"));
        Thread thread3 = new Thread(etaThreadRunnable);
        System.out.println("Task starting");
        thread1.start();
        System.out.println("Task 1 started");
        thread2.start();
        System.out.println("Task 2 started");
        thread3.start();
        System.out.println("Task 3 started");
        // how to get returning result?
        try{
            thread1.join();
            thread2.join();
            thread3.join();
            String eta = (String)etaThreadRunnable.get();
            System.out.println("ETA is "+ eta);
            System.out.println("Task done");
        } catch (Exception e){
            e.printStackTrace();
        }
        */

        // do we have any other ways of executing tasks inside thread class?
        // directly define a runnable instance as well.
        // this can be used to instantiate a thread.
        try{
            OrderService.main(args);
        } catch (Exception e){
            e.printStackTrace();
        }
        /*
        //another way, just not using lambda function.
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000); // Simulate delay
                    System.out.println("Task completed using direct Runnable.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Create and start the thread
        Thread thread = new Thread(task);
        thread.start();
        */
    }
}