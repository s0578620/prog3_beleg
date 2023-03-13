package Simulation;

import GL.Hersteller;
import GL.Obj;
import GL.ObjDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ObjDatabaseObserverSimOne;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimOneTest {

    private ObjDatabase o;
    private Lock lock;
    private String Hersteller;
    private LinkedList<Allergen> list;

    @BeforeEach
    public void setup() {
        o = new ObjDatabase(10);
        o.addObserver(new ObjDatabaseObserverSimOne(o));
        lock = new ReentrantLock();
        this.Hersteller = "NESTLE";
        this.list = new LinkedList<>();
        o.addHersteller(Hersteller);
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
    }

    @Test
    public void testDeterministicSimulation() {
        // Define a list of predetermined objects to add and remove
        List<Obj> objs = new ArrayList<>(Arrays.asList(
                new Obj("Kremkuchen", new Hersteller(Hersteller), new BigDecimal(200), 150, 20,new Date(),list,new Date()),
                new Obj("Obstkuchen", new Hersteller(Hersteller), new BigDecimal(200), 500, 20, new Date(),list,new Date()),
                new Obj("Kremkuchen", new Hersteller(Hersteller), new BigDecimal(200), 700, 20, new Date(),list,new Date()),
                new Obj("Obstkuchen", new Hersteller(Hersteller), new BigDecimal(200), 1000, 20, new Date(),list,new Date())
        ));

        // Add the predetermined objects to the database
        for (Obj obj : objs) {
          //  o.addObj(obj.getKuchentyp(), obj.getHersteller().getName(), obj.getPreis(), obj.getNaehrwert(), obj.getHaltbarkeit(), obj.getAllergene());
        }

        // Remove the predetermined objects from the database
        for (Obj obj : objs) {
            o.removeObj(obj.getFachnummer());
        }

        // Check that the number of added and removed objects is equal to the number of predetermined objects
        Assertions.assertEquals(objs.size(), o.getObjList().size(), "Number of added objects should match the number of predetermined objects");
        Assertions.assertEquals(0, o.getObjList().size(), "Number of removed objects should match the number of predetermined objects");
    }
}
