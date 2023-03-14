import CLI.Cli;
import GL.ObjDatabase;
import Routing.Events.*;
import Routing.EventsReverse.*;
import Routing.Handler.Handler;
import Routing.Listener.Listener.*;
import Routing.Listener.ListenerReverse.*;
import util.ObjDatabaseObserver;

public class CliAlternativ {
    // TODO TEST
    public static void main(String[] args) {

        int capacity = 0;

        if (args.length > 0) {
            for (String arg : args) {
                if (arg.matches("\\d+")) {
                    capacity = Integer.parseInt(arg);
                }
            }
        }

        ObjDatabase db = new ObjDatabase(capacity);
        db.addObserver(new ObjDatabaseObserver(db));

        ReverseShowKuchenListener listenerReverseShowKuchen = new ReverseShowKuchenListener();
        ReverseShowKuchenTypListener listenerReverseShowKuchenTyp = new ReverseShowKuchenTypListener();
        ReverseShowHerstellerListener listenerReverseShowHersteller = new ReverseShowHerstellerListener();

        Handler handlerReverse = new Handler();
        handlerReverse.addListener(ReverseShowKuchenEvent.class,listenerReverseShowKuchen);
        handlerReverse.addListener(ReverseShowKuchenTypEvent.class,listenerReverseShowKuchenTyp);
        handlerReverse.addListener(ReverseShowHerstellerEvent.class,listenerReverseShowHersteller);

        AddHerstellerListener listenerHersteller = new AddHerstellerListener(db);
        AddKuchenListener listenerKuchen = new AddKuchenListener(db);
        AddTorteListener listenerTorte = new AddTorteListener(db);
        IOLoadFileListener listenerLoad = new IOLoadFileListener(db);
        IOSafeFileListener listenerSafe = new IOSafeFileListener(db);
        RemoveKuchenListener listenerRemoveKuchen = new RemoveKuchenListener(db);
        ShowKuchenListener listenerShowKuchen = new ShowKuchenListener(db,handlerReverse);
        ShowKuchenTypListener listenerShowKuchenTyp = new ShowKuchenTypListener(db,handlerReverse);
        ShowHerstellerListener listenerShowHersteller = new ShowHerstellerListener(db,handlerReverse);
        UpdateInspListener listenerUpdateInsp = new UpdateInspListener(db);

        Handler handler = new Handler();
        handler.addListener(AddHerstellerEvent.class,listenerHersteller);
        handler.addListener(AddKuchenEvent.class,listenerKuchen);
        handler.addListener(AddTorteEvent.class,listenerTorte);
        handler.addListener(LoadFileEvent.class,listenerLoad);
        handler.addListener(SaveFileEvent.class,listenerSafe);

        handler.addListener(RemoveKuchenEvent.class,listenerRemoveKuchen);
        handler.addListener(ShowKuchenEvent.class,listenerShowKuchen);
        handler.addListener(ShowKuchenTypEvent.class,listenerShowKuchenTyp);
        handler.addListener(ShowHerstellerEvent.class,listenerShowHersteller);
        handler.addListener(UpdateInspEvent.class,listenerUpdateInsp);

        new Cli(handler).start();
    }
}
