package bl4ckscor3.game.GameDev.entity;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.util.Direction;
import bl4ckscor3.game.GameDev.util.TextureManager;

public class Player extends Entity
{
	private Direction lastMovedDir = Direction.DOWN;

	public Player()
	{
		super(TextureManager.loadTextureFromPath("playerDown0", "player/"));
	}
	
	public Direction getLastMovedDir()
	{
		return lastMovedDir;
	}

	public void setLastMovedDir(Direction lmd)
	{
		lastMovedDir = lmd;
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(getCurrentTexture(),
				Main.width / 2 - Main.scaleFactor.getWidth() + Screen.tileSize / 4,
				Main.height / 2 - Main.scaleFactor.getHeight(),
				(Main.scaleFactor.getWidth() * 2) - ((Main.scaleFactor.getWidth() * 2) / 4),
				Main.scaleFactor.getHeight() * 2,
				null);	
	}
	
	public enum PlayerTextures
	{
		UP(TextureManager.loadTextureFromPath("playerUp0", "player/"), TextureManager.loadTextureFromPath("playerUp1", "player/"), TextureManager.loadTextureFromPath("playerUp2", "player/")),
		LEFT(TextureManager.loadTextureFromPath("playerLeft0", "player/"), TextureManager.loadTextureFromPath("playerLeft1", "player/"), TextureManager.loadTextureFromPath("playerLeft2", "player/")),
		DOWN(TextureManager.loadTextureFromPath("playerDown0", "player/"), TextureManager.loadTextureFromPath("playerDown1", "player/"), TextureManager.loadTextureFromPath("playerDown2", "player/")),
		RIGHT(TextureManager.loadTextureFromPath("playerRight0", "player/"), TextureManager.loadTextureFromPath("playerRight1", "player/"), TextureManager.loadTextureFromPath("playerRight2", "player/"));
		
		private Image standing;
		private Image moving1;
		private Image moving2;
		
		private PlayerTextures(Image s, Image m1, Image m2)
		{
			standing = s;
			moving1 = m1;
			moving2 = m2;
		}
		
		public Image getStandingImage()
		{
			return standing;
		}
		
		public Image getFirstMovingImage()
		{
			return moving1;
		}
		
		public Image getSecondMovingImage()
		{
			return moving2;
		}
	}
}
