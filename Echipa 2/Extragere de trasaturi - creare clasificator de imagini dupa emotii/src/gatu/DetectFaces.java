package gatu;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

//import static org.opencv.imgcodecs.Imgcodecs.imread;
//import static org.opencv.imgcodecs.Imgcodecs.imwrite;
//import static org.opencv.imgproc.Imgproc.rectangle;

public class DetectFaces 
{
	private String fileCC, fileFD;
	
	public DetectFaces()
	{
		fileCC = new String("C:\\Users\\gatuvlad\\Downloads\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
		fileFD = new String("C:\\Users\\gatuvlad\\Downloads\\opencv\\people-5.jpg");
	}
	
	public void detect()
	{
		System.out.println("Runing FaceDetector ...");
		CascadeClassifier faceDetector = new CascadeClassifier(fileCC);
		Mat image = Highgui.imread(fileFD);
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		for(Rect rect : faceDetections.toArray())
		{
			Core.rectangle(image,new Point(rect.x,rect.y), new Point(rect.x+rect.width, rect.y+rect.height),new Scalar(0,255,0));
		}
		
		String filename = "faceDetection.jpg";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename,image);
	}
	
	public void detect(String filename)
	{
		System.out.println("Runing FaceDetector ...");
		CascadeClassifier faceDetector = new CascadeClassifier(fileCC);
		Mat image = Highgui.imread(new File(filename).getAbsolutePath());
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		for(Rect rect : faceDetections.toArray())
		{
			Core.rectangle(image,new Point(rect.x,rect.y), new Point(rect.x+rect.width, rect.y+rect.height),new Scalar(0,255,0));
		}
		
		String fn = "faceDetection.jpg";
		System.out.println(String.format("Writing %s", fn));
		Highgui.imwrite(fn,image);
	}
}
