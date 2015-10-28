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

public class MainMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		//the options available to click on the screen
		String[] options =
			{
				"Play",
				"Settings",
				"Load (currently not active)",
				"Save (currently not active)",
				"Exit"
			};
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsO = g.getFontMetrics(fontO); //used to correctly display the middle string in the middle of the screenwidth
		int i = 0;

		Menu.optionBounds = new Rectangle[options.length];
		Menu.optionLocations = new Point[options.length];

		//determining wether the main menu was opened after stopping to play and then displaying the correct background (with/without alpha value)
		if(!Game.hasRunBefore)
		{
			g.setColor(Menu.colorM);
			g.fillRect(0, 0, 1920, 1080);
			g.setColor(Menu.colorF);
		}
		else
		{
			g.setColor(Menu.colorMa);
			g.fillRect(0, 0, 1920, 1080);
			g.setColor(Menu.colorF);
		}

		Utilities.drawHeadline(g, "The Game!");
		g.setFont(fontO);

		//drawing the strings at the correct positions
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
		//determining which option was clicked
		switch(Menu.getSelectedOption())
		{
			case 0: //play
				Game.hasRunBefore = true;
				Menu.setState(Menu.STATE_OFF);
				Main.game = new Game();
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
			case 4: //exit
				System.exit(0);
		}
	}
}
