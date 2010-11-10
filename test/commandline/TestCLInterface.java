package commandline;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import commandline.CLInterface;
import commandline.CLMenu;
import commandline.CLOption;


public class TestCLInterface extends CLInterface{
	
	public TestCLInterface() {
		
		super("Hello this is the test application!\nPlease choose an option to start:");
		
		CLMenu menu1 = new CLMenu("First Menu", "This is the First Menu");
		CLMenu menu11 = new CLMenu("Another Manu", "This is Another Menu");
		CLMenu menu2 = new CLMenu("Second Menu", "This is the Second Menu");

		CLForm personalForm = new CLForm("Personal information", "Please, enter your personal information"){
			@Override
			public void run() {
				System.out.println("you name is " + getQuestions().get(0).getAnswer());
				System.out.println("you age is " + getQuestions().get(1).getAnswer());
				System.out.println("you gender is " + getQuestions().get(2).getAnswer());
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		personalForm.addQuestion("What is your name?");
		personalForm.addQuestion("What is your age?");
		personalForm.addQuestion("What is your gender?");
		
		
		addOption(menu1);
		addOption(menu2);
		addOption(new CLOption("A single option"));
		
		menu1.addOption(menu11);
		menu1.addOption(new CLOption("Do something"));
		menu1.addOption(personalForm);
		
		menu2.addOption(new CLOption("Do something else"));
		menu2.addOption(new CLOption("Do yet another thing"));
		
	}
	
	public static void main(String[] args) throws IOException {
		CLInterface cl = new TestCLInterface();
		cl.run();
	}

}
