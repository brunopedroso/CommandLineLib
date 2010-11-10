
public class CLCompositeOption extends CLOption {

	private String text;

	public CLCompositeOption(String name, String text) {
		super(name);
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * should be overriden
	 */
	public CLCompositeOption answer(String answer) {
		return null;
	}


}
