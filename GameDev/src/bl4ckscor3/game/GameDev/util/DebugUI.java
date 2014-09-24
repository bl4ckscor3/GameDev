package bl4ckscor3.game.GameDev.util;

import java.awt.Font;
import java.awt.Graphics;

import bl4ckscor3.game.GameDev.core.Core;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.listener.MouseMotion;

public class DebugUI
{
	public static void displayDebugUI(Graphics g)
	{
		int spaceY = 15;

		g.setFont(new Font("Times New Roman", 1, 14));

		for(int i : Key.keysPressed)
		{
			g.drawString("Key Pressed: " + i, Core.SCREEN_WIDTH - 110, spaceY);
			spaceY += 15;
		}

		g.drawString("Horizontal mouse position: " + MouseMotion.mouseX, Core.SCREEN_WIDTH - 350, 15);
		g.drawString("Vertical mouse position: " + MouseMotion.mouseY, Core.SCREEN_WIDTH - 350, 30);
	}
}
