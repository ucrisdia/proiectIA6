package stefanuca_anghel;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
 
public class CinciCulori extends Component {
	
	//sursa rgb culori: http://www.marplo.net/html/coduri_culori.html
	
	/*
	    Culorile si modul cum echipa1 a ales sentimentele pentru o anumita culoare
	   
	    albastru-deschis: grief,pensiveness,sadness
		albestru-inchis: amazement, distraction, surprise
		galben: ecstasy, joy, serenity
		portocaliu: anticipation, interest,vigilance
		roz: anger, annoyance, rage
		violet: boredom, disgust, loathing
		lime: acceptance, admiration, trust
		verde: apprehension, fear, terror 
	 */
		
	
	 int vector[] = new int[24];
	
	 int categorieCuloare [] = new int[8];
	 static String culori[] = {"AlbastruDeschis","AlbastruInchis","Galben","Portocaliu","Roz","Violet","Verde","Lime"};
	 int intervaleRGBp[][][] = {	 
			//  R                             G                       B
			//sunt cate 5 culori
			 
			 {{176,176,173,135,135},{196,224,216,206,206},{222,230,230,235,250}}, // albastru deschis
			 {{0,0,0,0,25},{0,0,0,0,25},{255,205,139,128,112}}, // albastru inchis
			 {{255,255,255,240,255},{255,255,255,230,215},{0,224,210,140,0}}, // galben
			 {{255,255,255,255,255},{160,127,99,69,140},{122,80,71,0,0}}, // portocaliu
			 {{255,255,255,255,199},{192,182,105,20,21},{203,193,180,147,133}}, // roz 
			 {{199,219,238,138,148},{21,112,130,43,0},{133,147,238,226,221}}, // violet
			 {{0,50,91,128,0},{255,205,215,205,254},{0,50,91,50,1}}, // lime
			 {{173,60,34,0,154},{255,179,139,100,205},{47,113,34,0,50}}  // verde
	 };
	
	 
	 void numaraCulori(int r,int g,int b)
	 {
		 for(int i=0;i<8;i++)
		 {
			 //prima pozitie, i-ul este tipul culorii (una din alea 8)
			 //a doua pozitie este pentru RGB, 012
			 // a treia pozitie variaza si a N-a culoare	
			 
			// System.out.println("Culoare "+(i+1)+": ");
			 for(int n=0;n<5;n++)
			 {
				 //System.out.println("RGB "+intervaleRGBp[i][0][n] +" "+intervaleRGBp[i][1][n]+" "+intervaleRGBp[i][2][n]);
			
				 if(intervaleRGBp[i][0][n]==r && intervaleRGBp[i][1][n]==g&& intervaleRGBp[i][2][n]==b)
				 {
					 this.categorieCuloare[i]++;
				 }
				 
			 }

			// System.out.println();
		 }
	 } 
	 
	 int[] getSentiment()
	 {
		 
		 int pozitie=0;
		 int maxim=0;
		 
		 for(int i=0;i<8;i++)
		 {
			 if(maxim<this.categorieCuloare[i])
			 {
				 pozitie =i;
				 maxim = categorieCuloare[i];
			 }
		 }
		 
		 ArrayList <String> hmap = new ArrayList<>();
		 
		 switch (pozitie)
		 {
		 case 0:
			 this.vector[18]=1;
			 this.vector[16]=1;
			 this.vector[17]=1;
			 
			 hmap.add("grief");
			 hmap.add("pensiveness");
			 hmap.add("sadness");
			 break;
		 case 1:
			 this.vector[15]=1;
			 this.vector[13]=1;
			 this.vector[14]=1;
			 
			 hmap.add("amazement");
			 hmap.add("distraction");
			 hmap.add("surprise");
			 break;
		 case 2:
			 this.vector[5]=1;
			 this.vector[6]=1;
			 this.vector[4]=1;
			 
			 hmap.add("joy");
			 hmap.add("ecstasy");
			 hmap.add("serenity");
			 break;
		 case 3:
			 this.vector[2]=1;
			 this.vector[1]=1;
			 this.vector[3]=1;
			 hmap.add("anticipation");
			 hmap.add("interest");
			 hmap.add("vigilance");
			 break;
		 case 4:
			 this.vector[23]=1;
			 this.vector[22]=1;
			 this.vector[23]=1;
			 hmap.add("anger");
			 hmap.add("annoyance");
			 hmap.add("rage");
			 break;
		 case 5:
			 this.vector[19]=1;
			 this.vector[20]=1;
			 this.vector[21]=1;
			 hmap.add("boredom");
			 hmap.add("disgust");
			 hmap.add("loathing");
			 break;
		 case 6:
			 this.vector[7]=1;
			 this.vector[9]=1;
			 this.vector[8]=1;
			 hmap.add("acceptance");
			 hmap.add("admiration");
			 hmap.add("trust");
			 break;
		 case 7:
			 this.vector[10]=1;
			 this.vector[11]=1;
			 this.vector[12]=1;
			 hmap.add("apprehension");
			 hmap.add("fear");
			 hmap.add("terror");
			 break;
			 
		 }
		 
		 
		 return this.vector;
		// return hmap;
	 }
	 

  public void printPixelARGB(int pixel) {
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0xff;
    
    this.numaraCulori(red, green, blue);    
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
 
  public CinciCulori(String poza) {	
	  for(int i=0;i<5;i++)
	  {
		  this.categorieCuloare[i]=0;
	  }
	  
	    try {
	      BufferedImage image = ImageIO.read(new File(poza));
	      marchThroughImage(image);
	    } catch (IOException e) {
	      System.err.println(e.getMessage());
	    }
  } 
  
  /*
	 public static void main(String [] args)
	 {
		 CinciCulori c = new CinciCulori("Gaussian1.jpg");
		 System.out.println(c.getSentiment());
	 }
  */
}
