import CLI.cli;
import CLI.objDatabaseObserver;
import Events.EventHandler;
import Events.ODBEventListener;
import GL.objDatabase;

public class main {
    public static void main(String[] args) {

        objDatabase db = new objDatabase(10);
        db.attachObserver(new objDatabaseObserver(db));
        EventHandler handler = new EventHandler();
        handler.add(new ODBEventListener(db));
        cli c = new cli(handler);
        c.start();
    }
}
