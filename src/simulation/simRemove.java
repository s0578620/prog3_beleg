package simulation;

import GL.objDatabase;

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class simRemove implements Runnable{

    private Random rnd;
    private final Lock lock;
    private final objDatabase oDB;


    public simRemove(objDatabase oDB, Lock lock){
        this.oDB = oDB;
        this.lock = lock;
        this.rnd = new Random();
    }

    @Override
    public void run() {
            do {
                synchronized (lock) {
                    oDB.removeObj(rnd.nextInt(oDB.getObjList().size()+1));
                    try {
                        oDB.removeObj(rnd.nextInt(oDB.getObjList().size()));
                    } catch (Exception e) {}
                }
            } while (true);
        }
}
