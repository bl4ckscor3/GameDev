package bl4ckscor3.game.GameDev.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Menu
{
	public static final int STATE_OFF = -1;
	public static final int STATE_MAIN = 0;
	public static final int STATE_PAUSE = 1;
	public static final int STATE_SETTINGS = 2;
	public static final int STATE_LOAD = 3;
	public static final int STATE_SAVE = 4;
	private static final int highestOption = 4;
	private static int previousState;
	private static int currentState = STATE_MAIN; //the main menu is open when the game starts
	private static int selectedOption = 0; //the option currently selected - 0 is the top option, the highest number is the bottom option
	public static Rectangle[] optionBounds;
	public static Point[] optionLocations;
	public static LinkedList<IMenu> menuStates = new LinkedList<IMenu>();
	public static final Color colorM = new Color(27, 72, 127); //color for the first main menu
	public static final Color colorMa = new Color(27, 72, 127, 64); //color for the main menu after playing the game
	public static final Color colorF = new Color(255, 255, 255); //color for the font
	
	/**
	 * Determine which menu to show
	 * @param g - The Graphics to draw with
	 */
	public static void displayMenu(Graphics g)
	{
		//drawing the rectangle in the back to make the screen look dimmed
		g.setColor(new Color(0, 0, 0, 64));
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(colorF);
		menuStates.get(currentState).show(g);
	}

	/**
	 * Setting the menu to display. Used to display the correct menu part (main, settings etc.) and determine the actions done on clicking
	 * @param state - The state aka menu to display
	 */
	public static void setState(int state)
	{
		previousState = currentState;
		currentState = state;
	}

	/**
	 * Getting the currently displayed menu
	 */
	public static int getState()
	{
		return currentState;
	}
	
	/**
	 * Getting the state the menu was in before the current state
	 * Used to determine wether ESC should bring the player back to the PauseMenu or not
	 */
	public static int getPreviousState()
	{
		return previousState;
	}
	
	/**
	 * Used to set the option the mouse is currently over
	 * @param selectedOption - The option the mouse is currently over
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
			Menu.setState(Menu.STATE_OFF);
	}
	
	/**
	 * Checks if the game is paused
	 */
	public static boolean isOpen()
	{
		return Menu.getState() != Menu.STATE_OFF ? true : false;
	}
	
	/**
	 * Gets the highest option currently available.
	 */
	public static int getHighestOption()
	{
		return highestOption;
	}
}
