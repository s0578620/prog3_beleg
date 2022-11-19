package Gui;

import CLI.console;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class Controller {
    private CLI.console console;

    @FXML private TextField commandField;
    @FXML private Button buttonSubmit;

    String input;

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
