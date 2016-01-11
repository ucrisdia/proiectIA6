import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;
import java.util.HashSet;
import java.awt.*;  
  
public class ImageSplitTest {  
	private HashSet hset ;
	
	ImageSplitTest(String caleImagine) throws IOException,FileNotFoundException, UnsupportedEncodingException {  
  
			
		//codul care face blur la imagine
	      try {
	          System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	          
	          Mat source = Highgui.imread(caleImagine,Highgui.CV_LOAD_IMAGE_COLOR);
	          
	          Mat destination = new Mat(source.rows(),source.cols(),source.type());
	          Imgproc.GaussianBlur(source, destination,new Size(45,45), 0);
	          Highgui.imwrite("1bloor_"+caleImagine, destination);
	       
	       } catch (Exception e) {
	          System.out.println("Error: " + e.getMessage());
	       }
	      
	      // a doua aplicare
	      try {
	          System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	          
	          Mat source = Highgui.imread("1bloor_"+caleImagine,Highgui.CV_LOAD_IMAGE_COLOR);
	          
	          Mat destination = new Mat(source.rows(),source.cols(),source.type());
	          Imgproc.GaussianBlur(source, destination,new Size(45,45), 0);
	          Highgui.imwrite("2bloor_"+caleImagine, destination);
	       
	       } catch (Exception e) {
	          System.out.println("Error: " + e.getMessage());
	       }
	      
	      
	      // a treia aplicare
	      try {
	          System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	          
	          Mat source = Highgui.imread("2bloor_"+caleImagine,Highgui.CV_LOAD_IMAGE_COLOR);
	          
	          Mat destination = new Mat(source.rows(),source.cols(),source.type());
	          Imgproc.GaussianBlur(source, destination,new Size(45,45), 0);
	          Highgui.imwrite("bloor_"+caleImagine, destination);
	       
	       } catch (Exception e) {
	          System.out.println("Error: " + e.getMessage());
	       }
		// end blur imagine
		
		
		
        File file = new File("bloor_"+caleImagine); 
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
        for (int i = 0; i < chunks; i++) { 
      	  GlobalColorHistogram test =new GlobalColorHistogram("img"+i+".jpg");
      	  hset.add(test.genereazaCuloare());
        }
    }  
	
	HashSet getCulori()
	{
		return hset;
	}
}  