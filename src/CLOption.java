
public class CLOption {

	private String text;

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

}
