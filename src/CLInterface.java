import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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

	public String getScreen() {
		StringBuffer screen = new StringBuffer();
		
		if (currentComposite!= null) {
			screen.append(currentComposite.getText());
		}
		
		screen.append("?> ");
		
		return screen.toString();
	}

	public void answer(String key) {
		
		CLCompositeOption result = currentComposite.answer(key);
		if (result==null) {
			if (currentComposite==mainMenu) {
				finished=true;
			} else {
				currentComposite = mainMenu;
			}
		} else {
			currentComposite = result;
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
			answer(option);
		}
	}

	public boolean finished() {
		return finished;
	}

}
