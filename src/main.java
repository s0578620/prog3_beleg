import CLI.cli;
import Routing.Events.*;
import Routing.EventsReverse.ReverseShowHerstellerEvent;
import Routing.EventsReverse.ReverseShowKuchenEvent;
import Routing.EventsReverse.ReverseShowKuchenTypEvent;
import Routing.Handler.AddHerstellerHandler;
import Routing.Listener.Listener.*;
import Routing.Listener.ListenerReverse.ReverseShowHerstellerListener;
import Routing.Listener.ListenerReverse.ReverseShowKuchenListener;
import Routing.Listener.ListenerReverse.ReverseShowKuchenTypListener;
import util.ObjDatabaseAllergeneObserver;
import util.ObjDatabaseObserver;
import GL.ObjDatabase;

public class main {
    public static void main(String[] args) {

        ObjDatabase db = new ObjDatabase(10);
        db.addObserver(new ObjDatabaseObserver(db));
        db.addObserver(new ObjDatabaseAllergeneObserver(db));   // TODO NEED TEST


        AddHerstellerHandler handlerReverse = new AddHerstellerHandler();
        ReverseShowKuchenListener listenerReverseShowKuchen = new ReverseShowKuchenListener();
        ReverseShowKuchenTypListener listenerReverseShowKuchenTyp = new ReverseShowKuchenTypListener();
        ReverseShowHerstellerListener listenerReverseShowHersteller = new ReverseShowHerstellerListener();
        handlerReverse.addListener(ReverseShowKuchenEvent.class,listenerReverseShowKuchen);
        handlerReverse.addListener(ReverseShowKuchenTypEvent.class,listenerReverseShowKuchenTyp);
        handlerReverse.addListener(ReverseShowHerstellerEvent.class,listenerReverseShowHersteller);


        AddHerstellerListener listenerHersteller = new AddHerstellerListener(db);
        AddKuchenListener listenerKuchen = new AddKuchenListener(db);
        AddTorteListener listenerTorte = new AddTorteListener(db);
        IOLoadFileListener listenerLoad = new IOLoadFileListener(db);
        IOSafeFileListener listenerSafe = new IOSafeFileListener(db);
        RemoveHerstellerListener listenerRemoveHersteller = new RemoveHerstellerListener(db);
        RemoveKuchenListener listenerRemoveKuchen = new RemoveKuchenListener(db);

        ShowKuchenListener listenerShowKuchen = new ShowKuchenListener(db,handlerReverse);
        ShowKuchenTypListener listenerShowKuchenTyp = new ShowKuchenTypListener(db,handlerReverse);
        ShowHerstellerListener listenerShowHersteller = new ShowHerstellerListener(db,handlerReverse);

        UpdateInspListener listenerUpdateInsp = new UpdateInspListener(db);

        AddHerstellerHandler handler = new AddHerstellerHandler();
        handler.addListener(AddHerstellerEvent.class,listenerHersteller);
        handler.addListener(AddKuchenEvent.class,listenerKuchen);
        handler.addListener(AddTorteEvent.class,listenerTorte);
        handler.addListener(LoadFileEvent.class,listenerLoad);
        handler.addListener(SaveFileEvent.class,listenerSafe);
        handler.addListener(RemoveHerstellerEvent.class,listenerRemoveHersteller);
        handler.addListener(RemoveKuchenEvent.class,listenerRemoveKuchen);

        handler.addListener(ShowKuchenEvent.class,listenerShowKuchen);
        handler.addListener(ShowKuchenTypEvent.class,listenerShowKuchenTyp);
        handler.addListener(ShowHerstellerEvent.class,listenerShowHersteller);

        handler.addListener(UpdateInspEvent.class,listenerUpdateInsp);

        cli c = new cli(handler);
        c.start();
    }
}
