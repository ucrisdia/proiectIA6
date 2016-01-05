package histograma;

import java.io.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class luminance
{
	public static void main(String [] args)
	{
		BufferedImage img = null;
		File f = null;
		
		int light[] = new int[256];
		
		try
		{
			//f = new File(); <-- insert input path here
			img = ImageIO.read(f);
		} catch(IOException e)
		{
			System.out.println(e);
		}
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		for(int i =0; i < width; i++)
		{
			for(int j=0; j < height; j++)
			{
				Color p = new Color(img.getRGB(i, j));
				
				int r = p.getRed();
				int g = p.getGreen();
				int b = p.getBlue();
				
				int avg = (r + g + b) / 3;
				
				light[avg]++;
				
				int rgb = new Color(avg, avg, avg).getRGB();
				
				img.setRGB(i, j, rgb);
			}
		}
		
		try
		{
			//f = new File(); <-- insert output path here
			ImageIO.write(img, "jpg", f);
		} catch(IOException e)
		{
			System.out.println(e);
		}
		
		for(int i=0; i < 256; i++)
			System.out.println("Position " + i + ": " + light[i] + " ");
	}
}
