package stefanuca_anghel;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
 
public class GlobalColorHistogram extends Component {
	static int category[] = new int[24];
	static String feelings[] = {"interest", "anticipation", "vigilance", "serenity", "joy", "ecstasy", "acceptance", "trust", "admiration", "apprehension", "fear", "terror",
			"distraction", "surprise", "amazement", "pensiveness", "sadness", "grief", "boredom", "disgust", "loathing", "annoyance", "anger", "rage"};	
 
  public void printPixelARGB(int pixel) {
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0xff;
        
    sortCategory(red,green,blue);
  }
  
  public static void sortCategory(int r, int g, int b)
	{
		if(r > 204 && g > 153 && g < 205 && b > 51 && b < 154) //interest
			category[0]++;
		
		if(r > 204 && g > 102 && g < 154  && b < 52)  //anticipation
			category[1]++;
		
		if(r < 205 && r > 102 && g > 51 && g < 103 && b < 26) //vigilance
			category[2]++;
		
		if(r > 204 && g > 204 && b > 51 && b < 154 && (r - g < 11) && (g - r < 11)) //serenity
			category[3]++;
		
		if(r > 204 && g > 204 && b < 52 && (r - g < 11) && (g - r < 11)) //joy
			category[4]++;
		
		if(r > 102 && r < 205 && g > 102 && g < 205 && b < 26 && (r - g < 11) && (g - r < 11)) //ecstasy
			category[5]++;
		
		if(r > 51 && r < 154 && g > 204 && b > 51 && b < 154 && (r - b < 11) && (b - r < 11)) //acceptance
			category[6]++;
		
		if(r < 52 && g > 204 && b < 52 && (r - b < 11) && (b - r < 11)) //trust
			category[7]++;
		
		if(r < 26 && g < 205 && g > 102 && b < 26 && (r - b < 11) && (b - r < 11)) //admiration
			category[8]++;
		
		if(r < 205 && r > 153 && g > 204 && b > 51 && b < 154) //apprehension
			category[9]++;
		
		if(r < 154 && r > 102 && g > 204 && b < 52) //fear
			category[10]++;
		
		if(r < 104 && r > 51 && g > 102 && g < 205 && b < 26) //terror
			category[11]++;
		
		if(r < 154 && r > 51 && g > 204 && b > 204 && (g - b < 11) && (b - g < 11)) //distraction
			category[12]++;
		
		if(r < 52 && g > 204 && b > 204 && (g - b < 11) && (b - g < 11)) //surprise
			category[13]++;
		
		if(r < 26 && g > 102 && g < 205 && b > 102 && b < 205 && (g - b < 11) && (b - g < 11)) //amazement
			category[14]++;
		
		if(r > 51 && r < 154 && g > 51 && g < 154 && b > 204 && (r - g < 11) && (g - r < 11)) //pensiveness
			category[15]++;
		
		if((r < 52 && g < 52 && b > 204 && (r - g < 11) && (g - r < 11)) || (r == b && r == g && b == g)) //sadness
			category[16]++;
		
		if(r < 26 && g < 26 && b > 102 && b < 205 && (r - g < 11) && (g - r < 11)) //grief
			category[17]++;
		
		if(r > 181 && g > 100 && g < 151 && b > 181 && (r - b < 11) && (b - r < 11)) //boredom
			category[18]++;
		
		if(r > 181 && g < 52 && b > 181 && (r - b < 11) && (b - r < 11)) //disgust
			category[19]++;
		
		if(r > 132 && r < 181 && g < 26 && b > 132 && b < 181 && (r - b < 11) && (b - r < 11)) //loathing
			category[20]++;
		
		if(r > 204 && g > 51 && g < 154 && b > 51 && b < 154 && (b - g < 11) && (g - b < 11)) //annoyance
			category[21]++;
		
		if(r > 204 && g < 52 && b < 52 && (b - g < 11) && (g - b < 11)) //anger
			category[22]++;
		
		if(r > 102 && r < 205 && g < 26 && b < 26 && (b - g < 11) && (g - b < 11)) //rage
			category[23]++;
	}
 
  private void marchThroughImage(BufferedImage image) {
    int w = image.getWidth();
    int h = image.getHeight();
 
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        int pixel = image.getRGB(j, i);
        printPixelARGB(pixel);
      }
    }
  }
 
  public GlobalColorHistogram(String poza) {	
	  
	  for(int i=0;i<24;i++)
	  {
		  category[i]=0;
	  }
	  
	    try {
	      BufferedImage image = ImageIO.read(new File(poza));
	      marchThroughImage(image);
	    } catch (IOException e) {
	      System.err.println(e.getMessage());
	    }
  } 
  
  
  String genereazaCuloare()
  {
	  int pozitie=0;
	  int maxim=0;
	  for(int i=0;i<24;i++)
	  {
		  if(maxim<category[i])
		  {
			  pozitie=i;
			  maxim=category[i];
		  }   
	  }	  
	  return feelings[pozitie]; 
  }
  
}
