package stefanuca_anghel;
import java.io.FileNotFoundException;
import nicu.Gravity_mass;
import rotaru.ClasificatorRotaru;
import gatu.MainProjIA;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		String[] imagini = {"acceptance","admiration","amazement","anger","annoyance","anticipation","apprehension","boredom","disgust","distraction","ecstasy","fear","grief","interest","joy","loathing","pensiveness","rage","sadness","serenity","surprise","terror","trust"};		
		String feelings[] = {"interest", "anticipation", "vigilance", "serenity", "joy", "ecstasy", "acceptance", "trust", "admiration", "apprehension", "fear", "terror",
				"distraction", "surprise", "amazement", "pensiveness", "sadness", "grief", "boredom", "disgust", "loathing", "annoyance", "anger", "rage"};

		
		for(int i=0;i<23;i++)
		{			
			//System.out.println("\n\nSentiment asteptat: "+imagini[i]);
			ImageSplitTest test = new ImageSplitTest("img\\"+imagini[i]+".jpg");
			//System.out.println(test.getCulori());
			CinciCulori c = new CinciCulori("img\\"+imagini[i]+".jpg");
			//System.out.println(c.getSentiment());
			
			
			
			
			int vRotaru [] = ClasificatorRotaru.algCulori("img\\"+imagini[i]+".jpg");
			int jaguar[] = Gravity_mass.printed("img\\"+imagini[i]+".jpg");
			int vetor[] = new int [24];
			System.out.println("Valori pentru imaginea: "+"img\\"+imagini[i]+".jpg");
			new MainProjIA("img\\"+imagini[i]+".jpg");
			for(int j=0;j<24;j++)
			{
				vetor[j] = c.getSentiment()[j]+test.getCulori()[j]+jaguar[j]+vRotaru[j];
				System.out.print(feelings[j]+" "+vetor[j]+"\n");
			}
			System.out.println();
			
		} 
	}
}
