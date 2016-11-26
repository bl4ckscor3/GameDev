package bl4ckscor3.game.gamedev.menu.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.IMenu;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.Utilities;

public class PauseMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		String[] options = {
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

		Menu.setHighestOption(options.length);
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

			i++;
		}
	}

	@Override
	public void onEnter(int option)
	{
		Menu.setSelectedOption(0);
		
		switch(option)
		{
			case 0: //play
				Game.unpause();
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
			case 4: //main menu
				Menu.setState(GameState.MAIN);
				break;
			case 5: //exit
				System.exit(0);
		}
	}

	@Override
	public GameState getDefinedState()
	{
		return GameState.PAUSE;
	}
}
