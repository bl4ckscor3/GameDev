package bl4ckscor3.game.GameDev.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bl4ckscor3.game.GameDev.menu.Menu;

public class Mouse implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent paramMouseEvent)
	{
		//only check if the pause menu is open
		if(Menu.getState() != -1)
			Menu.menuStates.get(Menu.getState()).onClick();
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
