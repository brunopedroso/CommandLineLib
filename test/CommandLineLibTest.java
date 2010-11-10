import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;


public class CommandLineLibTest {
	
	@Test
	public void shouldShowOneOption() {
		
		CLInterface cl = new CLInterface();
		
		String optionText = "option one";
		CLOption firstOption = new CLOption(optionText); 
		
		cl.addOption(firstOption);
		
		String expectedScreen =   "1 - " + optionText + "\n"
								+ "2 - Cancel\n"
								+ "?> ";
		
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
							  + "3 - Cancel\n"
							  + "?> ";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}
	
	@Test
	public void shouldExecuteTheChoosenOption() {
		
		CLInterface cl = new CLInterface();
		
		final ArrayList<String> list = new ArrayList<String>(); 
		
		cl.addOption(new CLOption("option one") {
			public void run() {
				list.add("executed!");
			}
		});
		
		cl.choose("1");
		
		Assert.assertEquals("should have executed the option", 1,list.size());
	}
	
	@Test
	public void shouldExecuteTheSecondOption() {
		
		CLInterface cl = new CLInterface();
		
		final ArrayList<String> list = new ArrayList<String>(); 
		
		cl.addOption(new CLOption("option one"));
		cl.addOption(new CLOption("option two") {
			public void run() {
				list.add("executed!");
			}
		});
		
		cl.choose("2");
		
		Assert.assertEquals("should have executed the option", 1,list.size());
	}
	
	@Test
	public void shouldShowAMessageIfUndefindedOption() {
		
		CLInterface cl = new CLInterface();
		cl.addOption(new CLOption("option one"));
		
		cl.choose("3");
		
		String expectedScreen =   "Invalid option!\n"
								+ "1 - option one\n" 
								+ "2 - Cancel\n"
								+ "?> ";

		Assert.assertEquals(expectedScreen, cl.getScreen());

	}
	

	
	@Test
	public void shouldShowTheWelcomeMessage() {
		
		CLInterface cl = new CLInterface("Hi, this is the welcome message");
		
		CLOption firstOption = new CLOption("option one"); 
		
		cl.addOption(firstOption);
		
		String expectedScreen =	  "Hi, this is the welcome message\n"   
								+ "1 - option one\n"
								+ "2 - Cancel\n"
								+ "?> ";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}
	
	@Test
	public void shouldShowTheMenuMessage() {
		
		CLMenu submenu = new CLMenu("submenu", "Please choose one of the options");
		submenu.addOption(new CLOption("first option"));
		
		CLInterface cl = new CLInterface();
		cl.addOption(submenu);
		
		cl.choose("1");
		
		String expectedScreen =	  "Please choose one of the options\n"
								+ "1 - first option\n"
								+ "2 - Cancel\n"
								+ "?> ";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}

	
	//TODO forms
	
}
