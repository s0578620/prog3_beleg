package GL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class objDatabaseTest {
    private ObjDatabase o;
    private String Kuchentyp;
    private String Kuchentyp1;
    private String Hersteller;
    private String Hersteller1;
    private BigDecimal Preis;
    private int Naehrwert;
    private Instant start;
    private Instant end;
    private int Haltbarkeit;
    private LinkedList<Allergen> list;
    private String Topping;
    private String Toppin2;
    private String Obsttorte;

    @BeforeEach
    void setup(){
        this.o = new ObjDatabase(10);
        this.Kuchentyp = "Kremkuchen";
        this.Kuchentyp1 = "Obstkuchen";
        this.Hersteller = "NESTLE";
        this.Hersteller1 = "UNILEVER";
        this.Preis = new BigDecimal("19.99");
        this.Naehrwert = 150;
        this.start = Instant.parse("2017-10-03T10:10:30.00Z");
        this.end = Instant.parse("2017-10-03T10:16:30.00Z");
        this.Haltbarkeit = 36; //Duration.between(start,end)
        this.list = new LinkedList<>();
        this.Topping = "Apfel";
        this.Toppin2 = "Nusscreme";
        this.Obsttorte = "Obsttorte";
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
        o.addHersteller(Hersteller);
    }

    @Test
    void addHersteller() {
        o.addHersteller(Hersteller1);

        assertEquals(2,o.getHerstellerList().size());
    }

    @Test
    void removeHersteller(){
        o.removeHersteller(Hersteller);
        assertEquals(0,o.getHerstellerList().size());
    }


    @Test
    void addHerstellerDuplicat() {
        o.addHersteller(Hersteller);

        // Size == 1 da Hersteller bereits im @BeforeEach hinzugefügt wurde // Size == 2 wenn doppelte Hersteller möglich
        assertEquals(1, o.getHerstellerList().size());
    }

    @ParameterizedTest
    @CsvSource({"Kremkuchen,NESTLE,350,NussCreme,1",//testet das hinzufügen von Kremkuchen
            "Obstkuchen,UNILEVER,240,Erdbeeren,1",  //testet das hinzufügen von Obstkuchen
            "Haribo,UNILEVER,240,Erdbeeren,0",  //testet das hinzufügen von ungültigen Artikeln
            "Obstkuchen,UNI,240,Erdbeeren,0"})      //testet auf hinzufügen eines Kuchens mit unbekanntem Hersteller
    void addObj(String iKuchentyp,String iHersteller,int iNaehrwert, String iTopping,int size) {

        o.addHersteller(Hersteller1);
        o.addObj(iKuchentyp,iHersteller,Preis,iNaehrwert,Haltbarkeit,list, iTopping);

        assertEquals(size,o.getObjList().size());
    }

    @Test
    void addObj(){  // testet das hinzufügen von Obsttorte

        o.addObj(Obsttorte,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping,Toppin2);

        assertEquals(1,o.getObjList().size());
    }

    @Test
    void addObjMulti(){  // testet das hinzufügen von mehreren Kuchen

        o.addObj(Obsttorte,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping,Toppin2);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(2,o.getObjList().size());
    }
    @Test
    void addObjFachnummer(){  // testet beim hinzufügen von Kuchen die Fachnummervergabe

        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(1,o.getObjList().get(1).getFachnummer());
    }
    @Test
    void addObjDate(){  // testet beim hinzufügen von Kuchen die Vergabe des Einfügedatums

        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        // Da Inspektionsdatum und insertDate gleichzeitig vergeben wird hier auf Existenz und Gleichheit geprüft
        assertEquals(o.getObjList().getFirst().getInspektionsdatum(), o.getObjList().getFirst().getInsertDate());
    }

    @Test
    void removeObj() {  // Überprüfung der Fachnummerkorrektion durch 0

        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.removeObj(0);

        // Aufgrund der Neuzuordnung der Fachnummern nach removeObj() muss das nachrückende Obj die Fachnummer 0 besitzen
        assertEquals(0,o.getObjList().get(0).getFachnummer());
    }

    @Test
    void removeObjSizeTest() {

        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.removeObj(0);

        assertEquals(1, o.getObjList().size());
    }

    @Test
    void updateInsp(){

        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertTrue(o.updateInsp(0));
    }

    @Test
    void showAllCakesSortedByHerrsteller() {

        o.addHersteller(Hersteller1);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller1,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        // Expected:5 weil +1 pro Hersteller an Listeneinträgen (Hersteller Gesamtaus)
        assertEquals(5, (long) o.showAllCakesSortedByHersteller().size());
    }

    @Test
    void showKuchenAll(){

        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Obsttorte,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping,Toppin2);

        assertEquals(3,o.showKuchenAll().size());
    }

    @Test
    void showKuchen(){

        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);
        o.addObj(Kuchentyp1,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(1,o.showKuchen(Kuchentyp).size());
    }

    @Test
    void showAllergene(){
        o.addObj(Kuchentyp,Hersteller,Preis,Naehrwert,Haltbarkeit,list, Topping);

        assertEquals(2,o.showAllergene(false).size());
    }
}