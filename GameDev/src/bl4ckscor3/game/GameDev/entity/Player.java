package bl4ckscor3.game.GameDev.entity;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.util.TextureManager;
import bl4ckscor3.game.GameDev.util.Utilities;

public class Player extends Entity
{
	public Player()
	{
		super(TextureManager.loadTextureFromPath("playerFacing", "player/"));
	}
}
