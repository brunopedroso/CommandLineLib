import junit.framework.Assert;

import org.junit.Test;


public class CLFormTest {

	@Test
	public void shouldAskAQuestionAndExecuteTheOption() {
		
		String optionText = "Fill the name form";
		String presentMessage = "This is the name form, please answer the questions";
		CLForm form = new CLForm(optionText, presentMessage);

		String question = "what is your name?";
		form.addQuestion(question);
		
		CLInterface cl = new CLInterface("Testing forms");
		cl.addOption(form);
		
		cl.choose("1");
		
		String expected = presentMessage + "\n"
						+ question + "\n"
						+ "?> ";
		
		Assert.assertEquals(expected, cl.getScreen());
		
	}
	
}
