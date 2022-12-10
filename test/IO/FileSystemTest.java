package IO;

import GL.ObjDatabase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.io.File;
import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {
    private ObjDatabase oDB;
    private FileSystem fileSystem;
    private String kuchentyp;
    private String hersteller;
    private BigDecimal preis;
    private int naehrwert;
    private int haltbarkeit;
    private LinkedList<Allergen> list;
    private String topping;

    @BeforeEach
    void setup(){
        this.oDB = new ObjDatabase(10);
        this.fileSystem = new FileSystem(oDB);
        this.kuchentyp = "Kremkuchen";
        this.hersteller = "NESTLE";
        this.preis = new BigDecimal("19.99");
        this.naehrwert = 150;
        this.haltbarkeit = 36;
        this.list = new LinkedList<>();
        this.topping = "Nuss";
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
        oDB.addHersteller(hersteller);
        oDB.addObj(kuchentyp,hersteller,preis,naehrwert,haltbarkeit,list,topping);
    }

    @AfterAll
    static void removeTestfiles(){
        deleteFileIfExists("junitJOS");
    }

    @Test
    void josObjectTest() {
        fileSystem.setJos("junitJOS");
        fileSystem.saveDB("jos");

        oDB.removeObj(0);
        assertEquals(0, oDB.getObjList().size());

        fileSystem.loadDB("jos" );
        assertEquals(1, oDB.getObjList().size());
    }

    @Test
    void josHerstellerTest() {
        fileSystem.setJos("junitJOS");
        fileSystem.saveDB("jos");

        oDB.removeHersteller(hersteller);
        assertEquals(0, oDB.getHerstellerList().size());

        fileSystem.loadDB("jos" );
        assertEquals(1, oDB.getHerstellerList().size());
    }

    @Test
    void josAllergeneTest() {
        fileSystem.setJos("junitJOS");
        fileSystem.saveDB("jos");

        oDB.removeObj(0);
        assertEquals(0, oDB.getAllergenList().size());

        fileSystem.loadDB("jos" );
        assertEquals(2, oDB.getAllergenList().size());
    }

    private static void deleteFileIfExists(String filename) {
        File file = new File(filename);
        if (file.exists()){
            file.delete();
        }
    }
}