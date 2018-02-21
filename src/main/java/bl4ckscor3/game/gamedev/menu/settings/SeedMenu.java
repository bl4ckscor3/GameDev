package bl4ckscor3.game.gamedev.menu.settings;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.GameThread;
import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.IMenu;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.Utilities;

public class SeedMenu implements IMenu
{
	private static String[] numbers = new String[9];

	@Override
	public void show(Graphics g)
	{
		g.setColor(Menu.colorM);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(Menu.colorF);
		Utilities.drawHeadline(g, "Set seed");
		Utilities.drawStringBelowHeadline(g, "Type in numbers, press ESC or Enter to set the seed.");

		for(int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] == null)
				break;

			g.drawString(numbers[i], (Main.width / 2 - 65) + i * 15, Main.height / 2);
		}

		g.drawRect(Main.width / 2 - 68, Main.height / 2 - 15, 135, 21);
	}

	@Override
	public void onEnter(int option)
	{
		String seed = "";

		for(String s : numbers)
		{
			seed += s;
		}

		try
		{
			GameThread.setSeed(Integer.parseInt(seed));
		}
		catch(NumberFormatException e){}

		Menu.setStateToLast();
	}

	@Override
	public GameState getDefinedState()
	{
		return GameState.SEED;
	}

	/**
	 * Gets called when a key in this menu is pressed
	 * @param key The key code
	 */
	public static void onKeyPressed(int key)
	{
		if(key == KeyEvent.VK_0 || key == KeyEvent.VK_NUMPAD0)
			populateNextArrayPosition(0);
		else if(key == KeyEvent.VK_1 || key == KeyEvent.VK_NUMPAD1)
			populateNextArrayPosition(1);
		else if(key == KeyEvent.VK_2 || key == KeyEvent.VK_NUMPAD2)
			populateNextArrayPosition(2);
		else if(key == KeyEvent.VK_3 || key == KeyEvent.VK_NUMPAD3)
			populateNextArrayPosition(3);
		else if(key == KeyEvent.VK_4 || key == KeyEvent.VK_NUMPAD4)
			populateNextArrayPosition(4);
		else if(key == KeyEvent.VK_5 || key == KeyEvent.VK_NUMPAD5)
			populateNextArrayPosition(5);
		else if(key == KeyEvent.VK_6 || key == KeyEvent.VK_NUMPAD6)
			populateNextArrayPosition(6);
		else if(key == KeyEvent.VK_7 || key == KeyEvent.VK_NUMPAD7)
			populateNextArrayPosition(7);
		else if(key == KeyEvent.VK_8 || key == KeyEvent.VK_NUMPAD8)
			populateNextArrayPosition(8);
		else if(key == KeyEvent.VK_9 || key == KeyEvent.VK_NUMPAD9)
			populateNextArrayPosition(9);
		else if(key == KeyEvent.VK_BACK_SPACE)
			removeLastArrayObject();
	}

	/**
	 * Sets the first numbers array position that is null to the given value
	 * @param value The value to set
	 */
	private static void populateNextArrayPosition(int value)
	{
		for(int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] == null)
			{
				numbers[i] = "" + value;
				return;
			}
		}
	}

	/**
	 * Removes the last object in the numbers array, aka the object with index numbers.length - 1
	 */
	private static void removeLastArrayObject()
	{
		if(numbers[0] == null)
			return;

		for(int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] == null)
			{
				numbers[i - 1] = null;
				return;
			}
		}

		numbers[numbers.length - 1] = null;
	}

	/**
	 * Puts each number of the seed into a seperate array position
	 */
	public static void populateSeedArray()
	{
		String[] seed = Main.config.get("seed").split("");

		for(int i = 0; i < 9; i++)
		{
			numbers[i] = seed[i];
		}
	}
}
