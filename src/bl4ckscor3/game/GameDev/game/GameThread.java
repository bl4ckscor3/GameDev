package bl4ckscor3.game.gamedev.game;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.menu.Menu;
import bl4ckscor3.game.gamedev.util.DebugUI;

public class GameThread extends Thread implements Runnable
{
	/** The seed to generate the map from*/
	private static int seed;
	
	@Override
	public void run()
	{
		int tick = 0;
		int fps = 0;
		int targetTps = 60;
		double fpsTimer = System.currentTimeMillis();
		double secondsPerTick = 1.0D / targetTps; //how long to wait between each update
		double nanosecondsPerTick = secondsPerTick * 1_000_000_000.0D;
		double then = System.nanoTime();
		double now = System.nanoTime();
		double unprocessed = 0;
		
		while(true)
		{
			now = System.nanoTime();
			unprocessed += (now - then) / nanosecondsPerTick;
			then = now;
			
			while(unprocessed >= 1)
			{
				if(!Menu.isOpen())
					Game.tick(tick);
				
				tick++;
				unprocessed--;
			}
			
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			Main.screen.repaint();
			fps++;
			
			if(System.currentTimeMillis() - fpsTimer >= 1000)
			{
				DebugUI.setFPS(fps);
				fps = 0;
				tick = 0;
				fpsTimer += 1000;
			}
		}
	}

	/**
	 * Sets the seed to be used for the map
	 * @param s The seed to use
	 */
	public static void setSeed(int s)
	{
		seed = s;
		Main.config.set("seed", "" + s);
	}
	
	/**
	 * @return The seed of the map
	 */
	public static int getSeed()
	{
		return seed;
	}
}
