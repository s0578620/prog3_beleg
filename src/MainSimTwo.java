import GL.Obj;
import GL.ObjDatabase;
import simulation.*;
import util.ObjDatabaseObserverSimOne;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainSimTwo {
    public static void main(String[] args) {
        // Create an ObjDatabase object and an ObjDatabaseObserverSimOne object
        ObjDatabase o = new ObjDatabase(10);
        o.addObserver(new ObjDatabaseObserverSimOne(o));
        Lock lock = new ReentrantLock();

        // Create two SimOneAdd and SimOneRemove objects
        SimOneAdd s = new SimOneAdd(o,lock);
        SimOneRemove sr = new SimOneRemove(o, lock) {
            @Override
            public void run() {
                while (true) {
                    // Acquire the lock
                    lock.lock();
                    try {
                        // Get the list of cakes in the database
                        List<Obj> objList = o.getObjList();
                        // Sort the list by inspection date
                        objList.sort(Comparator.comparing(Obj::getInspektionsdatum));
                        // Delete the oldest cake from the list
                        o.removeObj(objList.get(0).getFachnummer());
                        // Notify other threads that are waiting on the lock
                        lock.notifyAll();
                    } catch (Exception e) {
                        // Handle the exception
                    } finally {
                        // Release the lock
                        lock.unlock();
                    }
                }
            }
        };

        // Create a new thread for inspecting a random cake
        Thread threadInspect = new Thread(() -> {
            Random rnd = new Random();
            while (true) {
                // Acquire the lock
                lock.lock();
                try {
                    // Get the list of cakes in the database
                    List<Obj> objList = o.getObjList();
                    // Select a random cake from the list
                    Obj cake = objList.get(rnd.nextInt(objList.size()));
                    // Modify the inspection date of the cake
                    cake.setInspektionsdatum(new Date());
                } finally {
                    // Release the lock
                    lock.unlock();
                }
            }
        });

        // Create two threads for adding and removing cakes
        Thread threadAdd = new Thread(s);
        Thread threadRemove = new Thread(sr);

        // Start the threads
        threadAdd.start();
        threadRemove.start();
        threadInspect.start();
    }
}
