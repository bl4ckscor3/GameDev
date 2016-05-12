package bl4ckscor3.game.gamedev.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import bl4ckscor3.game.gamedev.menu.main.LoadMenu;
import bl4ckscor3.game.gamedev.menu.main.MainMenu;
import bl4ckscor3.game.gamedev.menu.main.PauseMenu;
import bl4ckscor3.game.gamedev.menu.main.SaveMenu;
import bl4ckscor3.game.gamedev.menu.settings.ResolutionMenu;
import bl4ckscor3.game.gamedev.menu.settings.SeedMenu;
import bl4ckscor3.game.gamedev.menu.settings.SettingsMenu;
import bl4ckscor3.game.gamedev.util.CustomArrayList;

public class Menu
{
	private static final ArrayList<GameState> previousStates = new ArrayList<GameState>();
	private static GameState currentState = GameState.MAIN; //the main menu is opened when the game starts
	private static int highestOption = 5;
	private static int selectedOption = 0; //the option currently selected - 0 is the top option, the highest number is the bottom option
	public static Point[] optionLocations;
	public static CustomArrayList<IMenu> menuStates = new CustomArrayList<IMenu>();
	public static final Color colorM = new Color(27, 72, 127); //color for the main menu
	public static final Color colorF = new Color(255, 255, 255); //color for the font
	
	/**
	 * Determine which menu to show
	 * @param g The Graphics to draw with
	 */
	public static void displayMenu(Graphics g)
	{
		//drawing the rectangle in the back to make the screen look dimmed
		g.setColor(new Color(0, 0, 0, 64));
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(colorF);
		
		if(currentState != GameState.OFF)
			getMenu(currentState).show(g);
	}

	/**
	 * Setting the menu to display. Used to display the correct menu part (main, settings etc.) and determine the actions done on clicking
	 * @param state The state aka menu to display
	 */
	public static void setState(GameState state)
	{
		previousStates.add(currentState);
		currentState = state;
		
		if(state == GameState.OFF)
			previousStates.clear();
	}

	/**
	 * Getting the currently displayed menu
	 */
	public static GameState getState()
	{
		return currentState;
	}
	
	/**
	 * Getting the state the menu was in before the current state
	 */
	public static GameState getLastState()
	{
		return previousStates.get(previousStates.size() - 1);
	}
	
	/**
	 * Sets the current state to the one which was before the current one
	 */
	public static void setStateToLast()
	{
		currentState = getLastState();
		previousStates.remove(previousStates.size() - 1);
	}
	
	/**
	 * Used to set the option the mouse is currently over
	 * @param selectedOption The option the mouse is currently over
	 */
	public static void setSelectedOption(int option)
	{
		selectedOption = option;
	}
	
	/**
	 * Used to get the option the mouse is currently over
	 */
	public static int getSelectedOption()
	{
		return selectedOption;
	}
	
	/**
	 * Closes any menu
	 */
	public static void closeMenu()
	{
		if(isOpen())
			setState(GameState.OFF);
	}
	
	/**
	 * Checks if the game is paused
	 */
	public static boolean isOpen()
	{
		return getState() != GameState.OFF ? true : false;
	}
	
	/**
	 * Sets the highest option currently available
	 */
	public static void setHighestOption(int highest)
	{
		highestOption = --highest;
	}
	
	/**
	 * Gets the highest option currently available
	 */
	public static int getHighestOption()
	{
		return highestOption;
	}
	
	/**
	 * Gets the menu with the given state
	 * @param state The state
	 * @return The menu, null if none has been found
	 */
	public static IMenu getMenu(GameState state)
	{
		for(IMenu menu : menuStates)
		{
			if(menu.getDefinedState() == state)
				return menu;
		}
		
		return null;
	}
	
	/**
	 * Sets up the menu
	 */
	public static void setupMenus()
	{
		Menu.menuStates.addEverything(new LoadMenu(),
				new MainMenu(),
				new PauseMenu(),
				new ResolutionMenu(),
				new SaveMenu(),
				new SeedMenu(),
				new SettingsMenu());
	}
}
