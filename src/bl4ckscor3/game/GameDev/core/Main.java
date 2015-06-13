package bl4ckscor3.game.GameDev.core;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.game.Screen;

public class Main
{
	public static Screen screen;
	public static Game game;
	private boolean windowBorder = false; //border of Windows' windows (the thing with minimize, maximize and close)
	private boolean fullscreen = windowBorder;
	public static int width = 1600;
	public static int height = 900;

	public static void main(String[] args)
	{	
//		setScreenSize(args);
		
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
		frame.add(screen);
		frame.setVisible(true);
		game = new Game();
		Game.start();
	}
	
	private static void setScreenSize(String[] args) 
	{
		Scanner s = new Scanner(System.in);
	
		System.out.println("Controls: Arrow keys OR WASD - Move | F3 - Debug Menu | ESC - Pause");
		System.out.println("Enter your preferred screen width: ");
		width = Integer.parseInt(s.nextLine());
		System.out.println("Enter your preferred screen height: ");
		height = Integer.parseInt(s.nextLine());
		s.close();
	}
}
