package bl4ckscor3.game.gamedev.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.game.GameThread;
import bl4ckscor3.game.gamedev.util.Utilities;

public class MainMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		//the options available to click on the screen
		String[] options = {
				"Play",
				"Settings",
				"Load (currently not active)",
				"Save (currently not active)",
				"Exit"
		};
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsO = g.getFontMetrics(fontO); //used to correctly display the middle string in the middle of the screenwidth
		int i = 0;

		Menu.setHighestOption(options.length);
		Menu.optionLocations = new Point[options.length];

		g.setColor(Menu.colorM);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(Menu.colorF);
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
				Main.game = new Game(GameThread.getSeed());
				Menu.setState(GameState.OFF);
				break;
			case 1: //settings
				Menu.setState(GameState.SETTINGS);
				break;
			case 2: //load
				Menu.setState(GameState.LOAD);
				break;
			case 3: //save
				Menu.setState(GameState.SAVE);
				break;
			case 4: //exit
				System.exit(0);
		}
	}

	@Override
	public GameState getDefinedState()
	{
		return GameState.MAIN;
	}
}
