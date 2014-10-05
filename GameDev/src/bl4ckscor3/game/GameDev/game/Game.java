package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.entity.Player;
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
	}

	/**
	 * starts the game
	 */
	public static void start()
	{
		thread.start();
	}
}