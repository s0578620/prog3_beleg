import GL.ObjDatabase;

import Simulation.SimThreeAdd;
import Simulation.SimThreeInspect;
import Simulation.SimThreeRemove;
import Simulation.SimThreeShow;
import util.ObjDatabaseObserverSimOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimThreeMain {
    public static void main(String[] args) {
        int capacity = 100; // default capacity
        int numAddThreads = 2; // default number of add threads
        int numRemoveThreads = 2; // default number of remove threads
        int numInspectThreads = 1; // default number of inspect threads
        int showInterval = 1000; // default interval for show thread (in ms)

        if (args.length >= 4) {
            try {
                capacity = Integer.parseInt(args[0]);
                numAddThreads = Integer.parseInt(args[1]);
                numRemoveThreads = Integer.parseInt(args[2]);
                numInspectThreads = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Arguments must be integers.");
                System.exit(1);
            }
        }

        ObjDatabase o = new ObjDatabase(capacity);
        o.addObserver(new ObjDatabaseObserverSimOne(o));

        Lock lock = new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();

        ExecutorService executor = Executors.newFixedThreadPool(numAddThreads + numRemoveThreads + numInspectThreads + 1);


        for (int i = 0; i < numAddThreads; i++) {
            SimThreeAdd addThread = new SimThreeAdd(o, lock, notFull, notEmpty, 1000);
            executor.execute(addThread);
        }

        // start remove threads
        for (int i = 0; i < numRemoveThreads; i++) {
            SimThreeRemove removeThread = new SimThreeRemove(o, lock, notFull, notEmpty, 2000);
            executor.execute(removeThread);
        }


        // start inspect threads
//        for (int i = 0; i < numInspectThreads; i++) {
//            SimThreeInspect inspectThread = new SimThreeInspect(o, lock);
//            executor.execute(inspectThread);
//        }

        // start show thread
        SimThreeShow showThread = new SimThreeShow(o, showInterval);
        executor.execute(showThread);

        // shut down executor after all threads have finished
        executor.shutdown();
    }


}
