package bl4ckscor3.game.gamedev.entity;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.gamedev.util.Direction;
import bl4ckscor3.game.gamedev.util.Vector2D;

public class Entity
{
	/** The texture of the entity*/
	private Image texture;
	/** The position of the entity*/
	public Vector2D position;
	/** Wether an animation is currently ongoing or not*/
	protected boolean animating = false;
	
	public Entity(Image tex)
	{
		texture = tex;
		position = new Vector2D();
	}

	public void render(Graphics g){}

	public void tick(){}
	
	/**
	 * Starts an animation in the specified direction
	 * @param dir The direction
	 */
	public void startAnimation(Direction dir){}
	
	public void stopAnimation(){}

	/**
	 * Setting the texture of the entity. Used for directional texture changes and animations.
	 * @param tex The texture to use
	 */
	public void setTexture(Image tex)
	{
		texture = tex;	
	}
	
	public Image getCurrentTexture()
	{
		return texture;
	}
	
	/**
	 * @return true if there is an animation ongoing right now, false otherwise
	 */
	public boolean isAnimating()
	{
		return animating;
	}
}
