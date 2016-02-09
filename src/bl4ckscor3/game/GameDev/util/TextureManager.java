package bl4ckscor3.game.gamedev.util;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class TextureManager
{
	/**
	 * Loading a texture from the resources folder without subfolders
	 * @param fileName The name of the file
	 */
	public static Image loadTexture(String fileName)
	{
		return new ImageIcon("resources/" + fileName + ".png").getImage();
	}
	
	/**
	 * Loading a texture from the resources folder with subfolders
	 * @param fileName The name of the file
	 * @param path The folder structure in which the file can be found
	 */
	public static Image loadTextureFromPath(String fileName, String path)
	{
		return new ImageIcon("resources/" + path + fileName + ".png").getImage();
	}
	
	/**
	 * Loading a random texture of files with the same file name and appended number (e.g. tex_0, tex_1, tex_2 ...) from the resources folder without subfolders
	 * @param fileName The name of the file
	 * @param textureAmount How many texturefiles there are for the same Material
	 */
	public static Image loadRandomTexture(String fileName, int textureAmount)
	{
		Random r = new Random();
		
		return new ImageIcon("resources/" + fileName + "_" + r.nextInt(textureAmount) + ".png").getImage();
	}
	
	/**
	 * Loading a random texture of files with the same file name and appended number (e.g. tex_0, tex_1, tex_2 ...) from the resources folder with subfolders
	 * @param fileName The name of the file
	 * @param path The folder structure in which the file can be found
	 * @param textureAmount How many texturefiles there are for the same Material
	 */
	public static Image loadRandomTextureFromPath(String fileName, String path, int textureAmount)
	{
		Random r = new Random();
		
		return new ImageIcon("resources/" + path + fileName + "_" + r.nextInt(textureAmount) + ".png").getImage();
	}
}
