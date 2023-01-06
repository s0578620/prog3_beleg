package Observer;

import GL.ObjDatabase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import util.ObjDatabaseObserver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjDatabaseObserverTest {

    @Test
    public void testUpdate() {
//        // Create a mock ObjDatabase
//
//        ObjDatabase mockDB = new ObjDatabase() {
//            private int capacityMax = 10;
//            private List<Object> objList = new ArrayList<>();
//
//            @Override
//            public int getCapacityMax() {
//                return capacityMax;d
//            }
//
//            @Override
//            public List<Object> getObjList() {
//                return objList;
//            }
//        };
//
//        // Create an ObjDatabaseObserver with the mock ObjDatabase
//        ObjDatabaseObserver observer = new ObjDatabaseObserver(mockDB);
//
//        // Set up a test PrintStream to capture the output of the observer
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        // Add 9 objects to the mock database to trigger the "90% capacity reached" message
//        for (int i = 0; i < 9; i++) {
//            mockDB.getObjList().add(new Object());
//        }
//        // Notify the observer of the update
//        mockDB.notifyObservers();
//
//        // Assert that the output of the observer is as expected
//        assertTrue(outContent.toString().contains("90% capacity reached"));
    }
}
