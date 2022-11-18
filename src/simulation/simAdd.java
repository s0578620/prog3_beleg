package simulation;

import GL.objDatabase;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class simAdd implements Runnable{


    private Lock lock;
    private objDatabase oDB;

    private String Kuchentyp = "Kremkuchen";
    private String Hersteller = "Hersteller1";

    private BigDecimal Preis = new BigDecimal(200);
    private int Nahrwert = 1000;
    private int Haltbarkeit = 20;
    private LinkedList<Allergen> list = new LinkedList<>();
    private String Topping = "Sahne";


    public simAdd(objDatabase oDB, Lock lock){
        this.oDB = oDB;
        this.lock = lock;
        oDB.addHersteller(Hersteller);
        oDB.addObj(Kuchentyp,Hersteller,Preis,Nahrwert,Haltbarkeit,list,Topping);
        oDB.addObj(Kuchentyp,Hersteller,Preis,Nahrwert,Haltbarkeit,list,Topping);
        oDB.addObj(Kuchentyp,Hersteller,Preis,Nahrwert,Haltbarkeit,list,Topping);
    }

    @Override
    public void run() {

            do {
                //oDB.addObj(Kuchentyp,Hersteller,Preis,Nahrwert,Haltbarkeit,list,Topping);
              synchronized (lock) {
                  try {
                      // lock.wait();
                      oDB.addObj(Kuchentyp, Hersteller, Preis, Nahrwert, Haltbarkeit, list, Topping);
                  } catch (Exception e) {

                  }
              }
            } while (true);

      }
}
