package bl4ckscor3.game.GameDev.core;

import javax.swing.JFrame;

/**
 * bl4ckscor3 - 23.09.2014
 */
public class Core
{
	public static void main(String[] args)
	{
		new Core();
	}
	
	public Core()
	{
		JFrame frame = new JFrame();
		
		frame.setTitle("GameDev");
		//setting window to full size of screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//getting rid of the border (pseudo fullscreen now created)
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Screen(frame));
		frame.setVisible(true);
	}
}
