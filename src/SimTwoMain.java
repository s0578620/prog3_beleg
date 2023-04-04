import GL.ObjDatabase;

import Simulation.SimTwoAdd;
import Simulation.SimTwoRemove;
import util.ObjDatabaseObserverSimOne;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimTwoMain {

    public static void main(String[] args) {
        int n = 2; // default value
        int capacity = 0; // default value
        if (args.length > 1) {
            try {
                capacity = Integer.parseInt(args[0]);
                n = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Arguments must be an integer (Capacity / Number Threads) .");
                System.exit(1);
            }
        }
        ObjDatabase o = new ObjDatabase(capacity);
        o.addObserver(new ObjDatabaseObserverSimOne(o));
        Lock lock = new ReentrantLock();

        for (int i = 0; i < n; i++) {
            SimTwoAdd s = new SimTwoAdd(o, lock);
            SimTwoRemove sr = new SimTwoRemove(o, lock);
            Thread thread = new Thread(s);
            Thread threadR = new Thread(sr);
            thread.start();
            threadR.start();
        }
    }
}
