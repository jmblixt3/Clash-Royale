package adb;

public class WTPassword
{
	public static void GuessPassword(){
		Commands.SwipeLeft();
		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Commands.SwipeLeft();
		Commands.SwipeLeft();
		Commands.Touch(320, 20);
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Commands.Touch(100, 65);
		
		Commands.Touch(200, 220);
		
		Commands.Touch(370, 250);
	}
}
