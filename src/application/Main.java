package application;

import static javafx.geometry.HPos.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{  primaryStage.setTitle("Java FX");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_CENTER);
    grid.getStyleClass().add("application.css");
    //grid.setGridLinesVisible(true);

    Label chests = new Label("Chests");
    grid.add(chests, 1, 1);

    Label battle = new Label("Battle");
    grid.add(battle, 2, 1);

    Label clan = new Label("Clan");
    grid.add(clan, 3, 1);
    
    Label decks = new Label("Decks");
    grid.add(decks, 4, 1);
    
    Label tournaments = new Label("Tournaments");
    grid.add(tournaments, 5, 1);
    
 
    
    

    /*btn.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent e) {
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText("Sign in button pressed");
        }
    });*/
    Scene scene = new Scene(grid, 600, 150);
    primaryStage.setScene(scene);
    scene.getStylesheets().add
     (Main.class.getResource("application.css").toExternalForm());
    primaryStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
