package bl4ckscor3.game.GameDev.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import bl4ckscor3.game.GameDev.resources.ImageLibrary;
import bl4ckscor3.game.GameDev.world.Map;

public class Game 
{
	public Screen screen;
	public Map map;
	public static GameThread thread;
	public int fps;
	public int ups;
	public static int mousePosX;
	public static int mousePosY;

	public Game()
	{
		map = new Map();
		//starts the game thread
		thread = new GameThread();
	}

	public static void update(){}

	/**
	 * starts the game
	 */
	public static void start()
	{
		thread.start();
	}
}