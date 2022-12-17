package simulation;

import GL.ObjDatabase;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class SimTwoAdd implements Runnable{

    private final Lock lock;
    private final ObjDatabase oDB;
    private final String[] typ = new String[]{"Kremkuchen","Obstkuchen"};
    private final String Hersteller = "Hersteller1";
    private final BigDecimal Preis = new BigDecimal(200);
    private final int[] naehrwert = {150,500,700,1000};
    private final int Haltbarkeit = 20;
    private final LinkedList<Allergen> list = new LinkedList<>();
    private final String Topping = "Sahne";


    public SimTwoAdd(ObjDatabase oDB, Lock lock){
        this.oDB = oDB;
        this.lock = lock;
        oDB.addHersteller(Hersteller);
    }

    @Override
    public void run() {
        Random rnd = new Random();
        do {
            synchronized (lock) {
                try {
                    oDB.addObj(typ[rnd.nextInt(typ.length)], Hersteller, Preis, naehrwert[rnd.nextInt(naehrwert.length)], Haltbarkeit, list, Topping);
                } catch (Exception e) {}
            }
        } while (true);
    }
}
