package bl4ckscor3.game.GameDev.menu;

import java.awt.Graphics;

import bl4ckscor3.game.GameDev.util.Utilities;

public class MainMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		Utilities.drawHeadline(g, "The Game!");
	}

	@Override
	public void onClick()
	{
	}
}
