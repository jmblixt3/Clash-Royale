package tests;




import adb.Commands;
import adb.ScreenRGBMatrix;



public class Test
{
	public static void main(String[] args){

		Commands.Command(new String[]{"adb","connect","192.168.1.18"});
		
		ScreenRGBMatrix.ScreenShot();
		System.exit(1);
		
		//Commands.Command("adb", "shell", "input", "tap", "39", "285");
		//ScreenRGBMatrix.ScreenShot();
	}
}