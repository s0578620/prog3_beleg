import GL.ObjDatabase;
import simulation.SimAdd;
import simulation.SimRemove;
import util.ObjDatabaseObserverSimOne;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainSimOne {

    public static void main(String[] args) {

        ObjDatabase o = new ObjDatabase(10);
        o.attachObserver(new ObjDatabaseObserverSimOne(o));
        Lock lock = new ReentrantLock();


        SimAdd s = new SimAdd(o,lock);
        SimRemove sr = new SimRemove(o, lock);
        Thread thread = new Thread(s);
        Thread threadR = new Thread(sr);
        thread.start();
        threadR.start();
    }
}
