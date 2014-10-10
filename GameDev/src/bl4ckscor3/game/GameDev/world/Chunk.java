package bl4ckscor3.game.GameDev.world;

import bl4ckscor3.game.GameDev.content.Material;
import bl4ckscor3.game.GameDev.content.Tile;

public class Chunk
{
	//amount of tiles horizontally
	public static final int chunkSizeX  = 16;
	//amount of tiles vertically
	public static final int chunkSizeY  = 16;
	//x position of whole chunk
	public int chunkX;
	//y position of whole chunk
	public int chunkY;
	public Tile[][] tiles = new Tile[chunkSizeX][chunkSizeY];
	
	public Chunk(int x, int y)
	{
		chunkX = x;
		chunkY = y;
	}
	
	public void populate()
	{
		//noise generation
		
		for(int x = 0; x < tiles.length; x++)
		{
			for(int y = 0; y < tiles[0].length; y++)
			{
				tiles[x][y] = new Tile(Material.GRASS);
			}
		}
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
