package lang;

import GL.objDatabase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainSimOne {


    public static void main(String[] args) {
//        sim o = new sim();
//        Thread s = new Thread(o);
//        s.start();


        objDatabase o = new objDatabase();
        final Lock lock = new ReentrantLock();
        final Condition full = lock.newCondition();
        final Condition removing = lock.newCondition();

        simAdd s = new simAdd(o,lock, full, removing);
        simRemove sr = new simRemove(o, lock, full, removing);
        Thread thread = new Thread(s);
        Thread threadR = new Thread(sr);
        thread.start();
        threadR.start();

    }
}
