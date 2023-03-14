package IO;

import CLI.Console;
import GL.ObjDatabase;
import Routing.Handler.Handler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vertrag.Allergen;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FileSystemTest {


    private static final String TEST_FILE = "JOS";

    private FileSystem fileSystem;
    private ObjDatabase objDatabase;

    private LinkedList<Allergen> list;

    @BeforeEach
    public void setUp() {
        objDatabase = new ObjDatabase(10);
        fileSystem = new FileSystem(objDatabase);
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
    }

    @AfterEach
    public void tearDown() throws Exception {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveLoadJOS() throws Exception {
        // Add some data to the database
        objDatabase.addHersteller("Hersteller1");
        objDatabase.addObj("Kremkuchen", "Hersteller1", new BigDecimal("4.99"), 500, 7, list, "Sahne");
        objDatabase.addObj("Obstkuchen", "Hersteller1", new BigDecimal("3.99"), 300, 5, list, "Zimt");

        // Save the database to a JOS file
        String result = fileSystem.saveDB(TEST_FILE);
        assertEquals("db saved", result);

        // Clear the database and load it from the JOS file
        objDatabase.getObjList().clear();
        result = fileSystem.loadDB(TEST_FILE);
        assertEquals("db loaded", result);

        // Verify that the loaded database contains the expected data
        assertEquals(1, objDatabase.getHerstellerList().size());
        assertEquals(2, objDatabase.getObjList().size());
        assertEquals(2, objDatabase.getAllergenList().size());
        assertEquals("Hersteller1", objDatabase.getHerstellerList().get(0).getName());
        assertEquals("Kremkuchen", objDatabase.getObjList().get(0).getKuchentyp());
        assertEquals(new BigDecimal("4.99"), objDatabase.getObjList().get(0).getPreis());
        assertEquals(500, objDatabase.getObjList().get(0).getNaehrwert());
        //assertEquals(7, objDatabase.getObjList().get(0).getHaltbarkeit().toDays());
        assertEquals(2, objDatabase.getObjList().get(0).getAllergene().size());
      //  assertEquals(Allergen.Gluten, objDatabase.getObjList().get(0).getAllergene().get(0));
        assertEquals("Obstkuchen", objDatabase.getObjList().get(1).getKuchentyp());
        assertEquals(new BigDecimal("3.99"), objDatabase.getObjList().get(1).getPreis());
        assertEquals(300, objDatabase.getObjList().get(1).getNaehrwert());
      //  assertEquals(5, objDatabase.getObjList().get(1).getHaltbarkeit().toDays());
        assertEquals(2, objDatabase.getObjList().get(1).getAllergene().size());
        //assertEquals(Allergen.Erdnuss, objDatabase.getObjList().get(1).getAllergene().get(0));
    }
}



