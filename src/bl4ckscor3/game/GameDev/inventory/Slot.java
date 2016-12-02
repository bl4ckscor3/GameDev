package bl4ckscor3.game.gamedev.inventory;

import java.awt.Image;

import bl4ckscor3.game.gamedev.util.TextureManager;

public class Slot
{
	public static final Image texture = TextureManager.loadTextureFromPath("slot", "gui/");
	public static final Image texture_selected = TextureManager.loadTextureFromPath("slot_selected", "gui/");
	private ItemStack itemStack;
	
	/**
	 * Creates an empty slot
	 */
	public Slot()
	{
		this(new ItemStack(null, 0));
	}
	
	/**
	 * Sets a Slot with the given ItemStack
	 */
	public Slot(ItemStack stack)
	{
		itemStack = stack;
	}
	
	/**
	 * Sets this Slot's ItemStack
	 * @param inv The ItemStack to set
	 */
	public void setItemStack(ItemStack stack)
	{
		itemStack = stack;
	}
	
	/**
	 * @return The ItemStack of this slot
	 */
	public ItemStack getItemStack()
	{
		return itemStack;
	}
	
	/**
	 * "Destroys" this slot, aka removes the ItemStack associated with it
	 */
	public void destroy()
	{
		itemStack = null;
	}
	
	/**
	 * Copies this slot
	 */
	public Slot copy()
	{
		return new Slot(getItemStack());
	}
}
