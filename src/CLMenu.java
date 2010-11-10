import java.util.Map;
import java.util.TreeMap;


public class CLMenu extends CLCompositeOption {
	
	Map<String, CLOption> options = new TreeMap<String, CLOption>();
	int optionsIndex = 1;
	
	private CLOption exitOption = new CLOption("Cancel");
	
	public CLMenu(String optionText) {
		this(optionText, null);
	}

	public CLMenu(String optionText, String message) {
		super(optionText, message);
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

	public String getText() {
		StringBuffer screen = new StringBuffer();
		
		String presentationMessage = getPresentationMessage();
		if (presentationMessage != null) {
			screen.append(presentationMessage);
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
