package CLI;

import Routing.Events.*;
import Routing.Handler.Handler;
import net.Client;
import net.ClientUDP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.EventObject;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {



    // CONSTRUCTOR
    @Test
    void testConstructorHandler() {
        Handler handler = new Handler();
        Console consoleObject = new Console(handler);

        assertNotNull(consoleObject);
    }

    @Test
    void testConstructorClient() {
        Client testClient = new ClientUDP();
        Console testConsole = new Console(testClient);

        assertNotNull(testConsole);
    }

    // HELP MENU
    @Test
    void testGetCorrectHelpMenuDefault() {
        Handler handler = new Handler();
        Console console = new Console(handler);

        assertEquals("*** Help Menu *** \n :c - Create mode\n :d - Delete mode\n :r - Show mode\n :p - Persistence mode\n", console.getCorrectHelpMenu());
    }

    @Test
    void testGetCorrectHelpMenuCreate() {
        Handler handler = new Handler();
        Console console = new Console(handler);

        console.execController(":c");
        assertEquals("*** Help Menu *** \n [Herstellername] - add Hersteller\n " +
                "[Kuchen-Typ] [Herstellername] [Preis] [Nährwert] [Haltbarkeit] [kommaseparierte Allergene, einzelnes Komma für keine] [[Obstsorte]] [[Kremsorte]] - add Kuchen\n", console.getCorrectHelpMenu());
    }

    @Test
    void testGetCorrectHelpMenuDelete() {
        Handler handler = new Handler();
        Console console = new Console(handler);

        console.execController(":d");
        assertEquals("*** Help Menu *** \n [Herstellername] - delete Hersteller\n [Fachnummer] - delete cake\n", console.getCorrectHelpMenu());
    }

    @Test
    void testGetCorrectHelpMenuShow() { // TODO CARE (Forgot to show event?)?
        Handler handler = new Handler();
        Console console = new Console(handler);

        console.execController(":r");
        assertEquals("*** Help Menu *** \n hersteller - show Hersteller (with Cake count)\n kuchen [[Typ]] - show Kuchen (-typ)\n allergene [i/e] - show Allergene (i = Inclusive / e = Exclusive)\n", console.getCorrectHelpMenu());
    }



    @Test
    void testGetCorrectHelpMenuUpdate() {
        Handler handler = new Handler();
        Console console = new Console(handler);

        console.execController(":u");
        assertEquals("*** Help Menu *** \n [Fachnummer] - update Inspektionsdatum\n", console.getCorrectHelpMenu());
    }

    @Test
    void testGetCorrectHelpMenuPersistence() {
        Handler handler = new Handler();
        Console console = new Console(handler);

        console.execController(":p");
        assertEquals("*** Help Menu *** \n safe [jos/jbp] - safe via jos/jbp\n load [jos/jbp] - load via jos/jbp\n", console.getCorrectHelpMenu());
    }

    // CORRECT EVENTS
    @Test
    void testGetCorrectEO_createMode() {
        Handler handler = new Handler();
        Console testConsole = new Console(handler);
        testConsole.setMode(Console.Mode.CREATE);
        EventObject result = testConsole.getCorrectEO("Obsttorte Hersteller2 7,50 632 24 Gluten Apfel Sahne");
        assertTrue(result instanceof AddTorteEvent);
    }

    @Test
    void testGetCorrectEO_deleteMode() {
        Handler handler = new Handler();
        Console testConsole = new Console(handler);
        testConsole.setMode(Console.Mode.DELETE);
        EventObject result = testConsole.getCorrectEO("Hersteller1");
        assertTrue(result instanceof RemoveHerstellerEvent);
    }

    @Test
    void testGetCorrectEO_showMode() {
        Handler handler = new Handler();
        Console testConsole = new Console(handler);
        testConsole.setMode(Console.Mode.SHOW);
        EventObject result = testConsole.getCorrectEO("hersteller");
        assertTrue(result instanceof ShowHerstellerEvent);
    }

    @Test
    void testGetCorrectEO_updateMode() {
        Handler handler = new Handler();
        Console testConsole = new Console(handler);
        testConsole.setMode(Console.Mode.UPDATE);
        EventObject result = testConsole.getCorrectEO("2");
        assertTrue(result instanceof UpdateInspEvent);
    }

    @Test
    void testGetCorrectEO_persistenceMode() {
        Handler handler = new Handler();
        Console testConsole = new Console(handler);
        testConsole.setMode(Console.Mode.PERSISTENCE);
        EventObject result = testConsole.getCorrectEO("load jos");
        assertTrue(result instanceof LoadFileEvent);
    }

    // EVENT - DELETE
    @Test
    void testDeleteHerstellerEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "Test Hersteller";
        EventObject event = console.deleteEO(input);
        assertTrue(event instanceof RemoveHerstellerEvent);
        assertEquals(input, ((RemoveHerstellerEvent) event).getHersteller());
    }

    @Test
    void testDeleteKuchenEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "42";
        RemoveKuchenEvent event = (RemoveKuchenEvent) console.deleteEO(input);

        assertEquals(Integer.parseInt(input), event.getFachnummer());
    }

    // EVENT - SHOW
    @Test
    public void testShowHerstellerEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        EventObject event = console.showEO("hersteller TestHersteller");
        ShowHerstellerEvent showHerstellerEvent = (ShowHerstellerEvent) event;

        assertEquals("hersteller TestHersteller", showHerstellerEvent.getTxt());
    }

    @Test
    public void testShowKuchenEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        EventObject event = console.showEO("kuchen");

        assertTrue(event instanceof ShowKuchenEvent);
    }

    @Test
    public void testShowKuchenTypEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        EventObject event = console.showEO("kuchen TestTyp");
        ShowKuchenTypEvent showKuchenTypEvent = (ShowKuchenTypEvent) event;

        Assertions.assertEquals("TestTyp", showKuchenTypEvent.getTyp());
    }

    @Test
    public void testShowAllergeneEventInclusive() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        EventObject event = console.showEO("allergene i");

        Assertions.assertTrue(event instanceof ShowAllergeneEventInclusive);
    }

    @Test
    public void testShowAllergeneEventExclusive() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        EventObject event = console.showEO("allergene e");

        Assertions.assertTrue(event instanceof ShowAllergeneEventExclusive);
    }

    // EVENT - UPDATE
    @Test
    void testUpdateEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "42";
        EventObject event = console.updateEO(input);
        assertTrue(event instanceof UpdateInspEvent);
    }

    // EVENT - CREATE
    @Test
    void testCreateEventAddHerstellerEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "TestHersteller";
        EventObject result = console.createEO(input);
        AddHerstellerEvent event = (AddHerstellerEvent) result;
        assertEquals("TestHersteller", event.getHersteller());
    }

    @Test
    void testCreateEventAddKuchenEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "TestTyp TestHersteller 3,99 250 7 a,b,c Schoko";
        EventObject result = console.createEO(input);
        assertTrue(result instanceof AddKuchenEvent);

//        assertTrue(result instanceof AddKuchenEvent);
//        AddKuchenEvent event = (AddKuchenEvent) result;
//        assertEquals("TestHersteller", event.getHersteller());
//        assertEquals("TestHersteller", event.getName());
//        assertEquals("TestTyp", event.getTyp());
//        assertEquals(new BigDecimal("3.99"), event.getPreis());
//        assertEquals(250, event.getNaehrwert());
//        assertEquals(7, event.getHaltbarkeit());
//        assertEquals(3, event.getAllergene().size());
//        assertTrue(event.getAllergene().contains(Allergen.a));
//        assertTrue(event.getAllergene().contains(Allergen.b));
//        assertTrue(event.getAllergene().contains(Allergen.c));
//        assertEquals("Schoko", event.getObstsorte());
    }

    @Test
    void testCreateEventAddTorteEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "TestTyp TestHersteller 3,99 250 7 a,b,c Schoko Sahne";
        EventObject result = console.createEO(input);
        assertTrue(result instanceof AddTorteEvent);

//        assertTrue(result instanceof AddTorteEvent);
//        AddTorteEvent event = (AddTorteEvent) result;
//        assertEquals("TestHersteller", event.getHersteller());
//        assertEquals("TestHersteller", event.getName());
//        assertEquals("TestTyp", event.getTyp());
//        assertEquals(new BigDecimal("3.99"), event.getPreis());
//        assertEquals(250, event.getNaehrwert());
//        assertEquals(7, event.getHaltbarkeit());
//        assertEquals(3, event.getAllergene().size());
//        assertTrue(event.getAllergene().contains(Allergen.a));
//        assertTrue(event.getAllergene().contains(Allergen.b));
//        assertTrue(event.getAllergene().contains(Allergen.c));
//        assertEquals("Schoko", event.getObstsorte());
//        assertEquals("Sahne", event.getKremsorte());
    }

    @Test
    void testCreateEO_InvalidInput() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "TestTyp TestHersteller";
        EventObject result = console.createEO(input);
        assertNull(result);
    }

    // ADDITIONAL TESTING
    @Disabled
    @Test
    public void testWrite() {   // TODO RICHTIGER OUTPUT ABER FALSCH...
        Handler handler = new Handler();
        Console console = new Console(handler);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        console.write("Test Output");
        System.setOut(System.out);
        assertEquals("Test Output\n", outContent.toString());
    }

}


