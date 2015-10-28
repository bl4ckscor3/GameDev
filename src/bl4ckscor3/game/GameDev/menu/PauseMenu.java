package bl4ckscor3.game.GameDev.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.util.Utilities;

public class PauseMenu implements IMenu
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
				"Main Menu (automatically saves the game)",
				"Exit (automatically saves the game (currently not active))"
			};
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsO = g.getFontMetrics(fontO); //used to correctly display the middle string in the middle of the screenwidth
		int i = 0;

		Menu.setHighestOption(5);
		Menu.optionBounds = new Rectangle[options.length];
		Menu.optionLocations = new Point[options.length];
		Utilities.drawHeadline(g, "Pause");
		g.setFont(fontO);

		for(String s : options)
		{
			Menu.optionLocations[i] = new Point(Main.width / 2 - metricsO.stringWidth(s) / 2, (Main.height / 4 + Main.height / 16) + 60 * (i + 1)); //same as with optionBounds
			
			if(Menu.getSelectedOption() != -1 && Menu.getSelectedOption() == i)
			{
				g.setColor(new Color(255, 0, 0));
				Utilities.drawStringAtPoint(g, s, Menu.optionLocations[i]);
				g.setColor(Menu.colorF);
			}
			else
				Utilities.drawStringAtPoint(g, s, Menu.optionLocations[i]);
			
			Menu.optionBounds[i] = metricsO.getStringBounds(s, g).getBounds(); //writing the bounds of the string into an array to detect if the mouse is over the string later on
			i++;
		}
	}
	
	@Override
	public void onEnter()
	{
		switch(Menu.getSelectedOption())
		{
			case 0: //play
				Game.unpause();
				break;
			case 1: //settings
				Menu.setState(Menu.STATE_SETTINGS);
				break;
			case 2: //load
				Menu.setState(Menu.STATE_LOAD);
				break;
			case 3: //save
				Menu.setState(Menu.STATE_SAVE);
				break;
			case 4: //main menu
				Menu.setState(Menu.STATE_MAIN);
				break;
			case 5: //exit
				System.exit(0);
		}
	}
}
