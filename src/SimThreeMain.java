import GL.ObjDatabase;

import Simulation.SimThreeAdd;
import Simulation.SimThreeInspect;
import Simulation.SimThreeRemove;
import Simulation.SimThreeShow;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimThreeMain {
    public static void main(String[] args) {
        int capacity = 20; // default capacity
        int numThreads = 2; // default num
//        int numRemoveThreads = 1; // default num
//        int numInspectThreads = 1; // default num
        int showInterval = 500; // default interval

        if (args.length >= 3) {
            try {
                capacity = Integer.parseInt(args[0]);
                numThreads = Integer.parseInt(args[1]);
//                numRemoveThreads = Integer.parseInt(args[2]);
//                numInspectThreads = Integer.parseInt(args[3]);
                showInterval = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Arguments must be integers.");
                System.exit(1);
            }
        }

        ObjDatabase o = new ObjDatabase(capacity);
//        o.addObserver(new ObjDatabaseObserverSimOne(o));

        Lock lock = new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads + numThreads + numThreads + 1);

        for (int i = 0; i < numThreads; i++) {
            SimThreeAdd addThread = new SimThreeAdd(o, lock, notFull, notEmpty, 0);
            executor.execute(addThread);
        }

        for (int i = 0; i < numThreads; i++) {
            SimThreeRemove removeThread = new SimThreeRemove(o, lock, notFull, notEmpty, 0);
            executor.execute(removeThread);
        }

        for (int i = 0; i < numThreads; i++) {
            SimThreeInspect inspectThread = new SimThreeInspect(o, lock);
            executor.execute(inspectThread);
        }

        SimThreeShow showThread = new SimThreeShow(o, showInterval);
        executor.execute(showThread);

        executor.shutdown();
    }
}
