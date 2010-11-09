

public class CLInterface {

	CLMenu mainMenu = new CLMenu("main");
	CLMenu currentMenu;
	
	String message;
	
	public CLInterface() {
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
		
		screen.append(currentMenu.getMenuText());
		screen.append("?>");
		
		return screen.toString();
	}

	public void choose(String key) {
		CLOption option = currentMenu.get(key);
		if (option != null) {
			
			if (option instanceof CLMenu) {
				currentMenu = (CLMenu) option;
			} else {
				option.run();
			}
			
		} else {
			
			message = "Invalid option!";
			
		}
	}

}
