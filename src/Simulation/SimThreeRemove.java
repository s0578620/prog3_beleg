package Simulation;

import GL.Obj;
import GL.ObjDatabase;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SimThreeRemove implements Runnable{
//    private final Lock lock;
//    private final ObjDatabase oDB;
//    private final String threadName = "Remove Thread";
//    private final long interval;
//
//    public SimThreeRemove(ObjDatabase oDB, Lock lock, long interval) {
//        this.oDB = oDB;
//        this.lock = lock;
//        this.interval = interval;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            synchronized (lock) {
//                System.out.println(threadName + ": Removing object from database.");
//                if (oDB.getObjList().size() > 0) {
//                    // sort list by inspection date
//                    Collections.sort(oDB.getObjList(), Comparator.comparing(Obj::getInspektionsdatum));
//                    // remove oldest object
//                    oDB.removeObj(0);
//                }
//            }
//            try {
//                Thread.sleep(interval);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private final ObjDatabase oDB;
    private final String threadName = "Remove Thread";
    private final long interval;

    public SimThreeRemove(ObjDatabase oDB, Lock lock, Condition notFull, Condition notEmpty, long interval) {
        this.oDB = oDB;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (oDB.getObjList().size() == 0) {
                    notEmpty.await();
                }
                System.out.println(threadName + ": Removing object from database.");
                // sort list by inspection date
                Collections.sort(oDB.getObjList(), Comparator.comparing(Obj::getInspektionsdatum));
                // remove oldest object
                oDB.removeObj(0);
                notFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
