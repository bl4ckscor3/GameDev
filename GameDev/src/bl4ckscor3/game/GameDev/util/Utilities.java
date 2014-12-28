package bl4ckscor3.game.GameDev.util;

import java.awt.Graphics;
import java.awt.Point;

public class Utilities
{
	/**
	 * Rounding down a double to an int
	 * @param x - The double to round
	 * @return - Rounded int
	 */
	public static int floor(double x)
	{
		int xi = (int) x;

		return x < xi ? xi - 1 : xi;
	}
	
	/**
	 * Rounding up a double to an int
	 * @param x - The double to round
	 * @return - Rounded int
	 */
	public static int ceil(double x)
	{
		int xi = (int) x;
		
		return x > xi ? xi + 1 : xi;
	}
	
	/**
	 * Draws a String on the screen using a Point instead of x y
	 * @param g - The Graphics to draw with
	 * @param s - The String to draw
	 * @param p - The coordinates of the String
	 */
	public static void drawStringAtPoint(Graphics g, String s, Point p)
	{
		g.drawString(s, p.x, p.y);
	}
}
