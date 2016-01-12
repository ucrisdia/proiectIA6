package stefanuca_anghel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.*;  
  
public class ImageSplitTest {  
	private HashSet hset ;
	private ArrayList aList;
	private int vector[];
	
	
	ImageSplitTest(String caleImagine) throws IOException,FileNotFoundException, UnsupportedEncodingException {  
  	
		//codul care face blur la imagine
	      try {
	          System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	          
	          Mat source = Highgui.imread(caleImagine,Highgui.CV_LOAD_IMAGE_COLOR);
	          
	          Mat destination = new Mat(source.rows(),source.cols(),source.type());
	          Imgproc.GaussianBlur(source, destination,new Size(45,45), 0);
	          Highgui.imwrite("Gaussian1.jpg", destination);
	       
	       } catch (Exception e) {
	          System.out.println("Error: " + e.getMessage());
	       }
	      
	      try {
	          System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	          
	          Mat source = Highgui.imread("Gaussian1.jpg",Highgui.CV_LOAD_IMAGE_COLOR);
	          
	          Mat destination = new Mat(source.rows(),source.cols(),source.type());
	          Imgproc.GaussianBlur(source, destination,new Size(45,45), 0);
	          Highgui.imwrite("Gaussian2.jpg", destination);
	       
	       } catch (Exception e) {
	          System.out.println("Error: " + e.getMessage());
	       }
	      
	      try {
	          System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	          
	          Mat source = Highgui.imread("Gaussian2.jpg",Highgui.CV_LOAD_IMAGE_COLOR);
	          
	          Mat destination = new Mat(source.rows(),source.cols(),source.type());
	          Imgproc.GaussianBlur(source, destination,new Size(45,45), 0);
	          Highgui.imwrite("Gaussian3.jpg", destination);
	       
	       } catch (Exception e) {
	          System.out.println("Error: " + e.getMessage());
	       }
	    	     
		// end blur imagine
		
		
		
        File file = new File("Gaussian3.jpg"); 
        FileInputStream fis = new FileInputStream(file);  
        BufferedImage image = ImageIO.read(fis); 
        
        int rows = 3; 
        int cols = 3;  
        int chunks = rows * cols;  
  
        int chunkWidth = image.getWidth() / cols;  
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks];   
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
  
        for (int i = 0; i < imgs.length; i++) {  
            ImageIO.write(imgs[i], "jpg", new File("img" + i + ".jpg"));  
        }  
        
        hset = new HashSet<String>();
        aList = new ArrayList<>();
        for (int i = 0; i < chunks; i++) { 
      	  GlobalColorHistogram test =new GlobalColorHistogram("img"+i+".jpg");
      	  hset.add(test.genereazaCuloare());
        }
        
        
        
        this.vector = new int[24];
    	for(int i=0;i<24;i++)
		{
			this.vector[i]=0;
		}
        
    	Iterator iterator = hset.iterator(); 
        while (iterator.hasNext())
        {	
        	String str= iterator.next().toString();
        	
        	if(str.equals("interest"))
        		this.vector[0]=1;
        	else
        	if(str.equals("anticipation"))
        		this.vector[1]=1;
        	if(str.equals("vigilance"))
        		this.vector[2]=1;
        	if(str.equals("serenity"))
        		this.vector[3]=1;
        	if(str.equals("joy"))
        		this.vector[4]=1;
        	if(str.equals("ecstasy"))
        		this.vector[5]=1;
        	if(str.equals("acceptance"))
        		this.vector[6]=1;
        	if(str.equals("trust"))
        		this.vector[7]=1;
        	if(str.equals("admiration"))
        		this.vector[8]=1;
        	if(str.equals("apprehension"))
        		this.vector[9]=1;
        	if(str.equals("fear"))
        		this.vector[10]=1;
        	if(str.equals("terror"))
        		this.vector[11]=1;
        	if(str.equals("distraction"))
        		this.vector[12]=1;
        	if(str.equals("surprise"))
        		this.vector[13]=1;
        	if(str.equals("amazement"))
        		this.vector[14]=1;
        	if(str.equals("pensiveness"))
        		this.vector[15]=1;
        	if(str.equals("sadness"))
        		this.vector[16]=1;
        	if(str.equals("grief"))
        		this.vector[17]=1;
        	if(str.equals("boredom"))
        		this.vector[18]=1;
        	if(str.equals("disgust"))
        		this.vector[19]=1;
        	if(str.equals("loathing"))
        		this.vector[20]=1;
        	if(str.equals("annoyance"))
        		this.vector[21]=1;
        	if(str.equals("anger"))
        		this.vector[22]=1;
        	if(str.equals("rage"))
        		this.vector[23]=1;
        	
        }   
    }  
	
	int [] getCulori()
	{		
		return this.vector;
		//return aList;
	}
}  