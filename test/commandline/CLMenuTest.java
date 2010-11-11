package commandline;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import commandline.CLInterface;
import commandline.CLMenu;
import commandline.CLOption;


public class CLMenuTest {

	@Test
	public void shouldShowTheMenuMessage() {
		
		CLMenu submenu = new CLMenu("submenu", "Please choose one of the options");
		submenu.addOption(new CLOption("first option"));
		
		CLInterface cl = new CLInterface();
		cl.addOption(submenu);
		
		cl.answer("1");
		
		String expectedScreen =	  "Please choose one of the options\n"
								+ "1 - first option\n"
								+ "2 - Cancelar\n";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());
		
	}
	
	@Test
	public void shouldShowTheSubmenu() {
		
		CLInterface cl = new CLInterface();
		
		CLMenu menu = new CLMenu("option one");
		menu.addOption(new CLOption("sub option one"));
		menu.addOption(new CLOption("sub option two"));
		
		cl.addOption(menu);
		
		cl.answer("1");
		
		String expectedScreen =   "1 - sub option one\n"
								+ "2 - sub option two\n"
								+ "3 - Cancelar\n";
		
		Assert.assertEquals(expectedScreen, cl.getScreen());

	}
	
	@Test
	public void shouldExecuteTheSubmenuOption() {
		
		CLInterface cl = new CLInterface();
		
		final ArrayList<String> list = new ArrayList<String>(); 
		
		CLMenu menu = new CLMenu("option one");
		menu.addOption(new CLOption("sub option one"));
		menu.addOption(new CLOption("sub option two") {
			@Override
			public String run() {
				list.add("executed!");
				return null;
			}
		});
		
		cl.addOption(menu);
		
		cl.answer("1");
		Assert.assertEquals("should not have executed the option yet", 0,list.size());
		
		cl.answer("2");
		Assert.assertEquals("should have executed the option", 1,list.size());
		
	}
	
	@Test
	public void shouldRemainInTheMainMenuWhenExecutedAnActionFromTheMainMenu() {
		
		CLInterface cl = new CLInterface();
		cl.addOption(new CLOption("first option"));
		
		Assert.assertFalse(cl.finished());
		
		cl.answer("1");
		
		Assert.assertFalse("should not have finished", cl.finished());
		
		String expectedScreen =	  
			  "1 - first option\n"
			+ "2 - Cancelar\n";

		Assert.assertEquals(expectedScreen, cl.getScreen());

		
	}
	
	@Test
	public void shouldReturnToMainMenuAfterOptionExecution() {
		
		CLMenu menu = new CLMenu("option one");
		menu.addOption(new CLOption("sub option one"));
		menu.addOption(new CLOption("sub option two"));
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		Assert.assertEquals("", cl.getMainMenu().getText(),cl.getScreen());
		
		cl.answer("1");
		Assert.assertEquals("", menu.getText(),cl.getScreen());
		
		cl.answer("2");
		Assert.assertEquals("", cl.getMainMenu().getText(),cl.getScreen());
		
	}
	
	@Test
	public void shouldReturnToParentMenuAfterSubOptionExecution() {
		
		CLMenu submenu = new CLMenu("option one-one");
		submenu.addOption(new CLOption("sub option one-one-one"));
		submenu.addOption(new CLOption("sub option one-one-two"));
		
		CLMenu menu = new CLMenu("option one");
		menu.addOption(submenu);
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		Assert.assertEquals("", cl.getMainMenu().getText(),cl.getScreen());
		
		cl.answer("1");
		Assert.assertEquals("", menu.getText(),cl.getScreen());
		
		cl.answer("1");
		Assert.assertEquals("", submenu.getText(),cl.getScreen());
		
		cl.answer("2");
		Assert.assertEquals("should bo back to the parent menu", menu.getText(),cl.getScreen());
		
	}
	
	@Test
	public void shouldExecuteTheSubSubmenuOption() {
		
		CLInterface cl = new CLInterface();
		
		final ArrayList<String> list = new ArrayList<String>(); 
		
		CLMenu subSubMenu = new CLMenu("option one-one");
		subSubMenu.addOption(new CLOption("option one-one-one"));
		subSubMenu.addOption(new CLOption("option one-one-two") {
			@Override
			public String run() {
				list.add("executed!");
				return null;
			}
		});
		
		CLMenu subMenu = new CLMenu("option one");
		subMenu.addOption(subSubMenu);
		
		cl.addOption(subMenu);
		
		cl.answer("1");
		Assert.assertEquals("should not have executed the option yet", 0,list.size());
		Assert.assertEquals("", subMenu.getText(),cl.getScreen());
		
		cl.answer("1");
		Assert.assertEquals("should not have executed the option yet", 0,list.size());
		Assert.assertEquals("", subSubMenu.getText() ,cl.getScreen());
		
		cl.answer("2");
		Assert.assertEquals("should have executed the option", 1,list.size());
		
	}
	
	@Test
	public void shouldFinishWhenCancelTheMainMenu() {
		
		CLInterface cl = new CLInterface();
		cl.addOption(new CLOption("first option"));
		
		Assert.assertFalse(cl.finished());
		
		cl.answer("2"); // exit
		Assert.assertTrue("should have finished", cl.finished());
		
	}
	

	
	@Test
	public void shouldReturnToMainMenuWhenCancelIsChoosenInSub() {
		
		CLMenu menu = new CLMenu("menu");
		menu.addOption(new CLOption("first option"));
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		cl.answer("1");
		cl.answer("2"); // exit
		
		Assert.assertFalse("should not have finished", cl.finished());
		Assert.assertEquals("", cl.getMainMenu().getText() ,cl.getScreen());
		
	}
	
	@Test
	public void shouldHaveTheCancelOptionEvenWithoutOtherOptions() {
		
		CLMenu menu = new CLMenu("menu");
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		cl.answer("1");
		
		String expected = "1 - Cancelar\n";
		Assert.assertEquals("", expected, cl.getScreen());
		
	}
	
	@Test
	public void shouldReturnToSubMenuWhenCancelIsChoosenInSubSub() {
		
		CLMenu subsubmenu = new CLMenu("subsubmenu");
		subsubmenu.addOption(new CLOption("first option"));
		
		CLMenu submenu = new CLMenu("submenu");
		submenu.addOption(subsubmenu);
		
		CLInterface cl = new CLInterface();
		cl.addOption(submenu);
		
		cl.answer("1");
		cl.answer("1");
		cl.answer("2"); // exit
		
		Assert.assertFalse("should not have finished", cl.finished());
		Assert.assertEquals("", submenu.getText() ,cl.getScreen());
		
	}	
	
	@Test
	public void shouldShowAMessageIfUndefindedOption() {
		
		CLInterface cl = new CLInterface();
		cl.addOption(new CLOption("option one"));
		
		cl.answer("3");
		
		String expectedScreen =   "Opção inválida!\n"
								+ "1 - option one\n" 
								+ "2 - Cancelar\n";

		Assert.assertEquals(expectedScreen, cl.getScreen());

	}
	
	@Test
	public void shouldShowAMessageIfNotANumberAnswer() {
		
		CLInterface cl = new CLInterface();
		cl.addOption(new CLOption("option one"));
		
		cl.answer("notAnumber");
		
		String expectedScreen =   "Opção inválida!\n"
								+ "1 - option one\n" 
								+ "2 - Cancelar\n";

		Assert.assertEquals(expectedScreen, cl.getScreen());

	}
	
}
