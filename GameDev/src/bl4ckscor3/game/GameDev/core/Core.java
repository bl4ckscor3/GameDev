package bl4ckscor3.game.GameDev.core;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * bl4ckscor3 - 23.09.2014
 */
public class Core
{
	public static int SCREEN_HEIGHT;
	public static int SCREEN_WIDTH;
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Core();
			}
		});
	}
	
	public Core()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		JFrame frame = new JFrame();

		frame.setTitle("GameDev");
		//setting window to full size of screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//getting rid of the border (pseudo fullscreen now created)
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Screen(frame));
		frame.setVisible(true);
		SCREEN_HEIGHT = tk.getScreenSize().height;
		SCREEN_WIDTH = tk.getScreenSize().width;
	}
}
