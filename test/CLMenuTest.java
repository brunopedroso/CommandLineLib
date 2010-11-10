import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;


public class CLMenuTest {

	@Test
	public void shouldShowTheSubmenu() {
		
		CLInterface cl = new CLInterface();
		
		CLMenu menu = new CLMenu("option one");
		menu.addOption(new CLOption("sub option one"));
		menu.addOption(new CLOption("sub option two"));
		
		cl.addOption(menu);
		
		cl.choose("1");
		
		String expectedScreen =   "1 - sub option one\n"
								+ "2 - sub option two\n"
								+ "3 - Cancel\n"
								+ "?> ";
		
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
			public void run() {
				list.add("executed!");
			}
		});
		
		cl.addOption(menu);
		
		cl.choose("1");
		Assert.assertEquals("should not have executed the option yet", 0,list.size());
		
		cl.choose("2");
		Assert.assertEquals("should have executed the option", 1,list.size());
		
	}
	
	@Test
	public void shouldReturnToMainMenuAfterOptionExecution() {
		
		CLMenu menu = new CLMenu("option one");
		menu.addOption(new CLOption("sub option one"));
		menu.addOption(new CLOption("sub option two"));
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		Assert.assertEquals("", cl.getMainMenu().getText() + "?> ",cl.getScreen());
		
		cl.choose("1");
		Assert.assertEquals("", menu.getText() + "?> ",cl.getScreen());
		
		cl.choose("2");
		Assert.assertEquals("", cl.getMainMenu().getText() + "?> ",cl.getScreen());
		
	}
	
	@Test
	public void shouldReturnToMainMenuAfterSubOptionExecution() {
		
		CLMenu submenu = new CLMenu("option one-one");
		submenu.addOption(new CLOption("sub option one-one-one"));
		submenu.addOption(new CLOption("sub option one-one-two"));
		
		CLMenu menu = new CLMenu("option one");
		menu.addOption(submenu);
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		Assert.assertEquals("", cl.getMainMenu().getText() + "?> ",cl.getScreen());
		
		cl.choose("1");
		Assert.assertEquals("", menu.getText() + "?> ",cl.getScreen());
		
		cl.choose("1");
		Assert.assertEquals("", submenu.getText() + "?> ",cl.getScreen());
		
		cl.choose("2");
		Assert.assertEquals("should bo back to the main menu", cl.getMainMenu().getText() + "?> ",cl.getScreen());
		
	}
	
	@Test
	public void shouldExecuteTheSubSubmenuOption() {
		
		CLInterface cl = new CLInterface();
		
		final ArrayList<String> list = new ArrayList<String>(); 
		
		CLMenu subSubMenu = new CLMenu("option one-one");
		subSubMenu.addOption(new CLOption("option one-one-one"));
		subSubMenu.addOption(new CLOption("option one-one-two") {
			@Override
			public void run() {
				list.add("executed!");
			}
		});
		
		CLMenu subMenu = new CLMenu("option one");
		subMenu.addOption(subSubMenu);
		
		cl.addOption(subMenu);
		
		cl.choose("1");
		Assert.assertEquals("should not have executed the option yet", 0,list.size());
		Assert.assertEquals("", subMenu.getText() + "?> ",cl.getScreen());
		
		cl.choose("1");
		Assert.assertEquals("should not have executed the option yet", 0,list.size());
		Assert.assertEquals("", subSubMenu.getText() + "?> ",cl.getScreen());
		
		cl.choose("2");
		Assert.assertEquals("should have executed the option", 1,list.size());
		
	}
	
	@Test
	public void shouldFinishWhenCancelTheMainMenu() {
		
		CLInterface cl = new CLInterface();
		cl.addOption(new CLOption("first option"));
		
		Assert.assertFalse(cl.finished());
		
		cl.choose("2"); // exit
		Assert.assertTrue("should have finished", cl.finished());
		
	}
	
	@Test
	public void shouldReturnToMainMenuWhenCancelIsChoosenInSub() {
		
		CLMenu menu = new CLMenu("menu");
		menu.addOption(new CLOption("first option"));
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		cl.choose("1");
		cl.choose("2"); // exit
		
		Assert.assertFalse("should not have finished", cl.finished());
		Assert.assertEquals("", cl.getMainMenu().getText() + "?> ",cl.getScreen());
		
	}
	
	@Test
	public void shouldHaveTheCancelOptionEvenWithoutOtherOptions() {
		
		CLMenu menu = new CLMenu("menu");
		
		CLInterface cl = new CLInterface();
		cl.addOption(menu);
		
		cl.choose("1");
		
		String expected = "1 - Cancel\n?> ";
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
		
		cl.choose("1");
		cl.choose("1");
		cl.choose("2"); // exit
		
		Assert.assertFalse("should not have finished", cl.finished());
		Assert.assertEquals("", submenu.getText() + "?> ",cl.getScreen());
		
	}	
	
}
