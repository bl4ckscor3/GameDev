package bl4ckscor3.game.gamedev.util;

public class ScaleFactor
{
	private int width;
	private int height;
	
	public ScaleFactor(int w, int h)
	{
		width = w;
		height = h;
	}
	
	/**
	 * @return The scale factor width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * @return The scale factor height
	 */
	public int getHeight()
	{
		return height;
	}
}
