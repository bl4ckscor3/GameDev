package bl4ckscor3.game.gamedev.util;

/**
 * Simplest (not)-vector ever!
*/
public class Vector2D
{
	public float x;
	public float y;
	
	public Vector2D(){};
	
	public Vector2D(float fX, float fY)
	{
		set(fX, fY);
	}
	
	public void set(float fX, float fY)
	{
		x = fX;
		y = fY;
	}
}
