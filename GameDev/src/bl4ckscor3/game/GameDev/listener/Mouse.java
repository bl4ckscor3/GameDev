package bl4ckscor3.game.GameDev.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.menu.pause.Pause;
import bl4ckscor3.game.GameDev.menu.pause.PauseMenu;
import bl4ckscor3.game.GameDev.menu.pause.Settings;

public class Mouse implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent paramMouseEvent)
	{
		//only check if the pause menu is open
		if(PauseMenu.getState() != -1)
			PauseMenu.menuStates.get(PauseMenu.getState()).onClick();
	}

	@Override
	public void mousePressed(MouseEvent paramMouseEvent)
	{
	}

	@Override
	public void mouseReleased(MouseEvent paramMouseEvent)
	{
	}

	@Override
	public void mouseEntered(MouseEvent paramMouseEvent)
	{
	}

	@Override
	public void mouseExited(MouseEvent paramMouseEvent)
	{
	}
}
