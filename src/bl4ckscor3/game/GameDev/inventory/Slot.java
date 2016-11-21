package bl4ckscor3.game.gamedev.inventory;

import java.awt.Image;

import bl4ckscor3.game.gamedev.util.TextureManager;

public class Slot
{
	public static final Image texture = TextureManager.loadTextureFromPath("slot", "gui/");
	private Item item;
	private int amount;
	
	/**
	 * Creates a slot without an item
	 */
	public Slot()
	{
		this(null);
	}
	
	/**
	 * Creates a slot with 1 item
	 * @param i The item this slot contains
	 */
	public Slot(Item i)
	{
		this(i, 1);
	}
	
	/**
	 * Creates a slot with a specific amount of one item
	 * @param i The item
	 * @param a The amount, cannot be less than 1 or more than 128
	 */
	public Slot(Item i, int a)
	{
		item = i;
		amount = a > 1 ? (a < 128 ? a : 128) : 1;
	}
	
	/**
	 * @return The item in this slot
	 */
	public Item getItem()
	{
		return item;
	}
	
	/**
	 * @return The amount
	 */
	public int getAmount()
	{
		return amount;
	}
	
	/**
	 * Sets the item in this slot
	 * @param i The item to set
	 */
	public void setItem(Item i)
	{
		item = i;
	}
	
	/**
	 * Sets the amount of the item in this slot
	 * @param a The amount, cannot be less than 1 or more than 128
	 */
	public void setAmount(int a)
	{
		amount = a > 1 ? (a < 128 ? a : 128) : 1;
	}
	
	/**
	 * "Destroys" this slot, aka removes the item and amount associated with it
	 */
	public void destroy()
	{
		item = null;
		amount = 0;
	}
}
