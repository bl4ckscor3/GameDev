package bl4ckscor3.game.GameDev.menu;

import java.awt.Graphics;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.util.Utilities;

public class SaveMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		if(!Game.hasRunBefore)
		{
			g.setColor(Menu.colorM);
			g.fillRect(0, 0, 1920, 1080);
			g.setColor(Menu.colorF);
		}
		else if(Menu.getPreviousState() == Menu.STATE_MAIN)
		{
			g.setColor(Menu.colorMa);
			g.fillRect(0, 0, 1920, 1080);
			g.setColor(Menu.colorF);
		}
		
		Utilities.drawHeadline(g, "Save");
	}

	@Override
	public void onClick()
	{
	}
}
