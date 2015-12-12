import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
 
public class GlobalColorHistogram extends Component {
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> vectorRosu;
	private ArrayList<Integer> vectorVerde;
	private ArrayList<Integer> vectorAlbastru;
		
  public static void main(String[] args) 
  { 
	  GlobalColorHistogram test =new GlobalColorHistogram("test1.png");
	  test.afisareVectori();  
  }
 
  public void printPixelARGB(int pixel) {
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0xff;
    
    int valoare;
    valoare = this.vectorRosu.get(red);
    this.vectorRosu.set(red, valoare+1);
    
    valoare = this.vectorRosu.get(blue);
    this.vectorAlbastru.set(blue, valoare+1);
    
    valoare = this.vectorRosu.get(green);
    this.vectorVerde.set(green, valoare);
    
   // System.out.println("rgb: " + red + ", " + green + ", " + blue);
  }
 
  private void marchThroughImage(BufferedImage image) {
    int w = image.getWidth();
    int h = image.getHeight();
    System.out.println("width, height: " + w + ", " + h);
 
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
      //  System.out.println("x,y: " + j + ", " + i);
        int pixel = image.getRGB(j, i);
        printPixelARGB(pixel);
       // System.out.println("");
      }
    }
  }
 
  public GlobalColorHistogram(String poza) {		  
	    vectorRosu = new ArrayList<>() ;
		vectorVerde = new ArrayList<>() ;
		vectorAlbastru = new ArrayList<>();		
		
		for(int i=0;i<256;i++)
		{
			vectorRosu.add(0);
			vectorVerde.add(0);
			vectorAlbastru.add(0);
		}
	  
    try {
      BufferedImage image = ImageIO.read(new File(poza));
      marchThroughImage(image);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  } 
  
  ArrayList<Integer> getVectorRosu()
  {
	  return this.vectorRosu;
  }
  
  ArrayList<Integer> getVectorAlbastru()
  {
	  return this.vectorAlbastru;
  }
  
  ArrayList<Integer> getVectorVerde()
  {
	  return this.vectorVerde;
  }
  
  void afisareVectori()
  {
	  System.out.println("Culoare rosu: ");
	  for(int i=1;i<this.vectorRosu.size();i++)
		  System.out.println("Pozitia "+i+" valoarea: "+this.vectorRosu.get(i));
	  
	  System.out.println("\n\nCuloare albastru: ");
	  for(int i=1;i<this.vectorAlbastru.size();i++)
		  System.out.println("Pozitia "+i+" valoarea: "+this.vectorAlbastru.get(i));
	  
	  System.out.println("\n\nCuloare verde: ");
	  for(int i=1;i<this.vectorVerde.size();i++)
		  System.out.println("Pozitia "+i+" valoarea: "+this.vectorVerde.get(i));
  }
}