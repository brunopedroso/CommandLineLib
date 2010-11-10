import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CLInterface {

	CLMenu mainMenu = new CLMenu("main");
	
	CLCompositeOption currentComposite;
	
	String message;
	private boolean finished;
	
	public CLInterface() {
		this(null);
	}
	
	public CLInterface(String welcomeMessage) {
		mainMenu = new CLMenu("main", welcomeMessage);
		currentComposite = mainMenu;
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
		
		if (currentComposite!= null) {
			screen.append(currentComposite.getText());
		}
		
		screen.append("?> ");
		
		return screen.toString();
	}

	public void choose(String key) {
		
		if (currentComposite instanceof CLMenu) {
			
			CLOption option = ((CLMenu)currentComposite).get(key);
			
			if (option != null) {
				
				if (option instanceof CLCompositeOption) {
					currentComposite = (CLCompositeOption) option;
					
				} else if (option.getText().equals("Cancel")) {
					
					if (currentComposite == mainMenu) {
						this.finished = true;
						
					} else {
						currentComposite = currentComposite.getSuperMenu();
					}
					
				} else {
					option.run();
					currentComposite = mainMenu;
				}
				
			} else {
				
				message = "Invalid option!";
				
			}
			
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
