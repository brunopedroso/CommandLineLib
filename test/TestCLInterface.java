import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class TestCLInterface extends CLInterface{
	
	public TestCLInterface() {
		addOption(new CLOption("testing"));
	}
	
	public static void main(String[] args) throws IOException {
		CLInterface cl = new TestCLInterface();
		cl.run();
	}

}
