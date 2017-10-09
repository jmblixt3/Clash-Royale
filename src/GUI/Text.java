package GUI;

import java.io.IOException;

import adb.Commands;
import imgProcessing.ScreenThread;

public class Text
{
	public static void main(String[] args) throws InterruptedException, IOException{
		Commands.Connect();
		System.out.println("connected");
		ScreenThread thread = new ScreenThread();
		thread.start();
		adb.Input.GetScreen();
		//ScreenThread.setIsactive(false);
	}
}
