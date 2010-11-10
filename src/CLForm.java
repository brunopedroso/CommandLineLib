import java.util.ArrayList;
import java.util.List;


public class CLForm extends CLOption {

	private int currentQuestion;
	private String presentMessage;
	private List<CLQuestion> questions = new ArrayList<CLQuestion>();

	public CLForm(String optionText, String presentMessage) {
		super(optionText);
		this.presentMessage = presentMessage;
	}
	
	@Override
	public String getText() {
		StringBuffer text = new StringBuffer();
		
		if (currentQuestion==0) {
			text.append(presentMessage + "\n");
		}
		
		text.append(questions.get(currentQuestion).getText() + "\n");
		
		return text.toString();
	}

	public void addQuestion(String question) {
		questions.add(new CLQuestion(question));
		
	}

}
