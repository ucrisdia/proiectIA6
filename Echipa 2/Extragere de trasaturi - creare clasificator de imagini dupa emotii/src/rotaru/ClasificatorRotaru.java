package rotaru;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.color.*;
import java.awt.Color;

public class ClasificatorRotaru 
{
	static int lrgb[][] = new int[4][256];
	static double gravity[][] = new double[100][8];
	static int category[] = new int[24];
	static int culori[] = new int[24];
	static int sentimente[] = new int[24];
	
	
	static String feelings[] = {"interest", "anticipation", "vigilance", "serenity", "joy", "ecstasy", "acceptance", "trust", "admiration", "apprehension", "fear", "terror",
								"distraction", "surprise", "amazement", "pensiveness", "sadness", "grief", "boredom", "disgust", "loathing", "annoyance", "anger", "rage"};
	
	public static BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) 
	{
	    BufferedImage bi = null;
	    try 
	    {
	        ImageIcon ii = new ImageIcon(filename);//path to image
	        bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = (Graphics2D) bi.createGraphics();
	        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	        g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
	    } catch (Exception e) 
	    {
	        e.printStackTrace();
	        return null;
	    }
	    return bi;
	}
	
	public static void sortColorCategory(int r, int g, int b)
	{

		float[] hsv = Color.RGBtoHSB(r, g, b, null);
		  float hue = hsv[0];
		  float sat=hsv[1];
		  float val=hsv[2];
		//	System.out.print("\nImage "+hsv[0]);

		  if(val<=0.20)
			  culori[0]++;//foarte inchis
		  else
		  if(val>=0.95&&sat<=0.15 || val>=0.90 && sat<0.10)
			  culori[1]++;//foarte deschis
		  else
			  if(sat<0.10 && val<0.25)
			  culori[0]++;
			  else 
				  if (sat<0.10&&val>0.85)
				  culori[1]++;
			  else 
				  if(sat<0.10)
				  {
					  if(val<0.51)
				  culori[3]++;//gri inchis
					  else culori[4]++;//gri deschis
				  }
			  else
			  {
		  if(hue>=0.95||hue<=0.06 )//rosu
			 if(val<=0.55)
				  {
				   if(sat<0.75)
					   category[21]++;//annoyance
				   else
					   category[22]++;//anger
				  }
			  else{ 
				  if(sat>=0.85)
				  {
					  category[23]++;//rage
				  }
				  else 	
					  category[11]++;//terror
				  
				  if(val>=0.90&&sat>=0.75) 
				  {
					  category[5]++;//ecstasy
					  category[13]++;//surprise
				  }
			   if(val>=0.85&&sat>0.60&&sat<0.86)
				   category[4]++;//joy
			  }
		  if((hue>0.06||hue<=0.14 ))//portocaliu
			  if(val<=0.40)
				  {
				  category[17]++;//grief
				  category[9]++;//apprehension
				  category[10]++;//fear
				  category[11]++;//terror
				  }
			  else if(val<0.50)
				  {
				  category[16]++;//sadness
				  category[19]++;//disgust
				  }
			  else if(val<0.65)
			  {
				  category[18]++;//boredom
				  category[15]++;//pensivness
				  category[1]++;//anticipation

			  }
			  else {
				  category[0]++;//interest
				  category[13]++;//surprise
				  if(val>0.75&&sat>0.80)
				  {
					  category[4]++;//joy
					  category[14]++;//amazement
				  }
			  }
			
		  if((hue>0.14||hue<=0.2 ))//galben
			  if(val<=0.35)
			  {
				  category[17]++;//grief
				  category[20]++;//loathing
			  }
			  else if(val<0.50)
				  {
				  category[16]++;//sadness
				  category[20]++;//disgust
				  }
			  else if(val<0.65)
			  {
				  category[15]++;//pensivness
				  category[1]++;//anticipation

			  }
			  else {
				  category[0]++;//interest
				  if(val>=0.85)
					  {
					  if(sat>0.65)
					  category[13]++;//surprise
					  if(sat>=0.75)
						  {
						  category[4]+=2;//joy
						  category[14]+=2;//amazement
						  }
					  else
						  if(sat<=0.65)
							  category[6]+=2;//acceptance
						  
					  category[3]++;//serenity
					  
					  }
			  }
		  
		  if((hue>0.2||hue<=0.41 ))//verde
			  if(val<=0.35)
				  category[15]++;//pensivness
			  else if(val<0.50)
				  category[1]++;//anticipation
			  else{ 
				  category[3]++;//serenity
				  if(val>0.75)
				  {
				  if(sat<=0.65)
				  {
					  category[6]+=2;//acceptance
				  }
				  else category[4]++;//joy
			  }
			  }
		  if((hue>0.41||hue<=0.57 ))//bleu
			  if(val<=0.35)
				  category[18]++;//boredom
			  else if(val<0.75)
				  {
				  category[2]++;//vigilence
				  category[12]++;//distraction
				  }
			  else {
				  category[14]++;//amazement
				  category[13]++;//surprise
				  category[3]++;//serenity
				  if(sat<0.70)
					  {
					  category[6]+=2;//acceptance
					  }
			  }
		  
		  if((hue>0.57||hue<=0.72 ))//albastru
			  if(val<=0.40)
				  {
				  category[17]++;//grief
				  category[10]++;//fear
				  }
			  else if(val<0.55)
			  {
				  category[16]++;//sadness
				  category[9]++;//apprehension
			  }
			  else 
				  {
				  if(sat<0.65)
					  {
					  category[7]+=2;//trust
					  }
				  category[15]++;//pensivness
				  category[2]++;//vigilance
				  category[8]++;//admiration
				
				  }
			
			  
		  if((hue>0.72||hue<0.95 ))//mov/roz
			  if(val<=0.35)
				  {
				  category[17]++;//grief
				  category[19]++;//disgust
				  }
			  else if(val<0.50)
				  category[20]++;//loathing
			  else{
				  category[12]++;//distraction
				  if(sat<0.55)
					  category[8]+=2;//admiration
				  if(val<0.65)
				  category[1]+=2;//anticipatioon
			  }
			  else {
				  if(val>0.85)
					  category[5]++;//ecstasy
				  else
				  category[0]++;//interest
			  }
			  }
		  
		               
	}
	
	public static int[] algCulori(String input)
	{
		for (int i=0;i<24;i++)
		{
			sentimente[i]=0;
		}
		
			for(int j = 0; j < 24; j++)
				category[j] = 0;
			culori[0]=0;
			culori[1]=0;
			culori[2]=0;
			culori[3]=0;
		
		for(int i=0; i<256; i++)
			for(int j=0; j<4; j++)
				lrgb[j][i]=0;
			
		BufferedImage img = null;
		
		int light[] = new int[256];
				
		img = scaleImage(600,400,input);
		
		
		int width = 600;
		int height = 400;
	
		
		for(int i =0; i < width; i++)
		{
			for(int j=0; j < height; j++)
			{
				Color p = new Color(img.getRGB(i, j));
				
				int r = p.getRed();
				int g = p.getGreen();
				int b = p.getBlue();
				
				sortColorCategory(r, g, b);
				
				int avg = (r + g + b) / 3;
				
				light[avg]++;
								
				lrgb[0][avg]++;
				lrgb[1][r]++;
				lrgb[2][g]++;
				lrgb[3][b]++;
					
			}
		}
		
		if(culori[0]+culori[2]>=(width*height)/2)
			{
			category[11]+=culori[0]+culori[2];//terror
			category[23]+=culori[0]+culori[2];//rage
			}
		else 
		{
			category[11]=0;//terror
			category[23]=0;//rage
			if(culori[0]+culori[2]>=(width*height)/4)
			{
				category[10]+=culori[0]+culori[2];//fear
				category[22]+=culori[0]+culori[2];//anger
				category[16]+=culori[0]+culori[2];//sadness
				category[17]+=culori[0]+culori[2];//grief

			}
				else
		    {
					category[22]=0;//anger
			category[10]=0;//fear
			if(culori[0]+culori[2]>=(width*height)/5)
		    category[21]+=culori[0]+culori[2];//annoyance
			category[9]+=culori[0]+culori[2];//apprehension
		    category[19]+=(culori[0]+culori[2])/2;//disgust
		    category[20]+=(culori[0]+culori[2])/2;//loathing
		    }
		}
		if(culori[1]+culori[3]>=(width*height)/3)
		{
			category[3]+=(culori[1]+culori[3])/2;//serenity
		}
		if(culori[3]+culori[2]>=(width*height)/3)
		{
			category[18]+=(culori[3]+culori[2]);//boredom
		}
			
		int max1 =category[0], max2= category[0], max3=category[0], cat1=0,cat2=0,cat3=0;
		for(int j = 1; j < 24; j++)
			if(category[j]>max1)
			{
				max3=max2;
				max2=max1;
				max1=category[j];
				cat3=cat2;
				cat2=cat1;
				cat1=j;
			}
			else if(category[j]>max2)
			{
				max3=max2;
				max2=category[j];
				cat3=cat2;
				cat2=j;
			}	
			else if(category[j]>max3)
			{
				max3=category[j];
				cat3=j;
			}
		 


			 sentimente[cat1]++;
			 sentimente[cat2]++;
			 sentimente[cat3]++;

		return sentimente;
	}
	

	
	public static void main(String [] args)
	{
		
			
			algCulori("img\\joy.jpg");
			
	}
}
