package commandline;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLInterface {

	CLMenu mainMenu = new CLMenu("main");
	
	CLCompositeOption currentComposite;
	
	private String welcomeMessage;
	private boolean welcomeShown = false;
	
	public CLInterface() {
		this(null);
	}
	
	public CLInterface(String welcomeMessage) {
		this(welcomeMessage, null);
	}
	
	public CLInterface(String welcomeMessage, String mainMenuMessage) {
		mainMenu = new CLMenu("main", mainMenuMessage);
		this.welcomeMessage = welcomeMessage;
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
		
		if (!welcomeShown && welcomeMessage != null) {
			screen.append(welcomeMessage);
			screen.append("\n");
			welcomeShown=true;
		}
			
		if (currentComposite!= null) {
			screen.append(currentComposite.getText());
		}
		
//		screen.append("?> ");
		
		return screen.toString();
	}

	/**
	 * Registers the input from the user
	 * @param key
	 */
	public void answer(String key) {
		
		CLCompositeOption result = currentComposite.answer(key);
		
		if (result!=null) {
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
			
			System.out.println(getScreen());
			System.out.print("?> ");
			
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
		return mainMenu.finished();
	}

}
