package Mockito;

import GL.ObjDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;



class MockitoTest {

    private ObjDatabase database;
    private String hersteller;
    private Collection<Allergen> allergens;


    @BeforeEach
    public void setUp() {
        database = new ObjDatabase(1);
        hersteller = "TestHersteller";
        allergens = new ArrayList<>();
        database.addHersteller(hersteller);
    }

    // Hersteller Tests

    @Test
    public void testAddHersteller() {
        assertTrue(database.addHersteller("TestHersteller2"));
    }

    @Test
    public void testAddHerstellerDuplicate() {
        assertFalse(database.addHersteller("TestHersteller"));
    }

    @Test
    public void testRemoveHersteller() {
        database.removeHersteller("TestHersteller");
        assertFalse(database.getHerstellerList().stream().anyMatch(h -> h.getName().equals("TestHersteller")));
    }

    @Test
    public void testRemoveNonExistingHersteller() {
        database.removeHersteller("TestHersteller2");
        assertFalse(database.getHerstellerList().stream().anyMatch(h -> h.getName().equals("TestHersteller2")));
    }

    // Object Tests

    @Test
    public void testAddObj() {
        assertTrue(database.addObj("Kremkuchen", hersteller, BigDecimal.ZERO, 0, 0, allergens, "topping"));
    }

    @Test
    public void testAddObjCapacityReached() {
        database.addObj("Kremkuchen", hersteller, BigDecimal.ZERO, 0, 0, allergens, "topping");
        assertFalse(database.addObj("Kremkuchen", hersteller, BigDecimal.ZERO, 0, 0, allergens, "topping"));
    }

    @Test
    public void testAddObjInvalidType() {
        assertFalse(database.addObj("InvalidType", hersteller, BigDecimal.ZERO, 0, 0, allergens, "topping"));
    }

    @Test
    public void testAddObjInvalidHersteller() {
        assertFalse(database.addObj("Kremkuchen", "InvalidHersteller", BigDecimal.ZERO, 0, 0, allergens, "topping"));
    }

}