package bl4ckscor3.game.GameDev.entity;

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
}
