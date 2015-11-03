package bl4ckscor3.game.GameDev.world.content;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.util.TextureManager;
import bl4ckscor3.game.GameDev.util.Utilities;

public class Tile
{
	public List<String> tiles = new ArrayList<String>();
	private Material material;
	public Image texture;
	
	/**
	 * Loading a texture of the given material from a subfolder of the default resource location
	 * @mat - Material of the texture
	 * @path - Subfolder to load the texture from
	 */
	public Tile(Material mat, String path)
	{
		material = mat;
		texture = TextureManager.loadTextureFromPath(material.getResourceID(), path);
	}
	
	/**
	 * Loading a random texture of the given material from a subfolder of the default resource location
	 * @mat - Material of the texture
	 * @path - Subfolder to load the texture from
	 * @textureAmount - Amount of textures to pick one from
	 */
	public Tile(Material mat, String path, int textureAmount)
	{
		material = mat;
		texture = TextureManager.loadRandomTextureFromPath(material.getResourceID(), path, textureAmount);
	}

	/**
	 * Gets called every game tick
	 */
	public void tick(){}
	
	/**
	 * Test rendering
	 */
	public void render(Graphics g)
	{
		render(g, 0, 0);
	}
	
	/**
	 * Rendering the tile without the width and height
	 */
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, Utilities.ceil(Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleWidth), Utilities.ceil(Screen.tileSize * Screen.pixelSize * Main.screen.pixelScaleHeight), null);
	}
	
	/**
	 * Rendering the tile
	 */
	public void render(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage(texture, x, y, width, height, null);
	}
	
	/**
	 * Checks if this Material is water
	 * @param m The Material to check
	 * @return Wether the Material is water or not
	 */
	public boolean isWater()
	{
		return material == Material.WATER_DEEP || material == Material.WATER_NORMAL;
	}
	
	/**
	 * Gets the Material of this Tile
	 */
	public Material getMaterial()
	{
		return material;
	}
}
