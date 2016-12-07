package bl4ckscor3.game.gamedev.menu.settings;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Controls;
import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.IMenu;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.Utilities;

public class ControlsMenu implements IMenu
{
	//the options available to click on the screen
	public boolean isSelecting = false;
	public int selectedKey = 0;
	private String[] options = {
			"Walk up:",
			"Walk left:",
			"Walk down:",
			"Walk right:",
			"Place objects:",
			"Destroy objects:",
			"Inventory: ",
			"Jesus:"
	};
	
	@Override
	public void show(Graphics g)
	{
		int[] controls = Controls.getControls();
		Font fontO = new Font("Candara", 1, 30); //options font
		FontMetrics metricsO = g.getFontMetrics(fontO); //used to correctly display the middle string in the middle of the screenwidth
		int i = 0;
		int x;
		
		Menu.setHighestOption(options.length);
		Menu.optionLocations = new Point[options.length];
		g.setColor(Menu.colorM);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(Menu.colorF);
		
		if(!isSelecting)
		{
			x = Utilities.drawHeadline(g, "Controls");
			g.setFont(fontO);
	
			//drawing the strings at the correct positions
			for(String s : options)
			{
				Menu.optionLocations[i] = new Point(x, (Main.height / 4 + Main.height / 16) + 40 * (i + 1)); //same as with optionBounds
	
				Point p = new Point(Menu.optionLocations[i].x + x / 2 + x / 14, Menu.optionLocations[i].y);
				
				if(Menu.getSelectedOption() != -1 && Menu.getSelectedOption() == i)
					g.setColor(new Color(255, 0, 0));
				
				Utilities.drawStringAtPoint(g, s, Menu.optionLocations[i]);
				Utilities.drawStringAtPoint(g, "" + KeyEvent.getKeyText(controls[i]), p);
				g.setColor(Menu.colorF);
				i++;
			}
		}
		else
		{
			String headline = "Press the key you want to set for \"" + options[Menu.getSelectedOption()].replaceAll(":", "") + "\"";
			String current = "Current key: " + KeyEvent.getKeyText(Controls.getControls()[Menu.getSelectedOption()]);
			
			g.setFont(fontO);
			g.drawString(headline, Main.width / 2 - metricsO.stringWidth(headline) / 2, Main.height / 4);
			g.drawString(current, Main.width / 2 - metricsO.stringWidth(current) / 2, Main.height / 3);
		}
	}

	@Override
	public void onEnter(int option)
	{
		if(!isSelecting)
			isSelecting = true;
		else
		{
			int[] controls = new int[Controls.getControls().length];
			
			for(int i = 0; i < controls.length; i++)
			{
				controls[i] = 0;
			}
			
			controls[option] = selectedKey;
			Controls.setControls(controls);
			
			isSelecting = false;
			selectedKey = 0;
		}
	}

	@Override
	public GameState getDefinedState()
	{
		return GameState.CONTROLS;
	}
}
