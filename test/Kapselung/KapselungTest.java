package Kapselung;

import GL.Hersteller;
import GL.Obj;
import GL.ObjDatabase;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KapselungTest {

    private ObjDatabase oDB;
    private LinkedList<Allergen> list;
    private BigDecimal Preis;


    @Test
    public void KapselungHerstellerListTest() {
        this.oDB = new ObjDatabase(10);
        this.list = new LinkedList<>();
        oDB.addHersteller("Hersteller1");
        oDB.addHersteller("Hersteller2");
        LinkedList<Hersteller> tempList = oDB.getHerstellerList();
        tempList.clear();
        LinkedList<Hersteller> resultList = oDB.getHerstellerList();
        assertEquals(2, resultList.size());
    }

    @Test
    public void KapselungObjListTest() {
        this.oDB = new ObjDatabase(10);
        this.Preis = new BigDecimal("19.99");
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        oDB.addHersteller("Hersteller1");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        LinkedList<Obj> tempList = oDB.getObjList();
        tempList.clear();
        LinkedList<Obj> resultList = oDB.getObjList();
        assertEquals(2, resultList.size());
    }

    @Test
    public void KapselungAllergeneListTest() {
        this.oDB = new ObjDatabase(10);
        this.Preis = new BigDecimal("19.99");
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        oDB.addHersteller("Hersteller1");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        LinkedList<Allergen> tempList = oDB.getAllergenList();
        tempList.clear();
        LinkedList<Allergen> resultList = oDB.getAllergenList();
        assertEquals(1, resultList.size());
    }

    @Test
    public void KapselungRemoveObjAndUpdateAllergeneListTest() {
        this.oDB = new ObjDatabase(10);
        this.Preis = new BigDecimal("19.99");
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        oDB.addHersteller("Hersteller1");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.removeObj(0);
        LinkedList<Allergen> resultList = oDB.getAllergenList();
        assertEquals(0, resultList.size());
    }

    @Test
    public void KapselungRemoveObjAndUpdateOtherFachnummern() {
        this.oDB = new ObjDatabase(10);
        this.Preis = new BigDecimal("19.99");
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        oDB.addHersteller("Hersteller1");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.removeObj(0);
        assertEquals(0, oDB.getObjList().get(0).getFachnummer());
    }
}
