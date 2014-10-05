package bl4ckscor3.game.GameDev.world;

public class Chunk
{
	public static final int chunkSizeX  = 16;
	public static final int chunkSizeY  = 16;
	public int chunkX;
	public int chunkY;
	
	public Chunk(int x, int y)
	{
		chunkX = x;
		chunkY = y;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Chunk))
			return false;
		
		Chunk c = (Chunk)o;
		
		return chunkX == c.chunkX && chunkY == c.chunkY;
	}
}
