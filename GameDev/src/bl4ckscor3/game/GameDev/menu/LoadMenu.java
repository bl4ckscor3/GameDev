package bl4ckscor3.game.GameDev.menu;

import java.awt.Graphics;

import bl4ckscor3.game.GameDev.util.Utilities;

public class LoadMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		Utilities.drawHeadline(g, "Load");
	}

	@Override
	public void onClick()
	{
	}
}
