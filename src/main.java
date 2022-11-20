import CLI.cli;
import util.ObjDatabaseObserver;
import Events.EventHandler;
import Events.ODBEventListener;
import GL.ObjDatabase;

public class main {
    public static void main(String[] args) {

        ObjDatabase db = new ObjDatabase(10);
        db.attachObserver(new ObjDatabaseObserver(db));
        EventHandler handler = new EventHandler();
        handler.add(new ODBEventListener(db));
        cli c = new cli(handler);
        c.start();
    }
}
