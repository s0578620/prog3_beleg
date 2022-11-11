package lang;

import GL.objDatabase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class simRemove implements Runnable{

    private Condition removing;
    private Condition full;
    private Lock lock;
    private objDatabase oDB;


    public simRemove(objDatabase oDB, Lock lock, Condition full, Condition removing){
        this.oDB = oDB;
        this.lock = lock;
        this.full = full;
        this.removing = removing;
    }

    @Override
    public void run() {

        while (true){
            try {
                lock.lock();
                full.await();


                System.out.println("Adding Thread");

                removing.signal();

            }catch (IllegalArgumentException e ){

            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
                System.out.println("UNLOCK");
            }

//            System.out.println("Thread ADDING");
//            try {
//                sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}
