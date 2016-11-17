package bl4ckscor3.game.gamedev.world.content;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.util.TextureManager;

public class Tile
{
	public List<String> tiles = new ArrayList<String>();
	private Material material;
	public Image texture;
	
	/**
	 * Loads a texture of the given material from the subfolder tiles/ of the default resource location
	 * @param mat Material of the texture
	 * @param path Subfolder to load the texture from
	 */
	public Tile(Material mat)
	{
		material = mat;
		texture = TextureManager.loadTextureFromPath(material.getResourceID(), "tiles/");
	}
	
	/**
	 * Loads a texture of the given material from the tiles/%path% subfolder of the default resource location
	 * @param mat Material of the texture
	 * @param path Subfolder to load the texture from
	 */
	public Tile(Material mat, String path)
	{
		material = mat;
		texture = TextureManager.loadTextureFromPath(material.getResourceID(), "tiles/" + path);
	}
	
	/**
	 * Loads a random texture of the given material from the tiles/%path% subfolder of the default resource location
	 * @param mat Material of the texture
	 * @param path Subfolder to load the texture from
	 * @param textureAmount Amount of textures to pick one from
	 */
	public Tile(Material mat, String path, int textureAmount)
	{
		material = mat;
		texture = TextureManager.loadRandomTextureFromPath(material.getResourceID(), "tiles/" + path, textureAmount);
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
	 * Checks if this Tile is water
	 * @return true if this Tile is water, false otherwise
	 */
	public boolean isWater()
	{
		return material == Material.WATER_DEEP || material == Material.WATER_NORMAL;
	}
	
	/**
	 * @return the Material of this Tile
	 */
	public Material getMaterial()
	{
		return material;
	}
}
