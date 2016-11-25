package bl4ckscor3.game.gamedev.util;

public class Position
{
	public int x;
	public int y;
	
	/**
	 * Creates a Position with x and y = 0
	 */
	public Position()
	{
		this(0, 0);
	}
	
	/**
	 * Creates a position with the defined values
	 * @param iX The x coordinate of this Position
	 * @param iY The y coordinate of this Position
	 */
	public Position(int iX, int iY)
	{
		set(iX, iY);
	}
	
	/**
	 * Copies the given position
	 * @param pos The position to copy
	 */
	public Position(Position pos)
	{
		x = pos.x;
		y = pos.y;
	}
	
	/**
	 * Sets the position to the defined values
	 * @param iX The x coordinate of this Position
	 * @param iY The y coordinate of this Position
	 */
	public void set(int iX, int iY)
	{
		x = iX;
		y = iY;
	}
	
	/**
	 * Modifies this position with the given x and y values
	 * @param modX The amount to modify the x coordinate
	 * @param modY The amount to modify the x coordinate
	 * @return This instance for chaining
	 */
	public Position modify(int modX, int modY)
	{
		x += modX;
		y += modY;
		return this;
	}
	
	/**
	 * Clones this position and gives the option to modify it with the given x and y values
	 * @param modX The amount to modify the x coordinate
	 * @param modY The amount to modify the x coordinate
	 * @return This new Position
	 */
	public Position clone(int modX, int modY)
	{
		return new Position(x, y).modify(modX, modY);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Position))
			return false;
		
		return x == ((Position)obj).x && y == ((Position)obj).y;
	}
	
	@Override
	public int hashCode()
	{
		return x + y; //nice hash function, but makes sure only equal positions have equal hash codes
	}
	
	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
