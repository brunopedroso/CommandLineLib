package commandline;

public class CLQuestion {

	private String text;
	private String answer;

	public CLQuestion(String question) {
		text = question;
	}

	public Object getText() {
		return text;
	}

	public void answer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

}
