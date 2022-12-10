package ObserverTest;

import GL.ObjDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import util.ObjDatabaseObserver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObjDatabaseObserverTest {
    private ObjDatabaseObserver oDBO;

    @Mock
    private ObjDatabase oDB;

    @BeforeEach
    public void setUp() {
        // Create a new ObjDatabase object with a maximum capacity of 10
        oDB = mock(ObjDatabase.class);

        // Create a new ObjDatabaseObserver object and pass the ObjDatabase object to it
        oDBO = new ObjDatabaseObserver(oDB);
        oDB.addObserver(oDBO);

        when(oDB.getCapacityMax()).thenReturn(10);


        oDB.addHersteller("ABC Bakery");
    }

    @Test
    public void testUpdate() {
        // Add objects to the ObjDatabase until it reaches 90% of its maximum capacity
        for (int i = 0; i < 9; i++) {
            oDB.addObj("Kremkuchen", "ABC Bakery", BigDecimal.ZERO, 100, 5, Collections.emptyList(), "Strawberry");
        }

        // Call the update method on the observer
        oDBO.update(oDB, oDB);

        // Verify that the update method printed the correct message to the console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertTrue(outContent.toString().contains("90% capacity reached"));
    }



    @AfterEach
    public void tearDown() {
        oDB = null;
        oDBO = null;
    }
}