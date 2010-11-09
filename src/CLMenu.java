import java.util.Map;
import java.util.TreeMap;


public class CLMenu extends CLOption {
	
	Map<String, CLOption> options = new TreeMap<String, CLOption>();
	int optionsIndex = 1;
	private CLOption exitOption;
	private CLMenu superMenu;

	public CLMenu(String optionText) {
		super(optionText);
		exitOption = new CLOption("Cancel");
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
