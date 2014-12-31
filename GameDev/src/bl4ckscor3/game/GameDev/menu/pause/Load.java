package bl4ckscor3.game.GameDev.menu.pause;

import java.awt.Graphics;

import bl4ckscor3.game.GameDev.util.Utilities;

public class Load implements IPauseMenu
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
