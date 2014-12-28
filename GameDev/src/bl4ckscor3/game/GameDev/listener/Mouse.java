package bl4ckscor3.game.GameDev.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.menu.PauseMenu;

public class Mouse implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent paramMouseEvent)
	{
		int selection = PauseMenu.selectedOption;
		
		switch(selection)
		{
			case 0: //play
				Game.unpause();
				break;
			case 1: //settings
				System.out.println("Setting menu is not implemented yet.");
				break;
			case 2: //load
				System.out.println("Loading is not implemented yet.");
				break;
			case 3: //save
				System.out.println("Saving is not implemented yet.");
				break;
			case 4: //main menu
				System.out.println("The Main Menu has not been implemented yet.");
				break;
			case 5: //exit
				System.exit(0);
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
