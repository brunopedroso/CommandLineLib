

public class CLInterface {

	CLMenu mainMenu = new CLMenu("main");
	CLMenu currentMenu;
	
	public CLInterface() {
		currentMenu = mainMenu;
	}
	
	public void addOption(CLOption option) {
		mainMenu.addOption(option);
	}

	public String getScreen() {
		return currentMenu.getMenuText();
	}

	public void choose(String key) {
		CLOption option = mainMenu.get(key);
		if (option instanceof CLMenu) {
			currentMenu = (CLMenu) option;
		} else {
			option.run();
			
		}
	}

}
