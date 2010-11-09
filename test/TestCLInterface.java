import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class TestCLInterface extends CLInterface{
	
	public TestCLInterface() {
		
		super("Hello this is the test application!\nPlease choose an option to start:");
		
		CLMenu menu1 = new CLMenu("First Menu", "This is the First Menu");
		CLMenu menu11 = new CLMenu("Another Manu", "This is Another Menu");
		CLMenu menu2 = new CLMenu("Second Menu", "This is the Second Menu");

		addOption(menu1);
		addOption(menu2);
		addOption(new CLOption("A single option"));
		
		menu1.addOption(menu11);
		menu1.addOption(new CLOption("Do something"));
		
		menu2.addOption(new CLOption("Do something else"));
		menu2.addOption(new CLOption("Do yet another thing"));
		
	}
	
	public static void main(String[] args) throws IOException {
		CLInterface cl = new TestCLInterface();
		cl.run();
	}

}
