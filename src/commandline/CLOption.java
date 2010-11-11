package commandline;

public class CLOption {

	private String name;
	private CLMenu superMenu;
	private String result;

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
	public String run() {
		return null;
	}

	/**
	 * should be called by run if there is results
	 * @param result
	 */
	protected void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	


}
