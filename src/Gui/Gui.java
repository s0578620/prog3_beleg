package Gui;

import CLI.console;
import Events.EventHandler;
import Events.ODBEventListener;
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

        ObjDatabase oDB = new ObjDatabase(10);  // TODO CAPACITY HARDCODED

        EventHandler handler = new EventHandler();
        handler.add(new ODBEventListener(oDB));
        Controller controller = loader.getController();
        controller.setConsole(new console(handler));
        oDB.attachObserver(new ObjDatabaseObserverGui(oDB,controller));


        stage.setTitle("Kuchenautomat");
        stage.setScene(scene);
        stage.isResizable();
        stage.show();
    }
}
