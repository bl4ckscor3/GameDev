package bl4ckscor3.game.gamedev.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CopyOnWriteArrayList;

import bl4ckscor3.game.gamedev.game.Controls;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.game.Screen;
import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.menu.settings.ControlsMenu;
import bl4ckscor3.game.gamedev.menu.settings.SeedMenu;

public class Key implements KeyListener
{
	public static CopyOnWriteArrayList<Integer> keysPressed = new CopyOnWriteArrayList<Integer>();
	public static ControlsMenu cm;
	
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
			if(Menu.getState() != GameState.MAIN)
			{
				if(!Menu.isOpen())
				{
					if(Game.player.getInventory().isOpen())
						Game.player.getInventory().close();
					else
						Game.pause();
				}
				else if(Menu.getState() == GameState.PAUSE)
				{
					if(Screen.debugWasShown)
					{
						Screen.displayDebug = true;
						Screen.debugWasShown = false;
					}

					Game.unpause();
				}
				else if(Menu.getState() == GameState.SEED)
				{
					try
					{
						Menu.getMenu(GameState.SEED).onEnter(Menu.getSelectedOption());
					}
					catch(Exception e){}
				}
				else
					Menu.setStateToLast();
			}
		}
		else if(key == Controls.INVENTORY)
		{
			if(!Menu.isOpen()) //disallow inventory to be opened when paused
			{
				if(Game.player.getInventory().isOpen())
					Game.player.getInventory().close();
				else
					Game.player.getInventory().open();
			}
		}
		else
		{
			if(Menu.getState() == GameState.SEED)
				SeedMenu.onKeyPressed(key);
		}
		
		if(Menu.isOpen())
		{
			if(key == KeyEvent.VK_UP && !cm.isSelecting)
				Menu.setSelectedOption(Menu.getSelectedOption() == 0 ? Menu.getHighestOption() : Menu.getSelectedOption() - 1);
			else if(key == KeyEvent.VK_DOWN && !cm.isSelecting)
				Menu.setSelectedOption(Menu.getSelectedOption() == Menu.getHighestOption() ? 0 : Menu.getSelectedOption() + 1);
			else if(key == 10) //enter
			{
				if(Menu.getState() != GameState.PLAYING)
				{
					try
					{
						Menu.getMenu(Menu.getState()).onEnter(Menu.getSelectedOption());
					}
					catch(Exception e){}
				}
			}
			else
			{
				if(Menu.getState() == GameState.CONTROLS && cm.isSelecting)
				{
					cm.selectedKey = key;
					cm.onEnter(Menu.getSelectedOption());
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		//removing the key if it's  no longer being pressed
		keysPressed.remove((Object)event.getKeyCode());
	}
}
