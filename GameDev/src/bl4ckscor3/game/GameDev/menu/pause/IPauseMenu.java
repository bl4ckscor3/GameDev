package bl4ckscor3.game.GameDev.menu.pause;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public interface IPauseMenu
{
	/**
	 * Showing the Settings menu in all its glory
	 * @param g
	 */
	public void show(Graphics g);
	
	/**
	 * Determines the action when clicking on the menu
	 */
	public void onClick();
}
