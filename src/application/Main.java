package application;

import static javafx.geometry.HPos.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

public class Main extends Application
{
	@FXML
	ChoiceBox<String> choiceBox;

	public void start(Stage stage) throws IOException
	{ /*
		 * primaryStage.setTitle("Java FX"); GridPane grid = new GridPane();
		 * grid.setAlignment(Pos.TOP_CENTER);
		 * grid.getStyleClass().add("application.css");
		 * //grid.setGridLinesVisible(true);
		 * Label battle = new Label("Battle"); grid.add(battle, 2, 1);
		 * 
		 * Label clan = new Label("Clan"); grid.add(clan, 3, 1);
		 * 
		 * Label decks = new Label("Decks"); grid.add(decks, 4, 1);
		 * 
		 * Label tournaments = new Label("Tournaments"); grid.add(tournaments,
		 * 5, 1);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * btn.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent e) {
		 * actiontarget.setFill(Color.FIREBRICK);
		 * actiontarget.setText("Sign in button pressed"); } }); Scene scene =
		 * new Scene(grid, 600, 150); primaryStage.setScene(scene);
		 * scene.getStylesheets().add
		 * (Main.class.getResource("application.css").toExternalForm());
		 * primaryStage.show();
		 */
		Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
		//initalize();
		Scene scene = new Scene(root, 300, 275);
		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void initalize()
	{
		choiceBox.setItems(FXCollections.observableArrayList("apples", "oranges"));
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
