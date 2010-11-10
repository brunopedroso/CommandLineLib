import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CLInterface {

	CLMenu mainMenu = new CLMenu("main");
	CLMenu currentMenu;
	
	String message;
	private boolean finished;
	private CLForm currentForm;
	private int currentQuestion;
	private CLQuestion question;
	
	public CLInterface() {
		this(null);
	}
	
	public CLInterface(String welcomeMessage) {
		mainMenu = new CLMenu("main", welcomeMessage);
		currentMenu = mainMenu;
	}

	public void addOption(CLOption option) {
		mainMenu.addOption(option);
	}

	public String getScreen() {
		StringBuffer screen = new StringBuffer();
		
		if (message != null) {
			 screen.append(message + "\n");
			 message = null;
		}
		
		if (currentForm!= null) {
			screen.append(currentForm.getText());
		} else {
			screen.append(currentMenu.getMenuText());
		}
		
		
		screen.append("?> ");
		
		return screen.toString();
	}

	public void choose(String key) {
		
		CLOption option = currentMenu.get(key);
		if (option != null) {
			
			if (option instanceof CLMenu) {
				currentMenu = (CLMenu) option;
				
			} else if (option instanceof CLForm) {
				
				currentForm = (CLForm) option;
				
			} else if (option.getText().equals("Cancel")) {
				
				if (currentMenu == mainMenu) {
					this.finished = true;
					
				} else {
					currentMenu = currentMenu.getSuperMenu();
				}
				
			} else {
				option.run();
				currentMenu = mainMenu;
			}
			
		} else {
			
			message = "Invalid option!";
			
		}
		
	}

	public CLMenu getMainMenu() {
		return mainMenu;
	}
	
	public void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(!finished()) {
			System.out.print(getScreen());
			String option = reader.readLine();
			choose(option);
		}
	}

	public boolean finished() {
		return finished;
	}

}
