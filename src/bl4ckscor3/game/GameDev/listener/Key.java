package bl4ckscor3.game.gamedev.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CopyOnWriteArrayList;

import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.game.Screen;
import bl4ckscor3.game.gamedev.game.content.Controls;
import bl4ckscor3.game.gamedev.inventory.AbstractInventory;
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

		if(key == KeyEvent.VK_F3) //toggle of debug ui
		{
			if(!Screen.displayDebug)
				Screen.displayDebug = true;
			else
				Screen.displayDebug = false;
		}
		else if(key == KeyEvent.VK_ESCAPE) //pausing and unpausing, also switching to the previous menu
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
		else if(!Menu.isOpen()) //disallow inventoríes to be opened when paused
		{
			if(key == Controls.INVENTORY)
			{
				if(Game.player.getInventory().isOpen())
					Game.player.getInventory().close();
				else
					Game.player.getInventory().open();
			}
			else if(key == Controls.CRAFTING)
			{
				if(Game.crafting.isOpen())
					Game.crafting.close();
				else
					Game.crafting.open();
			}
		}
		else
		{
			if(Menu.getState() == GameState.SEED)
				SeedMenu.onKeyPressed(key);
		}

		if(Game.player != null && AbstractInventory.isInventoryOpen())
		{
			if(key == KeyEvent.VK_UP)
			{
				if(keysPressed.contains(KeyEvent.VK_SHIFT))
					AbstractInventory.openInventory.modifyStack(key);
				else
					AbstractInventory.openInventory.up();
			}
			else if(key == KeyEvent.VK_LEFT)
				AbstractInventory.openInventory.left();
			else if(key == KeyEvent.VK_DOWN)
			{
				if(keysPressed.contains(KeyEvent.VK_SHIFT))
					AbstractInventory.openInventory.modifyStack(key);
				else
					AbstractInventory.openInventory.down();
			}
			else if(key == KeyEvent.VK_RIGHT)
				AbstractInventory.openInventory.right();
			else if(key == KeyEvent.VK_ENTER)
				AbstractInventory.openInventory.selectCurrentSlot();
		}
		else if(Menu.isOpen())
		{
			if(key == KeyEvent.VK_UP && !cm.isSelecting)
				Menu.setSelectedOption(Menu.getSelectedOption() == 0 ? Menu.getHighestOption() : Menu.getSelectedOption() - 1);
			else if(key == KeyEvent.VK_DOWN && !cm.isSelecting)
				Menu.setSelectedOption(Menu.getSelectedOption() == Menu.getHighestOption() ? 0 : Menu.getSelectedOption() + 1);
			else if(key == KeyEvent.VK_ENTER)
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
		//removing the key if it's no longer being pressed
		keysPressed.remove((Object)event.getKeyCode());
	}
}
