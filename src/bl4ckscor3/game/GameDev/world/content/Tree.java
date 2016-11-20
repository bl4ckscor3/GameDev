package bl4ckscor3.game.gamedev.world.content;

import java.awt.Graphics;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.util.Position;
import bl4ckscor3.game.gamedev.world.Chunk;

public class Tree extends PlaceableObject
{
	public Tree(Material mat, Chunk c, Position pos)
	{
		super(mat, c, pos);
	}

	@Override
	public void render(Graphics g)
	{
		render(g, 0, 0);
	}
	
	@Override
	public void render(Graphics g, int x, int y)
	{
		render(g, x, y, Main.scaleFactor.getWidth() * 2, Main.scaleFactor.getHeight() * 2);
	}
	
	@Override
	public void render(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage(texture, x, y - Main.scaleFactor.getHeight(), width, height, null);
	}
}
