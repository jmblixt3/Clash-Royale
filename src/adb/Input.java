package adb;

import java.io.IOException;

import imgProcessing.*;


public class Input
{
	public static void GetScreen() throws InterruptedException, IOException
	{
		System.out.println("get Screen");
		
		System.out.println(ScanFor.chestSlot(1));
		System.out.println(ScanFor.chestSlot(2));
		System.out.println(ScanFor.chestSlot(3));
		System.out.println(ScanFor.chestSlot(4));

	}


}
