package bl4ckscor3.game.gamedev.menu.settings;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.IMenu;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.Utilities;

public class ResolutionMenu implements IMenu
{
	//the options available to click on the screen
	private String[] options = {
			"800x600",
			"1024x600",
			"1280x720",
			"1366x768",
			"1440x900",
			"1600x200",
			"1600x900",
			"1680x1050",
			"1920x1080",
	};

	@Override
	public void show(Graphics g)
	{
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsO = g.getFontMetrics(fontO); //used to correctly display the middle string in the middle of the screenwidth
		int i = 0;

		Menu.setHighestOption(options.length);
		Menu.optionLocations = new Point[options.length];

		g.setColor(Menu.colorM);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(Menu.colorF);
		Utilities.drawHeadline(g, "Resolution");
		g.setFont(fontO);

		//drawing the strings at the correct positions
		for(String s : options)
		{
			Menu.optionLocations[i] = new Point(Main.width / 2 - metricsO.stringWidth(s) / 2, (Main.height / 4 + Main.height / 16) + 40 * (i + 1)); //same as with optionBounds

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

		Utilities.drawStringAtPoint(g, "<-----", getSelectedResolutionPoint(200));
		Utilities.drawStringAtPoint(g, "----->", getSelectedResolutionPoint(-140));
	}

	@Override
	public void onEnter(int option) throws Exception
	{
		Main.config.set("width", options[option].split("x")[0]);
		Main.config.set("height", options[option].split("x")[1]);
		Menu.setStateToLast();
	}

	@Override
	public GameState getDefinedState()
	{
		return GameState.RESOLUTION;
	}

	/**
	 * Gets the point to draw the arrows around. Used to show what the current resolution is
	 * @param x How much to draw the arrow to the right/left
	 * @return The Point at which to draw the arrow at
	 */
	private Point getSelectedResolutionPoint(int x)
	{
		String resolution = Main.config.get("width") + "x" + Main.config.get("height");

		for(int i = 0; i < options.length; i++)
		{
			if(options[i].equals(resolution))
			{
				Point p = new Point();

				p.setLocation(Menu.optionLocations[i].getX() + x, Menu.optionLocations[i].getY());
				return p;
			}
		}

		return null;
	}
}
