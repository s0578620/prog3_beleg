package Observer;


import GL.ObjDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ObjDatabaseObserver;
import vertrag.Allergen;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class ObjDatabaseObserverTest {


    private ObjDatabase oDB;
    private ObjDatabaseObserver observer;
    private ByteArrayOutputStream outContent;
    private LinkedList<Allergen> list;
    private BigDecimal Preis;

    @BeforeEach
    void setUp() {
        oDB = new ObjDatabase(10);
        observer = new ObjDatabaseObserver(oDB);
        oDB.addObserver(observer);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testConstructor() {
        assertEquals(oDB, observer.getoDB());
    }

    @Test
    void testCapacityNinetyPercentReached() {
        this.Preis = new BigDecimal("19.99");
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
        oDB.addHersteller("Hersteller1");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        String expectedOutput = "90% capacity reached\n";
        assertEquals(expectedOutput, outContent.toString().replaceAll("\r\n", "\n"));
    }
    @Test
    void testCapacityReachedNotTriggeredBelowNinetyPercent() {
        this.Preis = new BigDecimal("19.99");
        this.list = new LinkedList<>();
        list.add(Allergen.Erdnuss);
        list.add(Allergen.Gluten);
        oDB.addHersteller("Hersteller1");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");
        oDB.addObj("Kremkuchen", "Hersteller1", Preis, 0, 0, list, "");

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString().replaceAll("\r\n", "\n"));
    }
}
