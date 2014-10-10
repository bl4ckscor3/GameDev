package bl4ckscor3.game.GameDev.game;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.listener.Mouse;
import bl4ckscor3.game.GameDev.listener.MouseMotion;
import bl4ckscor3.game.GameDev.util.DebugUI;
import bl4ckscor3.game.GameDev.world.Chunk;

public class Screen extends JPanel 
{
	public static int width;
	public static int height;
	public static int pixelSize = 3;
	public static int tileSize = 16;
	public static boolean shouldDisplayDebug = false;

	public Screen(JFrame frame)
	{
		frame.addKeyListener(new Key());
		frame.addMouseListener(new Mouse());
		frame.addMouseMotionListener(new MouseMotion());
	}

	public void paintComponent(Graphics g)
	{
		//clears screen
		g.clearRect(0, 0, width, height);
		renderBackground(g);
		render(g);
		renderForeground(g);
	}

	private void renderBackground(Graphics g)
	{
		//drawing "chunks"
		for(Chunk c : Game.map.loadedChunks)
		{
			for(int x = 0; x < c.tiles.length; x++)
			{
				for(int y = 0; y < c.tiles[0].length; y++)
				{
					//texture to use, pos inside of chunk + pos x of chunk, same for y, width of chunk, height of chunk
					g.drawImage(c.tiles[x][y].texture, x * tileSize * pixelSize + c.chunkX * c.chunkSizeX * tileSize * pixelSize,  y * tileSize * pixelSize + c.chunkY * c.chunkSizeY * tileSize * pixelSize, tileSize * pixelSize, tileSize * pixelSize, null);
				}
			}
			
			if(shouldDisplayDebug)
				DebugUI.drawChunkLines(g, c);
		}
	}

	private void render(Graphics g){}

	private void renderForeground(Graphics g)
	{
		if(shouldDisplayDebug)
			DebugUI.displayDebugUI(g);
	}
}
