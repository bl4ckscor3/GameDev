package bl4ckscor3.game.GameDev.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.game.Screen;

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
			if(!Game.isPaused())
				Game.pause();
			else
			{
				if(Main.screen.debugWasShown)
					Main.screen.displayDebug = true;

				Game.unpause();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		keysPressed.remove((Object)event.getKeyCode());
	}
}
