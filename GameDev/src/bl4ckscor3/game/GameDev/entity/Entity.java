package bl4ckscor3.game.GameDev.entity;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.util.Utilities;
import bl4ckscor3.game.GameDev.util.Vector2D;

public class Entity
{
	private Image texture;
	public Vector2D position;
	
	public Entity(Image texture)
	{
		this.texture = texture;
		position = new Vector2D();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(texture, Main.width / 2 - Utilities.ceil(Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleWidth / 2), Main.height / 2 - Utilities.ceil(Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleHeight / 2) - 6, 32, 41, null);	
	}
	
	public void tick(){}

	public void setTexture(Image tex)
	{
		texture = tex;	
	}
}
