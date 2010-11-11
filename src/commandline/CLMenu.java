package commandline;
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
//			screen.append(Constants.TEXT_ERROR + message + Constants.TEXT_NORMAL + "\n");
			screen.append(message + "\n");
			message = null;
		}
		
		String menuText = super.getText();
		if (menuText != null) {
//			screen.append(Constants.TEXT_TITLE);
			screen.append(menuText);
			screen.append("\n");
//			screen.append(Constants.TEXT_NORMAL);
		}
		
		for (String key: options.keySet()) {
			CLOption op = options.get(key);
//			screen.append(Constants.TEXT_OPTIONS);
			screen.append(key + " - ");
			screen.append(op.getName());
			screen.append("\n");
		}
		
		screen.append(optionsIndex + " - ");
		screen.append("Cancelar");
		screen.append("\n");
		
//		screen.append(Constants.TEXT_NORMAL);
		
		return screen.toString();	}

	@Override
	public CLCompositeOption answer(String key) {
		
		try {
			// cancel option choosen
			if (Integer.parseInt(key) == optionsIndex) {
				return getSuperMenu();
			} 
		} catch (NumberFormatException e) {
			message = "Opção inválida!";
			return this;
		}
		
		
		CLOption option =  options.get(key);
		
		if (option == null) {
			message = "Opção inválida!";
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
