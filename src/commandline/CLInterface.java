package commandline;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLInterface {

	CLMenu mainMenu = new CLMenu("main");
	
	CLCompositeOption currentComposite;
	
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

	/**
	 * The current screen that will be shown to the user
	 * @return
	 */
	public String getScreen() {
		StringBuffer screen = new StringBuffer();
		
		if (currentComposite!= null) {
			screen.append(currentComposite.getText());
		}
		
		screen.append("?> ");
		
		return screen.toString();
	}

	/**
	 * Registers the input from the user
	 * @param key
	 */
	public void answer(String key) {
		
		CLCompositeOption result = currentComposite.answer(key);
		
		// composite has finished
		if (result==null) {
			
			// main menu has finished
			if (currentComposite==mainMenu) {
				finished=true;
				
			} else {
				//any other menu has finished, go back to main menu
				currentComposite = mainMenu;
			}
			
		} else {
			// go to the menu specified by the composite
			currentComposite = result;
			
		}
			
	}

	public CLMenu getMainMenu() {
		return mainMenu;
	}
	
	public void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(!finished()) {
			
			clearScreen();
			
			System.out.print(getScreen());
			String option = reader.readLine();
			answer(option);
		}
	}

	private void clearScreen() {
		
//		char[] cls = new char[100];
//		for(int i=0;i<100;i++)
//			cls[i]='\n';
//			
//		System.out.println(String.valueOf(cls));
		
		System.out.println("\033[2J");
		System.out.println("\033[0;0H");
		
	}

	public boolean finished() {
		return finished;
	}

}
