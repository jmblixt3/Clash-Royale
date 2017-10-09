package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import adb.Commands;
import adb.WTPassword;
import imgProcessing.ScreenThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application
{

	@FXML
	private ChoiceBox<String> choiceBox;

	@Override
	public void start(Stage stage) throws InterruptedException, IOException
	{

		FXMLC home = new FXMLC();
		stage.setScene(new Scene(home));
		stage.setTitle("Custom Control");
		// stage.setWidth(700);
		// stage.setHeight(500);
		stage.show();
		startTask(home);
		/*
		 * ObservableList<String> list =
		 * FXCollections.observableArrayList(cities);
		 * System.out.println(list.get(0));
		 */
		// choiceBox.setItems(FXCollections.observableArrayList("moo"));
		/*
		 * Observable choiceBox.setItems
		 */
	}

	public void startTask(final FXMLC home)
	{
		// Create a Runnable
		Runnable task = new Runnable()
		{
			public void run()
			{
				runTask(home);
			}
		};

		// Run the task in a background thread
		Thread backgroundThread = new Thread(task);
		// Terminate the running thread if the application exits
		backgroundThread.setDaemon(true);
		// Start the thread
		backgroundThread.start();
	}

	public void runTask(final FXMLC home)
	{
		Commands.Connect();
		System.out.println("connected");
		while (true)
		{
			try
			{
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						Task(home);
					}
				});

				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public void Task(FXMLC home)
	{
		if (home.isHackActive())
		{
			if (home.TournamentHackBool())
			{
				if(!home.getHackStarted()){
				home.ScreenThreadStop();
				WTPassword.GuessPassword();
				System.out.println("active");
				home.setHackStarted(true);
				}
			}
		}
		else
		{
			//home.ScreenThreadPause();
		}
	}
}