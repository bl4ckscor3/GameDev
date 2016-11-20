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
	private Direction lastMovedDir = Direction.DOWN;
	private boolean walking = false;
	private ScheduledExecutorService ses;
	private Direction prevDir;
	/** The path of the player textures*/
	private static final String texturePath = "entities/player/";

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
			Chunk c;
			Position newPos;
			PlaceableObject po;
			
			//update keys
			if(Key.keysPressed.size() != 0)
			{
				if(Key.keysPressed.contains(32)) //space
				{
					switch(getLastMovedDir())
					{
						case UP:
							newPos = Game.map.getChunkPosition(this, 0, -1);
							c = Game.map.getChunk(this, 0, -1);

							if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
								c.placeObject(new Bridge(c, newPos, "_vertical"));
							break;
						case LEFT:
							newPos = Game.map.getChunkPosition(this, -1, 0);
							c = Game.map.getChunk(this, -1, 0);

							if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
								c.placeObject(new Bridge(c, newPos, "_horizontal"));
							break;
						case DOWN:
							newPos = Game.map.getChunkPosition(this, 0, 1);
							c = Game.map.getChunk(this, 0, 1);

							if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
								c.placeObject(new Bridge(c, newPos, "_vertical"));
							break;
						case RIGHT:
							newPos = Game.map.getChunkPosition(this, 1, 0); 
							c = Game.map.getChunk(this, 1, 0);

							if(c.getPlaceableObject(newPos) == null && c.getTile(newPos).isWater())
								c.placeObject(new Bridge(c, newPos, "_horizontal"));
							break;
					}
				}

				if(Key.keysPressed.contains(16)) //shift
				{
					switch(getLastMovedDir())
					{
						case UP:
							newPos = Game.map.getChunkPosition(this, 0, -1);
							c = Game.map.getChunk(this, 0, -1);
							po = c.getPlaceableObject(newPos);
							
							if(po != null && po.getMaterial() == Material.TREE)
								c.removeObject(po);
							break;
						case LEFT:
							newPos = Game.map.getChunkPosition(this, -1, 0);
							c = Game.map.getChunk(this, -1, 0);
							po = c.getPlaceableObject(newPos);
							
							if(po != null && po.getMaterial() == Material.TREE)
								c.removeObject(po);
							break;
						case DOWN:
							newPos = Game.map.getChunkPosition(this, 0, 1);
							c = Game.map.getChunk(this, 0, 1);
							po = c.getPlaceableObject(newPos);
							
							if(po != null && po.getMaterial() == Material.TREE)
								c.removeObject(po);
							break;
						case RIGHT:
							newPos = Game.map.getChunkPosition(this, 1, 0); 
							c = Game.map.getChunk(this, 1, 0);
							po = c.getPlaceableObject(newPos);

							if(po != null && po.getMaterial() == Material.TREE)
								c.removeObject(po);
							break;
					}
				}
				
				int lastMovementKey = 0;

				//movement
				for(int key : Key.keysPressed)
				{
					if(key == 87 || key == 38) //w or up arrow
						lastMovementKey = key;
					else if(key == 65 || key == 37) //a or left arrow
						lastMovementKey = key;
					else if(key == 83 || key == 40) //s or down arrow
						lastMovementKey = key;
					else if(key == 68 || key == 39) //d or right arrow
						lastMovementKey = key;
				}

				if(lastMovementKey == 87 || lastMovementKey == 38) //w or up arrow
				{
					newPos = Game.map.getChunkPosition(this, 0, -1);
					setLastMovedDir(Direction.UP);
					setWalking(true);
					c = Game.map.getChunk(this, 0, -1);
					po = c.getPlaceableObject(newPos);
					
					if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(17))
					{
						if(!(po != null && po.getMaterial() == Material.BRIDGE))
							return;
					}
					else if(c.getTile(newPos).getMaterial() == Material.GRASS)
					{
						if(po != null && po.getMaterial() == Material.TREE)
							return;
					}

					position.y--;
					DebugUI.setCurrentTile(c.getTile(newPos));
					return;
				}
				else if(lastMovementKey == 65 || lastMovementKey == 37) //a or left arrow
				{
					newPos = Game.map.getChunkPosition(this, -1, 0);
					setLastMovedDir(Direction.LEFT);
					setWalking(true);
					c = Game.map.getChunk(this, -1, 0);
					po = c.getPlaceableObject(newPos);
					
					if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(17))
					{
						if(!(po != null && po.getMaterial() == Material.BRIDGE))
							return;
					}
					else if(c.getTile(newPos).getMaterial() == Material.GRASS)
					{
						if(po != null && po.getMaterial() == Material.TREE)
							return;
					}

					position.x--;
					DebugUI.setCurrentTile(c.getTile(newPos));
					return;
				}
				else if(lastMovementKey == 83 || lastMovementKey == 40) //s or down arrow
				{
					newPos = Game.map.getChunkPosition(this, 0, 1);
					setLastMovedDir(Direction.DOWN);
					setWalking(true);
					c = Game.map.getChunk(this, 0, 1);
					po = c.getPlaceableObject(newPos);
					
					if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(17))
					{
						if(!(po != null && po.getMaterial() == Material.BRIDGE))
							return;
					}
					else if(c.getTile(newPos).getMaterial() == Material.GRASS)
					{
						if(po != null && po.getMaterial() == Material.TREE)
							return;
					}

					position.y++;
					DebugUI.setCurrentTile(c.getTile(newPos));
					return;
				}
				else if(lastMovementKey == 68 || lastMovementKey == 39) //d or right arrow
				{
					newPos = Game.map.getChunkPosition(this, 1, 0);
					setLastMovedDir(Direction.RIGHT);
					setWalking(true);
					c = Game.map.getChunk(this, 1, 0);
					po = c.getPlaceableObject(newPos);
					
					if(c.getTile(newPos).isWater() && !Key.keysPressed.contains(17))
					{
						if(!(po != null && po.getMaterial() == Material.BRIDGE))
							return;
					}
					else if(c.getTile(newPos).getMaterial() == Material.GRASS)
					{
						if(po != null && po.getMaterial() == Material.TREE)
							return;
					}

					position.x++;
					DebugUI.setCurrentTile(c.getTile(newPos));
					return;
				}
				else
					setWalking(false);
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
