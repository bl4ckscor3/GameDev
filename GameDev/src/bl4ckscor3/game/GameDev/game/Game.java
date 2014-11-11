package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.util.ImageWriter;
import bl4ckscor3.game.GameDev.world.Chunk;
import bl4ckscor3.game.GameDev.world.Map;
import bl4ckscor3.game.GameDev.world.generation.SimplexNoise;

public class Game 
{
	public Screen screen;
	public static int seed;
	public static Map map;
	public static GameThread thread;
	public int fps;
	public int ups;
	public static int mousePosX;
	public static int mousePosY;

	public Game()
	{
		Player player = new Player();

		//just writing the noise map to a file
		SimplexNoise sn = new SimplexNoise(7, 0.1);
		double xStart = 0;
		double yStart = 0;
		double xEnd = 200;
		double yEnd = 200;
		int xRes = 200;
		int yRes = 200;
		double[][] data = new double[xRes][yRes];
	
		for(int i = 0; i < xRes; i++)
		{
			for(int j = 0; j < yRes; j++)
			{
				int x = (int) (xStart + (i * (xEnd - xStart) / xRes));
				int y = (int) (yStart + (j * (yEnd - yStart) / yRes));
				double noise = (1 + sn.getNoise(x, y)) / 2; //number between 0 and 1
				
				data[i][j] = noise;
			}
		}
		
		ImageWriter.greyWriteImage(data);
		//done
		
		map = new Map(player);
		//starts the game thread
		thread = new GameThread();
	}

	public static void update()
	{
		map.checkChunks();

		//update keys
		for(int key : Key.keysPressed)
		{
			if(key == 87) //w
				map.player.posY--;
			else if(key == 65) //a
				map.player.posX--;
			else if(key == 83) //s
				map.player.posY++;
			else if(key == 68) //d
				map.player.posX++;
		}
	}

	/**
	 * Starts the game
	 */
	public static void start()
	{
		thread.start();
	}
}