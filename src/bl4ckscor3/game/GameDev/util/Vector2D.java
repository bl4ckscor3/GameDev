package bl4ckscor3.game.gamedev.util;

/**
 * Simplest (not)-vector ever!
*/
public class Vector2D
{
	public int x;
	public int y;
	
	public Vector2D(){};
	
	public Vector2D(int fX, int fY)
	{
		set(fX, fY);
	}
	
	public void set(int fX, int fY)
	{
		x = fX;
		y = fY;
	}
}
