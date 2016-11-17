package bl4ckscor3.game.gamedev.world.content;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.util.TextureManager;
import bl4ckscor3.game.gamedev.util.Vector2D;

public class PlaceableObject
{
	private Material material;
	public Image texture;
	private Vector2D pos;
	
	/**
	 * Sets up an object that can be placed in the world
	 * @param mat The Material of the texture to use
	 * @param chunkX The x-coordinate of the chunk this object is placed in
	 * @param chunkY The y-coordinate of the chunk this object is placed in
	 * @param pos The x and y coordinates within the chunk that this object is placed in
	 */
	public PlaceableObject(Material mat, Vector2D pos)
	{
		material = mat;
		texture = TextureManager.loadTextureFromPath(mat.getResourceID(), "tiles/");
		this.pos = pos;
	}
	
	/**
	 * Gets called every game tick
	 */
	public void tick(){}
	
	/**
	 * Renders the tile at the position 0 0
	 * @param g The graphics to render the tile with
	 */
	public void render(Graphics g)
	{
		render(g, 0, 0);
	}
	
	/**
	 * Renders the tile at the x and y position
	 * @param g The graphics to render the tile with
	 * @param x The x position at which to render the tile
	 * @param y The y position at which to render the tile
	 */
	public void render(Graphics g, int x, int y)
	{
		render(g, x, y, Main.scaleFactor.getWidth() * 2, Main.scaleFactor.getHeight() * 2);
	}
	
	/**
	 * Renders the tile at the x and y position with the given width and height
	 * @param g The graphics to render the tile with
	 * @param x The x position at which to render the tile
	 * @param y The y position at which to render the tile
	 * @param width The width of the tile to render
	 * @param height The height of the tile to render
	 */
	public void render(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage(texture, x, y, width, height, null);
	}
	
	/**
	 * @return The Material of which this object consists
	 */
	public Material getMaterial()
	{
		return material;
	}

	/**
	 * @return The x and y coordinates within the chunk that this object is placed in
	 */
	public Vector2D getPos()
	{
		return pos;
	}
}
