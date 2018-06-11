package application;

import java.io.IOException;
import java.util.ArrayList;

import adb.Commands;
import imgProcessing.ScreenThread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class FXMLC extends VBox
{
	// @FXML private TextField textField;
	@FXML
	private ChoiceBox<String> choiceBox;
	@FXML
	private CheckBox TournamentHack;
	@FXML
	private Button StartButton;
	private boolean started;
	private boolean hasHackStarted = false;
	private ScreenThread thread = new ScreenThread();
	// @FXML private ChoiceBox<String> choiceBox;

	public boolean getHackStarted()
	{
		return hasHackStarted;
	}

	public void setHackStarted(boolean hasStartedHackThread)
	{
		this.hasHackStarted = hasStartedHackThread;
	}

	public FXMLC()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NEWHOME.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try
		{
			fxmlLoader.load();
		} catch (IOException exception)
		{
			throw new RuntimeException(exception);
		}
		ChoiceBoxInit();
		thread.start();
		System.out.println("thread is active");
	}

	/*
	 * public String getText() { return textProperty().get(); }
	 * 
	 * public void setText(String value) { textProperty().set(value); }
	 * 
	 * public StringProperty textProperty() { return textField.textProperty(); }
	 */
	public void ChoiceBoxInit()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("Item A");
		list.add("Item B");
		list.add("Item C");
		ObservableList<String> choices = FXCollections.observableArrayList(list);
		if (choiceBox == null)
			System.exit(1);
		choiceBox.setItems(choices);
	}

	public boolean TournamentHackBool()
	{
		return TournamentHack.isSelected();
	}

	private void start()
	{
		started = true;
	}

	private void stop()
	{
		started = false;
	}

	public boolean isHackActive()
	{
		return started;
	}

	@FXML
	protected void StartHack()
	{
		start();
		System.out.println("Hack Started");
		thread.Start();
	}
	@FXML
	protected void ResumeHack()
	{
		Commands.Connect();
		System.out.println("connected");
		start();
		System.out.println("Hack Started");
		thread.Start();
	}
	
	@FXML
	protected void Kill()
	{
		Commands.Kill();
	}
	@FXML
	protected void StopHack()
	{
		stop();
		System.out.println("Hack Stopped");
		thread.Stop();
	}

	public void ScreenThreadStop()
	{
		thread.Stop();
	}
}
