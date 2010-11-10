import java.util.Map;
import java.util.TreeMap;


public class CLMenu extends CLCompositeOption {
	
	Map<String, CLOption> options = new TreeMap<String, CLOption>();
	int optionsIndex = 1;
	
	//TODO is there a better way?
	private CLOption exitOption = new CLOption("Cancel");
	
	public CLMenu(String optionText) {
		this(optionText, null);
	}

	public CLMenu(String optionText, String text) {
		super(optionText, text);
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

	@Override
	public String getText() {
		StringBuffer screen = new StringBuffer();
		
		String menuText = super.getText();
		if (menuText != null) {
			screen.append(menuText);
			screen.append("\n");
		}
		
		for (String key: options.keySet()) {
			CLOption op = options.get(key);
			screen.append(key + " - ");
			screen.append(op.getName());
			screen.append("\n");
		}
		
		return screen.toString();	}

	//TODO tell dont ask?
	public CLOption get(String key) {
		return options.get(key);
	}


	
}
