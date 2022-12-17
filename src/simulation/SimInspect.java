package simulation;

import GL.Obj;
import GL.ObjDatabase;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class SimInspect implements Runnable {
    private final Lock lock;
    private final ObjDatabase oDB;
    private final Random rnd;

    public SimInspect(ObjDatabase oDB, Lock lock) {
        this.oDB = oDB;
        this.lock = lock;
        this.rnd = new Random();
    }

    @Override
    public void run() {
        while (true) {
            // Acquire the lock
            lock.lock();
            try {
                // Get the list of cakes in the database
                List<Obj> objList = oDB.getObjList();
                // Select a random cake from the list
                Obj cake = objList.get(rnd.nextInt(objList.size()));
                // Modify the inspection date of the cake
                cake.setInspektionsdatum(new Date());
            } finally {
                // Release the lock
                lock.unlock();
            }
        }
    }
}
