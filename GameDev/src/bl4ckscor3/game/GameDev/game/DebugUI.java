package bl4ckscor3.game.GameDev.game;

import java.awt.Font;
import java.awt.Graphics;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.listener.MouseMotion;
import bl4ckscor3.game.GameDev.world.Chunk;

public class DebugUI
{
	public static void displayDebugUI(Graphics g)
	{
		int spaceY = 45;

		g.setFont(new Font("Times New Roman", 1, 16));

		//currently pressed keys
		for(int i : Key.keysPressed)
		{
			g.drawString("Key Pressed: " + i, Main.screen.width - 120, spaceY);
			spaceY += 15;
		}

		//mouse position
		g.drawString("Horizontal mouse position: " + MouseMotion.mouseX, Main.screen.width - 360, 15);
		g.drawString("Vertical mouse position: " + MouseMotion.mouseY, Main.screen.width - 360, 30);
		
		//player position
		g.drawString("Player position X: " + Game.map.player.posX, Main.screen.width - 360, 60);
		g.drawString("Player position Y: " + Game.map.player.posY, Main.screen.width - 360, 75);
		
		//fps
		g.drawString("FPS: " + GameThread.fps, Main.screen.width - 91, 15);
	}
	
	public static void drawChunkLines(Graphics g, Chunk c, int posX, int posY)
	{
		//vertical lines
		g.drawLine(posX, 0, posX, Screen.height);
		//horizontal lines
		g.drawLine(0, posY, Screen.width, posY);
	}
}
