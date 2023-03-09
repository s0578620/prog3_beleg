package CLI;

import Routing.Events.*;
import Routing.Handler.Handler;
import net.Client;
import net.ClientUDP;
import org.junit.jupiter.api.Test;

import java.util.EventObject;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

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

    @Test
    void testDeleteHerstellerEvent() {
        Handler handler = new Handler();
        Console console = new Console(handler);
        String input = "Test Hersteller";
        EventObject event = console.deleteEO(input);
        assertTrue(event instanceof RemoveHerstellerEvent);
        assertEquals(input, ((RemoveHerstellerEvent) event).getHersteller());
    }

}


