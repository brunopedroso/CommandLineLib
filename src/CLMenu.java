import java.util.Map;
import java.util.TreeMap;


public class CLMenu extends CLCompositeOption {
	
	Map<String, CLOption> options = new TreeMap<String, CLOption>();
	int optionsIndex = 1;
	
	//TODO is there a better way?
	private CLOption exitOption = new CLOption("Cancel");
	private String message;
	
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
		
		if (message != null) {
			 screen.append(message + "\n");
			 message = null;
		}
		
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

	@Override
	public CLCompositeOption answer(String key) {
		
		CLOption option =  options.get(key);
		
		if (option != null) {
			
			if (option instanceof CLCompositeOption) {
				return (CLCompositeOption) option;
				
			//TODO can it be done in a smarter way? finished?
			} else if (option.getName().equals("Cancel")) {
				return getSuperMenu();
				
			} else {
				option.run();
				return null;
				
			}
			
		} else {
			
			message = "Invalid option!";
			return this;
			
		}
		
	}


	
}
