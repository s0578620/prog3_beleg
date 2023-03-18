package GL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class objDatabaseTestMockito {
    private ObjDatabase objDatabase;
    private Hersteller hersteller;


    @BeforeEach
    public void setUp() {
        objDatabase = new ObjDatabase(10);
        hersteller = new Hersteller("TestHersteller");
    }


    @Test
    public void testShowKuchenAll1() {
        // Mock Hersteller
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller Name").when(herstellerMock).getName();

        // Create a real Obj with mock Hersteller
        Obj obj = new Obj("Kremkuchen", herstellerMock, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());

        // Initialize ObjDatabase with real Obj
        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        List<String> result = objDatabase.showKuchenAll();
        assertFalse(result.isEmpty());
    }
    @Test
    public void testShowKuchenWithValidKuchentyp() {
        // Mock Hersteller
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller Name").when(herstellerMock).getName();

        // Create real Obj objects with mock Hersteller
        Obj obj1 = new Obj("Kremkuchen", herstellerMock, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", herstellerMock, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

        // Initialize ObjDatabase with real Obj objects
        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj1, obj2));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        List<String> result = objDatabase.showKuchen("Kremkuchen");
        assertEquals(1, result.size());
    }

    @Test
    public void testShowKuchenWithInvalidKuchentyp() {
        // Mock Hersteller
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller Name").when(herstellerMock).getName();

        // Create real Obj objects with mock Hersteller
        Obj obj1 = new Obj("Kremkuchen", herstellerMock, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", herstellerMock, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

        // Initialize ObjDatabase with real Obj objects
        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj1, obj2));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        List<String> result = objDatabase.showKuchen("InvalidKuchentyp");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShowKuchenWithEmptyDatabase() {
        // Create an empty ObjDatabase
        objDatabase = new ObjDatabase(10, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

        List<String> result = objDatabase.showKuchen("Kremkuchen");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShowAllCakesSortedByHerstellerWithNonEmptyDatabase() {
        // Mock Hersteller
        Hersteller herstellerMock1 = Mockito.mock(Hersteller.class);
        doReturn("Hersteller A").when(herstellerMock1).getName();

        Hersteller herstellerMock2 = Mockito.mock(Hersteller.class);
        doReturn("Hersteller B").when(herstellerMock2).getName();

        // Create real Obj objects with mock Hersteller
        Obj obj1 = new Obj("Kremkuchen", herstellerMock1, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", herstellerMock2, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

        // Initialize ObjDatabase with real Obj objects
        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj1, obj2));
        LinkedList<Hersteller> herstellerList = new LinkedList<>(Arrays.asList(herstellerMock1, herstellerMock2));
        objDatabase = new ObjDatabase(10, herstellerList, objList, new LinkedList<>());

        List<String> result = objDatabase.showAllCakesSortedByHersteller();
        assertEquals(4, result.size());
        assertEquals("Hersteller A", result.get(0).substring(10, 22));
        assertEquals("Hersteller B", result.get(1).substring(10, 22));
        assertTrue(result.get(2).contains("Hersteller: Hersteller A besitzt Kuchen: 1"));
        assertTrue(result.get(3).contains("Hersteller: Hersteller B besitzt Kuchen: 1"));
    }

    @Test
    public void testShowAllCakesSortedByHerstellerWithEmptyDatabase() {
        // Create an empty ObjDatabase
        objDatabase = new ObjDatabase(10, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

        List<String> result = objDatabase.showAllCakesSortedByHersteller();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testStringForShowKuchen() {
        // Mock Obj
        Obj objMock = Mockito.mock(Obj.class);
        doReturn("Kremkuchen").when(objMock).getKuchentyp();

        // Mock Hersteller
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller A").when(herstellerMock).getName();
        doReturn(herstellerMock).when(objMock).getHersteller();

        // Mock Allergene
        LinkedList<Allergen> allergene = new LinkedList<Allergen>();
        allergene.add(Allergen.Gluten);
        allergene.add(Allergen.Haselnuss);
        doReturn(allergene).when(objMock).getAllergene();

        // Other attributes
        doReturn(100).when(objMock).getNaehrwert();
        doReturn(Duration.ofMinutes(120)).when(objMock).getHaltbarkeitDuration();

        // Initialize ObjDatabase
        ObjDatabase objDatabase = new ObjDatabase(10, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

        // Test stringForShowKuchen method
        String result = objDatabase.stringForShowKuchen(objMock);
        assertEquals("Typ: Kremkuchen| Hersteller: Hersteller A| Allergene: [Gluten Haselnuss]| Nährwert: 100| Haltbarkeit: 120", result);
    }
    @Test
    public void testSetFachnummer() {
        // Create a mock object of Obj
        Obj objMock = mock(Obj.class);

        // Create real Obj objects
        Obj obj1 = new Obj("Kremkuchen", hersteller, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", hersteller, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

        // Initialize ObjDatabase with real Obj objects
        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj1, obj2));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        // Call setFachnummer method with the objMock object
        Obj result = objDatabase.setFachnummer(objMock);

        // Verify the interaction with objMock
        verify(objMock).setFachnummer(2);
    }
}




