package IO;

import GL.ObjDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {


    private static final String TEST_FILE = "JOS";
    private FileSystem fileSystem;
    private ObjDatabase oDB;
    private LinkedList<Allergen> list;

    @BeforeEach
    public void setUp() {
        oDB = new ObjDatabase(10);
        fileSystem = new FileSystem(oDB);
    }

    @Test
    public void testSaveLoadJOS() throws Exception {
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
        oDB.addHersteller("Hersteller1");
        oDB.addObj("Kremkuchen", "Hersteller1", new BigDecimal("4.99"), 500, 7, list, "Sahne");
        oDB.addObj("Obstkuchen", "Hersteller1", new BigDecimal("3.99"), 300, 5, list, "Zimt");

        // Save the database to a JOS file
        String result = fileSystem.saveDB(TEST_FILE);
        assertEquals("db saved", result);

        // Clear the database and load it from the JOS file
        oDB = new ObjDatabase(10);
        fileSystem = new FileSystem(oDB);
        result = fileSystem.loadDB(TEST_FILE);
        assertEquals("db loaded", result);

        // Verify that the loaded database contains the expected data
        assertEquals(1, oDB.getHerstellerList().size());
        assertEquals(2, oDB.getObjList().size());
        assertEquals(2, oDB.getAllergenList().size());
        assertEquals("Hersteller1", oDB.getHerstellerList().get(0).getName());
        assertEquals("Kremkuchen", oDB.getObjList().get(0).getKuchentyp());
        assertEquals(new BigDecimal("4.99"), oDB.getObjList().get(0).getPreis());
        assertEquals(500, oDB.getObjList().get(0).getNaehrwert());
        //assertEquals(7, objDatabase.getObjList().get(0).getHaltbarkeit().toDays());
        assertEquals(2, oDB.getObjList().get(0).getAllergene().size());
      //  assertEquals(Allergen.Gluten, objDatabase.getObjList().get(0).getAllergene().get(0));
        assertEquals("Obstkuchen", oDB.getObjList().get(1).getKuchentyp());
        assertEquals(new BigDecimal("3.99"), oDB.getObjList().get(1).getPreis());
        assertEquals(300, oDB.getObjList().get(1).getNaehrwert());
      //  assertEquals(5, objDatabase.getObjList().get(1).getHaltbarkeit().toDays());
        assertEquals(2, oDB.getObjList().get(1).getAllergene().size());
        //assertEquals(Allergen.Erdnuss, objDatabase.getObjList().get(1).getAllergene().get(0));
    }
    @Test
    void testSaveDBInvalidFilename() {
        Assertions.assertEquals("invalid choice", fileSystem.saveDB("invalidFilename"));
    }

    @Test
    void testLoadDBWithInvalidFilename() {
        assertEquals("invalid choice", fileSystem.loadDB("invalidFilename"));
    }
}



