import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		ImageSplitTest test = new ImageSplitTest("f.jpg");
		System.out.println(test.getCulori());
	}

}
