import java.util.ArrayList;
import java.util.List;


public class CLForm extends CLCompositeOption {

	private int currentQuestion = 0;
	
	private List<CLQuestion> questions = new ArrayList<CLQuestion>();

	public CLForm(String optionText, String presentMessage) {
		super(optionText, presentMessage);
	}
	
	@Override
	public String getText() {
		StringBuffer text = new StringBuffer();
		
		if (currentQuestion==0) {
			//TODO super text can be null
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
		if (currentQuestion==questions.size()) {
			run();
			return null;
		}
		return this;
	}


}
