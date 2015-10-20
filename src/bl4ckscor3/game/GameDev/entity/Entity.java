package bl4ckscor3.game.GameDev.entity;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.util.Utilities;
import bl4ckscor3.game.GameDev.util.Vector2D;

public class Entity
{
	/** The texture of the entity*/
	private Image texture;
	/** The position of the entity*/
	public Vector2D position;
	
	public Entity(Image texture)
	{
		this.texture = texture;
		position = new Vector2D();
	}

	public void render(Graphics g)
	{
		//currently only rendering the player
		g.drawImage(texture, Main.width / 2 - Utilities.ceil(Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleWidth / 2), Main.height / 2 - Utilities.ceil(Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleHeight / 2) - 6, 32, 41, null);	
	}

	public void tick(){}

	/**
	 * Setting the texture of the entity. Used for directional texture changes
	 * @param tex - The texture to use
	 */
	public void setTexture(Image tex)
	{
		texture = tex;	
	}
}
