package bl4ckscor3.game.gamedev.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.game.Screen;
import bl4ckscor3.game.gamedev.listener.Key;
import bl4ckscor3.game.gamedev.util.DebugUI;
import bl4ckscor3.game.gamedev.util.Direction;
import bl4ckscor3.game.gamedev.util.TextureManager;
import bl4ckscor3.game.gamedev.util.Vector2D;
import bl4ckscor3.game.gamedev.world.Chunk;
import bl4ckscor3.game.gamedev.world.content.Material;
import bl4ckscor3.game.gamedev.world.content.PlaceableObject;

public class Player extends Entity
{
	private Direction lastMovedDir = Direction.DOWN;
	private boolean walking = false;
	private ScheduledExecutorService ses;
	private Direction prevDir;

	public Player()
	{
		super(TextureManager.loadTextureFromPath("playerDown0", "player/"));
	}

	@Override
	public void tick(int tick)
	{
		//making the player able to only move every other 4 ticks
		if(tick % 4 == 0)
		{
			Chunk c;

			//update keys
			if(Key.keysPressed.size() != 0)
			{
				for(int key : Key.keysPressed)
				{
					if(key == 87 || key == 38) //w or up arrow
					{
						Vector2D newPos = Game.map.getChunkPosition(this, 0, -1);

						setLastMovedDir(Direction.UP);
						setWalking(true);
						c = Game.map.getChunk(this, 0, -1);

						if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(16))
						{
							PlaceableObject po = c.getPlaceableObject(newPos);

							if(!(po != null && po.getMaterial() == Material.BRIDGE))
								return;
						}

						position.y--;
						DebugUI.setCurrentTile(c.getTile(newPos));
						return;
					}
					else if(key == 65 || key == 37) //a or left arrow
					{
						Vector2D newPos = Game.map.getChunkPosition(this, -1, 0);

						setLastMovedDir(Direction.LEFT);
						setWalking(true);
						c = Game.map.getChunk(this, -1, 0);

						if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(16))
						{
							PlaceableObject po = c.getPlaceableObject(newPos);

							if(!(po != null && po.getMaterial() == Material.BRIDGE))
								return;
						}

						position.x--;
						DebugUI.setCurrentTile(c.getTile(newPos));
						return;
					}
					else if(key == 83 || key == 40) //s or down arrow
					{
						Vector2D newPos = Game.map.getChunkPosition(this, 0, 1);

						setLastMovedDir(Direction.DOWN);
						setWalking(true);
						c = Game.map.getChunk(this, 0, 1);

						if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(16))
						{
							PlaceableObject po = c.getPlaceableObject(newPos);

							if(!(po != null && po.getMaterial() == Material.BRIDGE))
								return;
						}

						position.y++;
						DebugUI.setCurrentTile(c.getTile(newPos));
						return;
					}
					else if(key == 68 || key == 39) //d or right arrow
					{
						Vector2D newPos = Game.map.getChunkPosition(this, 1, 0);

						setLastMovedDir(Direction.RIGHT);
						setWalking(true);
						c = Game.map.getChunk(this, 1, 0);

						if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(16))
						{
							PlaceableObject po = c.getPlaceableObject(newPos);

							if(!(po != null && po.getMaterial() == Material.BRIDGE))
								return;
						}

						position.x++;
						DebugUI.setCurrentTile(c.getTile(newPos));
						return;
					}
					else
						setWalking(false);

					if(key == 32) //space
					{
						Vector2D newPos;

						switch(getLastMovedDir())
						{
							case UP:
								newPos = Game.map.getChunkPosition(this, 0, -1);
								c = Game.map.getChunk(this, 0, -1);

								if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
									c.placeObject(new PlaceableObject(Material.BRIDGE, newPos));
								break;
							case LEFT:
								newPos = Game.map.getChunkPosition(this, -1, 0);
								c = Game.map.getChunk(this, -1, 0);

								if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
									c.placeObject(new PlaceableObject(Material.BRIDGE, newPos));
								break;
							case DOWN:
								newPos = Game.map.getChunkPosition(this, 0, 1);
								c = Game.map.getChunk(this, 0, 1);

								if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
									c.placeObject(new PlaceableObject(Material.BRIDGE, newPos));
								break;
							case RIGHT:
								newPos = Game.map.getChunkPosition(this, 1, 0);
								c = Game.map.getChunk(this, 1, 0);

								if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
									c.placeObject(new PlaceableObject(Material.BRIDGE, newPos));
								break;
						}
					}
				}
			}
			else
				setWalking(false);
		}
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
			stopAnimation();

		g.drawImage(getCurrentTexture(), Main.width / 2 - Main.scaleFactor.getWidth() + Screen.tileSize / 4, Main.height / 2 - Main.scaleFactor.getHeight(), (Main.scaleFactor.getWidth() * 2) - ((Main.scaleFactor.getWidth() * 2) / 4), Main.scaleFactor.getHeight() * 2, null);	
	}

	@Override
	public void startAnimation(Direction dir)
	{
		PlayerAnimations[] constants = PlayerAnimations.class.getEnumConstants();
		Runnable r = new Runnable()
		{
			int currentTexture = 0;

			@Override
			public void run()
			{
				for(PlayerAnimations tex : constants)
				{
					if(tex.name().equals(dir.name()))
						setTexture(tex.getImages()[currentTexture]);
				}

				currentTexture++;

				if(currentTexture == 3)
					currentTexture = 0;
			}
		};

		animating = true;
		ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(r, 0, 67, TimeUnit.MILLISECONDS); //roughly every four ticks
	}

	@Override
	public void stopAnimation()
	{
		if(animating)
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

	public enum PlayerAnimations
	{
		UP(TextureManager.loadTextureFromPath("playerUp0", "player/"), TextureManager.loadTextureFromPath("playerUp1", "player/"), TextureManager.loadTextureFromPath("playerUp2", "player/")),
		LEFT(TextureManager.loadTextureFromPath("playerLeft0", "player/"), TextureManager.loadTextureFromPath("playerLeft1", "player/"), TextureManager.loadTextureFromPath("playerLeft2", "player/")),
		DOWN(TextureManager.loadTextureFromPath("playerDown0", "player/"), TextureManager.loadTextureFromPath("playerDown1", "player/"), TextureManager.loadTextureFromPath("playerDown2", "player/")),
		RIGHT(TextureManager.loadTextureFromPath("playerRight0", "player/"), TextureManager.loadTextureFromPath("playerRight1", "player/"), TextureManager.loadTextureFromPath("playerRight2", "player/"));

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
