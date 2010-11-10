import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;


public class CLFormTest {

	@Test
	public void shouldAskOneQuestion() {
		
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
	
	@Test
	public void shouldShowTheSeccondQuestion() {
		
		String optionText = "Fill the name form";
		String enunce = "Personal questions";
		CLForm form = new CLForm(optionText, enunce);
		
		String q1 = "what is your name?";
		String q2 = "what is your age?";
		form.addQuestion(q1);
		form.addQuestion(q2);
		
		CLInterface cl = new CLInterface("Testing forms");
		cl.addOption(form);
		
		cl.choose("1");
		
		Assert.assertEquals(enunce + "\n" + q1 + "\n?> " , cl.getScreen());
		cl.choose("Bruno");
		
		Assert.assertEquals(q2 + "\n?> " , cl.getScreen());
		
	}
	
	@Test
	public void shouldExecuteTheFormWhenTheLastQuestionIsAnswered() {
		
		String optionText = "Fill the name form";
		String enunce = "Personal questions";
		CLForm form = new CLForm(optionText, enunce);
		
		String q1 = "what is your name?";
		String q2 = "what is your age?";
		form.addQuestion(q1);
		form.addQuestion(q2);
		
		CLInterface cl = new CLInterface("Testing forms");
		cl.addOption(form);
		
		cl.choose("1");
		
		cl.choose("Bruno");
		cl.choose("32");
		
		List<String> answers = form.getAnswers();
		
		Assert.assertEquals(2, answers.size());
		Assert.assertEquals("Bruno", answers.get(0));
		Assert.assertEquals("32", answers.get(1));
		
	}
	
}
