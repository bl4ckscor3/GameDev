package bl4ckscor3.game.GameDev.menu.pause;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.util.Utilities;

public class Pause implements IPauseMenu
{
	@Override
	public void show(Graphics g)
	{
		String[] options =
			{
				"Play",
				"Settings",
				"Load (currently not active)",
				"Save (currently not active)",
				"Main Menu (automatically saves the game (currently not active))",
				"Exit (automatically saves the game (currently not active))"
			};
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsO = g.getFontMetrics(fontO); //used to correctly display the middle string in the middle of the screenwidth
		int i = 0;

		PauseMenu.optionBounds = new Rectangle[options.length];
		PauseMenu.optionLocations = new Point[options.length];
		Utilities.drawHeadline(g, "Pause");
		g.setFont(fontO);

		for(String s : options)
		{
			PauseMenu.optionLocations[i] = new Point(Main.width / 2 - metricsO.stringWidth(s) / 2, (Main.height / 4 + Main.height / 16) + 60 * (i + 1)); //same as with optionBounds
			
			if(PauseMenu.getSelectedOption() != -1 && PauseMenu.getSelectedOption() == i)
			{
				g.setFont(new Font("Candara", 1, 35));
				Utilities.drawStringAtPoint(g, s, PauseMenu.optionLocations[i]);
				g.setFont(fontO);
			}
			else
				Utilities.drawStringAtPoint(g, s, PauseMenu.optionLocations[i]);
			
			PauseMenu.optionBounds[i] = metricsO.getStringBounds(s, g).getBounds(); //writing the bounds of the string into an array to detect if the mouse is over the string later on
			i++;
		}
	}
	
	@Override
	public void onClick()
	{
		switch(PauseMenu.getSelectedOption())
		{
			case 0: //play
				Game.unpause();
				break;
			case 1: //settings
				PauseMenu.setState(PauseMenu.STATE_SETTINGS);
				break;
			case 2: //load
				System.out.println("Loading is not implemented yet.");
				break;
			case 3: //save
				System.out.println("Saving is not implemented yet.");
				break;
			case 4: //main menu
				System.out.println("The Main Menu has not been implemented yet.");
				break;
			case 5: //exit
				System.exit(0);
		}
	}
}
