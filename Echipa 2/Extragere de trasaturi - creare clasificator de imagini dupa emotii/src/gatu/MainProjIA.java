package gatu;

import java.io.File;
import java.io.IOException;



public class MainProjIA 
{
	private static final String libfile = new String("src/Project/lib/opencv_java248.dll");
	private static final String sPath = new String("src/Project/Resurse/poze/Smile/");
	private static final String nsPath = new String("src/Project/Resurse/Poze/NotSmile/");
	
	public MainProjIA(String cale) 
	{
		
		
		//System.out.println("Hello OpenCV !");
		System.load(new File(libfile).getAbsolutePath());
		//new DetectFaces().detect(testPath);
		
		
		SmileDetect sd = new SmileDetect();
			
		File f = new File(cale);
		sd.detectSmile(f.getAbsolutePath());
		
		
		/*
		
		System.out.println("Happy set : ");
		for (File file : new File(sPath).listFiles()) 
		{
			sd.detectSmile(file.getAbsolutePath());
			
			//System.out.println(file.getAbsolutePath());
			System.out.println();
			
		}
		
		System.out.println("Sad set : ");
		for (File file : new File(nsPath).listFiles()) 
		{
			sd.detectSmile(file.getAbsolutePath());
			
			//System.out.println(file.getAbsolutePath());
			System.out.println();
			
		}
		
		*/
	}
}
