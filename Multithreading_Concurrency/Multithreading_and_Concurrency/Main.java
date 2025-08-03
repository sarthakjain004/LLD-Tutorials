// package Multithreading_Concurrency.Multithreading_and_Concurrency;

public class Main {
    public static void main(String[] args) {
        
        //say uber-> cab, fare,time
        // cab->sedan,go premiere etc. each one will have different fare and time etc.
        // multiple options
        // first compute fare(2 secs) and compute time(2 secs)
        // just uber black-> take 4 secs to calc.
        // can i calc in parallel?
        // calc fare and time at same time
        // hence 2 sec total for uber black
        // similar for uber go, uber premiere etc.
        // total 2 sec since every computation is parallel.

        // dont wait for fare to be calc, direct calculate time.
        

        //what is a program, process and a thread?
        // program is an executable file (chrome.exe)
        // process: a process is an executing instance of a program.
        // process own memory, program counter, stack, heap etc, system resources.
        // process is a container for threads.
        // process can have multiple threads.
        // thread is a lightweight process, it is a sub-task of a process.
        // threads share memory, program counter, stack, heap etc. of the process.
        // threads are independent of each other.
        // threads can run in parallel on multi-core processors.
        // threads are used to perform multiple tasks concurrently within a single process.
        // thread: thread is the smallest executable unit of a process. threads
        // are sub-tasks of processes.

        // fare in one thraed, time in another thread.

        // what are cores in CPU?
        //a core is an individual processing unit inside ur CPU - capable of executing 
        // instructions independently. mini CPU inside a CPU.
        // layman analogy -> single core kitchen / multi core kitchen
        // one thread at a time per core. (or more with hyper threading)
        // hyper thrading: intelligent time slicing and resource sharing
        // say core 1-> T1, t2 asking for core 1. use hyper threading
        // say T1 requies something, pause, T2 takes over, does its job, pause, then T1 come back, so on.
        // they make time for each other.
        // logically one thread at one core at a time.

        // what is context switching?
        // process where the CPu stops executing one thread/process, save its state, and switches to another.
        // T1, then T2, load back T1, save, start t2, so on.
        // when does context switch happens?
        // the cpu saves the current thread's content
        // load the next thread's content
        // resumes the exeution of new thread.
        // switching is managed by the thread scheduler
        // takes time to save and load states
        // performance degradation due to high no. of threads.
        
        // what is multithreading? why use?
        // is the ability of a program to run multiple threads (independent tasks) concurrently (not parallely)
        // either truly in parallel(multi-core) or via context switching (single core)
        // each thread
        // runs independently
        // shares the same mem space
        // performs a specific task

        // why use?
        // better performance
        // non blocking: allows other tasks to proceed without waiting for the current task to complete
        // resource sharing
        // scalability in backend services.

        // what is concurrency? what is parallelism?
        // concurrency means multiple tasks making progress over time, but not necessarily at the same exact time
        // can work with one core
        // takss appear tu run at the same time
        // focus is on the structure, on how to do many things

        // parallelism means multiple tasks executing at the exact same time on multiple cores
        // can work with multiple cores
        // tasks are actually running simultaneously
        // focus is on execution, how to finish many things

        //concurrency more focused on in system design
        
        //difference between process and thread?
        //process is independent program in execution
        // has its own memory
        //fully isolated
        // commn is complex (IPC, socket)
        // overhead is high
        // one process crash doesnt affect others
        // eg postgreSQL

        // thread
        // subunit of a process
        // shares memory with other threads in the same process
        // not fully isolated
        // easy comm (shared memory)
        // overhead is low
        // one thread crash can affect others in the same process
        // eg chrome tabs

        //when to use thread and process?
        // when to use threads?
        // when tasks need to share data
        // low overhead is required
        // tasks are part of the same logic
        // high performance is required
        // tightly coupled behaviour
        // responsiveness is key

        //when to use processes?
        //tasks require isolation
        // one crash should not affect others
        // security boundaries are needed
        // different tech stacks are used
        // resource limits are needed
        // used by different users

        // fault tolerance and isolation
        //fault tolerance is the ability of the system to continue operating correctly,
        // even when some of the components fail
        // it detects, contains and recovers from failures without affecting user experience
        // real life -> plane
        // redundancy increase
        // graceful degradation (continue functioning with reduced performance)
        // self healing (automatically detect and recover from failures)
        // error containment -> user shouldnt be aware of the failure
        
        // isolation means keeping different components or tasks independently from each other
        // so that actions or failure do not affect each other
        // design strategy to ensure that the components are sandboxed from each other
        // memory separation, failure containment
        // security boundaries, predictable behaviour

        
    }
}
