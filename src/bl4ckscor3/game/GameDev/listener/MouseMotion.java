package bl4ckscor3.game.gamedev.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotion implements MouseMotionListener
{
	public static int mouseX;
	public static int mouseY;
	
	@Override
	public void mouseDragged(MouseEvent event){}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		//setting the x and y position of the mouse cursor
		mouseX = event.getX();
		mouseY = event.getY();
	}
}
