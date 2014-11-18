package bl4ckscor3.game.GameDev.game;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.listener.Mouse;
import bl4ckscor3.game.GameDev.listener.MouseMotion;
import bl4ckscor3.game.GameDev.manager.TextureManager;
import bl4ckscor3.game.GameDev.util.Utilities;
import bl4ckscor3.game.GameDev.world.Chunk;

public class Screen extends JPanel 
{
	public static float pixelSize = 2.5F;
	public float pixelScaleWidth;
	public float pixelScaleHeight;
	public static int tileSize = 16;
	public static boolean shouldDisplayDebug = true;
	public float optimizedScreenWidth = 1600;
	public float optimizedScreenHeight = 900;
	
	public Screen(JFrame frame)
	{
		pixelScaleWidth = Main.width / optimizedScreenWidth;
		pixelScaleHeight = Main.height / optimizedScreenHeight;
		frame.addKeyListener(new Key());
		frame.addMouseListener(new Mouse());
		frame.addMouseMotionListener(new MouseMotion());
	}

	public void paintComponent(Graphics g)
	{
		//clears screen
		g.clearRect(0, 0, Main.width, Main.height);
		renderBackground(g);
		render(g);
		renderForeground(g);
	}

	private void renderBackground(Graphics g)
	{
		//drawing chunks
		for(Chunk c : Game.map.loadedChunks)
		{
			int posX = Utilities.ceil((c.chunkX * c.chunkSizeX * tileSize * pixelSize - Game.map.player.posX * tileSize * pixelSize - tileSize * pixelSize / 2) * pixelScaleWidth + Main.width / 2);
			int posY = Utilities.ceil((c.chunkY * c.chunkSizeY * tileSize * pixelSize - Game.map.player.posY * tileSize * pixelSize - tileSize * pixelSize / 2) * pixelScaleHeight + Main.height / 2);
			
			for(int x = 0; x < c.tiles.length; x++)
			{
				for(int y = 0; y < c.tiles[0].length; y++)
				{
					//texture to use, pos inside of chunk + pos x of chunk, same for y, width of chunk, height of chunk
					g.drawImage(c.tiles[x][y].texture, Utilities.ceil(x * tileSize * pixelSize * pixelScaleWidth) + posX, Utilities.ceil(y * tileSize * pixelSize * pixelScaleHeight) + posY, Utilities.ceil(tileSize * pixelSize * pixelScaleWidth), Utilities.ceil(tileSize * pixelSize * pixelScaleHeight), null);
				}
			}
			
			if(shouldDisplayDebug)
				DebugUI.drawChunkInfo(g, c, posX, posY);
		}
	}

	private void render(Graphics g)
	{
		g.drawImage(Game.map.player.texture, Main.width / 2 - Utilities.ceil(tileSize * pixelSize * pixelScaleWidth / 2), Main.height / 2 - Utilities.ceil(tileSize * pixelSize * pixelScaleHeight / 2), Utilities.ceil(tileSize * pixelSize * pixelScaleWidth), Utilities.ceil(tileSize * pixelSize * pixelScaleHeight), null);
	}

	private void renderForeground(Graphics g)
	{
		if(shouldDisplayDebug)
			DebugUI.displayDebugUI(g);
	}
}
