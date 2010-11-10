package commandline;
import java.util.ArrayList;
import java.util.List;


public class CLForm extends CLCompositeOption {

	private List<CLQuestion> questions = new ArrayList<CLQuestion>();
	private int currentQuestion = 0;

	public CLForm(String optionText, String presentMessage) {
		super(optionText, presentMessage);
	}
	
	@Override
	public String getText() {
		StringBuffer text = new StringBuffer();
		
		if (currentQuestion==0) {
			text.append(super.getText() + "\n");
		}
		
		text.append(questions.get(currentQuestion).getText() + "\n");
		
		return text.toString();
	}

	public void addQuestion(String question) {
		questions.add(new CLQuestion(question));
		
	}
	
	public List<CLQuestion> getQuestions() {
		return questions;
	}

	@Override
	public CLCompositeOption answer(String answer) {
		
		questions.get(currentQuestion).answer(answer);
		currentQuestion++;
		
		// form is full
		if (currentQuestion==questions.size()) {
			
			run();
			
			// return to the main menu
			return null;
		}
		
		// maintain in this composite
		return this;
	}


}
