package bl4ckscor3.game.GameDev.content;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.game.GameDev.manager.TextureManager;

public class Tile
{
	public List<String> tiles = new ArrayList<String>();
	private Material material;
	public Image texture;
	
	public Tile(Material mat)
	{
		material = mat;
		texture = TextureManager.loadTexture(material.getRandomResourceID(), 4);
	}
}
