package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.menu.LoadMenu;
import bl4ckscor3.game.GameDev.menu.MainMenu;
import bl4ckscor3.game.GameDev.menu.PauseMenu;
import bl4ckscor3.game.GameDev.menu.Menu;
import bl4ckscor3.game.GameDev.menu.SaveMenu;
import bl4ckscor3.game.GameDev.menu.SettingsMenu;
import bl4ckscor3.game.GameDev.util.TextureManager;
import bl4ckscor3.game.GameDev.world.Map;

public class Game 
{
	/** The seed to generate the map from*/
	public static int seed = 123456789;
	/** The map*/
	public static Map map;
	/** The player*/
	public static Player player;
	/** The game thread (infinite loop for updates etc.)*/
	public static GameThread thread;
	/** The current X position of the mouse cursor*/
	public static int mousePosX;
	/** The current Y position of the mouse cursor*/
	public static int mousePosY;
	public static boolean hasRunBefore = false; //used to stop the map from rendering if the game is still in the main menu directly after starting
	
	public Game()
	{
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
		if(Menu.getState() != Menu.STATE_MAIN)
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
						player.setTexture(TextureManager.loadTextureFromPath("playerBack", "player/"));
					}
					else if(key == 65 || key == 37) //a or left arrow
					{
						player.position.x--;
						player.setTexture(TextureManager.loadTextureFromPath("playerLeft", "player/"));
					}
					else if(key == 83 || key == 40) //s or down arrow
					{
						player.position.y++;
						player.setTexture(TextureManager.loadTextureFromPath("playerFacing", "player/"));
					}
					else if(key == 68 || key == 39) //d or right arrow
					{
						player.position.x++;
						player.setTexture(TextureManager.loadTextureFromPath("playerRight", "player/"));
					}
				}
			}
		}
	}

	/**
	 * Sets up the menu and starts the game
	 */
	public static void start()
	{
		Menu.menuStates.add(new MainMenu());
		Menu.menuStates.add(new PauseMenu());
		Menu.menuStates.add(new SettingsMenu());
		Menu.menuStates.add(new LoadMenu());
		Menu.menuStates.add(new SaveMenu());
		thread.start();
	}

	/**
	 * Pauses the game
	 */
	public static void pause()
	{
		if(!Menu.isOpen())
			Menu.setState(Menu.STATE_PAUSE);
	}

	/**
	 * Unpauses the game
	 */
	public static void unpause()
	{
		Menu.closeMenu();
	}
}