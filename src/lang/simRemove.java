package lang;

import GL.objDatabase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class simRemove implements Runnable{


    private Lock lock;
    private objDatabase oDB;


    public simRemove(objDatabase oDB, Lock lock){
        this.oDB = oDB;
        this.lock = lock;

    }

    @Override
    public void run() {
        synchronized (lock) {
            do {
                System.out.println("Removing THREAD");
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e){}
            } while (true);
        }
    }
}
