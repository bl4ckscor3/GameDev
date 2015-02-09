package bl4ckscor3.game.GameDev.entity;

import bl4ckscor3.game.GameDev.util.TextureManager;

public class Player extends Entity
{
	public Player()
	{
		super(TextureManager.loadTextureFromPath("playerFacing", "player/"));
	}
}
