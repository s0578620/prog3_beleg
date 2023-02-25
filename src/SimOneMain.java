import GL.ObjDatabase;
import simulation.SimOneAdd;
import simulation.SimOneRemove;
import util.ObjDatabaseObserverSimOne;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimOneMain {

    public static void main(String[] args) {

        ObjDatabase o = new ObjDatabase(10);
        o.addObserver(new ObjDatabaseObserverSimOne(o));
        Lock lock = new ReentrantLock();


        SimOneAdd s = new SimOneAdd(o,lock);
        SimOneRemove sr = new SimOneRemove(o, lock);
        Thread thread = new Thread(s);
        Thread threadR = new Thread(sr);
        thread.start();
        threadR.start();
    }
}

