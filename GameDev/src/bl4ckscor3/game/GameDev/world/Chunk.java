package bl4ckscor3.game.GameDev.world;

import bl4ckscor3.game.GameDev.content.Material;
import bl4ckscor3.game.GameDev.content.Tile;
import bl4ckscor3.game.GameDev.world.generation.SimplexNoise;

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
	public Tile[][] tiles = new Tile[chunkSizeX][chunkSizeY];;

	public Chunk(int x, int y)
	{
		chunkX = x;
		chunkY = y;
	}

	public void populate()
	{
		SimplexNoise sn = new SimplexNoise(7, 0.1);
		double xStart = chunkX * chunkSizeX;
		double yStart = chunkY * chunkSizeY;
		double xEnd = xStart + chunkSizeX;
		double yEnd = yStart + chunkSizeY;
		int xRes = chunkSizeX;
		int yRes = chunkSizeY;
		double[][] data = new double[xRes][yRes];

		for(int i = 0; i < xRes; i++)
		{
			for(int j = 0; j < yRes; j++)
			{
				Material mat;
				int x = (int) (xStart + (i * (xEnd - xStart) / xRes));
				int y = (int) (yStart + (j * (yEnd - yStart) / yRes));
				double noise = (1 + sn.getNoise(x, y)) / 2; //number between 0 and 1

				//determining which texture to use
				if(noise < 0.490F)
				{
					mat = Material.WATER_DEEP;
					tiles[i][j] = new Tile(mat);
				}
				else if(noise < 0.5F)
				{
					mat = Material.WATER_NORMAL;
					tiles[i][j] = new Tile(mat);
				}
				else if(noise < 0.520F)
				{
					mat = Material.SAND;
					tiles[i][j] = new Tile(mat);
				}
				else
				{
					mat = Material.GRASS;
					tiles[i][j] = new Tile(mat, "grass/", 12);
				}

				data[i][j] = noise;
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
