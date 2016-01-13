package gatu;

import java.io.File;
import java.io.IOException;

import org.opencv.core.Point;



public class MainProjIA 
{
	private static final String libfile = new String("src/Project/lib/opencv_java248.dll");
	private static final String sPath = new String("src/Project/Resurse/poze/Smile/");
	private static final String nsPath = new String("src/Project/Resurse/Poze/NotSmile/");

	public Point getResult(String cale)
	{
		System.load(new File(libfile).getAbsolutePath());
		SmileDetect sd = new SmileDetect();

		File f = new File(cale);
		Point p = sd.detectSmile(f.getAbsolutePath());
		
		return p;
	}

	public MainProjIA() 
	{}
}
