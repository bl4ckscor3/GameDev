package bl4ckscor3.game.GameDev.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.util.Utilities;

public class PauseMenu
{
	public static Rectangle[] optionBounds;
	public static Point[] optionLocations;
	public static int selectedOption;
	
	public static void show(Graphics g)
	{
		String[] options =
			{
				"Play",
				"Settings (currently not active)",
				"Load (currently not active)",
				"Save (currently not active)",
				"Main Menu (currently not active)",
				"Exit (automatically saves the game (currently not active))"
			};
		Color colorBg = new Color(0, 0, 0, 64); //color for the background
		Color colorF = new Color(200, 200, 200); //color for the font
		Font fontH = new Font("Candara", 1, 60); //header font
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsH = g.getFontMetrics(fontH); //used to correctly display the string in the middle of the screenwidth
		FontMetrics metricsO = g.getFontMetrics(fontO); 
		int i = 0;
		
		optionBounds = new Rectangle[options.length];
		optionLocations = new Point[options.length];
		g.setColor(colorBg);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(colorF);
		g.setFont(fontH);
		g.drawString("Pause", Main.width / 2 - metricsH.stringWidth("Pause") / 2, Main.height / 4);
		g.drawString("______", Main.width / 2 - metricsH.stringWidth("______") / 2, Main.height / 4);
		g.setFont(fontO);

		for(String s : options)
		{
			optionLocations[i] = new Point(Main.width / 2 - metricsO.stringWidth(s) / 2, (Main.height / 4 + Main.height / 16) + 60 * (i + 1)); //same as with optionBounds
			
			if(selectedOption != -1 && selectedOption == i)
			{
				g.setFont(new Font("Candara", 1, 35));
				Utilities.drawStringAtPoint(g, s, optionLocations[i]);
				g.setFont(fontO);
			}
			else
				Utilities.drawStringAtPoint(g, s, optionLocations[i]);
			
			optionBounds[i] = metricsO.getStringBounds(s, g).getBounds(); //writing the bounds of the string into an array to detect if the mouse is over the string later on
			i++;
		}
	}
}
