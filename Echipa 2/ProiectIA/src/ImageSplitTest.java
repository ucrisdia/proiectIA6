import javax.imageio.ImageIO;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;  
  
public class ImageSplitTest {  
    public static void main(String[] args) throws IOException,FileNotFoundException, UnsupportedEncodingException {  
  
        File file = new File("leu.jpg"); 
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
        System.out.println("Splitting done");  
  
        for (int i = 0; i < imgs.length; i++) {  
            ImageIO.write(imgs[i], "jpg", new File("img" + i + ".jpg"));  
        }  
        System.out.println("Mini images created"); 
        for (int i = 0; i < chunks; i++) { 
      	  GlobalColorHistogram test =new GlobalColorHistogram("E:\\programare\\proiecte\\java\\ProiectIA\\img"+i+".jpg");
      	  test.afisareVectori(i);  
      	 
        }
    }  
}  