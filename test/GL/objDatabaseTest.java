package GL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class objDatabaseTest {
    private ObjDatabase o;
    private String Kuchentyp;
    private String Kuchentyp1;
    private String Hersteller;
    private String Hersteller1;
    private BigDecimal Preis;
    private int Naehrwert;
    private int Haltbarkeit;
    private LinkedList<Allergen> list;
    private String Topping;
    private String Toppin2;
    private String Obsttorte;

    @BeforeEach
    public void setup(){
        this.o = new ObjDatabase(10);
        this.Kuchentyp = "Kremkuchen";
        this.Kuchentyp1 = "Obstkuchen";
        this.Hersteller = "NESTLE";
        this.Hersteller1 = "UNILEVER";
        this.Preis = new BigDecimal("19.99");
        this.Naehrwert = 150;
        this.Haltbarkeit = 36;
        this.list = new LinkedList<>();
        this.Topping = "Apfel";
        this.Toppin2 = "Nusscreme";
        this.Obsttorte = "Obsttorte";
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
    }

    @Test
    public void addHersteller() {
        o.addHersteller(Hersteller1);

        assertEquals(1,o.getHerstellerList().size());
    }

    @Test
    public void removeHersteller(){
        o.removeHersteller(Hersteller);
        assertEquals(0,o.getHerstellerList().size());
    }


    @Test
    void addHerstellerDuplicat() {
        o.addHersteller(Hersteller);
        o.addHersteller(Hersteller);

        assertEquals(1, o.getHerstellerList().size());
    }

    @ParameterizedTest
    @CsvSource({"Kremkuchen,NESTLE,350,NussCreme,1",//testet das hinzufügen von Kremkuchen
            "Obstkuchen,UNILEVER,240,Erdbeeren,1",  //testet das hinzufügen von Obstkuchen
            "Haribo,UNILEVER,240,Erdbeeren,0",  //testet das hinzufügen von ungültigen Artikeln
            "Obstkuchen,UNI,240,Erdbeeren,0"})      //testet auf hinzufügen eines Kuchens mit unbekanntem Hersteller
    void addObj(String iKuchentyp,String iHersteller,int iNaehrwert, String iTopping,int size) {
        o.addHersteller(Hersteller);
        o.addHersteller(Hersteller1);
        o.addObj(iKuchentyp,iHersteller,Preis,iNaehrwert,Haltbarkeit,list, iTopping);

        assertEquals(size,o.getObjList().size());
    }

    @Test
    void addObj(){  // testet das hinzufügen von Obsttorte
        o.addHersteller(Hersteller);
        o.addObj(Obsttorte,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping,Toppin2);

        assertEquals(1,o.getObjList().size());
    }

    @Test
    void addObjMulti(){  // testet das hinzufügen von mehreren Kuchen
        o.addHersteller(Hersteller);
        o.addObj(Obsttorte,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping,Toppin2);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(2,o.getObjList().size());
    }
    @Test
    void addObjFachnummer(){  // testet beim hinzufügen von Kuchen die Fachnummervergabe
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(1,o.getObjList().get(1).getFachnummer());
    }
    @Test
    void addObjDate(){  // testet beim hinzufügen von Kuchen die Vergabe des Einfügedatums
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        // Da Inspektionsdatum und insertDate gleichzeitig vergeben wird hier auf Existenz bzw Gleichheit geprüft
        assertEquals(o.getObjList().getFirst().getInspektionsdatum(), o.getObjList().getFirst().getInsertDate());
    }

    @Test
    void removeObj() {  // Überprüfung der Fachnummerkorrektion durch 0
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.removeObj(0);

        // Aufgrund der Neuzuordnung der Fachnummern nach removeObj() muss das nachrückende Obj die Fachnummer 0 besitzen
        assertEquals(0,o.getObjList().get(0).getFachnummer());
    }

    @Test
    void removeObjSizeTest() {
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.removeObj(0);

        assertEquals(1, o.getObjList().size());
    }

    @Test
    void updateInsp(){
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertTrue(o.updateInsp(0));
    }

    @Test
    public void testShowAllCakesSortedByHersteller() {
        o.addHersteller(Hersteller);
        o.addHersteller(Hersteller1);
        o.addObj(Kuchentyp, Hersteller, Preis, Naehrwert, Haltbarkeit, list, Topping);
        o.addObj(Kuchentyp1, Hersteller, Preis, Naehrwert, Haltbarkeit, list, Topping);
        o.addObj(Kuchentyp, Hersteller1, Preis, Naehrwert, Haltbarkeit, list, Topping);

        List<String> expectedList = new LinkedList<>();
        expectedList.add("Result -- NESTLE Kremkuchen");
        expectedList.add("Result -- NESTLE Obstkuchen");
        expectedList.add("Result -- UNILEVER Kremkuchen");
        expectedList.add("Hersteller: NESTLE besitzt Kuchen: 2");
        expectedList.add("Hersteller: UNILEVER besitzt Kuchen: 1");

        assertEquals(expectedList, o.showAllCakesSortedByHersteller());
    }

    @Test
    void showKuchenAll(){
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Obsttorte,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping,Toppin2);

        assertEquals(3,o.showKuchenAll().size());
    }

    @Test
    void showKuchen(){
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(1,o.showKuchen(Kuchentyp).size());
    }

    @Test
    void showAllergeneInclusive(){
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(2,o.showAllergene(true).size());
    }

    @Test
    void showAllergeneExclusive(){
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        List<String> showlist = o.showAllergene(false);
        //showlist.forEach(System.out::println);
        assertTrue(showlist.contains("Haselnuss") && showlist.contains("Sesamsamen"));
    }

    @Test
    void formatTestEmptyCollection() {
        Collection<Integer> c = new ArrayList<>();
        String expected = "[]";
        String actual = o.format(c);

        assertEquals(expected, actual);
    }
    @Test
    void testSingleItemCollection() {
        Collection<Integer> c = Arrays.asList(1);
        String expected = "[1]";
        String actual = o.format(c);

        assertEquals(expected, actual);
    }
    @Test
    void testMultiItemCollection() {
        Collection<Integer> c = Arrays.asList(1, 2, 3);
        String expected = "[1 2 3]";
        String actual = o.format(c);

        assertEquals(expected, actual);
    }

    @Test
    void testSetFachnummer() {
        o.addHersteller(Hersteller);
        Obj obj1 = new Obj(Kuchentyp,new Hersteller(Hersteller),Preis,Naehrwert,Haltbarkeit,new Date(),list,new Date());
        Obj obj1WithFachnummer = o.setFachnummer(obj1);

        assertEquals(0, obj1WithFachnummer.getFachnummer());
    }

    @Test
    void testReadInAllergens() {
        Collection<Allergen> allergens = Arrays.asList(
                Allergen.Erdnuss,
                Allergen.Gluten,
                Allergen.Erdnuss
        );
        LinkedList<Allergen> expected = new LinkedList<>(Arrays.asList(
                Allergen.Erdnuss,
                Allergen.Gluten
        ));

        o.readInAllergens(allergens);

        assertEquals(expected, o.getAllergenList());
    }

    @Test
    void testSwitchObjDatabaseCapacityMax() {
        ObjDatabase db1 = new ObjDatabase(5);
        ObjDatabase db2 = new ObjDatabase(10);

        db2.switchObjDatabase(db1);

        assertEquals(5, db2.getCapacity());
    }
    @Test
    void testSwitchObjDatabaseCapacityAct() {
        ObjDatabase db1 = new ObjDatabase(5);
        ObjDatabase db2 = new ObjDatabase(10);
        db1.addHersteller(Hersteller);
        db1.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        db2.switchObjDatabase(db1);
        assertEquals(db1.getCapacityAct(), db2.getCapacityAct());
    }
    @Test
    void testSwitchObjDatabaseHersteller() {
        ObjDatabase db1 = new ObjDatabase(5);
        ObjDatabase db2 = new ObjDatabase(10);
        db1.addHersteller(Hersteller);

        db2.switchObjDatabase(db1);

        assertEquals(db1.getHerstellerList(), db2.getHerstellerList());
    }
    @Test
    void testSwitchObjDatabaseObj() {   // TODO auseinanderziehen
        ObjDatabase db1 = new ObjDatabase(5);
        ObjDatabase db2 = new ObjDatabase(10);
        db1.addHersteller(Hersteller);
        db1.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        db2.switchObjDatabase(db1);

        assertEquals(db1.getObjList(), db2.getObjList());
        assertEquals(db1.getAllergenList(), db2.getAllergenList());
    }
    @Test
    void testSwitchObjDatabaseAllergene() { // TODO auseinanderziehen
        ObjDatabase db1 = new ObjDatabase(5);
        ObjDatabase db2 = new ObjDatabase(10);
        db1.addHersteller(Hersteller);
        db1.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        db2.switchObjDatabase(db1);
        assertEquals(db1.getCapacityAct(), db2.getCapacityAct());
        assertEquals(db1.getAllergenList(), db2.getAllergenList());
    }

    @Test
    void testStringForShowKuchen() {
        Obj obj1 = new Obj(Kuchentyp,new Hersteller(Hersteller),Preis,Naehrwert,Haltbarkeit,new Date(),list,new Date());

        String result = o.stringForShowKuchen(obj1);
        assertEquals("Typ: Kremkuchen| Hersteller: NESTLE| Allergene: [Erdnuss Gluten]| Nährwert: 150| Haltbarkeit: 36", result);
    }

    @Test
    void testRefreshAllergeneList() {
        o.addHersteller(Hersteller);
        o.addObj(Kuchentyp, Hersteller, Preis, Naehrwert, Haltbarkeit, list, Topping);
        o.refreshAllergeneList();

        assertEquals(2, o.getAllergenList().size());
    }


}