package adb;

import java.io.IOException;
import java.lang.ProcessBuilder;

public class Commands

{
	static String ip = "192.168.1.18";

	public static void Command(String[] command)
	{
		Process builder =
		null;
		try
		{
			builder = new ProcessBuilder(command).start();
			builder.waitFor();
		} catch (IOException | InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		builder.destroy();
		
		
	}

	

	public static void Touch(double x, double y)
	{
		Commands.Command(new String[]
		{ "adb", "shell", "input", "tap", String.valueOf(x), String.valueOf(y) });
	}

	public static void TouchSelectedObject()
	{
		Touch(imgProcessing.CenterObject.Avgx(), imgProcessing.CenterObject.Avgy());
		System.out.println(imgProcessing.CenterObject.Avgx() + " " + imgProcessing.CenterObject.Avgy());
	}

	public static void OpenChest()
	{
		ScreenRGBMatrix.ScreenShot();
		imgProcessing.ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\icons\\icons_chests_startUnlock.png");
		Commands.TouchSelectedObject();
	}

	public static void Swipe(String x1, String y1, String x2, String y2)
	{
		Commands.Command(new String[]
		{ "adb", "shell", "input", "swipe", x1, y1, x2, y2 });
	}

	public static void SwipeRight()
	{
		Commands.Swipe("100", "300", "400", "300");
	}

	public static void SwipeLeft()
	{
		Commands.Swipe("400", "300", "100", "300");
	}

	public static void Connect()
	{
		Commands.Command(new String[]
		{ "adb", "connect", ip });
	}

	public static void Home()
	{
		Command(new String[]
		{ "adb", "shell", "am", "start", "-c", "android.intent.category.HOME", "-a", "android.intent.action.MAIN" });
	}

	public static void OpenCR()
	{
		ScreenRGBMatrix.ScreenShot();
		imgProcessing.ImageFinderSIFT.ScanForImageExist("images\\Images\\icon\\Rounded_1024x1024.png");
		Commands.TouchSelectedObject();
		waitForDevice();
	}

	public static void waitForDevice()
	{
		Command(new String[]
		{ "adb", "wait-for-device" });
	}
	// public static
}
