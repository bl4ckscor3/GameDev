package bl4ckscor3.game.GameDev.manager;

import java.awt.Image;

import javax.swing.ImageIcon;

public class TextureManager
{
	public static Image loadTexture(String resourceID)
	{
		return new ImageIcon("resources/" + resourceID + ".png").getImage();
	}
}
