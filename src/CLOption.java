
public class CLOption {

	private String text;
	private CLMenu superMenu;

	public CLOption(String optionText) {
		this.text = optionText;
	}

	public String getText() {
		return text;
	}

	/**
	 * must be implemented by concrete CLOptions
	 */
	public void run() {
	}
	
	public void setSuperMenu(CLMenu clMenu) {
		superMenu = clMenu;
	}

	public CLMenu getSuperMenu() {
		return superMenu;
	}
	


}
