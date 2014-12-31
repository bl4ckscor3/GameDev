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
	//pause menu is off by default
	private static int currentState = STATE_OFF;
	private static int selectedOption;
	public static Rectangle[] optionBounds;
	public static Point[] optionLocations;
	public static LinkedList<IMenu> menuStates = new LinkedList<IMenu>();
	
	/**
	 * Determine which menu to show
	 * @param g - The Graphics to draw with
	 */
	public static void displayMenu(Graphics g)
	{
		Color colorBg = new Color(0, 0, 0, 64); //color for the background
		Color colorF = new Color(200, 200, 200); //color for the font
		
		//drawing the rectangle in the back to make the screen look dimmed
		g.setColor(colorBg);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(colorF);
		menuStates.get(getState()).show(g);
	}

	/**
	 * Setting the menu to display. Used to display the correct menu part (main, settings etc.) and determine the actions done on clicking
	 * @param state - The state aka menu to display
	 */
	public static void setState(int state)
	{
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
}
