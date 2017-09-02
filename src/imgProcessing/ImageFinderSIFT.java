package imgProcessing;

import java.io.File;
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

public class ImageFinderSIFT
{
	protected static float nndrRatio = 0.5f;
	public static Mat scene_corners;

	public static boolean ScanForImageExist(String scanObjectLocation)
	{
		return ScanForImageExist(scanObjectLocation, "screen.png");

	}

	public static boolean ScanForImageExist(String scanObjectLocation, String backroundImageLocation)
	{

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

		String scanObject = scanObjectLocation;
		String screenShot = backroundImageLocation;
		if (!new File(scanObjectLocation).exists())
		{

			System.out.println(scanObjectLocation + " does not exist");
			System.exit(1);
		}
		else if (!new File(backroundImageLocation).exists())
		{

			System.out.println(backroundImageLocation + " does not exist");
			System.exit(1);
		}
		else
		{
			if (!new File(backroundImageLocation).exists() || !new File(scanObjectLocation).exists())
			{
				System.out.println("does not exist");
				System.exit(1);
			}
		}
		Mat objectImage = Highgui.imread(scanObject, Highgui.CV_LOAD_IMAGE_COLOR);
		Mat sceneImage = Highgui.imread(screenShot, Highgui.CV_LOAD_IMAGE_COLOR);

		MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
		FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SIFT);
		featureDetector.detect(objectImage, objectKeyPoints);
		@SuppressWarnings("unused")
		KeyPoint[] keypoints = objectKeyPoints.toArray();

		MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
		DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);

		// Create the matrix for output image.
		Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);
		Scalar newKeypointColor = new Scalar(255, 0, 0);

		Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);

		// Match object image with the scene image
		MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();
		MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();
		featureDetector.detect(sceneImage, sceneKeyPoints);
		descriptorExtractor.compute(sceneImage, sceneKeyPoints, sceneDescriptors);

		Mat matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);
		Scalar matchestColor = new Scalar(0, 255, 0);

		List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
		DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);

		descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);

		LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();

		for (int i = 0; i < matches.size(); i++)
		{
			MatOfDMatch matofDMatch = matches.get(i);
			DMatch[] dmatcharray = matofDMatch.toArray();
			DMatch m1 = dmatcharray[0];
			DMatch m2 = dmatcharray[1];

			if (m1.distance <= m2.distance * nndrRatio)
			{
				goodMatchesList.addLast(m1);

			}
		}
		if (goodMatchesList.size() >= 7)
		{
			System.out.println("Object Found!!!");
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

			Mat img = Highgui.imread(screenShot, Highgui.CV_LOAD_IMAGE_COLOR);

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
			System.out.println("found");
			return true;
		}
		else
		{
			System.out.println(" not found");
			return false;
		}

	}
}