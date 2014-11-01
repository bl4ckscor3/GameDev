package bl4ckscor3.game.GameDev.world.generation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWriter
{
	public static void writeImage(double[][] data)
	{
		BufferedImage img = new BufferedImage(data.length, data[0].length, BufferedImage.TYPE_INT_RGB);
	
		for(int x = 0; x < data.length; x++)
		{
			for(int y = 0; y < data[0].length; y++)
			{
				Color c = new Color((float) data[x][y], (float) data[x][y], (float) data[x][y]);
				
				img.setRGB(x, y, c.getRGB());
			}
		}
		
		try
		{
			File f = new File("noise.png");
			
			f.createNewFile();
			ImageIO.write(img, "PNG", f);
		}
		catch(IOException e)
		{
			throw new RuntimeException("Unable to write noise");
		}
	}
}	
