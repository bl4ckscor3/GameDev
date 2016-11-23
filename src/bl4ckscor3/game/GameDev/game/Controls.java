package bl4ckscor3.game.gamedev.game;

import bl4ckscor3.game.gamedev.Main;

public class Controls
{
	public static int UP;
	public static int LEFT;
	public static int DOWN;
	public static int RIGHT;
	public static int PLACE;
	public static int DESTROY;
	public static int INVENTORY;
	public static int JESUS;
	
	/**
	 * Sets all controls in the order they appear
	 */
	public static void setControls(int... controls)
	{
		if(controls[0] != 0)
		{
			UP = controls[0];
			Main.config.set("up", "" + UP);
		}
		
		if(controls[1] != 0)
		{
			LEFT = controls[1];
			Main.config.set("left", "" + LEFT);
		}
		
		if(controls[2] != 0)
		{
			DOWN = controls[2];
			Main.config.set("down", "" + DOWN);
		}
		
		if(controls[3] != 0)
		{
			RIGHT = controls[3];
			Main.config.set("right", "" + RIGHT);
		}
		
		if(controls[4] != 0)
		{
			PLACE = controls[4];
			Main.config.set("place", "" + PLACE);
		}
		
		if(controls[5] != 0)
		{
			DESTROY = controls[5];
			Main.config.set("destroy", "" + DESTROY);
		}
		
		if(controls[6] != 0)
		{
			INVENTORY = controls[6];
			Main.config.set("inventory", "" + INVENTORY);
		}
		
		if(controls[7] != 0)
		{
			JESUS = controls[7];
			Main.config.set("jesus", "" + JESUS);
		}
	}
	
	/**
	 * getInts all controls in the order they appear within a single array
	 */
	public static int[] getControls()
	{
		return new int[]{UP, LEFT, DOWN, RIGHT, PLACE, DESTROY, INVENTORY, JESUS};
	}
	
	/**
	 * Loads all controls from the configuration file
	 */
	public static void load()
	{
		setControls(Main.config.getInt("up"),
				Main.config.getInt("left"),
				Main.config.getInt("down"),
				Main.config.getInt("right"),
				Main.config.getInt("place"),
				Main.config.getInt("destroy"),
				Main.config.getInt("inventory"),
				Main.config.getInt("jesus"));
	}
}
