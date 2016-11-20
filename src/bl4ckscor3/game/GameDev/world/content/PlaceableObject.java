package bl4ckscor3.game.gamedev.world.content;

import java.awt.Graphics;
import java.awt.Image;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.util.Position;
import bl4ckscor3.game.gamedev.util.TextureManager;
import bl4ckscor3.game.gamedev.world.Chunk;

public class PlaceableObject
{
	protected Material material;
	protected Image texture;
	public Chunk chunk;
	protected Position pos;
	
	/**
	 * Sets up an object that can be placed in the world
	 * @param mat The Material of the texture to use
	 * @param c The Chunk this PlaceableObject is in
	 * @param pos The x and y coordinates within the chunk that this object is placed in
	 */
	public PlaceableObject(Material mat, Chunk c, Position pos)
	{
		this(mat, c, pos, "", "");
	}
	
	/**
	 * Sets up an object that can be placed in the world
	 * @param mat Material of the texture
	 * @param chunk The Chunk this PlaceableObject is in
	 * @param pos The x and y coordinates within the chunk that this object is placed in
	 * @param appendix The appendix to the file name
	 * @param path Subfolder to load the texture from
	 */
	public PlaceableObject(Material mat, Chunk c, Position pos, String appendix, String path)
	{
		material = mat;
		texture = TextureManager.loadTextureFromPath(material.getResourceID() + appendix, "objects/" + path);
		chunk = c;
		this.pos = pos;
	}

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
	 * @return The Chunk this PlaceableObject is in
	 */
	public Chunk getChunk()
	{
		return chunk;
	}
	
	/**
	 * @return The x and y coordinates within the chunk that this object is placed in
	 */
	public Position getPos()
	{
		return pos;
	}
	
	@Override
	public String toString()
	{
		return pos + "-" + chunk + "-" + material;
	}
}
