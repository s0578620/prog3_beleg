package Simulation;

import GL.ObjDatabase;

import java.util.concurrent.TimeUnit;

public class SimThreeShow implements Runnable{
    private final ObjDatabase oDB;
    private final long interval;
    private final String threadName = "Show Thread";

    public SimThreeShow(ObjDatabase oDB, long interval) {
        this.oDB = oDB;
        this.interval = interval;
    }

    @Override
    public void run() {
        do {
            System.out.print(threadName + ": Current state of database:");
            //System.out.println(oDB.showKuchenAll());
            System.out.println(oDB.getCapacityAct());
            try {
                TimeUnit.MILLISECONDS.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
