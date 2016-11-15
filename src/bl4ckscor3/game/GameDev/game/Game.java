package bl4ckscor3.game.gamedev.game;

import bl4ckscor3.game.gamedev.entity.Player;
import bl4ckscor3.game.gamedev.entity.Player.PlayerTextures;
import bl4ckscor3.game.gamedev.listener.Key;
import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.DebugUI;
import bl4ckscor3.game.gamedev.util.Direction;
import bl4ckscor3.game.gamedev.util.Vector2D;
import bl4ckscor3.game.gamedev.world.Chunk;
import bl4ckscor3.game.gamedev.world.Map;

public class Game 
{
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

	public Game(int seed)
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
		map.tick();

		//making the player able to only move every other 4 ticks
		if(moveTimer % 4 == 0)
		{
			Chunk c;

			//update keys
			for(int key : Key.keysPressed)
			{
				if(key == 87 || key == 38) //w or up arrow
				{
					Vector2D newPos = map.getChunkPosition(player, 0, -1);
					
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(PlayerTextures.UP.getStandingImage());
							moveCount++;
							break;
						case 1:
							player.setTexture(PlayerTextures.UP.getFirstMovingImage());
							moveCount++;
							break;
						case 3:
							player.setTexture(PlayerTextures.UP.getSecondMovingImage());
							moveCount = 0;
					}

					player.setLastMovedDir(Direction.UP);
					c = map.getChunk(player, 0, -1);

					if(c.getTile(newPos.x, newPos.y).isWater() && !Key.keysPressed.contains(16))
						return;

					player.position.y--;
					DebugUI.setCurrentTile(c.getTile(newPos.x, newPos.y).getMaterial());
					return;
				}
				else if(key == 65 || key == 37) //a or left arrow
				{
					Vector2D newPos = map.getChunkPosition(player, -1, 0);
					
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(PlayerTextures.LEFT.getStandingImage());
							moveCount++;
							break;
						case 1:
							player.setTexture(PlayerTextures.LEFT.getFirstMovingImage());
							moveCount++;
							break;
						case 3:
							player.setTexture(PlayerTextures.LEFT.getSecondMovingImage());
							moveCount = 0;
							break;
					}

					player.setLastMovedDir(Direction.LEFT);
					c = map.getChunk(player, -1, 0);

					if(c.getTile(newPos.x, newPos.y).isWater() && !Key.keysPressed.contains(16))
						return;

					player.position.x--;
					DebugUI.setCurrentTile(c.getTile(newPos.x, newPos.y).getMaterial());
					return;
				}
				else if(key == 83 || key == 40) //s or down arrow
				{
					Vector2D newPos = map.getChunkPosition(player, 0, 1);
					
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(PlayerTextures.DOWN.getStandingImage());
							moveCount++;
							break;
						case 1:
							player.setTexture(PlayerTextures.DOWN.getFirstMovingImage());
							moveCount++;
							break;
						case 3:
							player.setTexture(PlayerTextures.DOWN.getSecondMovingImage());
							moveCount = 0;
							break;
					}

					player.setLastMovedDir(Direction.DOWN);
					c = map.getChunk(player, 0, 1);

					if(c.getTile(newPos.x, newPos.y).isWater() && !Key.keysPressed.contains(16))
						return;

					player.position.y++;
					DebugUI.setCurrentTile(c.getTile(newPos.x, newPos.y).getMaterial());
					return;
				}
				else if(key == 68 || key == 39) //d or right arrow
				{
					Vector2D newPos = map.getChunkPosition(player, 1, 0);
					
					switch(moveCount)
					{
						case 0: case 2:
							player.setTexture(PlayerTextures.RIGHT.getStandingImage());
							moveCount++;
							break;
						case 1:
							player.setTexture(PlayerTextures.RIGHT.getFirstMovingImage());
							moveCount++;
							break;
						case 3:
							player.setTexture(PlayerTextures.RIGHT.getSecondMovingImage());
							moveCount = 0;
							break;
					}

					player.setLastMovedDir(Direction.RIGHT);
					c = map.getChunk(player, 1, 0);

					if(c.getTile(newPos.x, newPos.y).isWater() && !Key.keysPressed.contains(16))
						return;

					player.position.x++;
					DebugUI.setCurrentTile(c.getTile(newPos.x, newPos.y).getMaterial());
					return;
				}
			}

			if(player.getLastMovedDir() == Direction.UP)
				player.setTexture(PlayerTextures.UP.getStandingImage());
			else if(player.getLastMovedDir() == Direction.LEFT)
				player.setTexture(PlayerTextures.LEFT.getStandingImage());
			else if(player.getLastMovedDir() == Direction.DOWN)
				player.setTexture(PlayerTextures.DOWN.getStandingImage());
			else if(player.getLastMovedDir() == Direction.RIGHT)
				player.setTexture(PlayerTextures.RIGHT.getStandingImage());
		}
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
}