package bl4ckscor3.game.GameDev.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.net.URISyntaxException;

import bl4ckscor3.game.GameDev.Main;
import bl4ckscor3.game.GameDev.util.Utilities;

public class SettingsMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		//the options available to click on the screen
		String[] options =
			{
					"Seed",
					"Resolution",
					"Reset",
					"Back",
			};
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsO = g.getFontMetrics(fontO); //used to correctly display the middle string in the middle of the screenwidth
		int i = 0;

		Menu.setHighestOption(options.length);
		Menu.optionLocations = new Point[options.length];

		g.setColor(Menu.colorM);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(Menu.colorF);
		Utilities.drawHeadline(g, "Settings");
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
	public void onEnter() throws URISyntaxException, IOException
	{
		switch(Menu.getSelectedOption())
		{
			case 0:
				SeedMenu.populateSeedArray();
				Menu.setState(GameState.SEED);
				break;
			case 1:
				Menu.setState(GameState.RESOLUTION);
				break;
			case 2:
				Main.config.writeDefaultValues();
				break;
			case 3:
				Menu.setStateToLast();
		}
	}

	@Override
	public GameState getDefinedState()
	{
		return GameState.SETTINGS;
	}
}
