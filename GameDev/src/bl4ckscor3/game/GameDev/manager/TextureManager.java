package bl4ckscor3.game.GameDev.manager;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class TextureManager
{
	public static Image loadTexture(String resourceID)
	{
		return new ImageIcon("resources/" + resourceID + ".png").getImage();
	}
	
	public static Image loadTexture(String resourceID, String path)
	{
		return new ImageIcon("resources/" + path + resourceID + ".png").getImage();
	}
	
	public static Image loadRandomTexture(String resourceID, int textureAmount)
	{
		Random r = new Random();
		
		return new ImageIcon("resources/" + resourceID + "_" + r.nextInt(textureAmount) + ".png").getImage();
	}
	
	public static Image loadRandomTexture(String resourceID, String path, int textureAmount)
	{
		Random r = new Random();
		
		return new ImageIcon("resources/" + path + resourceID + "_" + r.nextInt(textureAmount) + ".png").getImage();
	}
}
