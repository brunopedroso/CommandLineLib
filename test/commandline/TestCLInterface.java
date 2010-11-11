package commandline;
import java.io.IOException;


public class TestCLInterface extends CLInterface{
	
	public TestCLInterface() {
		
		super("Hello this is the test application!\nPlease choose an option to start:");
		
		CLMenu menu1 = new CLMenu("First Menu", "This is the First Menu");
		CLMenu menu11 = new CLMenu("Another Menu", "This is Another Menu");
		CLMenu menu2 = new CLMenu("Second Menu", "This is the Second Menu");

		CLForm personalForm = new CLForm("Personal information", "Please, enter your personal information"){
			@Override
			public String run() {
				
				return "you name is " + getAnswer(0) + "\n"
							+ "you age is " + getAnswer(1)  + "\n"
							+ "you gender is " + getAnswer(2) + "\n";
				
			}
		};
		personalForm.addQuestion("What is your name?");
		personalForm.addQuestion("What is your age?", new AnswerValidator() {
			public boolean isAnswerValid(String answer) {
				try {
					Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					return false;
				}
				return true;
			}
		});
		personalForm.addQuestion("What is your gender?");
		
		
		addOption(menu1);
		addOption(menu2);
		addOption(new CLOption("A single option"){
			@Override
			public String run() {
				return "Something done!";
			}
		});
		
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
