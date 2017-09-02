package tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

import imgProcessing.ImageGoodValues;

public class SIFTTest
{
	private static Mat objectImage;
	private static Mat sceneImage;
	private static MatOfKeyPoint objectKeyPoints;
	private static FeatureDetector featureDetector ;
	private static MatOfKeyPoint objectDescriptors;
	private static DescriptorExtractor descriptorExtractor;
	private static Mat outputImage;
	private static Scalar newKeypointColor;
	private static MatOfKeyPoint sceneKeyPoints;
	private static MatOfKeyPoint sceneDescriptors;
	private static Mat matchoutput;
	private static Scalar matchestColor= new Scalar(0, 255, 0);
	private static List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
	private static DescriptorMatcher descriptorMatcher;
	private static LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();
	private static MatOfDMatch matofDMatch;
	private static DMatch[] dmatcharray;
	private static DMatch m1,m2;
	private static Mat scene_corners;	
	private static double nndrRatio = 0.4;
	private static KeyPoint[] keypoints;
	private static int CurrentGoodPoint;
	
	public static int test(String scanObject){
		return test(scanObject, "screen.png");
	}
	
	public static int test(String scanObject,String backroundImage){
		init();
		if (!new File(scanObject).exists())
		{

			System.out.println(scanObject + " does not exist");
			System.exit(1);
		}
		else if (!new File(backroundImage).exists())
		{

			System.out.println(backroundImage + " does not exist");
			System.exit(1);
		}
		else
		{
			if (!new File(backroundImage).exists() || !new File(scanObject).exists())
			{
				System.out.println("does not exist");
				System.exit(1);
			}
		}
		
		objectImage = Highgui.imread(scanObject, Highgui.CV_LOAD_IMAGE_COLOR);
		sceneImage = Highgui.imread(backroundImage, Highgui.CV_LOAD_IMAGE_COLOR);
		
		objectKeyPoints = new MatOfKeyPoint();
		featureDetector = FeatureDetector.create(FeatureDetector.SIFT);
		featureDetector.detect(objectImage, objectKeyPoints);
		keypoints = objectKeyPoints.toArray();
		
		objectDescriptors = new MatOfKeyPoint();
		descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);
		
		outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);
		newKeypointColor = new Scalar(255, 0, 0);
	
		Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);	
		
		sceneKeyPoints = new MatOfKeyPoint();
		sceneDescriptors = new MatOfKeyPoint();
		featureDetector.detect(sceneImage, sceneKeyPoints);

		descriptorExtractor.compute(sceneImage, sceneKeyPoints, sceneDescriptors);
	
		matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);
		
		descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
		descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);
		while(!goodMatchesList.isEmpty()){
			int i = 0;
			goodMatchesList.remove(i);
			i++;
		}
		for (int i = 0; i < matches.size(); i++)
		{
			matofDMatch = matches.get(i);
			dmatcharray = matofDMatch.toArray();
			m1 = dmatcharray[0];
			m2 = dmatcharray[1];

			if (m1.distance <= m2.distance * nndrRatio)
			{
				goodMatchesList.addLast(m1);

			}
		}
		return goodMatchesList.size();
		/*if (goodMatchesList.size() >= 10)
		{
			//System.out.println("Object Found!!!");
			List<KeyPoint> objKeypointlist = objectKeyPoints.toList();
			List<KeyPoint> scnKeypointlist = sceneKeyPoints.toList();

			LinkedList<Point> objectPoints = new LinkedList<>();
			LinkedList<Point> scenePoints = new LinkedList<>();

			for (int i = 0; i < goodMatchesList.size(); i++)
			{
				objectPoints.addLast(objKeypointlist.get(goodMatchesList.get(i).queryIdx).pt);
				scenePoints.addLast(scnKeypointlist.get(goodMatchesList.get(i).trainIdx).pt);
			}

			MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
			objMatOfPoint2f.fromList(objectPoints);
			MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
			scnMatOfPoint2f.fromList(scenePoints);

			Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);

			Mat obj_corners = new Mat(4, 1, CvType.CV_32FC2);
			scene_corners = new Mat(4, 1, CvType.CV_32FC2);

			obj_corners.put(0, 0, new double[]
			{ 0, 0 });
			obj_corners.put(1, 0, new double[]
			{ objectImage.cols(), 0 });
			obj_corners.put(2, 0, new double[]
			{ objectImage.cols(), objectImage.rows() });
			obj_corners.put(3, 0, new double[]
			{ 0, objectImage.rows() });

			System.out.println("Transforming object corners to scene corners...");
			Core.perspectiveTransform(obj_corners, scene_corners, homography);

			Mat img = Highgui.imread(backroundImage, Highgui.CV_LOAD_IMAGE_COLOR);

			Core.line(img, new Point(scene_corners.get(0, 0)), new Point(scene_corners.get(1, 0)),
					new Scalar(0, 255, 0), 4);
			Core.line(img, new Point(scene_corners.get(1, 0)), new Point(scene_corners.get(2, 0)),
					new Scalar(0, 255, 0), 4);
			Core.line(img, new Point(scene_corners.get(2, 0)), new Point(scene_corners.get(3, 0)),
					new Scalar(0, 255, 0), 4);
			Core.line(img, new Point(scene_corners.get(3, 0)), new Point(scene_corners.get(0, 0)),
					new Scalar(0, 255, 0), 4);

			System.out.println("Drawing matches image...");
			MatOfDMatch goodMatches = new MatOfDMatch();
			goodMatches.fromList(goodMatchesList);

			Features2d.drawMatches(objectImage, objectKeyPoints, sceneImage, sceneKeyPoints, goodMatches, matchoutput,
					matchestColor, newKeypointColor, new MatOfByte(), 2);

			Highgui.imwrite("output//outputImage.jpg", outputImage);
			Highgui.imwrite("output//matchoutput.jpg", matchoutput);
			Highgui.imwrite("output//img.jpg", img);
			//System.out.println("found");
			//return ;
		}
		else
		{
			//System.out.println(" not found");
			//return false;
		}*/
	}

	private static void init(){
		File lib = null;
		String os = System.getProperty("os.name");
		String bitness = System.getProperty("sun.arch.data.model");

		if (os.toUpperCase().contains("WINDOWS"))
		{
			if (bitness.endsWith("64"))
			{
				lib = new File("libs//opencv//x64//" + System.mapLibraryName("opencv_java2411"));
			}
			else
			{
				lib = new File("libs//opencv//x86//" + System.mapLibraryName("opencv_java2411"));
			}
		}
		System.load(lib.getAbsolutePath());
	}
	
}
