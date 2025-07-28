// package Creational.Singleton;
/* [1]
class JudgeAnalytics{
    private int run = 0;
    private int submit = 0;
    public void countRun(){
        run++;
    }
    public void countSubmit(){
        submit++;
    }
    public int getRunCount(){
        return run;
    }
    public int getSubmitCount(){
        return submit;
    }
}
*/

import javax.print.attribute.standard.JobHoldUntil;

/* [2]
//Eager Loading.
class JudgeAnalytics{
    private static final JudgeAnalytics judgeAnalytics = new JudgeAnalytics(); // this line executes during the class load time thereby this is called eager loading and this is thread safe.
    // even if multiple threads are created, this has already been assigned and always returns the same instance as executed during class load time into the system. 

    private JudgeAnalytics(){

    }
    public static JudgeAnalytics getInstance(){ // made method static so that we dont need to create an object to access this method.
        return judgeAnalytics;
    }
}
*/


/* [3]
//lazy loading without thread safety.
class JudgeAnalytics{
    private static JudgeAnalytics judgeAnalytics;

    private JudgeAnalytics(){

    }
    // whenever called, first check if the object is present or not, if not only for the first time we create one
    // and assign an object
    // call is executed only for the first time its used.
    // this is called lazy loading. this is not thread safe. its created during execution time not during class load time.
    // with eager loading, everytime the object is created, even useless things. lazy loading only initializes the objects we actually need.
    // memory will be filled with instances not needed in eager loading.
    public static JudgeAnalytics getInstance(){
        if(judgeAnalytics==null){
            judgeAnalytics = new JudgeAnalytics();
        }
        return judgeAnalytics;
    }
}
*/

/* [4]
//lazy loading with synchronized keyword.
// thread safe.
//not that efficient. on every call of getInstance it will go in a synchronized way. not that efficient.

class JudgeAnalytics {
    private static JudgeAnalytics judgeAnalytics;

    private JudgeAnalytics(){

    }
    public static synchronized JudgeAnalytics getInstance(){
        if(judgeAnalytics == null){
            judgeAnalytics = new JudgeAnalytics();
        }
        return judgeAnalytics;
    }
}
*/

/* [5]
// lazy loading with double-checked locking.
// thread safe.
class JudgeAnalytics{
    private static volatile JudgeAnalytics judgeAnalytics;

    private JudgeAnalytics(){

    }

    public static JudgeAnalytics getInstance(){
        if(judgeAnalytics == null){
            // only for the first time i use synchronized.
            synchronized (JudgeAnalytics.class){
                if(judgeAnalytics == null){ // to make sure some other thread didnt do it. hence double check.
                    judgeAnalytics = new JudgeAnalytics();
                }
            }
        }
    }
}
*/

/*  [6]
// Bill Pugh Singleton -> Best practice for lazy loading.
//This is a highly efficient way to implement the Singleton pattern. 
//It uses a static inner helper class to hold the Singleton instance. 
//The instance is created only when the inner class is loaded, which happens 
//only when getInstance() is called for the first time.
// this is thread safe and lazy loading.
class JudgeAnalytics{

    private JudgeAnalytics(){

    }
    private static class Holder { // when class JudgeAnalytics is loading, this inner class is not loaded.
        // hence the object wont be instantiated.
        // whenever the getInstance is triggered then this class is loaded. and object is initialised.
        private static final JudgeAnalytics judgeAnalytics = new JudgeAnalytics();
    }

    public static JudgeAnalytics getInstance(){
        return Holder.judgeAnalytics;
    }
}
*/

public class Main {
    public static void main(String[] args) {
        /* [1]
        JudgeAnalytics judgeAnalytics = new JudgeAnalytics();
        judgeAnalytics.countRun();
        judgeAnalytics.countSubmit();

        //say if some other service wants to access the analytics
        //  then it will create another instance of JudgeAnalytics
        JudgeAnalytics judgeAnalytics2 = new JudgeAnalytics();
        judgeAnalytics2.countRun();
        // problem is that this will not give the same count
        //  as the first instance
        // we want the number of runs and submits to be global
        // not specific to a service.
        System.out.println(judgeAnalytics2.getRunCount());
        // in total the countRun has been called 2 times.
        // but output shows only 1
        // you can see both these objects are different
        System.out.println(judgeAnalytics2);
        System.out.println(judgeAnalytics);
        // see the address is different.
        // this is a problem which singleton pattern solves.
        // Singleton pattern ensures that a class has only one instance
        // we want to have only one instance of JudgeAnalytics
        // to solve this we can use singleton pattern
        // if i need a value under global circumstances 
        // i need a single object to be used across my services.
        // The singleton pattern ensures that a class has only one instance throughout the application's lifecycle 
        // and provides a global point of access to that instance.
        // if you write obj = something under global scope
        // then it will be accessible to all the services and someone can modify it.
        // Singleton pattern is used to restrict the instantiation of a class to one single instance.

        //Why do we need?
        // Should exist only once due to global state, resources constraints(cant create a lot of objects) or logical reasoning.
        // Need to be accessed from different parts of the system.

        // examples - db, logging, analytics.

        // Can be implemented in two ways
        // Eager loading and lazy loading.
        */

        /* [2]
        // Now we cannot create an object of JudgeAnalytics
        // JudgeAnalytics judgeAnalytics = new JudgeAnalytics(); // will give error
        JudgeAnalytics judgeAnalytics = JudgeAnalytics.getInstance();
        System.out.println(judgeAnalytics);
        JudgeAnalytics judgeAnalytics2 = JudgeAnalytics.getInstance();
        System.out.println(judgeAnalytics2); // same address printed.
        */
        

        // Pros and cons of Singeton
        // pros
        // Cleaner Implementation
        // Guarantees one instance
        // provides a way to maintain a global resource.
        // supports lazy loading so we dont overload system with thread aafety as well.

        // cons
        // used with paramters and confused with factory pattern.
        // hard to write unit tests
        // classes using it are tightly coupled to it.
        // special cases to avoid race conditions.
        // violates the SRP. single responsiblity principle.
        // first-> single instance, second->the code logic.
        
    }   
}

