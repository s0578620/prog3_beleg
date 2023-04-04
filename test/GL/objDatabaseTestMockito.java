package GL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vertrag.Allergen;
import vertrag.Obstkuchen;

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
        hersteller = new Hersteller("TestHersteller");
    }


    @Test
    public void testShowKuchenAll1() {
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller Name").when(herstellerMock).getName();

        Obj obj = new Obj("Kremkuchen", herstellerMock, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());

        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        List<String> result = objDatabase.showKuchenAll();
        assertFalse(result.isEmpty());
    }
    @Test
    public void testShowKuchenWithValidKuchentyp() {
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller Name").when(herstellerMock).getName();

        Obj obj1 = new Obj("Kremkuchen", herstellerMock, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", herstellerMock, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj1, obj2));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        List<String> result = objDatabase.showKuchen("Kremkuchen");
        assertEquals(1, result.size());
    }

    @Test
    public void testShowKuchenWithInvalidKuchentyp() {
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller Name").when(herstellerMock).getName();

        Obj obj1 = new Obj("Kremkuchen", herstellerMock, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", herstellerMock, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj1, obj2));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        List<String> result = objDatabase.showKuchen("InvalidKuchentyp");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShowKuchenWithEmptyDatabase() {
        objDatabase = new ObjDatabase(10, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

        List<String> result = objDatabase.showKuchen("Kremkuchen");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShowAllCakesSortedByHerstellerWithNonEmptyDatabase() {
        Hersteller herstellerMock1 = Mockito.mock(Hersteller.class);
        doReturn("Hersteller A").when(herstellerMock1).getName();

        Hersteller herstellerMock2 = Mockito.mock(Hersteller.class);
        doReturn("Hersteller B").when(herstellerMock2).getName();

        Obj obj1 = new Obj("Kremkuchen", herstellerMock1, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", herstellerMock2, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

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
        objDatabase = new ObjDatabase(10, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

        List<String> result = objDatabase.showAllCakesSortedByHersteller();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testStringForShowKuchen() {
        Obj objMock = Mockito.mock(Obj.class);
        doReturn("Kremkuchen").when(objMock).getKuchentyp();

        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
        doReturn("Hersteller A").when(herstellerMock).getName();
        doReturn(herstellerMock).when(objMock).getHersteller();

        LinkedList<Allergen> allergene = new LinkedList<Allergen>();
        allergene.add(Allergen.Gluten);
        allergene.add(Allergen.Haselnuss);
        doReturn(allergene).when(objMock).getAllergene();

        doReturn(100).when(objMock).getNaehrwert();
        doReturn(Duration.ofMinutes(120)).when(objMock).getHaltbarkeitDuration();

        ObjDatabase objDatabase = new ObjDatabase(10, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

        String result = objDatabase.stringForShowKuchen(objMock);
        assertEquals("Typ: Kremkuchen| Hersteller: Hersteller A| Allergene: [Gluten Haselnuss]| Nährwert: 100| Haltbarkeit: 120", result);
    }
    @Test
    public void testSetFachnummer() {
        Obj objMock = mock(Obj.class);

        Obj obj1 = new Obj("Kremkuchen", hersteller, BigDecimal.valueOf(5.0), 100, 120, new Date(), new ArrayList<>(), new Date());
        Obj obj2 = new Obj("Obstkuchen", hersteller, BigDecimal.valueOf(6.0), 110, 130, new Date(), new ArrayList<>(), new Date());

        LinkedList<Obj> objList = new LinkedList<>(Arrays.asList(obj1, obj2));
        objDatabase = new ObjDatabase(10, new LinkedList<>(), objList, new LinkedList<>());

        objDatabase.setFachnummer(objMock);

        verify(objMock).setFachnummer(2);
    }
    @Test
    public void testObjGetPreis() {
        BigDecimal expectedPrice = new BigDecimal("3.99");
        Obj obj = new Obj("Kremkuchen", mock(Hersteller.class), expectedPrice, 200, 2, new Date(), new ArrayList<>(), new Date());
        assertEquals(expectedPrice, obj.getPreis());
    }

    @Test
    public void testObjGetHaltbarkeit() {
        int expectedHaltbarkeit = 3;
        Obj obj = new Obj("Kremkuchen", mock(Hersteller.class), new BigDecimal("3.99"), 200, expectedHaltbarkeit, new Date(), new ArrayList<>(), new Date());
        assertEquals(expectedHaltbarkeit, obj.getHaltbarkeit());
    }

    @Test
    public void testKremkuchenGetKremsorte() {
        String expectedKremsorte = "Sahne";
        Kremkuchen obj = new Kremkuchen("Kremkuchen",mock(Hersteller.class), new BigDecimal("3.99"), 200, 3,new Date(),new ArrayList<>(),expectedKremsorte,new Date());
        assertEquals(expectedKremsorte, obj.getKremsorte());
    }

    @Test
    public void testObstkuchenGetObstsorte() {
        String expectedObstsorte = "Apfel";
        Obstkuchen obj = new GL.Obstkuchen("Kremkuchen",mock(Hersteller.class), new BigDecimal("3.99"), 200, 3,new Date(),new ArrayList<>(),expectedObstsorte,new Date());
        assertEquals(expectedObstsorte, obj.getObstsorte());
    }

    @Test
    public void testObsttorteGetKremsorte() {
        String expectedKremsorte = "Sahne";
        Obsttorte obj = new Obsttorte("Kremkuchen",mock(Hersteller.class), new BigDecimal("3.99"), 200, 3,new Date(),new ArrayList<>(),"Apfel",expectedKremsorte,new Date());
        assertEquals(expectedKremsorte, obj.getKremsorte());
    }

    @Test
    public void testObsttorteGetObstsorte() {
        String expectedObstsorte = "Apfel";
        Obsttorte obj = new Obsttorte("Kremkuchen",mock(Hersteller.class), new BigDecimal("3.99"), 200, 3,new Date(),new ArrayList<>(),expectedObstsorte,"Sahne",new Date());
        assertEquals(expectedObstsorte, obj.getObstsorte());
    }

}




