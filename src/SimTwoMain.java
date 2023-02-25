import GL.ObjDatabase;

import simulation.SimTwoAdd;
import simulation.SimTwoRemove;
import util.ObjDatabaseObserverSimOne;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimTwoMain {

    public static void main(String[] args) {
        int n = 2; // default value
        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }
        ObjDatabase o = new ObjDatabase(100);
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
