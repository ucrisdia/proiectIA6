package stefanuca_anghel;
import java.io.FileNotFoundException;

import nicu.Gravity_mass;
import rotaru.ClasificatorRotaru;
import gatu.MainProjIA;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.opencv.core.Point;

public class Main 
{
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException 
	{
		
		//String[] imagini = {"acceptance","admiration","amazement","anger","annoyance","anticipation","apprehension","boredom","disgust","distraction","ecstasy","fear","grief","interest","joy","loathing","pensiveness","rage","sadness","serenity","surprise","terror","trust"};		
		String feelings[] = {"interest", "anticipation", "vigilance", "serenity", "joy", "ecstasy", "acceptance", "trust", "admiration", "apprehension", "fear", "terror",
				"distraction", "surprise", "amazement", "pensiveness", "sadness", "grief", "boredom", "disgust", "loathing", "annoyance", "anger", "rage"};

		MainProjIA mpia = new MainProjIA();
		
		for(File file : new File("img/").listFiles())
		{
			//if(!file.getName().equals("gsmile.jpg")) continue;
			
			//System.out.println("\n\nSentiment asteptat: "+imagini[i]);
			ImageSplitTest test = new ImageSplitTest(file.getAbsolutePath());
			//System.out.println(test.getCulori());
			CinciCulori c = new CinciCulori(file.getAbsolutePath());
			//System.out.println(c.getSentiment());
			
			
			
			
			int vRotaru [] = ClasificatorRotaru.algCulori(file.getAbsolutePath());
			int jaguar[] = Gravity_mass.printed(file.getAbsolutePath());
			int vetor[] = new int [24];
			System.out.println("Valori pentru imaginea: "+file.getName());
			//new MainProjIA(file.getAbsolutePath());
			
			Point p = mpia.getResult(file.getAbsolutePath());
			
			for(int j=0;j<24;j++)
			{
				vetor[j] = c.getSentiment()[j]+test.getCulori()[j]+jaguar[j]+vRotaru[j];
				if(j == 4) vetor[j] += p.y * 5;
				System.out.print(feelings[j]+" "+vetor[j]+"\n");
			}
			System.out.println();
		}
	}
}
