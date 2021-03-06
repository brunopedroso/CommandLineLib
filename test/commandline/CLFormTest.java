package commandline;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import commandline.CLForm;
import commandline.CLInterface;
import commandline.CLQuestion;


public class CLFormTest {

	private String formTitle;
	private String enunce;
	private CLForm form;
	private String q1;
	private String q2;
	private CLInterface cl;

	private boolean executed;
	private String expectedResult = "my results";
	
	@Before
	public void setup() {
		
		formTitle = "Fill the name form";
		enunce = "Personal questions";
		form = new CLForm(formTitle, enunce) {

			@Override
			public String run() {
				executed = true;
				return expectedResult;
			};
		};
		
		executed = false;
		
		q1 = "what is your name?";
		q2 = "what is your age?";
		form.addQuestion(q1);
		form.addQuestion(q2, new AnswerValidator(){
			public boolean isAnswerValid(String answer){
				try {
					Integer.parseInt(answer);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		});
		
		cl = new CLInterface();
		cl.addOption(form);
		
	}
	
	@Test
	public void shouldAskOneQuestion() {
		
		cl.answer("1");
		
		String expected = enunce + "\n"
						+ q1 + "\n";
		
		Assert.assertEquals(expected, cl.getScreen());
		
	}
	
	@Test
	public void shouldShowTheSeccondQuestion() {
		
		cl.answer("1");
		
		Assert.assertEquals(enunce + "\n" + q1 + "\n", cl.getScreen());
		cl.answer("Bruno");
		
		Assert.assertEquals(q2  + "\n", cl.getScreen());
		
	}
	
	@Test
	public void shouldShowTheSeccondQuestionAgainIWhileTheAnswerIsNotValid() {
		
		cl.answer("1");
		
		cl.answer("Bruno");
		
		Assert.assertEquals(q2  + "\n", cl.getScreen());

		for (int i = 0; i < 3; i++) {
			cl.answer("NotANumber");
			Assert.assertEquals("Valor inválido!\n" + q2  + "\n", cl.getScreen());
		}
		
		cl.answer("123");
		
		Assert.assertEquals(expectedResult + "\n" , cl.getScreen());
		
	}
	
	@Test
	public void shouldAnswerTheQuestion() {
		
		cl.answer("1");
		
		cl.answer("Bruno");
		cl.answer("32");
		
		Assert.assertEquals("Bruno", form.getAnswer(0));
		Assert.assertEquals("32", form.getAnswer(1));
		
	}
	
	@Test
	public void shouldRunTheFormAfterLastQuestion() {
		
		cl.answer("1");
		Assert.assertFalse(executed);
		
		cl.answer("Bruno");
		Assert.assertFalse(executed);
		
		cl.answer("32");
		
		Assert.assertTrue(executed);
		
	}
	
	@Test
	public void shouldShowResultAfterLastAnswer() {
		
		cl.answer("1");
		Assert.assertFalse(executed);
		
		cl.answer("Bruno");
		Assert.assertFalse(executed);
		
		cl.answer("32");
		
		Assert.assertEquals(expectedResult + "\n" , cl.getScreen());
		
	}
	
	@Test
	public void shouldReturnToMainMenuAfterConfrmResult() {
		
		cl.answer("1");
		Assert.assertFalse(executed);
		
		cl.answer("Bruno");
		Assert.assertFalse(executed);
		
		cl.answer("32");
		
		// confirm: i've seen the results
		cl.answer("");
		
		Assert.assertEquals(cl.getMainMenu().getText() , cl.getScreen());
		
	}
	
	@Test
	public void shouldResetAfterConfrmResult() {
		
		cl.answer("1");
		Assert.assertFalse(executed);
		
		cl.answer("Bruno");
		Assert.assertFalse(executed);
		
		cl.answer("32");
		
		// confirm: i've seen the results
		cl.answer("");
		
		Assert.assertEquals(cl.getMainMenu().getText(), cl.getScreen());
		
		// if it doesnt reset, it throws an error
		cl.answer("1");
		cl.answer("Bruno");
		
	}

	
}
