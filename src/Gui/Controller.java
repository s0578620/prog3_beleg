package Gui;

import CLI.Console;
import Routing.Handler.Handler;
import Routing.Events.LoadFileEvent;
import Routing.Events.SaveFileEvent;
import GL.Hersteller;
import GL.Obj;
import Gui.Beans.AllergenBean;
import Gui.Beans.HerstellerBean;
import Gui.Beans.ObjBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import vertrag.Allergen;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class Controller implements Initializable {

    @FXML private TextField commandField;
    @FXML private Label mode;
    @FXML private TableView<ObjBean> tableObj;
    @FXML private TableColumn<ObjBean, Integer> fach;
    @FXML private TableColumn<ObjBean, String> typ;
    @FXML private TableColumn<ObjBean, String> hersteller;
    @FXML private TableColumn<ObjBean, Date> inspektionsdatum;
    @FXML private TableColumn<ObjBean, Duration> haltbarkeit;
    @FXML private TableView<HerstellerBean> tableHersteller;
    @FXML private TableColumn<HerstellerBean, String> herstellerList;
    @FXML private TableView<AllergenBean> tableAllergene;
    @FXML private TableColumn<AllergenBean, String> allergeneList;

    private Console console;
    private String input;
    private Handler handler;

    private ObservableList<AllergenBean> allergenObservableList = FXCollections.observableArrayList(AllergenBean.extractor());
    private ObservableList<ObjBean> objObservableList = FXCollections.observableArrayList(ObjBean.extractor());
    private ObservableList<HerstellerBean> herstellerObservableList = FXCollections.observableArrayList(HerstellerBean.extractor());


    public void updateObsList(List<Hersteller> herstellerList, List<Obj> objList, List<Allergen> allergenList){
        herstellerObservableList.clear();
        for (Hersteller h : herstellerList){
            herstellerObservableList.add(new HerstellerBean(h));
        }
        objObservableList.clear();
        for (Obj o : objList){
            objObservableList.add(new ObjBean(o));
        }
        allergenObservableList.clear();
        for (Allergen a : allergenList){
            allergenObservableList.add(new AllergenBean(a));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableHersteller.setItems(herstellerObservableList);
        this.herstellerList.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableObj.setItems(objObservableList);
        this.fach.setCellValueFactory(new PropertyValueFactory<>("fach"));
        this.typ.setCellValueFactory(new PropertyValueFactory<>("kuchentyp"));
        this.hersteller.setCellValueFactory(new PropertyValueFactory<>("hersteller"));
        this.inspektionsdatum.setCellValueFactory(new PropertyValueFactory<>("inspDate"));
        this.haltbarkeit.setCellValueFactory(new PropertyValueFactory<>("haltbarkeit"));

        tableAllergene.setItems(allergenObservableList);
        this.allergeneList.setCellValueFactory(new PropertyValueFactory<>("allergenString"));

        tableObj.setRowFactory(tv -> {
            TableRow<ObjBean> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.putString(index.toString());
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                    event.consume();
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    int sourceFach = Integer.parseInt(db.getString());
                    int targetFach = row.getIndex();
                    if (sourceFach != targetFach) {
                        ObjBean sourceObjBean = objObservableList.get(sourceFach);
                        ObjBean targetObjBean = objObservableList.get(targetFach);
                        sourceObjBean.setFach(targetFach);
                        targetObjBean.setFach(sourceFach);
                        objObservableList.set(sourceFach, targetObjBean);
                        objObservableList.set(targetFach, sourceObjBean);
                        success = true;
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            });

            return row;
        });


    }

    public void onSubmit(javafx.event.ActionEvent actionEvent) {
        this.input = commandField.getText();
        console.execController(input);
        commandField.clear();
    }

    public void handleCommandField(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            this.input = commandField.getText();
            commandField.clear();
            if(input.startsWith(":")) {
                switch (input) {
                    case ":c": mode.setText("Create");
                        break;
                    case ":d": mode.setText("Delete");
                        break;
                    case ":u": mode.setText("Update");
                        break;
                    case ":p": mode.setText("Persi");
                        break;
                    case ":r": input = "";
                        break;
                    default:
                        break;
                }
            }
            console.execController(input);
        }
    }


    @FXML
    void handleLoadJOS(javafx.event.ActionEvent actionEvent) {
        LoadFileEvent event = new LoadFileEvent( "", "JOS" );
        this.handler.handle(event);
    }

    @FXML
    void handleSaveJOS(javafx.event.ActionEvent actionEvent) {
        SaveFileEvent event = new SaveFileEvent( "", "JOS" );
        this.handler.handle(event);
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }


}
