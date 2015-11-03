package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.menu.GameState;
import bl4ckscor3.game.GameDev.menu.LoadMenu;
import bl4ckscor3.game.GameDev.menu.MainMenu;
import bl4ckscor3.game.GameDev.menu.Menu;
import bl4ckscor3.game.GameDev.menu.PauseMenu;
import bl4ckscor3.game.GameDev.menu.SaveMenu;
import bl4ckscor3.game.GameDev.menu.SettingsMenu;
import bl4ckscor3.game.GameDev.util.TextureManager;
import bl4ckscor3.game.GameDev.util.Vector2D;
import bl4ckscor3.game.GameDev.world.Chunk;
import bl4ckscor3.game.GameDev.world.Map;

public class Game 
{
	/** The seed to generate the map from*/
	private static int seed = 25687431;
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
	private static int moveCount = 0; //used to determine texture

	public Game()
	{
		player = new Player();
		map = new Map(seed);
		//spawning of the entities
		map.spawnEntity(player, 0, 0);
	}

	/**
	 * Updates the screen every tick if ingame
	 */
	public static void tick(int moveTimer)
	{
		map.tick(seed);

		//making the player able to only move every other 4 ticks
		if(moveTimer % 4 == 0)
		{
			Vector2D pos = map.getChunkPosition(player);
			Chunk c = map.getChunk(((int)(player.position.x / Chunk.chunkSizeX)), ((int)(player.position.y / Chunk.chunkSizeY)));

			//update keys
			for(int key : Key.keysPressed)
			{
				if(key == 87 || key == 38) //w or up arrow
				{
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerBack0", "player/"));
							moveCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerBack1", "player/"));
							moveCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerBack2", "player/"));
							moveCount = 0;
							break;
					}

					if((int)pos.y - 1 == -1)
					{
						pos.y = 16;
						c = map.getChunk((int)(player.position.x / Chunk.chunkSizeX), (int)(player.position.y / Chunk.chunkSizeY) - 1);
					}

					if(c.getTile((int)pos.x, ((int)pos.y) - 1).isWater())
						return;

					player.position.y--;
					return;
				}
				else if(key == 65 || key == 37) //a or left arrow
				{
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerLeft0", "player/"));
							moveCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerLeft1", "player/"));
							moveCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerLeft2", "player/"));
							moveCount = 0;
							break;
					}

					if((int)pos.x - 1 == -1)
					{
						pos.x = 16;
						c = map.getChunk((int)(player.position.x / Chunk.chunkSizeX) - 1, (int)(player.position.y / Chunk.chunkSizeY));
					}

					if(c.getTile(((int)pos.x - 1), (int)pos.y).isWater())
						return;

					player.position.x--;
					return;
				}
				else if(key == 83 || key == 40) //s or down arrow
				{
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerFacing0", "player/"));
							moveCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerFacing1", "player/"));
							moveCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerFacing2", "player/"));
							moveCount = 0;
							break;
					}

					if((int)pos.y + 1 == 16)
					{
						pos.y = -1;
						c = map.getChunk((int)(player.position.x / Chunk.chunkSizeX), (int)(player.position.y / Chunk.chunkSizeY) + 1);
					}

					if(c.getTile((int)pos.x, ((int)pos.y) + 1).isWater())
						return;

					player.position.y++;
					return;
				}
				else if(key == 68 || key == 39) //d or right arrow
				{
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(TextureManager.loadTextureFromPath("playerRight0", "player/"));
							moveCount++;
							break;
						case 1:
							player.setTexture(TextureManager.loadTextureFromPath("playerRight1", "player/"));
							moveCount++;
							break;
						case 3:
							player.setTexture(TextureManager.loadTextureFromPath("playerRight2", "player/"));
							moveCount = 0;
							break;
					}

					if((int)pos.x + 1 == 16)
					{
						pos.x = -1;
						c = map.getChunk((int)(player.position.x / Chunk.chunkSizeX) + 1, (int)(player.position.y / Chunk.chunkSizeY));
					}

					if(c.getTile(((int)pos.x + 1), (int)pos.y).isWater())
						return;

					player.position.x++;
					return;
				}
			}
		}
	}

	/**
	 * Sets up the menu and starts the game
	 */
	public static void setupMenus()
	{
		Menu.menuStates.addEverything(new MainMenu(),
				new PauseMenu(),
				new SettingsMenu(),
				new LoadMenu(),
				new SaveMenu());
		thread.start();
	}

	/**
	 * Pauses the game
	 */
	public static void pause()
	{
		if(!Menu.isOpen())
			Menu.setState(GameState.PAUSE);
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