package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.world.Map;

public class Game 
{
	public static int seed = 123456789;
	public static Map map;
	public static GameThread thread;
	public int fps;
	public int ups;
	public static int mousePosX;
	public static int mousePosY;

	public Game()
	{
		Player player = new Player();

		map = new Map(player);
		//starts the game thread
		thread = new GameThread();
	}

	public static void update(int moveTimer)
	{
		map.checkChunks();

		//making the player able to only move every other tick
		if(moveTimer % 2 == 0)
		{
			//update keys
			for(int key : Key.keysPressed)
			{
				if(key == 87 || key == 38) //w or up arrow
					map.player.posY--;
				else if(key == 65 || key == 37) //a or left arrow
					map.player.posX--;
				else if(key == 83 || key == 40) //s or down arrow
					map.player.posY++;
				else if(key == 68 || key == 39) //d or right arrow
					map.player.posX++;
			}
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