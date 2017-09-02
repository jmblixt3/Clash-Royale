package imgProcessing;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import adb.Commands;

public class ScreenThread extends Thread
{
	private static boolean isactive =true;
	private static Image img = null;
	public void run()
	{

		// Loop for ten iterations.
		while(isIsactive())
		{
			adb.ScreenRGBMatrix.ScreenShot();
			System.out.println("click");
			// Sleep for a while
			setCurrentScreen();
			try
			{
				ScreenThread.sleep(200);
			} catch (InterruptedException e)
			{
				// Interrupted exception will occur if
				// the Worker object's interrupt() method
				// is called. interrupt() is inherited
				// from the Thread class.
				break;
			}
		}
	}
	public static boolean isIsactive()
	{
		return isactive;
	}
	public static void setIsactive(boolean isactive)
	{
		ScreenThread.isactive = isactive;
	}
	public static void ScreenShot()
	{
		
		do{
		Commands.Command(new String[]{"adb", "shell", "screencap", "-p", "/sdcard/screen.png"});
		Commands.Command(new String[]{"adb", "pull", "/sdcard/screen.png"});
		Commands.Command(new String[]{"adb", "shell", "rm", "/sdcard/screen.png"});
		System.out.println("click");
		}while(!new File("screen.png").exists());
		
	}
	public static Image getCurrentScreen(){
			return img;
	}
	public static String getScreenLocation(){
		return "screen.png";
	}
	public static void setCurrentScreen(){
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
