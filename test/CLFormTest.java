import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class CLFormTest {

	private String formTitle;
	private String enunce;
	private CLForm form;
	private String q1;
	private String q2;
	private CLInterface cl;

	private boolean executed;
	
	@Before
	public void setup() {
		
		formTitle = "Fill the name form";
		enunce = "Personal questions";
		form = new CLForm(formTitle, enunce) {
			@Override
			public void run() {
				executed = true;	
			};
		};
		
		executed = false;
		
		q1 = "what is your name?";
		q2 = "what is your age?";
		form.addQuestion(q1);
		form.addQuestion(q2);
		
		cl = new CLInterface("Testing forms");
		cl.addOption(form);
		
	}
	
	@Test
	public void shouldAskOneQuestion() {
		
		cl.choose("1");
		
		String expected = enunce + "\n"
						+ q1 + "\n"
						+ "?> ";
		
		Assert.assertEquals(expected, cl.getScreen());
		
	}
	
	@Test
	public void shouldShowTheSeccondQuestion() {
		
		cl.choose("1");
		
		Assert.assertEquals(enunce + "\n" + q1 + "\n?> " , cl.getScreen());
		cl.choose("Bruno");
		
		Assert.assertEquals(q2 + "\n?> " , cl.getScreen());
		
	}
	
	@Test
	public void shouldAnswerTheQuestion() {
		
		cl.choose("1");
		
		cl.choose("Bruno");
		cl.choose("32");
		
		List<CLQuestion> questions = form.getQuestions();
		
		Assert.assertEquals(2, questions.size());
		Assert.assertEquals("Bruno", questions.get(0).getAnswer());
		Assert.assertEquals("32", questions.get(1).getAnswer());
		
	}
	
	@Test
	public void shouldRunTheFormAfterLastQuestion() {
		
		cl.choose("1");
		Assert.assertFalse(executed);
		
		cl.choose("Bruno");
		Assert.assertFalse(executed);
		
		cl.choose("32");
		
		Assert.assertTrue(executed);
		
	}
	
	@Test
	public void shouldReturnToMainMenuAfterLastAnswer() {
		
		cl.choose("1");
		Assert.assertFalse(executed);
		
		cl.choose("Bruno");
		Assert.assertFalse(executed);
		
		cl.choose("32");
		
		Assert.assertEquals(cl.getMainMenu().getText() + "?> ", cl.getScreen());
		
	}

	
}
