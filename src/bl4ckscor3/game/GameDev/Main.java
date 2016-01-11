package bl4ckscor3.game.GameDev;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.game.GameThread;
import bl4ckscor3.game.GameDev.game.Screen;
import bl4ckscor3.game.GameDev.util.ScaleFactor;
import bl4ckscor3.game.GameDev.util.Utilities;

public class Main
{
	public static Screen screen;
	public static Game game;
	private boolean windowBorder = false; //border of Windows' windows (the thing with minimize, maximize and close)
	private boolean fullscreen = windowBorder;
	public static ConfigurationFile config = new ConfigurationFile();
	public static int width = Integer.parseInt(config.getValue("width"));
	public static int height = Integer.parseInt(config.getValue("height"));
//	public static int width = 1920;
//	public static int height = 1080;
	public static ScaleFactor scaleFactor;

	public static void main(String[] args)
	{	
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Main();
			}
		});
	}

	public Main()
	{
		JFrame frame = new JFrame();
		Dimension maxScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setTitle("GameDev");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setting width and height + border yes/no
		if(width == 0)
			width = maxScreenSize.width;
		
		if(height == 0)
			height = maxScreenSize.height;
		
		if(width == maxScreenSize.width && height == maxScreenSize.height)
			fullscreen = true;

		if(fullscreen)
		{
			frame.setUndecorated(true);
			frame.setSize(width, height);
		}
		else
		{
			frame.getContentPane().setPreferredSize(new Dimension(width, height));
			frame.pack();
		}
		//end
		
		frame.getContentPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		screen = new Screen(frame);
		scaleFactor = new ScaleFactor(Utilities.ceil(Screen.tileSize * Screen.pixelSize * screen.pixelScaleWidth / 2), Utilities.ceil(Screen.tileSize * Screen.pixelSize * screen.pixelScaleHeight / 2));
		frame.add(screen);
		frame.setVisible(true);
		//starts the game thread
		Game.thread = new GameThread();
		Game.setupMenus();
	}
}
