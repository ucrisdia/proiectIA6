package gatu;

import java.util.ArrayList;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.ml.CvSVM;
import org.opencv.ml.CvSVMParams;
import org.opencv.core.CvType;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class SmileDetect 
{
	
	private CascadeClassifier faceDetector;
	private CascadeClassifier mouthDetector;
	private CascadeClassifier smileDetector;
	private Mat image;
	private Mat trainingImages;
	private Mat trainingLabels;
	private CvSVM clasificator;
	private static final String trainPath = "src/Project/Resurse/train/";
	private static final Size trainSize = new Size(50, 30);
	private static final String facexml = new String("src/Project/Resurse/haarcascade_frontalface_alt.xml");
	private static final String mouthxml = new String("src/Project/Resurse/haarcascade_mcs_mouth.xml");
	private int nrOfFaces;
	
	private void init()
	{
		nrOfFaces = 0;
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		faceDetector = new CascadeClassifier(facexml);
		mouthDetector = new CascadeClassifier(mouthxml);
		trainingImages = new Mat();
		trainingLabels = new Mat();
	}
	
	public Point detectSmile(String filename) 
	{
		init();
		return detectSmile(detectMouth(filename));
	}
	
	private Point detectSmile(ArrayList<Mat> mouths) 
	{
		trainSVM();
		CvSVMParams params = new CvSVMParams();
		params.set_kernel_type(CvSVM.LINEAR);
		clasificator = new CvSVM(trainingImages, trainingLabels, new Mat(), new Mat(), params);
		clasificator.save("svm.xml");
		clasificator.load("svm.xml");
		
		if (mouths.isEmpty()) 
		{
			System.out.println("No mouth detected");
			return new Point(0,0);
		}
		
		int chf = 0; // count happy faces
		
		for (Mat mouth : mouths) 
		{
			Mat out = new Mat();
			mouth.convertTo(out, CvType.CV_32FC1);
			if (clasificator.predict(out.reshape(1, 1)) == 1.0) chf++;
				//System.out.println("Detected happy face");
			//else System.out.println("Detected not a happy face");
		}
		if(chf > nrOfFaces) return new Point(nrOfFaces, nrOfFaces);
		return new Point(nrOfFaces, chf);
	}
	
	private ArrayList<Mat> detectMouth(String filename) 
	{
		int i = 0;
		ArrayList<Mat> mouths = new ArrayList<Mat>();
		image = Highgui.imread(filename, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		//Mat image2 = Highgui.imread(filename);
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		//System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		MatOfRect mouthDetections = new MatOfRect();
		mouthDetector.detectMultiScale(image, mouthDetections);
		//System.out.println(String.format("Detected %s mouths", mouthDetections.toArray().length));
		
		//Mat img_cpy = image;
		//image.copyTo(img_cpy);
		nrOfFaces = faceDetections.toArray().length;
		
		for (Rect face : faceDetections.toArray()) 
		{
			Mat outFace = image.submat(face);
			Highgui.imwrite("face" + i + ".png", outFace);
			
			//Core.rectangle(image2,new Point(face.x,face.y), new Point(face.x+face.width, face.y+face.height),new Scalar(0,0,255));
			
			for (Rect mouth : mouthDetections.toArray()) 
			{
				if (mouth.y > (face.y + face.height * 3 / 5) && mouth.y + mouth.height <= face.y + face.height
						&& Math.abs((mouth.x + mouth.width / 2)) - (face.x + face.width / 2) < face.width / 10) 
				{
					Mat outMouth = image.submat(mouth);
					Imgproc.resize(outMouth, outMouth, trainSize);
					mouths.add(outMouth);
					//Highgui.imwrite("mouth" + i + ".png", outMouth);
					i++;
					//Core.rectangle(image2,new Point(mouth.x,mouth.y), new Point(mouth.x+mouth.width, mouth.y+mouth.height),new Scalar(0,0,255));
				}
			}
		}
		
		/*
		String fn = "mouthDetect.jpg";
		System.out.println(String.format("Writing %s", fn));
		Highgui.imwrite(fn,image2);
		*/
		
		return mouths;
	}
	
	
	private void trainSVM() 
	{
		train("positive");
		train("negative");
	}
	
	
	private void train(String flag) 
	{
		String path;
		if (flag.equalsIgnoreCase("positive")) 
			path = trainPath + "smile/";
		else path = trainPath + "neutral/";
		
		for (File file : new File(path).listFiles()) 
		{
			Mat img = new Mat();
			Mat con = Highgui.imread(file.getAbsolutePath(), Highgui.CV_LOAD_IMAGE_GRAYSCALE);
			con.convertTo(img, CvType.CV_32FC1, 1.0 / 255.0);
			Imgproc.resize(img, img, trainSize);
			trainingImages.push_back(img.reshape(1, 1));
			if (flag.equalsIgnoreCase("positive")) 
				trainingLabels.push_back(Mat.ones(new Size(1, 1), CvType.CV_32FC1));
			else trainingLabels.push_back(Mat.zeros(new Size(1, 1), CvType.CV_32FC1));
		}
	}

}
