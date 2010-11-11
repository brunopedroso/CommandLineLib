package commandline;

public class CLQuestion {

	private String text;
	private String answer;
	private AnswerValidator answerValidator;

	public CLQuestion(String question, AnswerValidator answerValidator) {
		text = question;
		this.answerValidator = answerValidator;
	}

	public Object getText() {
		return text;
	}

	public boolean answer(String answer) {
		if (answerValidator.isAnswerValid(answer)) {
			this.answer = answer;
			return true;
		} else {
			return false;
		}
	}

	public String getAnswer() {
		return answer;
	}

}
