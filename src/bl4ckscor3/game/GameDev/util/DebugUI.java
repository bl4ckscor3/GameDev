package bl4ckscor3.game.GameDev.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.listener.MouseMotion;
import bl4ckscor3.game.GameDev.world.Chunk;

public class DebugUI
{
	private static int fps = 0;
	
	public static void displayDebugUI(Graphics g)
	{
		//the space between the different strings for showing which keys are pressed
		int spaceY = 45;

		g.setColor(new Color(100010000)); //some kind of red
		g.setFont(new Font("Times New Roman", 1, 16));

		//currently pressed keys
		for(int i : Key.keysPressed)
		{
			g.drawString("Key Pressed: " + i, Main.width - 120, spaceY);
			spaceY += 15;
		}

		//mouse position
		g.drawString("Horizontal mouse position: " + MouseMotion.mouseX, Main.width - 360, 15);
		g.drawString("Vertical mouse position: " + MouseMotion.mouseY, Main.width - 360, 30);
		
		//player position
		g.drawString("Player position X: " + Game.player.position.x, Main.width - 360, 60);
		g.drawString("Player position Y: " + Game.player.position.y, Main.width - 360, 75);
		
		//fps
		g.drawString("FPS: " + fps, Main.width - 91, 15);
	}
	
	public static void drawChunkInfo(Graphics g, Chunk c, int posX, int posY)
	{
		//vertical lines
		g.drawLine(posX, 0, posX, Main.height);
		//horizontal lines
		g.drawLine(0, posY, Main.width, posY);
		//chunk position
		g.drawString("Chunk (" + c.chunkX + ", " + c.chunkY + ")", Utilities.ceil(posX), Utilities.ceil(posY));
	}
	
	public static void setFPS(int f)
	{
		fps = f;
	}
}
