package Simulation;

import GL.ObjDatabase;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SimThreeAdd implements Runnable{
private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private final ObjDatabase oDB;
    private final String[] typ = new String[]{"Kremkuchen", "Obstkuchen"};
    private final String Hersteller = "Hersteller1";
    private final BigDecimal Preis = new BigDecimal(200);
    private final int[] naehrwert = {150, 500, 700, 1000};
    private final int Haltbarkeit = 20;
    private final LinkedList<Allergen> allergenList = new LinkedList<>();
    private final String Topping = "Sahne";
    private final String threadName = "Add Thread";
    private final int interval;


    private final int[] allergenIndices = {0, 1, 2};
    private int allergenIndexCounter = 0;
    private int typCounter = 0;
    private int naehrwertCounter = 0;

    public SimThreeAdd(ObjDatabase oDB, Lock lock, Condition notFull, Condition notEmpty, int interval) {
        this.oDB = oDB;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
        this.interval = interval;
        oDB.addHersteller(Hersteller);
    }

//    @Override
//    public void run() {
//        Random rnd = new Random();
//        do {
//            try {
//                lock.lock();
//                while (oDB.getObjList().size() >= oDB.getCapacity()) {
//                    notFull.await();
//                }
//                System.out.println(threadName + ": Adding new object to database.");
//                allergenList.clear();
//                int numAllergens = rnd.nextInt(3);
//                for (int i = 0; i < numAllergens; i++) {
//                    allergenList.add(Allergen.values()[rnd.nextInt(Allergen.values().length)]);
//                }
//                oDB.addObj(typ[rnd.nextInt(typ.length)], Hersteller, Preis, naehrwert[rnd.nextInt(naehrwert.length)], Haltbarkeit, allergenList, Topping);
//                notEmpty.signalAll();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//
//            try {
//                Thread.sleep(interval);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } while (true);
//    }
@Override
public void run() {
    do {
        try {
            lock.lock();
            while (oDB.getObjList().size() >= oDB.getCapacity()) {
                notFull.await();
            }
            System.out.println(threadName + ": Adding new object to database.");
            allergenList.clear();
            int numAllergens = allergenIndices[allergenIndexCounter % allergenIndices.length];
            for (int i = 0; i < numAllergens; i++) {
                allergenList.add(Allergen.values()[i]);
            }
            oDB.addObj(typ[typCounter % typ.length], Hersteller, Preis, naehrwert[naehrwertCounter % naehrwert.length], Haltbarkeit, allergenList, Topping);
            notEmpty.signalAll();

            allergenIndexCounter++;
            typCounter++;
            naehrwertCounter++;
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
    } while (true);
}
}
