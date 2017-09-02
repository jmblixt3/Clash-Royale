package imgProcessing;

import org.opencv.core.Point;

public class CenterObject
{
	public static Point[] objectlocation = new Point[3];


	protected static Point[] getObjectlocation()
	{
		return objectlocation;
	}
	protected static void setObjectlocation()
	{
		for(int i=0; i<3;i++){	
		if(!(ImageFinderSIFT.scene_corners.get(i,0) == null))
			CenterObject.objectlocation[i] = new Point(ImageFinderSIFT.scene_corners.get(i,0));
		else if(!(ImageFinderSIFT.scene_corners.get(i, 0)==null))
			CenterObject.objectlocation[i] = new Point(ImageFinderSIFT.scene_corners.get(i,0));
			// TODO Auto-generated catch block
		else
			throw new NullPointerException("Parameter Type cannot be null");
		
		}
	}
	private static Point Average(){
		setObjectlocation();
		double avgx = 0;
		double avgy = 0;
		for(int i=0; i<3;i++){
			avgx += CenterObject.objectlocation[i].x;
			avgy += CenterObject.objectlocation[i].y;
			}
		return new Point(avgx/objectlocation.length,avgy/objectlocation.length);
	}
	public static double Avgx(){
		return Average().x;
	}
	public static double Avgy(){
		return Average().y;
	}
	public static String Avgxs(){
		return String.valueOf(Average().x);
	}
	public static String Avgys(){
		return String.valueOf(Average().y);
	}
}
