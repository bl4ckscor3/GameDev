package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.menu.LoadMenu;
import bl4ckscor3.game.GameDev.menu.MainMenu;
import bl4ckscor3.game.GameDev.menu.Menu;
import bl4ckscor3.game.GameDev.menu.PauseMenu;
import bl4ckscor3.game.GameDev.menu.SaveMenu;
import bl4ckscor3.game.GameDev.menu.SettingsMenu;
import bl4ckscor3.game.GameDev.util.TextureManager;
import bl4ckscor3.game.GameDev.world.Map;

public class Game 
{
	/** The seed to generate the map from*/
	private static int seed;
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
	private static int moveUpCount = 0; //used to determine texture
	private static int moveLeftCount = 0;
	private static int moveDownCount = 0;
	private static int moveRightCount = 0;

	public Game()
	{
		player = new Player();
		map = new Map(seed);
		//spawning of the entities
		map.spawnEntity(player, 0, 0);
		//starts the game thread
		thread = new GameThread();
	}

	/**
	 * Updates the screen every tick if ingame
	 */
	public static void tick(int moveTimer)
	{
		map.tick(seed);

		//making the player able to only move every other tick
		if(moveTimer % 4 == 0)
		{
			//update keys
			for(int key : Key.keysPressed)
			{
				if(key == 87 || key == 38) //w or up arrow
				{
					player.position.y--;

					switch(moveUpCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerBack0", "player/"));
							moveUpCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerBack1", "player/"));
							moveUpCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerBack2", "player/"));
							moveUpCount = 0;
							break;
					}
				}
				else if(key == 65 || key == 37) //a or left arrow
				{
					player.position.x--;

					switch(moveLeftCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerLeft0", "player/"));
							moveLeftCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerLeft1", "player/"));
							moveLeftCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerLeft2", "player/"));
							moveLeftCount = 0;
							break;
					}
				}
				else if(key == 83 || key == 40) //s or down arrow
				{
					player.position.y++;

					switch(moveDownCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerFacing0", "player/"));
							moveDownCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerFacing1", "player/"));
							moveDownCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerFacing2", "player/"));
							moveDownCount = 0;
							break;
					}
				}
				else if(key == 68 || key == 39) //d or right arrow
				{
					player.position.x++;

					switch(moveRightCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerRight0", "player/"));
							moveRightCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerRight1", "player/"));
							moveRightCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerRight2", "player/"));
							moveRightCount = 0;
							break;
					}
				}
			}
		}
	}

	/**
	 * Updates the screen every tick if in a menu
	 */
	public static void tickMenu(int ticks)
	{
		if(ticks % 4 == 0)
		{
			for(int key : Key.keysPressed)
			{
				if(key == 38) //up arrow
					Menu.setSelectedOption(Menu.getSelectedOption() == 0 ? Menu.getHighestOption() : Menu.getSelectedOption() - 1);
				else if(key == 40) //down arrow
					Menu.setSelectedOption(Menu.getSelectedOption() == Menu.getHighestOption() ? 0 : Menu.getSelectedOption() + 1);
				else if(key == 10) //enter
				{
					if(Menu.getState() != -1)
						Menu.menuStates.get(Menu.getState()).onEnter();
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

	public static void setSeed(int s)
	{
		seed = s;
	}
}