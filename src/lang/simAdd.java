package lang;

import GL.objDatabase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class simAdd implements Runnable{


    private Lock lock;
    private objDatabase oDB;

    public simAdd(objDatabase oDB, Lock lock){
        this.oDB = oDB;
        this.lock = lock;

    }

    @Override
    public void run() {

        synchronized (lock) {
            do {
                System.out.println("ADDING THREAD");
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException ex) { }
            } while (true);
        }
//        while (true){
//            try {
//
//
//
////                lock.lock();
////                System.out.println("Adding Thread");
////
////                full.signal();
////                removing.await();
//
//            }catch (IllegalArgumentException e ){
//
//            } catch (InterruptedException e) {
//
//            } finally {
//                lock.unlock();
//                System.out.println("UNLOCK");
//            }
//
//        }
//
      }
}
