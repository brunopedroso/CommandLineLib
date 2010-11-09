import java.util.Map;
import java.util.TreeMap;


public class CLInterface {

	Map<String, CLOption> options = new TreeMap<String, CLOption>();
	int optionsIndex = 1;
	
	public void addOption(CLOption option) {
		options.put(Integer.toString(optionsIndex), option);
		optionsIndex++;
		
	}

	public String getScreen() {
		StringBuffer screen = new StringBuffer();
		
		for (String key: options.keySet()) {
			CLOption op = options.get(key);
			screen.append(key + " - ");
			screen.append(op.getText());
			screen.append("\n");
		}
		
		screen.append("?>");
		
		return screen.toString();
	}

	public void choose(String key) {
		options.get(key).run();
	}

}
