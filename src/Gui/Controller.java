package Gui;

import CLI.console;
import GL.Hersteller;
import GL.Obj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import vertrag.Allergen;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class Controller implements Initializable {
    private CLI.console console;
    private String input;
    @FXML private TextField commandField;
    @FXML private Button buttonSubmit;
    @FXML private Label mode;
    @FXML private TableColumn<Obj, Integer> fach;
    @FXML private TableColumn<Obj, Date> inspektionsdatum;
    @FXML private TableColumn<Obj, String> hersteller;
    @FXML private TableColumn<Obj, Duration> haltbarkeit;
    @FXML private TableColumn<Hersteller, String> herstellerList;
    @FXML private TableView<Hersteller> tableHersteller;
    @FXML private ListView<Allergen> tableAllergene;
    @FXML private TableView<Obj> tableObj;
    @FXML private TableColumn<Obj, String> typ;

    private ObservableList<Hersteller> herstellerObservableList;
    private ObservableList<Obj> objObservableList;
    private ObservableList<Allergen> allergenObservableList;

    public void updateObsList(List<Hersteller> herstellerList, List<Obj> objList, List<Allergen> allergenList){
        this.herstellerObservableList = FXCollections.observableList(herstellerList);
        this.objObservableList = FXCollections.observableList(objList);
        this.allergenObservableList = FXCollections.observableList(allergenList);
        tableHersteller.setItems(herstellerObservableList);
        tableObj.setItems(objObservableList);
        tableAllergene.setItems(allergenObservableList);
        tableObj.refresh();
        tableAllergene.refresh();
        tableHersteller.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        herstellerList.setCellValueFactory(new PropertyValueFactory<Hersteller,String>("name"));
        inspektionsdatum.setCellValueFactory(new PropertyValueFactory<Obj,Date>("inspektionsdatum"));
        haltbarkeit.setCellValueFactory(new PropertyValueFactory<Obj, Duration>("haltbarkeit"));
        fach.setCellValueFactory(new PropertyValueFactory<Obj,Integer>("fachnummer"));
        typ.setCellValueFactory(new PropertyValueFactory<Obj,String>("kuchentyp"));
        hersteller.setCellValueFactory(new PropertyValueFactory<Obj,String>("herstellerString"));


    }

    public void onSubmit(javafx.event.ActionEvent actionEvent) {
        this.input = commandField.getText();
        console.execController(input);
        commandField.clear();
    }

    public void handleCommandField(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            this.input = commandField.getText();
            console.execController(input);
            commandField.clear();
            if(input.startsWith(":")) {
                switch (input) {
                    case ":c": mode.setText("C");
                        break;
                    case ":d": mode.setText("D");
                        break;
                    case ":u": mode.setText("U");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void setConsole(console console) {
        this.console = console;
    }
}
