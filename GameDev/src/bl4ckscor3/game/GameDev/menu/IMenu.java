package bl4ckscor3.game.GameDev.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public interface IMenu
{
	/**
	 * Showing the menu in all its glory
	 * @param g - The Graphics to draw with
	 */
	public void show(Graphics g);
	
	/**
	 * Determines the action when clicking on the menu
	 */
	public void onClick();
}
