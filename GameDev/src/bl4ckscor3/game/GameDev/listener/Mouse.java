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
		switch(PauseMenu.getState())
		{
			case PauseMenu.STATE_MAIN:
				PauseMenu.menuStates.get(PauseMenu.STATE_MAIN).onClick();
				break;
			case PauseMenu.STATE_SETTINGS:
				PauseMenu.menuStates.get(PauseMenu.STATE_SETTINGS).onClick();
		}
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
