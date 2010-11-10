
public class CLCompositeOption extends CLOption {

	private String presentationMessage;

	public String getPresentationMessage() {
		return presentationMessage;
	}

	public void setPresentationMessage(String presentationMessage) {
		this.presentationMessage = presentationMessage;
	}

	public CLCompositeOption(String optionText, String presentMessage) {
		super(optionText);
		this.presentationMessage = presentMessage;
	}

}
