package bl4ckscor3.game.GameDev.core;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.game.Screen;

/**
 * bl4ckscor3 - 23.09.2014
 */
public class Main
{
	public static Screen screen;
	public static Game game;

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
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		JFrame frame = new JFrame();

		screen = new Screen(frame);

		if(screen.width == 0)
			screen.width = toolkit.getScreenSize().width;
		if(screen.height == 0)
			screen.height = toolkit.getScreenSize().height;
		
		frame.setTitle("GameDev"); 
		frame.setSize(screen.width, screen.height);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		//getting rid of the border (pseudo fullscreen now created)
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(screen);
		frame.setVisible(true);
		game = new Game();
		Game.start();
	}
}
