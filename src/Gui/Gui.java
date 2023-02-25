package Gui;

import CLI.console;
import Routing.Events.*;
import Routing.EventsReverse.*;
import Routing.Handler.Handler;
import Routing.Listener.Listener.*;
import Routing.Listener.ListenerReverse.*;
import GL.ObjDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ObjDatabaseObserverGui;

public class Gui extends Application {

    public void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/App.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        ObjDatabase oDB = new ObjDatabase(10);


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

        AddHerstellerListener listenerHersteller = new AddHerstellerListener(oDB);
        AddKuchenListener listenerKuchen = new AddKuchenListener(oDB);
        AddTorteListener listenerTorte = new AddTorteListener(oDB);
        IOLoadFileListener listenerLoad = new IOLoadFileListener(oDB);
        IOSafeFileListener listenerSafe = new IOSafeFileListener(oDB);
        RemoveHerstellerListener listenerRemoveHersteller = new RemoveHerstellerListener(oDB);
        RemoveKuchenListener listenerRemoveKuchen = new RemoveKuchenListener(oDB);
        ShowKuchenListener listenerShowKuchen = new ShowKuchenListener(oDB,handlerReverse);
        ShowKuchenTypListener listenerShowKuchenTyp = new ShowKuchenTypListener(oDB,handlerReverse);
        ShowHerstellerListener listenerShowHersteller = new ShowHerstellerListener(oDB,handlerReverse);
        ShowAllergeneIncListener listenerShowAllergeneInc = new ShowAllergeneIncListener(oDB,handlerReverse);
        ShowAllergeneExcListener listenerShowAllergeneExc = new ShowAllergeneExcListener(oDB,handlerReverse);
        UpdateInspListener listenerUpdateInsp = new UpdateInspListener(oDB);

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


        Controller controller = loader.getController();
        controller.setHandler(handler);
        controller.setConsole(new console(handler));

        oDB.addObserver(new ObjDatabaseObserverGui(oDB,controller));


        stage.setTitle("Kuchenautomat");
        stage.setScene(scene);
        stage.isResizable();
        stage.show();
    }
}
