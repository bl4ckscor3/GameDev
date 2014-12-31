package bl4ckscor3.game.GameDev.listener;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.menu.pause.PauseMenu;

public class MouseMotion implements MouseMotionListener
{
	public static int mouseX;
	public static int mouseY;
	
	@Override
	public void mouseDragged(MouseEvent event)
	{
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		mouseX = event.getX();
		mouseY = event.getY();

		if(Game.isPaused())
		{
			Rectangle[] optionBounds = PauseMenu.optionBounds;
			Point[] optionLocations = PauseMenu.optionLocations;
			int k = 0;
			
			for(int i = 0; i < optionBounds.length; i++)
			{
				optionBounds[i].translate(optionLocations[i].x, optionLocations[i].y + (Main.width * Main.height <= 1440000 ? 20 : 0));
				
				if(optionBounds[i].contains(event.getPoint()))
				{
					PauseMenu.setSelectedOption(k);
					break;
				}
				else
					PauseMenu.setSelectedOption(-1);
			
				k++;
			}
		}
	}
}
