package commandline;

public class CLOption {

	private String name;
	private CLMenu superMenu;

	public CLOption(String optionName) {
		this.name = optionName;
	}

	public String getName() {
		return name;
	}

	public void setSuperMenu(CLMenu clMenu) {
		superMenu = clMenu;
	}

	public CLMenu getSuperMenu() {
		return superMenu;
	}
	
	/**
	 * must be implemented by concrete CLOptions
	 */
	public void run() {
	}
	


}
