package Gui;

import CLI.console;
import GL.Hersteller;
import GL.Obj;
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
import vertrag.Allergen;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private CLI.console console;
    private String input;
    @FXML private TextField commandField;
    @FXML private Button buttonSubmit;
    @FXML private TableColumn<Obj, Integer> fach;
    @FXML private TableColumn<Obj, String> hersteller;
    @FXML private TableColumn<Hersteller, String> herstellerList;
    @FXML private TableView<Hersteller> tableHersteller;
    @FXML private TableView<Obj> tableObj;
    @FXML private TableColumn<Obj, String> typ;


    // TODO DELETE TEST VARIABLES
    Hersteller testHersteller = new Hersteller("Nestle");
    Hersteller testHersteller1 = new Hersteller("Unilever");
    BigDecimal testpreis = new BigDecimal(10);
    Date testDate = new Date();
    LinkedList<Allergen> testlist = new LinkedList<>();

    private ObservableList<Hersteller> herstellerObservableList = FXCollections.observableArrayList(testHersteller,testHersteller1);
    private ObservableList<Obj> objObservableList = FXCollections.observableArrayList(new Obj("Kremkuchen",testHersteller,testpreis,150,20,testDate,testlist,testDate),new Obj("Obstkuchen",testHersteller1,testpreis,1,1,testDate,testlist,testDate));

    public void updateObsList(List<Hersteller> herstellerList, List<Obj> objList){      // TODO updateObserverList

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        herstellerList.setCellValueFactory(new PropertyValueFactory<Hersteller,String>("name"));
        tableHersteller.setItems(herstellerObservableList); // TODO INSERT HERSTELLER ???

        fach.setCellValueFactory(new PropertyValueFactory<Obj,Integer>("fachnummer"));
        typ.setCellValueFactory(new PropertyValueFactory<Obj,String>("kuchentyp"));
        hersteller.setCellValueFactory(new PropertyValueFactory<Obj,String>("hersteller")); // TODO Richtig oder Hersteller Objekt?
        tableObj.setItems(objObservableList);
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
