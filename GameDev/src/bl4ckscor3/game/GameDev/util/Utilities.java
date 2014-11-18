package bl4ckscor3.game.GameDev.util;

public class Utilities
{
	/**
	 * Rounding down a double to an int
	 * @param x - The double to round
	 * @return - Rounded int
	 */
	public static int fastFloor(double x)
	{
		int xi = (int) x;

		return x < xi ? xi - 1 : xi;
	}
	
	/**
	 * Rounding up a double to an int
	 * @param x - The double to round
	 * @return - Rounded int
	 */
	public static int ceil(double x)
	{
		int xi = (int) x;
		
		return x > xi ? xi + 1 : xi;
	}
}
