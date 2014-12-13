package bl4ckscor3.game.GameDev.entity;

import java.awt.Image;

import bl4ckscor3.game.GameDev.manager.TextureManager;

public class Player
{
	public int posX;
	public int posY;
	private Image texture;
	
	public Player()
	{
		setTexture(TextureManager.loadTextureFromPath("playerFacing", "player/"));
	}
	
	public void setTexture(Image texture)
	{
		this.texture = texture;
	}
	
	public Image getTexture() 
	{
		return texture;
	}
}
