package bl4ckscor3.game.gamedev.util;

import java.util.ArrayList;

/**
 * Simple wrapper class to make adding values much easier and more compact
 */
public class CustomArrayList<T> extends ArrayList<T>
{
	private static final long serialVersionUID = -5666169746866399571L;

	public void addEverything(T... ts)
	{
		for(T t : ts)
		{
			add(t);
		}
	}
}
