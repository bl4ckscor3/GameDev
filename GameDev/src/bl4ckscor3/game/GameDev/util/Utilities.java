package bl4ckscor3.game.GameDev.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import bl4ckscor3.game.GameDev.core.Main;

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
	
	/**
	 * Drawing a headline for the menus
	 * @param g - The Graphics to draw with
	 * @param text - The text to display
	 */
	public static void drawHeadline(Graphics g, String text)
	{
		Font fontH = new Font("Candara", 1, 60); //header font
		FontMetrics metricsH = g.getFontMetrics(fontH); //used to correctly display the middle of the string in the middle of the screenwidth
		String underscores = "";
		
		//calculating the amount of underscores to underline the text with
		for(int i = 0; i < text.length(); i++)
		{
			underscores += "_";
		}
		
		//additional underscore for beauty
		underscores += "_";
		g.setFont(fontH);
		g.drawString(text, Main.width / 2 - metricsH.stringWidth(text) / 2, Main.height / 4);
		g.drawString(underscores, Main.width / 2 - metricsH.stringWidth(underscores) / 2, Main.height / 4);
	}
}
