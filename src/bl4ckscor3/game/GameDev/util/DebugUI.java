package bl4ckscor3.game.gamedev.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.listener.Key;
import bl4ckscor3.game.gamedev.world.Chunk;
import bl4ckscor3.game.gamedev.world.content.Tile;

public class DebugUI
{
	private static int fps = 0;
	private static String currentTile = "Spawn";
	
	public static void displayDebugUI(Graphics g)
	{
		//the space between the different strings for showing which keys are pressed
		int spaceY = 45;

		g.setColor(new Color(100010000)); //some kind of red
		g.setFont(new Font("Times New Roman", 1, 16));

		//currently pressed keys
		for(int i : Key.keysPressed)
		{
			if(i == 16) //because jesus
				continue;
			
			g.drawString("Key Pressed: " + i, Main.width - 110, spaceY);
			spaceY += 15;
		}

		g.drawString("Player position X: " + Game.player.position.x, 5, 15);
		g.drawString("Player position Y: " + Game.player.position.y, 5, 30);
		g.drawString("Chunk (X, Y): " + "(" + Game.map.getChunk(Game.player).chunkX + ", " + Game.map.getChunk(Game.player).chunkY + ")", 5, 45);
		g.drawString("Chunk position X: " + Game.map.getChunkPosition(Game.player).x, 5, 60);
		g.drawString("Chunk position Y: " + Game.map.getChunkPosition(Game.player).y, 5, 75);
		g.drawString("Current tile: " + currentTile, 5, 90);
		
		//jesus
		if(Key.keysPressed.contains(16))
			g.drawString("Jesus: true", 5, 105);
		else
			g.drawString("Jesus: false", 5, 105);
		
		//fps
		g.drawString("FPS: " + fps, Main.width - 60, 15);
	}
	
	public static void drawChunkGrid(Graphics g, Chunk c, int posX, int posY)
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
	
	/**
	 * Sets the current tile to the Material of the given tile
	 * @param t The tile to set the material of
	 */
	public static void setCurrentTile(Tile t)
	{
		currentTile = t.getMaterial().getResourceID();
	}
}
