import java.util.Map;
import java.util.TreeMap;


public class CLMenu extends CLOption {
	
	Map<String, CLOption> options = new TreeMap<String, CLOption>();
	int optionsIndex = 1;
	private CLOption exitOption;
	private CLMenu superMenu;
	private String menuMessage;

	public CLMenu(String optionText) {
		this(optionText, null);
	}

	public CLMenu(String optionText, String message) {
		super(optionText);
		exitOption = new CLOption("Cancel");
		this.menuMessage = message;
		options.put(Integer.toString(optionsIndex), exitOption);
	}

	public void addOption(CLOption option) {
		if (option instanceof CLMenu) {
			((CLMenu)option).setSuperMenu(this);
		}
		options.put(Integer.toString(optionsIndex), option);
		optionsIndex++;
		options.put(Integer.toString(optionsIndex), exitOption);
		
	}

	private void setSuperMenu(CLMenu clMenu) {
		superMenu = clMenu;
	}

	public CLMenu getSuperMenu() {
		return superMenu;
	}
	
	public String getMenuText() {
		StringBuffer screen = new StringBuffer();
		
		if (menuMessage != null) {
			screen.append(menuMessage);
			screen.append("\n");
		}
		
		for (String key: options.keySet()) {
			CLOption op = options.get(key);
			screen.append(key + " - ");
			screen.append(op.getText());
			screen.append("\n");
		}
		
		return screen.toString();	}

	public CLOption get(String key) {
		return options.get(key);
	}


	
}
