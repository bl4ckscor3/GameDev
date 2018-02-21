package bl4ckscor3.game.gamedev.game;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bl4ckscor3.game.gamedev.inventory.AbstractInventory;
import bl4ckscor3.game.gamedev.listener.Key;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.DebugUI;

public class Screen extends JPanel 
{
	public static float pixelSize = 2.5F;
	public static int tileSize = 16;
	public static boolean displayDebug = false;
	public static boolean debugWasShown = false;

	public Screen(JFrame frame)
	{
		frame.addKeyListener(new Key());
	}

	@Override
	public void paintComponent(Graphics g)
	{
		//clears screen
		g.clearRect(0, 0, 1920, 1080);
		
		if(Game.map != null)
			Game.map.render(g);

		if(Menu.isOpen())
		{
			if(displayDebug)
			{
				displayDebug = false;
				debugWasShown = true;
			}

			Menu.displayMenu(g);
		}
		else
		{
			for(AbstractInventory inv : AbstractInventory.inventories)
			{
				if(inv.isOpen())
					inv.render(g);
			}
			
			if(displayDebug)
				DebugUI.displayDebugUI(g);	
		}
	}
}
