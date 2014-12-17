package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.util.TextureManager;
import bl4ckscor3.game.GameDev.world.Map;

public class Game 
{
	public static int seed = 123456789;
	public static Map map;
	public static Player player;
	public static GameThread thread;
	public int fps;
	public int ups;
	public static int mousePosX;
	public static int mousePosY;
	public static Game instance;

	public Game()
	{
		instance = this;
		player = new Player();
		map = new Map();
		//spawning of the entities
		map.spawnEntity(player, 0, 0);
		//starts the game thread
		thread = new GameThread();
	}

	/**
	 * Updates the screen every tick
	 */
	public static void tick(int moveTimer)
	{
		map.tick();

		//making the player able to only move every other tick
		if(moveTimer % 4 == 0)
		{
			//update keys
			for(int key : Key.keysPressed)
			{
				if(key == 87 || key == 38) //w or up arrow
				{
					player.position.y--;
					player.texture = TextureManager.loadTextureFromPath("playerBack", "player/");
				}
				else if(key == 65 || key == 37) //a or left arrow
				{
					player.position.x--;
					player.texture = TextureManager.loadTextureFromPath("playerLeft", "player/");
				}
				else if(key == 83 || key == 40) //s or down arrow
				{
					player.position.y++;
					player.texture = TextureManager.loadTextureFromPath("playerFacing", "player/");
				}
				else if(key == 68 || key == 39) //d or right arrow
				{
					player.position.x++;
					player.texture = TextureManager.loadTextureFromPath("playerRight", "player/");
				}
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