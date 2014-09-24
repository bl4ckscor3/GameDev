package bl4ckscor3.game.GameDev.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotion implements MouseMotionListener
{
	public static int mouseX;
	public static int mouseY;
	
	@Override
	public void mouseDragged(MouseEvent event)
	{
		mouseX = event.getX();
		mouseY = event.getY();
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		mouseX = event.getX();
		mouseY = event.getY();
	}
}
