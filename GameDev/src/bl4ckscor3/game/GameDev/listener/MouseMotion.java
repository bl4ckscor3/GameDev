package bl4ckscor3.game.GameDev.listener;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import bl4ckscor3.game.GameDev.core.Main;
import bl4ckscor3.game.GameDev.menu.Menu;

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
		//setting the x and y position of the mouse cursor
		mouseX = event.getX();
		mouseY = event.getY();

		if(Menu.isOpen())
		{
			//the with and height of the string
			Rectangle[] optionBounds = Menu.optionBounds;
			//the location of the string
			Point[] optionLocations = Menu.optionLocations;
			int k = 0;
			
			for(int i = 0; i < optionBounds.length; i++)
			{
				optionBounds[i].translate(optionLocations[i].x, optionLocations[i].y + (Main.width * Main.height <= 1440000 ? 20 : 0));
				
				if(optionBounds[i].contains(event.getPoint()))
				{
					Menu.setSelectedOption(k);
					break;
				}
				else
					Menu.setSelectedOption(-1);
			
				k++;
			}
		}
	}
}
