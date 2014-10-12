package bl4ckscor3.game.GameDev.manager;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class TextureManager
{
	public static Image loadTexture(String resourceID, int textureAmount)
	{
		Random r = new Random();
		
		return new ImageIcon("resources/" + resourceID + "_" + r.nextInt(textureAmount) + ".png").getImage();
	}
}
