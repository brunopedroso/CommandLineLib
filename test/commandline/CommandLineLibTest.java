package commandline;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import commandline.CLInterface;
import commandline.CLMenu;
import commandline.CLOption;


public class CommandLineLibTest {
	
	@Test
	public void shouldShowOneOption() {
		
		CLInterface cl = new CLInterface();
		
		String optionText = "option one";
		CLOption firstOption = new CLOption(optionText); 
		
		cl.addOption(firstOption);
		
		String expectedScreen =   "1 - " + optionText + "\n"
								+ "2 - Cancelar\n";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}
	
	@Test
	public void shouldShowTwoOptions() {
		
		CLInterface cl = new CLInterface();
		
		String optionText = "option one";
		CLOption firstOption = new CLOption(optionText);
		
		String optionText2 = "option two";
		CLOption secondOption = new CLOption(optionText2); 
		
		cl.addOption(firstOption);
		cl.addOption(secondOption);
		
		String expectedScreen = "1 - " + optionText + "\n" 
							  + "2 - " + optionText2 + "\n"
							  + "3 - Cancelar\n";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}
	
	@Test
	public void shouldExecuteTheChoosenOption() {
		
		CLInterface cl = new CLInterface();
		
		final ArrayList<String> list = new ArrayList<String>(); 
		
		cl.addOption(new CLOption("option one") {
			public String run() {
				list.add("executed!");
				return null;
			}
		});
		
		cl.answer("1");
		
		Assert.assertEquals("should have executed the option", 1,list.size());
	}
	
	@Test
	public void shouldExecuteTheSecondOption() {
		
		CLInterface cl = new CLInterface();
		
		final ArrayList<String> list = new ArrayList<String>(); 
		
		cl.addOption(new CLOption("option one"));
		cl.addOption(new CLOption("option two") {
			public String run() {
				list.add("executed!");
				return null;
			}
		});
		
		cl.answer("2");
		
		Assert.assertEquals("should have executed the option", 1,list.size());
	}
	
	@Test
	public void shouldShowResultOfExecutedOption() {
		
		CLInterface cl = new CLInterface();
		
		cl.addOption(new CLOption("option one"));
		cl.addOption(new CLOption("option two") {
			public String run() {
				return "I have executed.";
			}
		});
		
		cl.answer("2");
		
		Assert.assertTrue(cl.getScreen().startsWith("I have executed."));
		
	}
	
	@Test
	public void shouldShowTheWelcomeMessage() {
		
		CLInterface cl = new CLInterface("Hi, this is the welcome message");
		
		CLOption firstOption = new CLOption("option one"); 
		
		cl.addOption(firstOption);
		
		String expectedScreen =	  "Hi, this is the welcome message\n"   
								+ "1 - option one\n"
								+ "2 - Cancelar\n";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}
	
	@Test
	public void shouldShowTheWelcomeMessageJustOnTheFirstTime() {
		
		CLInterface cl = new CLInterface("Hi, this is the welcome message", "and this is the mainMenu message");
		
		CLOption firstOption = new CLOption("option one"); 
		
		cl.addOption(firstOption);
		
		String expectedScreen =	  "Hi, this is the welcome message\n" 
			+ "and this is the mainMenu message\n"
			+ "1 - option one\n"
			+ "2 - Cancelar\n";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
		expectedScreen = "and this is the mainMenu message\n"
			+ "1 - option one\n"
			+ "2 - Cancelar\n";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}
	
	//TODO validation of form data (guarda o object logo..)

}
