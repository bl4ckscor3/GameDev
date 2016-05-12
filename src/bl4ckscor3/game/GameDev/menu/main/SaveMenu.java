package bl4ckscor3.game.gamedev.menu.main;

import java.awt.Graphics;

import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.IMenu;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.Utilities;

public class SaveMenu implements IMenu
{
	@Override
	public void show(Graphics g)
	{
		g.setColor(Menu.colorM);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(Menu.colorF);
		Utilities.drawHeadline(g, "Save");
	}

	@Override
	public void onEnter(){}
	
	@Override
	public GameState getDefinedState()
	{
		return GameState.SAVE;
	}
}
