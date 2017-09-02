package imgProcessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import adb.Commands;

public class ScanFor
{
	public static int Arena()
	{
		// adb.ScreenRGBMatrix.ScreenShot();
		if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_hogmountain.png"))
		{
			return 10;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_barbarian.png"))
		{
			return 3;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_bone.png"))
		{
			return 2;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_builders.png"))
		{
			return 6;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_champion.png"))
		{
			return 11;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_goblin.png"))
		{
			return 1;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_ice.png"))
		{
			return 8;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_jungle.png"))
		{
			return 9;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_pekka.png"))
		{
			return 4;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_royal.png"))
		{
			return 7;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_spell.png"))
		{
			return 5;
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\arenas\\ui_icon_training.png"))
		{
			return 0;
		}
		else
		{
			return -1;
		}

	}

	public static String WinChest()
	{
		// adb.ScreenRGBMatrix.ScreenShot();
		if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\silver-chest.png"))
		{
			return "silver";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\epic-chest.png"))
		{
			return "clan";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\crown-chest.png"))
		{
			return "crown";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\giant-chest.png"))
		{
			return "giant";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\golden-chest.png"))
		{
			return "gold";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\legendary-chest.png"))
		{
			return "legendary";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\magical-chest.png"))
		{
			return "magical";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\super-magical-chest.png"))
		{
			return "super magical";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\wooden-chest.png"))
		{
			return "free";
		}
		else
		{
			return null;
		}
	}

	public static boolean FreeChest()
	{
		// adb.ScreenRGBMatrix.ScreenShot();
		if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\wooden-chest.png"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean CardRequest()
	{
		Commands.SwipeLeft();
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
		}
		// adb.ScreenRGBMatrix.ScreenShot();
		if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\icons\\icon_request_cards.png"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean DonateHog()
	{
		if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\d_cards\\hog_rider.png"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static String chestSlot(int slot)
	{
		String location;
		location = "images\\Images\\croped\\crop" + slot + ".png";
		int x;
		switch (slot)
		{
		case 1:
			x = 48;
			ScanFor.crop("images\\Images\\croped\\", "crop1.png", x, 540, 90, 121);
			break;
		case 2:
			x = 150;
			ScanFor.crop("images\\Images\\croped\\", "crop2.png", x, 540, 90, 121);
			break;
		case 3:
			x = 256;
			ScanFor.crop("images\\Images\\croped\\", "crop3.png", x, 540, 90, 121);
			break;
		case 4:
			x = 360;
			ScanFor.crop("images\\Images\\croped\\", "crop4.png", x, 540, 90, 121);
			break;
		default:
			x = 0;
			System.out.println("Not a valid chest slot");
			break;
		}
		System.out.println(slot);
		if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\silver-chest.png", location))
		{
			return "silver";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\epic-chest.png", location))
		{
			return "clan";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\crown-chest.png", location))
		{
			return "crown";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\giant-chest.png", location))
		{
			return "giant";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\golden-chest.png", location))
		{
			return "gold";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\legendary-chest.png", location))
		{
			return "legendary";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\magical-chest.png", location))
		{
			return "magical";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\super-magical-chest.png", location))
		{
			return "super magical";
		}
		else if (ImageFinderSIFT.ScanForImageExist("images\\Images\\ui\\chests\\wooden-chest.png", location))
		{
			return "free";
		}
		else
		{
			return null;
		}
	}

	public static void crop(String newLocation, int x, int y, int w, int h)
	{
		crop(newLocation, "crop.png", x, y, w, h);
	}

	public static void crop(String newLocation, String name, int x, int y, int w, int h)
	{
		try
		{
			//ystem.out.println("attempting read");
			Image src = imgProcessing.ScreenThread.getCurrentScreen();
			System.out.println(imgProcessing.ScreenThread.getCurrentScreen());
			//System.out.println("read sucessful");
			BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
			//System.out.println("attempting write");
			ImageIO.write(dst, "png", new File(newLocation + name));
			//System.out.println("write sucessful");
		} catch (IOException e)
		{
			System.out.println("File " + imgProcessing.ScreenThread.getCurrentScreen()+ " does not exist");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
