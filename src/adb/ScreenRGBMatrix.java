package adb;


import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;




public class ScreenRGBMatrix
{
	//private static int[][][][][] RGBXY = new int[256][256][256][500][750];
	static BufferedImage img = null;
	public static void ScreenShot()
	{
		
		do{
		Commands.Command(new String[]{"adb", "shell", "screencap", "-p", "/sdcard/screen.png"});
		Commands.Command(new String[]{"adb", "pull", "/sdcard/screen.png"});
		Commands.Command(new String[]{"adb", "shell", "rm", "/sdcard/screen.png"});
		System.out.println("click");
		}while(!new File("screen.png").exists());
		
	}
	public static Image getScreen(){
		try
		{
			img = ImageIO.read(new File("screen.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return img;
	}
	public static String getScreenLocation(){
		return "screen.png";
	}
	/*private static void SetRGBXYMatrix(){
		GetImage();
		int z,y,x,w,v = 0;
		for(z=0 ; z<256;z++){
			for(y=0 ; y<256;y++){
				for(x=0 ; x<256;x++){
					for(w=0 ; w<256;w++){
						for(v=0 ; v<256;v++){
							RGBXY[z][y][x][w][v] = img.getRGB(w, v);
						}
					}
				}
			}
			
		}
	}*/
	@SuppressWarnings("unused")
	private static Color GetRGBXYvalue(int x,int y){
		GetImage();
		int clr=  img.getRGB(x,y); 
		  int  red   = (clr & 0x00ff0000) >> 16;
		  int  green = (clr & 0x0000ff00) >> 8;
		  int  blue  =  clr & 0x000000ff;
		  Color c = new Color(red, green, blue);
		return c;
	}
	private static void GetImage()
	{
		try
		{
			img = ImageIO.read(new File("C:\\Users\\User\\workspace\\ClashRoyale\\images\\Images\\ScreenShots\\screen.png"));
			//image = Mat.
		} catch (IOException e)
		{
			System.out.println("Error");
		}
	}
}
