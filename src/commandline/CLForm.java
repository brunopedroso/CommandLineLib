package commandline;
import java.util.ArrayList;
import java.util.List;


public class CLForm extends CLCompositeOption {

	private List<CLQuestion> questions = new ArrayList<CLQuestion>();
	private int currentQuestion = 0;
	private String result = null;

	public CLForm(String optionText, String presentMessage) {
		super(optionText, presentMessage);
	}
	
	@Override
	public String getText() {
		
		StringBuffer text = new StringBuffer();
		
		// already finished. I'll show the result
		if (result!= null){
			
			text.append(result);
			text.append("\n");
			
		} else {
			
			if (currentQuestion==0) {
				text.append(super.getText());
				text.append("\n");
			}
			
			text.append(questions.get(currentQuestion).getText());
			text.append("\n");
			
		}
		
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

		// have just ran
		if (result!=null) {
			
			// reset the form
			currentQuestion = 0;
			result = null;
			
			// return to the parent menu
			return getSuperMenu();
			
		} else {
			
			questions.get(currentQuestion).answer(answer);
			currentQuestion++;
			
			// this was the last question
			if (currentQuestion==questions.size()) {
				result = run();
			}
			
			// maintain in this composite
			return this;
			
		}
		
	}


}
