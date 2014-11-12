package bl4ckscor3.game.GameDev.content;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.game.GameDev.manager.TextureManager;

public class Tile
{
	public List<String> tiles = new ArrayList<String>();
	private Material material;
	public Image texture;
	
	/**
	 * Loading a texture of the given material from the default resource location
	 * @mat - Material of the texture
	 */
	public Tile(Material mat)
	{
		material = mat;
		texture = TextureManager.loadTexture(material.getResourceID(mat));
	}
	
	/**
	 * Loading a texture of the given material from a subfolder of the default resource location
	 * @mat - Material of the texture
	 * @path - Subfolder to load the texture from
	 */
	public Tile(Material mat, String path)
	{
		material = mat;
		texture = TextureManager.loadTextureFromPath(material.getResourceID(mat), path);
	}
	
	/**
	 * Loading a random texture of the given material from the default resource location
	 * @mat - Material of the texture
	 * @textureAmount - Amount of textures to pick one from
	 */
	public Tile(Material mat, int textureAmount)
	{
		material = mat;
		texture = TextureManager.loadRandomTexture(material.getResourceID(mat), textureAmount);
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
		texture = TextureManager.loadRandomTextureFromPath(material.getResourceID(mat), path, textureAmount);
	}
}
