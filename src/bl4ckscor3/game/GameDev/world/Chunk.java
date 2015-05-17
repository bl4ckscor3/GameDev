package bl4ckscor3.game.GameDev.world;

import java.awt.Graphics;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.util.DebugUI;
import bl4ckscor3.game.GameDev.util.Utilities;
import bl4ckscor3.game.GameDev.world.content.Material;
import bl4ckscor3.game.GameDev.world.content.Tile;
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

	/**
	 * Determines which material to draw
	 */
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

	/**
	 * Renders the current chunk
	 */
	public void render(Graphics g)
	{
		//drawing chunks
		int posX = Utilities.ceil((chunkX * chunkSizeX * Screen.tileSize * Screen.pixelSize - Game.player.position.x * Screen.tileSize * Screen.pixelSize - Screen.tileSize * Screen.pixelSize / 2) * Main.screen.pixelScaleWidth + Main.width / 2);
		int posY = Utilities.ceil((chunkY * chunkSizeY * Screen.tileSize * Screen.pixelSize - Game.player.position.y * Screen.tileSize * Screen.pixelSize - Screen.tileSize * Screen.pixelSize / 2) * Main.screen.pixelScaleHeight + Main.height / 2);

		for(int x = 0; x < tiles.length; x++)
		{
			for(int y = 0; y < tiles[0].length; y++)
			{
				//texture to use, pos inside of chunk + pos x of chunk, same for y, width of chunk, height of chunk
				tiles[x][y].render(g, Utilities.ceil(x * Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleWidth) + posX, Utilities.ceil(y * Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleHeight) + posY);
			}
		}

		if(Screen.displayDebug)
			DebugUI.drawChunkInfo(g, this, posX, posY);
	}
	
	/**
	 * Gets called every gametick
	 */
	public void tick()
	{
		for(int x = 0; x < tiles.length; x++)
		{
			for(int y = 0; y < tiles[0].length; y++)
			{
				tiles[x][y].tick();
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