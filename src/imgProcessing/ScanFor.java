package imgProcessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import adb.Commands;

public class ScanFor
{
	public static int Arena()
	{
		String location;
		location = "images\\Images\\croped\\arena.png";
		crop("images\\Images\\croped\\arena.png", 109, 131, 244, 291);
		int[] matches = new int[11];
		matches[10] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_hogmountain.png", location);
		matches[3] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_barbarian.png", location);
		matches[2] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_bone.png", location);
		matches[6] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_builders.png", location);
		matches[11] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_champion.png", location);
		matches[1] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_goblin.png", location);
		matches[8] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_ice.png", location);
		matches[9] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_jungle.png", location);
		matches[4] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_pekka.png", location);
		matches[7] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_royal.png", location);
		matches[5] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_spell.png", location);
		matches[0] = tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_training.png", location);
		
		
		int max = 0;
		int maxc = 0;
		for (int counter = 1; counter < matches.length; counter++)
		{
			if (matches[counter] > max)
			{
				max = matches[counter];
				maxc = counter;
			}
		}
		return maxc;
	}	/*
	 * public static int Arena() { // adb.ScreenRGBMatrix.ScreenShot(); if
	 * (tests.SIFTTest.test(
	 * "images\\Images\\ui\\arenas\\ui_icon_hogmountain.png")) { return 10; }
	 * else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_barbarian.png")
	 * ) { return 3; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_bone.png")) {
	 * return 2; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_builders.png"))
	 * { return 6; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_champion.png"))
	 * { return 11; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_goblin.png")) {
	 * return 1; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_ice.png")) {
	 * return 8; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_jungle.png")) {
	 * return 9; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_pekka.png")) {
	 * return 4; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_royal.png")) {
	 * return 7; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_spell.png")) {
	 * return 5; } else if
	 * (tests.SIFTTest.test("images\\Images\\ui\\arenas\\ui_icon_training.png"))
	 * { return 0; } else { return -1; }
	 * 
	 * }
	 */

	/*
	 * public static boolean CardRequest() { Commands.SwipeLeft(); try {
	 * Thread.sleep(1000); } catch (InterruptedException e) { } //
	 * adb.ScreenRGBMatrix.ScreenShot(); if
	 * (tests.SIFTTest.test("images\\Images\\ui\\icons\\icon_request_cards.png")
	 * ) { return true; } else { return false; } }
	 */

	/*
	 * public static boolean DonateHog() { if
	 * (tests.SIFTTest.test("images\\Images\\ui\\d_cards\\hog_rider.png")) {
	 * return true; } else { return false; } }
	 */

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
		System.out.println(slot + " " + location);
		int[] matches = new int[8];
		matches[1] = tests.SIFTTest.test("images\\Images\\ui\\chests\\silver-chest.png", location);
		matches[2] = tests.SIFTTest.test("images\\Images\\ui\\chests\\epic-chest.png", location);
		matches[3] = tests.SIFTTest.test("images\\Images\\ui\\chests\\giant-chest.png", location);
		matches[4] = tests.SIFTTest.test("images\\Images\\ui\\chests\\golden-chest.png", location);
		matches[5] = tests.SIFTTest.test("images\\Images\\ui\\chests\\legendary-chest.png", location);
		matches[6] = tests.SIFTTest.test("images\\Images\\ui\\chests\\magical-chest.png", location);
		matches[7] = tests.SIFTTest.test("images\\Images\\ui\\chests\\super-magical-chest.png", location);
		int max = matches[0];
		int maxc = 0;
		for (int counter = 1; counter < matches.length; counter++)
		{
			if (matches[counter] > max)
			{
				max = matches[counter];
				maxc = counter;
			}
		}
		System.out.println("The highest maximum for slot " + slot + " " + maxc);
		switch (maxc)
		{
		case 1:
			return "silver";
		case 2:
			return "epic";
		case 3:
			return "giant";
		case 4:
			return "gold";
		case 5:
			return "legendary";
		case 6:
			return "magical";
		case 7:
			return "suoer-magical";
		default:
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
			// ystem.out.println("attempting read");
			Image src = imgProcessing.ScreenThread.getCurrentScreen();
			System.out.println(imgProcessing.ScreenThread.getScreenLocation());
			/*
			 * try { ImageIO.write((RenderedImage) src,"png", new
			 * File("crop.png")); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); System.exit(1); }
			 */
			// System.out.println("read sucessful");
			BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
			// System.out.println("attempting write");
			ImageIO.write(dst, "png", new File(newLocation + name));
			// System.out.println("write sucessful");
		} catch (IOException e)
		{
			System.out.println("File " + imgProcessing.ScreenThread.getCurrentScreen() + " does not exist");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
