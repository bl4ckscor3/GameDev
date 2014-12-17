package bl4ckscor3.game.GameDev.game;

import bl4ckscor3.game.GameDev.core.Main;

public class GameThread extends Thread implements Runnable
{
	private int tps = 0;
	public static int fps = 0;
	
	@Override
	public void run()
	{
		int ticksPerSecond = 50;
		int framesPerSecond = 100;
		int loops = 0; //amount of loops it has done before rendering
		int ticks = 0; //variable to update TICKS_PER_SECOND
		int frames = 0; //variable to update FRAMES_PER_SECOND
		long gameSkipTicks = 1000 / ticksPerSecond; //amount of time the game waits until it does the next update
		long frameSkipTicks = 1000 / framesPerSecond;
		long maxFrameSkips = 5; //if the game runs 5 updates at the same time without updating the screen it stops updating to prevent lag
		long nextGameTick = System.currentTimeMillis(); //next tick to update
		long nextFrameTick = System.currentTimeMillis();
		long time = System.currentTimeMillis();
		
		while(true)
		{
			loops = 0;
			
			while(System.currentTimeMillis() > nextGameTick && loops < maxFrameSkips)
			{
				Game.tick(ticks);
				nextGameTick += gameSkipTicks; //makes sure to wait 16ms before updating again
				ticks++;
				loops++;
			}
			
			if(System.currentTimeMillis() > nextFrameTick)
			{
				nextFrameTick += frameSkipTicks;
				Main.screen.repaint();
				frames++;
			}
			
			if(time + 1000 <= System.currentTimeMillis())
			{
				time += 1000;
				tps = ticks;
				fps = frames;
				ticks = 0;
				frames = 0;
			}
		}
	}
}
