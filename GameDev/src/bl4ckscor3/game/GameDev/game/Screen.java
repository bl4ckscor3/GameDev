package bl4ckscor3.game.GameDev.game;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.listener.Key;
import bl4ckscor3.game.GameDev.listener.Mouse;
import bl4ckscor3.game.GameDev.listener.MouseMotion;
import bl4ckscor3.game.GameDev.menu.Menu;
import bl4ckscor3.game.GameDev.util.DebugUI;

public class Screen extends JPanel 
{
	public static float pixelSize = 2.5F;
	public float pixelScaleWidth;
	public float pixelScaleHeight;
	public static int tileSize = 16;
	public static boolean displayDebug = false;
	public static boolean debugWasShown = false;
	public float optimizedScreenWidth = 1920;
	public float optimizedScreenHeight = 1080;

	public Screen(JFrame frame)
	{
		pixelScaleWidth = Main.width / optimizedScreenWidth;
		pixelScaleHeight = Main.height / optimizedScreenHeight;
		frame.addKeyListener(new Key());
		frame.addMouseListener(new Mouse());
		frame.addMouseMotionListener(new MouseMotion());
	}

	/**
	 * Drawing on the screen
	 * @param g - The Graphics to draw with
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		//clears screen
		g.clearRect(0, 0, 1920, 1080);
		
		if(Game.hasRunBefore)
			Game.map.render(g);

		if(Menu.isOpen())
		{
			if(displayDebug)
			{
				displayDebug = false;
				debugWasShown = true;
			}

			Menu.displayMenu(g);
			return; //no need to check if the debug menu is open, since it's closed in menus anyways
		}

		if(displayDebug)
			DebugUI.displayDebugUI(g);
	}
}
