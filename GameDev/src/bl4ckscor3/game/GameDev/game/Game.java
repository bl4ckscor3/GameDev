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
	public static int seed = 123456789;
	public static Map map;
	public static Player player;
	public static GameThread thread;
	public int fps;
	public int ups;
	public static int mousePosX;
	public static int mousePosY;
	public static Game instance;
	public static boolean hasRunBefore = false; //used to stop the map from rendering if the game is still in the main menu directly after starting
	
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
		if(Menu.getState() != Menu.STATE_MAIN)
		{
			System.out.println("hi");
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
	 * Starts the game and sets up the pause menu
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
		if(!isMenuOpen())
			Menu.setState(Menu.STATE_PAUSE);
	}

	/**
	 * Unpauses the game
	 */
	public static void unpause()
	{
		closeMenu();
	}
	
	/**
	 * Closes any menu
	 */
	public static void closeMenu()
	{
		if(isMenuOpen())
			Menu.setState(Menu.STATE_OFF);
	}
	
	/**
	 * Checks if the game is paused
	 */
	public static boolean isMenuOpen()
	{
		return Menu.getState() != Menu.STATE_OFF ? true : false;
	}
}