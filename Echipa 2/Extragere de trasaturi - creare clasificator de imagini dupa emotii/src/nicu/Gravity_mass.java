package nicu;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Gravity_mass
{  

	static int lrgb[][] = new int[4][256];
	static double gravity[][] = new double[100][8];
	static int category[] = new int[24];
	static int category2[] = new int[24];
		
	static String feelings[] = {"interest", "anticipation", "vigilance", "serenity", "joy", "ecstasy", "acceptance", "trust", "admiration", "apprehension", "fear", "terror",
								"distraction", "surprise", "amazement", "pensiveness", "sadness", "grief", "boredom", "disgust", "loathing", "annoyance", "anger", "rage"};
	
	//the fixed point of feelings in RGB
	static int colors[][] = {{255, 196, 140}, {255, 168, 84}, {255, 125, 0}, {255, 255, 177}, {255, 255, 84}, {255, 232, 84}, {140, 255, 140}, {84, 255, 84}, {0, 180, 0},
		    				  {140, 198, 140}, {0, 150, 0}, {0, 128, 0}, {165, 219, 255}, {89, 189, 255}, {0, 137, 224}, {140, 140, 255}, {81, 81, 255}, {0, 0, 200}, 
		    				  {255, 198, 255}, {255, 84, 255}, {222, 0, 222}, {255, 140, 140}, {255, 0, 0}, {212, 0, 0}};
	
	static int finalFeelings[] = new int[24];
	//the fixed black-white point of feelings
	static int infoColors[] = new int [24];
	
	//the resize image function
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
	
	
	//range find feeling function (method 1)
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
		
		if((r < 52 && g < 52 && b > 204 && (r - g < 11) && (g - r < 11)) || ( r > 50 && r < 200 && r == b && r == g && b == g))// || (r < 26 && g > 51 && g < 103 && b > 102 && b < 205)) //sadness
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
		
	//aproximate fixed point feeling function (method 2)
	public static void aproximateCategory(int r, int g, int b)
	{
		int rmean, distr, distg, distb;
		double mat = 0, min = 999999, index = 0;
		
		for(int i=0; i<24; i++)
		{
		    rmean = (r + colors[i][0]) >> 1;
		    distr = r - colors[i][0];
		    distg = g - colors[i][1];
		    distb = b - colors[i][2];
		    
		    if((r + g + b) > 80 && (r + g + b) < 725 )
		    {
		    	//euclidian distance formula
			    mat = Math.sqrt((((512+rmean)*distr*distr)>>8) + 4*distg*distg + (((767-rmean)*distb*distb)>>8));
			    
			    if(min > mat)
			    {
			    	min = mat;
			    	index = i;
			    }
		    }
		}
		
		category2[(int)index]++;
	}
	
	//aproximate gravity point function (method 6)
	public static void aproximateGravityPoint(int r, int g, int b)
	{
		int min1 = 99999, min2 = 99999, min3 = 99999;
		int i1 = 0, i2 = 0, i3 = 0;
		
		int average = (r + g + b) / 3;
		
		for(int i=0; i<24; i++)
		{
		    if(Math.abs(infoColors[i] - average) < min1)
		    {
		    	min1 = Math.abs(infoColors[i] - average);
		    	i1 = i;
		    }
		    
		    if(Math.abs(infoColors[i] - average) < min2 && i != i1)
		    {
		    	min2 = Math.abs(infoColors[i] - average);
		    	i2 = i;
		    }
		    
		    if(Math.abs(infoColors[i] - average) < min3 && i != i1 && i != i2)
		    {
		    	min3 = Math.abs(infoColors[i] - average);
		    	i3 = i;
		    }
		}
		
		//System.out.println("Aproximate gravity points after the range of the feelings: " + feelings[i1] + ", " + feelings[i2] + ", " + feelings[i3]);
		finalFeelings[i1]++;
		finalFeelings[i2]++;
		finalFeelings[i3]++;
	}

	
	public static void extractRGB(int k, int H, int W, String file)
	{
		
		for(int i=0; i<256; i++)
			for(int j=0; j<4; j++)
				lrgb[j][i]=0;
			
		BufferedImage img = null;
		
		int light[] = new int[256];
		
		
		img = scaleImage(W, H ,file);
		
		
		int width = W;
		int height = H;
	
		
		for(int i =0; i < width; i++)
		{
			for(int j=0; j < height; j++)
			{
				Color p = new Color(img.getRGB(i, j));
				
				int r = p.getRed();
				int g = p.getGreen();
				int b = p.getBlue();
				
				sortCategory(r, g, b);
				aproximateCategory(r, g, b);
				
				int avg = (r + g + b) / 3;
				
				light[avg]++;
								
				lrgb[0][avg]++;
				lrgb[1][r]++;
				lrgb[2][g]++;
				lrgb[3][b]++;
					
			}
		}
		
		
	}
	
	public static void gravity_point(int x, int H, int W, int y, String file)
	{
		
		extractRGB(x+1, H, W, file);
		
		double  yl=0,  yr=0,  yg=0,  yb=0, countl=0, countr=0, countg=0, countb=0, suml=0, sumr=0, sumg=0, sumb=0;
		
		for(int i=0; i<256; i++)
		{
			if(lrgb[0][i] < y) countl++;
			else
			{
				yl += lrgb[0][i];
				suml += i;
			}
			if(lrgb[1][i] < y) countr++;
			else
			{
				yr += lrgb[1][i];
				sumr += i;
			}
			if(lrgb[2][i] < y) countg++;
			else
			{
				yg += lrgb[2][i];
				sumg += i;
			}
			if(lrgb[3][i] < y) countb++;
			else
			{
				yb += lrgb[3][i];
				sumb += i;
			}
			//System.out.println(yl + " " + yr + " " + yg + " " + yb );
		}
		
		yl /= (256-countl);
		yr /= (256-countr);
		yg /= (256-countg);
		yb /= (256-countb);
		
		suml /= (256-countl);
		sumr /= (256-countr);
		sumg /= (256-countg);
		sumb /= (256-countb);
		
		gravity[x][0] = (double)Math.round(yl * 100) / 100;
		gravity[x][1] = (double)Math.round(yr * 100) / 100;
		gravity[x][2] = (double)Math.round(yg * 100) / 100;
		gravity[x][3] = (double)Math.round(yb * 100) / 100;
		gravity[x][4] = (double)Math.round(suml * 100) / 100;
		gravity[x][5] = (double)Math.round(sumr * 100) / 100;
		gravity[x][6] = (double)Math.round(sumg * 100) / 100;
		gravity[x][7] = (double)Math.round(sumb * 100) / 100;
		
		
	}
	
	//3 feelings for range method
	public static void maximalFeeling()
	{
		int max1=0, max2=0, max3=0, index1=0, index2=0, index3=0;
		
		for(int i=0; i<24; i++)
			if(category[i] > max1) 
			{
				max1=category[i];
				index1=i;
			}
		
		for(int i=0; i<24; i++)
			if(category[i] > max2 && index1 != i)
			{
				max2=category[i];
				index2 = i;
			}
		
		for(int i=0; i<24; i++)
			if(category[i] > max3 && index1 != i && index2 != i)
			{
				max3=category[i];
				index3 = i;
			}
		if(max1 == 0 && max2 == 0 && max3 ==0)
		{
			//System.out.print("\nFinal feelings: no feeling found\n");
		}
		
		else
		{
			
			
			if(max1 == 0)
			{
				//System.out.print("no feeling, ");
			}
			else
			{
				//System.out.print(feelings[index1] + ", ");
				finalFeelings[index1]++;
			}
			
			if(max2 == 0)
			{
				//System.out.print("no feeling, ");
			}
			else
			{
				//System.out.print(feelings[index2] + ", ");
				finalFeelings[index2]++;
			}
			
			if(max3 == 0)
				System.out.print("no feeling\n");
			else
			{
				//System.out.print(feelings[index3] + "\n");
				finalFeelings[index3]++;
			}
				
		}
	}
	
	//3 feelings from aproximate method
	public static void maximalFeeling2()
	{
		int max1=0, max2=0, max3=0, index1=0, index2=0, index3=0;
		
		for(int i=0; i<24; i++)
			if(category2[i] > max1) 
			{
				max1=category2[i];
				index1=i;
			}
		
		for(int i=0; i<24; i++)
			if(category2[i] > max2 && index1 != i)
			{
				max2=category2[i];
				index2 = i;
			}
		
		for(int i=0; i<24; i++)
			if(category2[i] > max3 && index1 != i && index2 != i)
			{
				max3=category2[i];
				index3 = i;
			}
		
		//System.out.print( feelings[index1] + ", " + feelings[index2] + ", " + feelings[index3] + "\n");
		finalFeelings[index1]++;
		finalFeelings[index2]++;
		finalFeelings[index3]++;
		
	}
	
	public static int[] printed(String file)
	{

		for(int i=0;i<24;i++)
		{
			finalFeelings[i]=0;
		}
		
		for(int i=0; i<24; i++)
			infoColors[i] = (colors[i][0] + colors[i][1] + colors[i][2]) / 3;
		
		
			for(int j = 0; j < 24; j++)
			{
				category[j] = 0;
				category2[j] = 0;
			}
			
			gravity_point(0, 900, 600, 2000, file);
			
			//System.out.print("\nImage " + (0 + 1) + ": \n");
			
			//System.out.print("Method range: ");
			
			maximalFeeling();
			
			//System.out.print("Method aproximate: ");		
			
			maximalFeeling2();
			
			for(int j = 0; j < 24; j++)
				category2[j] = 0;
			
			//System.out.print("Method gravity center: ");	
			
			
			
			aproximateCategory((int)gravity[0][5], (int)gravity[0][6], (int)gravity[0][7]);
			
			for(int j=0; j<24; j++)
				if(category2[j] == 1)
				{
					finalFeelings[j]++;
					//System.out.print( feelings[j] + "\n");
					break;
				}
			
			for(int j = 0; j < 24; j++)
				category2[j] = 0;
			
			aproximateCategory((int)gravity[0][4], (int)gravity[0][4], (int)gravity[0][4]);
			
			for(int j=0; j<24; j++)
				if(category2[j] == 1)
				{
					finalFeelings[j]++;
					//System.out.print("Predominant luminance feeling: " + feelings[j] + "\n");
					break;
				}
			

			for(int j = 0; j < 24; j++)
				category2[j] = 0;
			
			aproximateCategory((int)((gravity[0][4] + gravity[0][5] + gravity[0][6] + gravity[0][7]) / 4), (int)((gravity[0][4] + gravity[0][5] + gravity[0][6] + gravity[0][7]) / 4), ((int)(gravity[0][4] + gravity[0][5] + gravity[0][6] + gravity[0][7]) / 4));
			
			for(int j=0; j<24; j++)
				if(category2[j] == 1)
				{
					finalFeelings[j]++;
					//System.out.print("Predominant gravity mass center feeling: " + feelings[j] + "\n");
					break;
				}
			
			aproximateGravityPoint((int)gravity[0][5], (int)gravity[0][6], (int)gravity[0][7]);
			
			
			return finalFeelings;
			
	}
	

	
	public static void main(String [] args)
	{
		int jaguar[] = printed("img/joy.jpg");
		for(int i=0; i<24; i++)
			System.out.print(jaguar[i]+" ");
	}
}
