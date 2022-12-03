import CLI.cli;
import util.ObjDatabaseAllergeneObserver;
import util.ObjDatabaseObserver;
import Events.EventHandler;
import Events.ODBEventListener;
import GL.ObjDatabase;

public class main {
    public static void main(String[] args) {

        ObjDatabase db = new ObjDatabase(10);
        db.addObserver(new ObjDatabaseObserver(db));
        db.addObserver(new ObjDatabaseAllergeneObserver(db));   // TODO NEED TEST
        EventHandler handler = new EventHandler();
        handler.add(new ODBEventListener(db));

        cli c = new cli(handler);
        c.start();
    }
}
