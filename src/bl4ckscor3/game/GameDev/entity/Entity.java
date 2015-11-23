package bl4ckscor3.game.GameDev.entity;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.GameDev.util.Vector2D;

public class Entity
{
	/** The texture of the entity*/
	private Image texture;
	/** The position of the entity*/
	public Vector2D position;
	
	public Entity(Image tex)
	{
		texture = tex;
		position = new Vector2D();
	}

	public void render(Graphics g){}

	public void tick(){}

	/**
	 * Setting the texture of the entity. Used for directional texture changes and animations.
	 * @param tex - The texture to use
	 */
	public void setTexture(Image tex)
	{
		texture = tex;	
	}
	
	public Image getCurrentTexture()
	{
		return texture;
	}
}
