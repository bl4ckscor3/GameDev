package bl4ckscor3.game.gamedev.inventory;

import java.awt.Image;

import bl4ckscor3.game.gamedev.util.TextureManager;
import bl4ckscor3.game.gamedev.world.content.Bridge;
import bl4ckscor3.game.gamedev.world.content.Material;

public enum Item
{
	WOOD(TextureManager.loadTextureFromPath("wood", "items/")),
	AXE(TextureManager.loadTextureFromPath("axe", "items/tools/")),
	BRIDGE(TextureManager.loadTextureFromPath(Material.BRIDGE.getResourceID() + "_horizontal", Bridge.texturePath));
	
	private Image texture;
	
	/**
	 * @param t The texture of this item
	 */
	private Item(Image t)
	{
		texture = t;
	}
	
	/**
	 * @return The texture of this item
	 */
	public Image getTexture()
	{
		return texture;
	}
}
