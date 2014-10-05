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
//		for(int x = 0; x < 20; x++)
//		{
//			for(int y = 0; y < 20; y++)
//			{
//				g.drawImage(ImageLibrary.GRASS, x * tileSize * pixelSize, y * tileSize * pixelSize, tileSize * pixelSize, tileSize * pixelSize, null);
//			}
//		}
		
		//drawing "chunks"
		for(Chunk c : Game.map.loadedChunks)
		{
			g.drawLine(100 + c.chunkX * 5, 100 + c.chunkY * 5, 100 + c.chunkX * 5, 100 + c.chunkY * 5);
		}
	}

	private void render(Graphics g){}
	
	private void renderForeground(Graphics g)
	{
		if(shouldDisplayDebug)
			DebugUI.displayDebugUI(g);
	}
}
