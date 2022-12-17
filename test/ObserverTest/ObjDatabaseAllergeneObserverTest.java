package ObserverTest;


import GL.ObjDatabase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import util.ObjDatabaseAllergeneObserver;
import vertrag.Allergen;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ObjDatabaseAllergeneObserverTest {
    private ObjDatabase oDB;
    private ObjDatabaseAllergeneObserver oDBAO;

    private LinkedList<Allergen> allergeneTestList;

    @BeforeEach
    public void setUp() {
        // Create a new ObjDatabase object and add some allergens to it
        oDB = new ObjDatabase();
        allergeneTestList = new LinkedList<>();
        allergeneTestList.add(Allergen.valueOf("Erdnuss"));
        oDB.readInAllergens(allergeneTestList);

        oDBAO = new ObjDatabaseAllergeneObserver(oDB);
    }

    @Test
    public void testUpdate() {      // Todo ask

        oDB.readInAllergens(allergeneTestList);
        allergeneTestList.add(Allergen.valueOf("Gluten"));
        oDB.readInAllergens(allergeneTestList);
        oDBAO.update(oDB, null);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("", outContent.toString());
    }

    @AfterEach
    public void tearDown() {
        oDB = null;
        oDBAO = null;
    }
}



//
//public class ObjDatabaseAllergeneObserverTest {
//
//    @Test
//    public void testUpdate() {
//        // Create a mock ObjDatabase object
//        ObjDatabase mockDB = Mockito.mock(ObjDatabase.class);
//
//        // Set the initial size of the allergen list to 2
//        when(mockDB.getAllergenList().size()).thenReturn(2);
//
//        // Create a new ObjDatabaseAllergeneObserver object and pass the mock DB to its constructor
//        ObjDatabaseAllergeneObserver observer = new ObjDatabaseAllergeneObserver(mockDB);
//        mockDB.addObserver(observer);
//        // Call the update() method of the observer and pass the mock DB as the Observable object
//        observer.update(mockDB, null);
//
//        // Verify that the update() method was called on the observer
//        Mockito.verify(observer).update(mockDB, null);
//
//        // Set the size of the allergen list to 4
//        when(mockDB.getAllergenList().size()).thenReturn(4);
//
//        // Call the update() method again
//        observer.update(mockDB, null);
//
//        // Verify that the update() method was called again
//        Mockito.verify(observer, Mockito.times(2)).update(mockDB, null);
//    }
//}
