package Gui;

import CLI.console;
import GL.Hersteller;
import GL.obj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private CLI.console console;
    private String input;
    @FXML private TextField commandField;
    @FXML private Button buttonSubmit;
    @FXML private TableColumn<obj, Integer> fach;
    @FXML private TableColumn<obj, String> hersteller;
    @FXML private TableColumn<Hersteller, String> herstellerList;
    @FXML private TableView<Hersteller> tableHersteller;
    @FXML private TableView<obj> tableObj;
    @FXML private TableColumn<obj, String> typ;

    private ObservableList<Hersteller> list = FXCollections.observableArrayList(new Hersteller("Nestle"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        herstellerList.setCellValueFactory(new PropertyValueFactory<Hersteller,String>("name"));
        tableHersteller.setItems(list); // TODO INSERT HERSTELLER ???
    }

    public void onSubmit(javafx.event.ActionEvent actionEvent) {
        this.input = commandField.getText();
        System.out.println(input);
        console.execController(input);
        commandField.clear();

    }

    public void handleCommandField(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            this.input = commandField.getText();
            System.out.println(input);
            console.execController(input);
            commandField.clear();
        }
    }

    public void setConsole(console console) {
        this.console = console;
    }

}
