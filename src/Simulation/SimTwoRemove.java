package Simulation;

import GL.ObjDatabase;

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class SimTwoRemove implements Runnable{

    private Random rnd;
    private final Lock lock;
    private final ObjDatabase oDB;
    private final String threadName = "Remove Thread";

    public SimTwoRemove(ObjDatabase oDB, Lock lock){
        this.oDB = oDB;
        this.lock = lock;
        this.rnd = new Random();
    }
    @Override
    public void run() {
        do {
            synchronized (lock) {
                System.out.println(threadName + ": Removing object from database.");
                oDB.removeObj(rnd.nextInt(oDB.getObjList().size()+1)); // TODO +1 abfangen -> Liste 0?
            }
        } while (true);
    }
}
