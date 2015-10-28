package bl4ckscor3.game.GameDev.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CopyOnWriteArrayList;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.menu.Menu;

public class Key implements KeyListener
{
	public static CopyOnWriteArrayList<Integer> keysPressed = new CopyOnWriteArrayList<Integer>();
	
	@Override
	public void keyTyped(KeyEvent event){}

	@Override
	public void keyPressed(KeyEvent event)
	{
		int key = event.getKeyCode();
		
		//adding the currently pressed key(s) to the list
		if(!keysPressed.contains(key))
			keysPressed.add(key);

		//toggle of debug ui
		if(key == 114) //F3
		{
			if(!Screen.displayDebug)
				Screen.displayDebug = true;
			else
				Screen.displayDebug = false;
		}
		//pausing and unpausing, also switching to the previous menu
		else if(key == 27) //ESC
		{
			if(Menu.getState() != Menu.STATE_MAIN)
			{
				if(!Menu.isOpen())
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
		
		if(Menu.isOpen())
		{
			if(key == 38) //up arrow
				Menu.setSelectedOption(Menu.getSelectedOption() == 0 ? Menu.getHighestOption() : Menu.getSelectedOption() - 1);
			else if(key == 40) //down arrow
				Menu.setSelectedOption(Menu.getSelectedOption() == Menu.getHighestOption() ? 0 : Menu.getSelectedOption() + 1);
			else if(key == 10) //enter
			{
				if(Menu.getState() != -1)
					Menu.menuStates.get(Menu.getState()).onEnter();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		//removing the keys if they are no longer being pressed
		keysPressed.remove((Object)event.getKeyCode());
	}
}
