import java.util.ArrayList;
import java.util.List;


public class CLForm extends CLCompositeOption {

	private int currentQuestion;
	
	private List<CLQuestion> questions = new ArrayList<CLQuestion>();

	public CLForm(String optionText, String presentMessage) {
		super(optionText, presentMessage);
	}
	
	@Override
	public String getText() {
		StringBuffer text = new StringBuffer();
		
		if (currentQuestion==0) {
			text.append(getPresentationMessage() + "\n");
		}
		
		text.append(questions.get(currentQuestion).getText() + "\n");
		
		return text.toString();
	}

	public void addQuestion(String question) {
		questions.add(new CLQuestion(question));
		
	}

}
