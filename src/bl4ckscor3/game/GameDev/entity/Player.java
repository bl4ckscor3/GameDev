package bl4ckscor3.game.gamedev.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Controls;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.game.Screen;
import bl4ckscor3.game.gamedev.inventory.PlayerInventory;
import bl4ckscor3.game.gamedev.listener.Key;
import bl4ckscor3.game.gamedev.menu.GameState;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.DebugUI;
import bl4ckscor3.game.gamedev.util.Direction;
import bl4ckscor3.game.gamedev.util.Position;
import bl4ckscor3.game.gamedev.util.TextureManager;
import bl4ckscor3.game.gamedev.world.Chunk;
import bl4ckscor3.game.gamedev.world.content.Bridge;
import bl4ckscor3.game.gamedev.world.content.Material;
import bl4ckscor3.game.gamedev.world.content.PlaceableObject;

public class Player extends Entity
{
	/** The path of the player textures*/
	private static final String texturePath = "entities/player/";
	private Direction lastMovedDir = Direction.DOWN;
	private boolean walking = false;
	private ScheduledExecutorService ses;
	private Direction prevDir;
	private PlayerInventory inventory = new PlayerInventory();
	
	public Player()
	{
		super(TextureManager.loadTextureFromPath("playerDown0", texturePath));
	}

	@Override
	public void tick(int tick)
	{
		//making the player able to only move every other 4 ticks
		if(tick % 4 == 0)
		{
			Chunk c = null;
			Position newPos = null;
			PlaceableObject po = null;
			String bridge = "";
			Direction dir;
			
			//update keys
			if(Key.keysPressed.size() != 0)
			{
				dir = getLastMovedDir();
				newPos = Game.map.getChunkPosition(this, dir);
				c = Game.map.getChunk(this, dir);
				po = c.getPlaceableObject(newPos);
				
				switch(dir)
				{
					case UP:
						bridge = "_vertical";
						break;
					case LEFT:
						bridge = "_horizontal";
						break;
					case DOWN:
						bridge = "_vertical";
						break;
					case RIGHT:
						bridge = "_horizontal";
						break;
				}
				
				if(Key.keysPressed.contains(Controls.PLACE))
				{
					if(po == null && c.getTile(newPos).isWater())
						c.placeObject(new Bridge(c, newPos, bridge));
				}
				
				if(Key.keysPressed.contains(Controls.DESTROY))
				{
					if(po != null && po.getMaterial() == Material.TREE)
						c.removeObject(po);
				}

				if(!inventory.isOpen())
				{
					int lastMovementKey = 0;
	
					//movement
					for(int key : Key.keysPressed)
					{
						if(key == Controls.UP)
						{
							lastMovementKey = key;
							dir = Direction.UP;
						}
						else if(key == Controls.LEFT)
						{
							lastMovementKey = key;
							dir = Direction.LEFT;
						}
						else if(key == Controls.DOWN)
						{
							lastMovementKey = key;
							dir = Direction.DOWN;
						}
						else if(key == Controls.RIGHT)
						{
							lastMovementKey = key;
							dir = Direction.RIGHT;
						}
					}
	
					newPos = Game.map.getChunkPosition(this, dir);
					c = Game.map.getChunk(this, dir);
					po = c.getPlaceableObject(newPos);
					DebugUI.setCurrentTile(c.getTile(newPos));
					
					if(!checkContent(c, newPos, po))
						return;
					
					if(lastMovementKey == Controls.UP)
					{
						setWalking(true);
						setLastMovedDir(Direction.UP);						
						position.y--;
						return;
					}
					else if(lastMovementKey == Controls.LEFT)
					{
						setWalking(true);						
						position.x--;
						DebugUI.setCurrentTile(c.getTile(newPos));
						return;
					}
					else if(lastMovementKey == Controls.DOWN)
					{
						setWalking(true);						
						position.y++;
						DebugUI.setCurrentTile(c.getTile(newPos));
						return;
					}
					else if(lastMovementKey == Controls.RIGHT)
					{
						setWalking(true);						
						position.x++;
						DebugUI.setCurrentTile(c.getTile(newPos));
						return;
					}
					else
						setWalking(false);
				}
			}
			else
				setWalking(false);
		}
	}

	/**
	 * Checks for any special conditions that could occur at a specific positon within a chunk
	 * @param c The Chunk to check in
	 * @param pos The position within the chunk to check
	 * @param po The PlaceableObject of the position within the chunk, can be null
	 * @return false if a specific conditon is met and the caller shouldn't continue, true otherwise
	 */
	private boolean checkContent(Chunk c, Position pos, PlaceableObject po)
	{
		if(c.getTile(pos).isWater() && !Key.keysPressed.contains(Controls.JESUS))
		{
			if(!(po != null && po.getMaterial() == Material.BRIDGE))
				return false;
		}
		else if(c.getTile(pos).getMaterial() == Material.GRASS)
		{
			if(po != null && po.getMaterial() == Material.TREE)
				return false;
		}
		
		return true;
	}

	@Override
	public void render(Graphics g)
	{
		if(isWalking())
		{
			if(isAnimating())
			{
				if(prevDir != getLastMovedDir())
				{
					stopAnimation();
					startAnimation(getLastMovedDir());
				}
			}
			else
				startAnimation(getLastMovedDir());

			prevDir = getLastMovedDir();
		}
		else
		{
			if(isAnimating())
				stopAnimation();
		}

		g.drawImage(getCurrentTexture(), Main.width / 2 - Main.scaleFactor.getWidth() + Screen.tileSize / 4, Main.height / 2 - Main.scaleFactor.getHeight() * 2, (Main.scaleFactor.getWidth() * 2) - ((Main.scaleFactor.getWidth() * 2) / 4), Main.scaleFactor.getHeight() * 2, null);	
	}

	@Override
	public void startAnimation(Direction dir)
	{
		Animation animation = new Animation();

		animating = true;
		ses = Executors.newSingleThreadScheduledExecutor();
		animation.setup(dir);
		ses.scheduleAtFixedRate(animation, 0, 67, TimeUnit.MILLISECONDS); //roughly every four ticks
	}

	@Override
	public void stopAnimation()
	{
		if(isAnimating())
		{
			animating = false;
			ses.shutdownNow();

			switch(getLastMovedDir())
			{
				case UP:
					setTexture(PlayerAnimations.UP.images[0]);
					break;
				case LEFT:
					setTexture(PlayerAnimations.LEFT.images[0]);
					break;
				case DOWN:
					setTexture(PlayerAnimations.DOWN.images[0]);
					break;
				case RIGHT:
					setTexture(PlayerAnimations.RIGHT.images[0]);
					break;
			}
		}
	}

	/**
	 * Sets the last direction the player moved towards
	 * @param lmd The direction
	 */
	public void setLastMovedDir(Direction lmd)
	{
		lastMovedDir = lmd;
	}

	/**
	 * @return The corresponding Direction
	 */
	public Direction getLastMovedDir()
	{
		return lastMovedDir;
	}

	/**
	 * Sets wether the player is walking or not. Note: This does not make the player walk!
	 * @param w true if the player is currently walking, false if not
	 */
	public void setWalking(boolean w)
	{
		walking = w;
	}

	/**
	 * @return true if the player is currently walking, false otherwise
	 */
	public boolean isWalking()
	{
		return walking;
	}

	/**
	 * @return This player's inventory
	 */
	public PlayerInventory getInventory()
	{
		return inventory;
	}
	
	private class Animation implements Runnable
	{
		Image[] currentAnimation;
		int currentTexture = 0;

		public void setup(Direction dir)
		{
			for(PlayerAnimations tex : PlayerAnimations.class.getEnumConstants())
			{
				if(tex.name().equals(dir.name()))
					currentAnimation = tex.getImages();
			}
		}

		@Override
		public void run()
		{
			setTexture(currentAnimation[currentTexture]);
			currentTexture++;

			if(currentTexture == 3)
				currentTexture = 0;

			if(Menu.getState() == GameState.PAUSE)
				stopAnimation();

		}
	}

	public enum PlayerAnimations
	{
		UP(TextureManager.loadTextureFromPath("playerUp0", texturePath), TextureManager.loadTextureFromPath("playerUp1", texturePath), TextureManager.loadTextureFromPath("playerUp2", texturePath)),
		LEFT(TextureManager.loadTextureFromPath("playerLeft0", texturePath), TextureManager.loadTextureFromPath("playerLeft1", texturePath), TextureManager.loadTextureFromPath("playerLeft2", texturePath)),
		DOWN(TextureManager.loadTextureFromPath("playerDown0", texturePath), TextureManager.loadTextureFromPath("playerDown1", texturePath), TextureManager.loadTextureFromPath("playerDown2", texturePath)),
		RIGHT(TextureManager.loadTextureFromPath("playerRight0", texturePath), TextureManager.loadTextureFromPath("playerRight1", texturePath), TextureManager.loadTextureFromPath("playerRight2", texturePath));

		private Image[] images;

		/**
		 * @param img The images of the animation, index 0 always holds the standing picture
		 */
		private PlayerAnimations(Image... img)
		{
			images = img;
		}

		public Image[] getImages()
		{
			return images;
		}
	}
}
