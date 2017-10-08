package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FXMLC extends VBox {
    //@FXML private TextField textField;
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private CheckBox TournamentHack;
    @FXML private Button StartButton;
    private boolean started;
    //@FXML private ChoiceBox<String> choiceBox;

    public FXMLC() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NEWHOME.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        ChoiceBoxInit();
    }

   /* public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }*/
    public void ChoiceBoxInit(){
    	ArrayList<String> list = new ArrayList<String>();
        list.add("Item A");
        list.add("Item B");
        list.add("Item C");
    	ObservableList<String> choices = FXCollections.observableArrayList(list);
    	if(choiceBox == null)
    		System.exit(1);
    	choiceBox.setItems(choices);
    }
    public boolean TournamentHackBool(){
    	return TournamentHack.isSelected();
    }
    private void start(){
    	started = true;
    }
    private void stop(){
    	started = false;
    }
    public boolean isHackActive(){
    	return started;
    }
    @FXML
    protected void StartHack() {
    	start();
        System.out.println("Hack Started");
    }
    @FXML
    protected void StopHack() {
    	stop();
        System.out.println("Hack Stopped");
    }
}
