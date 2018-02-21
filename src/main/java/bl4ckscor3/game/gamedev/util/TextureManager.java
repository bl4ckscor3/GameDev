package bl4ckscor3.game.gamedev.util;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

import bl4ckscor3.game.gamedev.game.Game;

/**
 * Contains useful methods for loading textures in different ways
 * @author bl4ckscor3
 */
public class TextureManager
{
	private static final HashMap<String,Image> loadedTextures = new HashMap<String,Image>(); //resource location, image itself
	private static final String RESOURCE_PATH = "src/main/resources/";

	/**
	 * Loads a texture from the resources folder without subfolders
	 * @param fileName The name of the file
	 * @return The loaded image
	 */
	public static Image loadTexture(String fileName)
	{
		String loc = RESOURCE_PATH + fileName + ".png";

		if(!loadedTextures.containsKey(loc))
			loadedTextures.put(loc, new ImageIcon(loc).getImage());

		return loadedTextures.get(loc);
	}

	/**
	 * Loads a texture from the resources folder with subfolders
	 * @param fileName The name of the file
	 * @param path The folder structure in which the file can be found
	 * @return The loaded image
	 */
	public static Image loadTextureFromPath(String fileName, String path)
	{
		String loc = RESOURCE_PATH + path + fileName + ".png";

		if(!loadedTextures.containsKey(loc))
			loadedTextures.put(loc, new ImageIcon(loc).getImage());

		return loadedTextures.get(loc);
	}

	/**
	 * Loads a random texture of files with the same file name and appended number (e.g. tex_0, tex_1, tex_2 ...) from the resources folder without subfolders
	 * @param fileName The name of the file
	 * @param textureAmount How many texturefiles there are for the same Material
	 * @return The loaded image
	 */
	public static Image loadRandomTexture(String fileName, int textureAmount)
	{
		String loc = RESOURCE_PATH + fileName + "_" + Game.r.nextInt(textureAmount) + ".png";

		if(!loadedTextures.containsKey(loc))
			loadedTextures.put(loc, new ImageIcon(loc).getImage());

		return loadedTextures.get(loc);
	}

	/**
	 * Loads a random texture of files with the same file name and appended number (e.g. tex_0, tex_1, tex_2 ...) from the resources folder with subfolders
	 * @param fileName The name of the file
	 * @param path The folder structure in which the file can be found
	 * @param textureAmount How many texturefiles there are for the same Material
	 * @return The loaded image
	 */
	public static Image loadRandomTextureFromPath(String fileName, String path, int textureAmount)
	{
		String loc = RESOURCE_PATH + path + fileName + "_" + Game.r.nextInt(textureAmount) + ".png";

		if(!loadedTextures.containsKey(loc))
			loadedTextures.put(loc, new ImageIcon(loc).getImage());

		return loadedTextures.get(loc);
	}
}
