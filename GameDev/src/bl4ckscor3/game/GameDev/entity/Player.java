package bl4ckscor3.game.GameDev.entity;

import java.awt.Image;

import bl4ckscor3.game.GameDev.manager.TextureManager;

public class Player
{
	public int posX;
	public int posY;
	public Image texture;
	
	public Player()
	{
		texture = TextureManager.loadTexture("player");
	}
}
