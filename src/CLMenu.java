import java.util.Map;
import java.util.TreeMap;


public class CLMenu extends CLCompositeOption {
	
	Map<String, CLOption> options = new TreeMap<String, CLOption>();
	int optionsIndex = 1;
	
	private String message;
	
	public CLMenu(String optionText) {
		this(optionText, null);
	}

	public CLMenu(String optionText, String text) {
		super(optionText, text);
	}

	public void addOption(CLOption option) {
		option.setSuperMenu(this);
		options.put(Integer.toString(optionsIndex), option);
		optionsIndex++;
		
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
		
		screen.append(optionsIndex + " - ");
		screen.append("Cancel");
		screen.append("\n");
		
		return screen.toString();	}

	@Override
	public CLCompositeOption answer(String key) {
		
		// cancel option choosen
		if (Integer.parseInt(key) == optionsIndex) {
			return getSuperMenu();
			
		} 
		
		CLOption option =  options.get(key);
		
		if (option == null) {
			message = "Invalid option!";
			return this;
		}
			
		if (option instanceof CLCompositeOption) {
			return (CLCompositeOption) option;
			
		} else {
			option.run();
			return null;
			
		}
			
		
	}


	
}
