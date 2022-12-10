package Events;

import CLI.console;
import GL.ObjDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {


    // TODO kombi test für console und events?

    private ObjDatabase oDB;
    private EventHandler handler;
    private ODBEventListener listener;
    private console console;


    @BeforeEach
    void setup(){
        this.oDB = new ObjDatabase(10);
        this.handler = new EventHandler();
        this.listener = new ODBEventListener(oDB);
        this.handler.add(listener);
        this.console = new console(handler);
    }


    @Test
    void addHerstellerEvent() {
        String input = "HerstellerTest";
        AddHerstellerEvent event = new AddHerstellerEvent(input,input);
        this.handler.handle(event);

        assertEquals( input,  oDB.getHerstellerList().getFirst().getName());
    }

    @Test
    void addKuchenEvent() {
        String input = "Kremkuchen Hersteller1 4,50 386 36 Gluten,Erdnuss Butter";
        oDB.addHersteller("Hersteller1");
        AddKuchenEvent event = (AddKuchenEvent) console.createEO(input);
        this.handler.handle(event);

        assertEquals( 1,  oDB.getObjList().size());
    }

    @Test
    void addTorteEvent() {
        String input = "Obsttorte Hersteller1 7,50 632 24 Gluten Apfel Sahne";
        oDB.addHersteller("Hersteller1");
        AddTorteEvent event = (AddTorteEvent) console.createEO(input);
        this.handler.handle(event);

        assertEquals( 1,  oDB.getObjList().size());
    }

    @Test
    void removeHerstellerEvent(){
        String input = "HerstellerTest";
        oDB.addHersteller("HerstellerTest");
        RemoveHerstellerEvent event = new RemoveHerstellerEvent(input,input);
        this.handler.handle(event);

        assertEquals( 0,  oDB.getHerstellerList().size());
    }

    @Test
    void removeKuchenEvent(){
        int input = 0;
        String inputString = "Kremkuchen Hersteller1 4,50 386 36 Gluten,Erdnuss Butter";
        oDB.addHersteller("Hersteller1");

        AddKuchenEvent event = (AddKuchenEvent) console.createEO(inputString);
        this.handler.handle(event);

        RemoveKuchenEvent event1 = new RemoveKuchenEvent("", input);
        this.handler.handle(event1);

        assertEquals( 0,  oDB.getObjList().size());
    }
}