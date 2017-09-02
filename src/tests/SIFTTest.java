package tests;

import java.io.File;

public class SIFTTest
{
	public static void main(String[] args){
		File lib = null;
		String os = System.getProperty("os.name");
		String bitness = System.getProperty("sun.arch.data.model");

		if (os.toUpperCase().contains("WINDOWS"))
		{
			if (bitness.endsWith("64"))
			{
				lib = new File("libs//opencv//build//java//x64//" + System.mapLibraryName("opencv_java2411"));
			}
			else
			{
				lib = new File("libs//opencv//build//java//x86//" + System.mapLibraryName("opencv_java2411"));
			}
		}
		System.load(lib.getAbsolutePath());
		String scanObject = "images\\Images\\ui\\chests\\silver-chest.png";
		String screenShot = "screen.png";
	}
}
