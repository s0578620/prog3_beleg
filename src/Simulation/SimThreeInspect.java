package Simulation;

import GL.Obj;
import GL.ObjDatabase;

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class SimThreeInspect implements Runnable{
    private Random rnd;
    private final Lock lock;
    private final ObjDatabase oDB;
    private final String threadName = "Inspect Thread";

    public SimThreeInspect(ObjDatabase oDB, Lock lock){
        this.oDB = oDB;
        this.lock = lock;
        this.rnd = new Random();
    }

    @Override
    public void run() {
        do {
            synchronized (lock) {
                System.out.println(threadName + ": Inspecting object in database.");
                int size = oDB.getObjList().size();
                if (size > 0) {
                    int index = rnd.nextInt(size);
                    Obj obj = oDB.getObjList().get(index);
                    oDB.updateInsp(obj.getFachnummer());
                    System.out.println(threadName + ": Object with Fachnummer " + obj.getFachnummer() + " has been inspected.");
                } else {
                    System.out.println(threadName + ": No objects in database to inspect.");
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (true);
    }
}
