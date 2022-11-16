import CLI.objDatabaseObserver;
import GL.objDatabase;
import simulation.simAdd;
import simulation.simRemove;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainSimOne {

    public static void main(String[] args) {

        objDatabase o = new objDatabase(10);
        o.attachObserver(new objDatabaseObserver(o));
        Lock lock = new ReentrantLock();


        simAdd s = new simAdd(o,lock);
        simRemove sr = new simRemove(o, lock);
        Thread thread = new Thread(s);
        Thread threadR = new Thread(sr);
        thread.start();
        threadR.start();
    }
}
