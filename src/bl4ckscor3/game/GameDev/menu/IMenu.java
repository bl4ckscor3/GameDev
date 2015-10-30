package bl4ckscor3.game.GameDev.menu;

import java.awt.Graphics;

public interface IMenu
{
	/**
	 * Showing the menu in all its glory
	 * @param g - The Graphics to draw with
	 */
	public void show(Graphics g);
	
	/**
	 * Determines the action when pressing enter in the menu
	 */
	public void onEnter();

	/**
	 * Determines the state this menu is defined as
	 */
	public GameState getDefinedState();
}
