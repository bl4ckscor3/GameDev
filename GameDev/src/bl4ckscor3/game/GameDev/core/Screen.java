package bl4ckscor3.game.GameDev.core;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * bl4ckscor3 - 24.09.2014
 */
public class Screen extends JPanel implements Runnable
{
	private Thread  thread = new Thread(this);
	private int tps = 0;
	private int fps = 0;
	public Screen(JFrame frame)
	{
		thread.start();
	}
	
	@Override
	public void run()
	{
		int TICKS_PER_SECOND = 50;
		int FRAMES_PER_SECOND = 100;
		int loops = 0; //amount of loops it has done before rendering
		int ticks = 0; //variable to update TICKS_PER_SECOND
		int frames = 0; //variable to update FRAMES_PER_SECOND
		long GAME_SKIP_TICKS = 1000 / TICKS_PER_SECOND; //amount of time the game waits until it does the next update
		long FRAME_SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
		long MAXIMUM_FRAME_SKIPS = 5; //if the game runs 5 updates at the same time without updating the screen it stops updating to prevent lag
		long NEXT_GAME_TICK = System.currentTimeMillis(); //next tick to update
		long NEXT_FRAME_TICK = System.currentTimeMillis();
		long time = System.currentTimeMillis();
		
		while(true)
		{
			loops = 0;
			
			while(System.currentTimeMillis() > NEXT_GAME_TICK && loops < MAXIMUM_FRAME_SKIPS)
			{
				update();
				NEXT_GAME_TICK += GAME_SKIP_TICKS; //makes sure to wait 16ms before updating again
				ticks++;
				loops++;
			}
			
			if(System.currentTimeMillis() > NEXT_FRAME_TICK)
			{
				NEXT_FRAME_TICK += FRAME_SKIP_TICKS;
				repaint();
				frames++;
			}
			
			if(time + 1000 <= System.currentTimeMillis())
			{
				time += 1000;
				tps = ticks;
				fps = frames;
				ticks = 0;
				frames = 0;
				
				System.out.println("TPS: " + tps);
				System.out.println("FPS: " + fps);
			}
		}
	}
	
	private void update()
	{
	}
}
