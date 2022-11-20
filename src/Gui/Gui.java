package Gui;

import CLI.console;
import Events.EventHandler;
import Events.ODBEventListener;
import GL.objDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Gui extends Application {

    public void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root, 600, 400);

        objDatabase oDB = new objDatabase(10);  // TODO CAPACITY HARDCODED
        EventHandler handler = new EventHandler();
        handler.add(new ODBEventListener(oDB));
        Controller controller = loader.getController();
        controller.setConsole(new console(handler));


        stage.setTitle("Kuchenautomat");
        stage.setScene(scene);
        stage.isResizable();
        stage.show();
    }
}
