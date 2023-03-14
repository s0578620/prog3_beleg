import CLI.Cli;
import GL.ObjDatabase;
import Routing.Events.*;
import Routing.EventsReverse.*;
import Routing.Handler.Handler;
import Routing.Listener.Listener.*;
import Routing.Listener.ListenerReverse.*;
import net.ClientTCP;
import net.ClientUDP;
import util.ObjDatabaseAllergeneObserver;
import util.ObjDatabaseObserver;

public class CliMain {

    public static void main(String[] args) {

        int capacity = 0;
        boolean isTCP = false;
        boolean isUDP = false;

        if (args.length > 0) {
            for (String arg : args) {
                if (arg.matches("\\d+")) {
                    capacity = Integer.parseInt(arg);
                } else if (arg.equalsIgnoreCase("TCP")) {
                    isTCP = true;
                } else if (arg.equalsIgnoreCase("UDP")) {
                    isUDP = true;
                }
            }
        }

        if (isTCP) {
            new Cli(new ClientTCP()).start();
        } else if (isUDP) {
            new Cli(new ClientUDP()).start();
        } else {
            ObjDatabase db = new ObjDatabase(capacity);
            db.addObserver(new ObjDatabaseObserver(db));
            db.addObserver(new ObjDatabaseAllergeneObserver(db));

            ReverseShowKuchenListener listenerReverseShowKuchen = new ReverseShowKuchenListener();
            ReverseShowKuchenTypListener listenerReverseShowKuchenTyp = new ReverseShowKuchenTypListener();
            ReverseShowHerstellerListener listenerReverseShowHersteller = new ReverseShowHerstellerListener();
            ReverseShowAllergeneIncListener listenerReverseShowAllergeneInc = new ReverseShowAllergeneIncListener();
            ReverseShowAllergeneExcListener listenerReverseShowAllergeneExc = new ReverseShowAllergeneExcListener();

            Handler handlerReverse = new Handler();
            handlerReverse.addListener(ReverseShowKuchenEvent.class,listenerReverseShowKuchen);
            handlerReverse.addListener(ReverseShowKuchenTypEvent.class,listenerReverseShowKuchenTyp);
            handlerReverse.addListener(ReverseShowHerstellerEvent.class,listenerReverseShowHersteller);
            handlerReverse.addListener(ReverseShowAllergeneIncEvent.class,listenerReverseShowAllergeneInc);
            handlerReverse.addListener(ReverseShowAllergeneExcEvent.class,listenerReverseShowAllergeneExc);

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
            ShowAllergeneIncListener listenerShowAllergeneInc = new ShowAllergeneIncListener(db,handlerReverse);
            ShowAllergeneExcListener listenerShowAllergeneExc = new ShowAllergeneExcListener(db,handlerReverse);
            UpdateInspListener listenerUpdateInsp = new UpdateInspListener(db);

            Handler handler = new Handler();
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
            handler.addListener(ShowAllergeneEventInclusive.class,listenerShowAllergeneInc);
            handler.addListener(ShowAllergeneEventExclusive.class,listenerShowAllergeneExc);
            handler.addListener(UpdateInspEvent.class,listenerUpdateInsp);
            new Cli(handler).start();

        }
    }
}
