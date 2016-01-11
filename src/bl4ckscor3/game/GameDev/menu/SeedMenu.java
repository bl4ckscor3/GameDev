package bl4ckscor3.game.GameDev.menu;

import java.awt.Graphics;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.GameThread;
import bl4ckscor3.game.GameDev.util.Utilities;

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
	public void onEnter()
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

	public static void onKeyPressed(int key)
	{
		if(key == 48 || key == 96) //0 - numpad0
			populateNextArrayPosition(0);
		else if(key == 49 || key == 97) //1 - numpad1
			populateNextArrayPosition(1);
		else if(key == 50 || key == 98) //2 - numpad2
			populateNextArrayPosition(2);
		else if(key == 51 || key == 99) //3 - numpad3
			populateNextArrayPosition(3);
		else if(key == 52 || key == 100) //4 - numpad4
			populateNextArrayPosition(4);
		else if(key == 53 || key == 101) //5 - numpad5
			populateNextArrayPosition(5);
		else if(key == 54 || key == 102) //6 - numpad6
			populateNextArrayPosition(6);
		else if(key == 55 || key == 103) //7 - numpad7
			populateNextArrayPosition(7);
		else if(key == 56 || key == 104) //8 - numpad8
			populateNextArrayPosition(8);
		else if(key == 57 || key == 105) //9 - numpad9
			populateNextArrayPosition(9);
		else if(key == 8) //backspace
			removeLastArrayObject();
	}

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

	public static void populateSeedArray()
	{
		String[] seed = Main.config.getValue("seed").split("");

		for(int i = 0; i < 9; i++)
		{
			numbers[i] = seed[i];
		}
	}
}
