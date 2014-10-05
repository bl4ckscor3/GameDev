package bl4ckscor3.game.GameDev.game;

import java.awt.event.KeyEvent;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.world.Map;

public class Game 
{
	public Screen screen;
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
	 * starts the game
	 */
	public static void start()
	{
		thread.start();
	}
}