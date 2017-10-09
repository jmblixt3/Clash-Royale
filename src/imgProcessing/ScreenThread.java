package imgProcessing;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import adb.Commands;

public class ScreenThread extends Thread
{
	private static boolean isactive = true;
	private static Image img = null;

	public void run()
	{
		System.out.print(isactive);
		while (Isactive())
		{
			ScreenShot();
			// Sleep for a while
		}
		try
		{
			ScreenThread.sleep(100);
		} catch (InterruptedException e)
		{
			// Interrupted exception will occur if
			// the Worker object's interrupt() method
			// is called. interrupt() is inherited
			// from the Thread class.
		}
		System.out.println("no click");
	}

	private static boolean Isactive()
	{
		return isactive;
	}

	private void setIsactive(boolean isactive)
	{
		ScreenThread.isactive = isactive;
	}

	public void Start()
	{
		setIsactive(true);
	}

	public void Stop()
	{
		setIsactive(false);
	}

	private static void ScreenShot()
	{

		// do{
		//System.out.println("click");
		Commands.Command(new String[]
		{ "adb", "shell", "screencap", "-p", "/sdcard/screen.png" });
		Commands.Command(new String[]
		{ "adb", "pull", "/sdcard/screen.png" });
		Commands.Command(new String[]
		{ "adb", "shell", "rm", "/sdcard/screen.png" });
		System.out.println("click");
		// }while(!new File("screen.png").exists());
		setCurrentScreen();
	}

	public static Image getCurrentScreen()
	{
		return img;
	}

	public static String getScreenLocation()
	{
		return "screen.png";
	}

	private static void setCurrentScreen()
	{
		try
		{
			img = ImageIO.read(new File("screen.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
