package bl4ckscor3.game.GameDev.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.menu.Menu;

public class Key implements KeyListener
{
	public static List<Integer> keysPressed = new ArrayList<Integer>();

	@Override
	public void keyTyped(KeyEvent event)
	{
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		if(!keysPressed.contains(event.getKeyCode()))
			keysPressed.add(event.getKeyCode());

		//toggle of debug ui
		if(event.getKeyCode() == 114) //F3
		{
			if(!Screen.displayDebug)
				Screen.displayDebug = true;
			else
				Screen.displayDebug = false;
		}
		//pausing and unpausing
		else if(event.getKeyCode() == 27) //ESC
		{
			if(Menu.getState() != Menu.STATE_MAIN)
			{
				if(!Game.isMenuOpen())
					Game.pause();
				else if(Menu.getState() == Menu.STATE_PAUSE)
				{
					if(Screen.debugWasShown)
					{
						Screen.displayDebug = true;
						Screen.debugWasShown = false;
					}

					Game.unpause();
				}
				else
				{
					//pressing escape while not being in the main (pause) menu gets you back to the main (pause) menu
					if(Menu.getState() != Menu.STATE_PAUSE || Menu.getState() != Menu.STATE_MAIN)
					{
						if(Menu.getPreviousState() == Menu.STATE_MAIN)
							Menu.setState(Menu.STATE_MAIN);
						else if(Menu.getPreviousState() == Menu.STATE_PAUSE)
							Menu.setState(Menu.STATE_PAUSE);
					}
					else
						Game.unpause();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		keysPressed.remove((Object)event.getKeyCode());
	}
}
