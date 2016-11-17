package bl4ckscor3.game.gamedev.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Screen;
import bl4ckscor3.game.gamedev.util.Direction;
import bl4ckscor3.game.gamedev.util.TextureManager;

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
	
	/**
	 * Starts an animation
	 * @param tex The PlayerTexture to animate
	 */
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
	
	/**
	 * Stops the current animation
	 */
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
