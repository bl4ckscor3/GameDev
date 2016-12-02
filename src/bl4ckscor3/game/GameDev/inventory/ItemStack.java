package bl4ckscor3.game.gamedev.inventory;

public class ItemStack
{
	public Item item;
	public int amount;
	
	/**
	 * Creates an ItemStack with a specific amount of one item
	 * @param i The item
	 * @param a The amount, cannot be less than 1 or more than 128
	 */
	public ItemStack(Item i, int a)
	{
		item = i;
		amount = a > 0 ? (a < 128 ? a : 128) : 0;
		
		if(amount == 0)
			destroy();
	}
	
	/**
	 * @return The item in this ItemStack
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
	 * Sets the item of this ItemStack
	 * @param i The item to set, if null this ItemStack will be destroyed
	 */
	public void setItem(Item i)
	{
		item = i;
		
		if(item == null)
			destroy();
	}
	
	/**
	 * Sets the amount of the item in this slot
	 * @param a The amount, cannot be less than 1 or more than 128 and if 0 this ItemStack will be destroyed
	 */
	public void setAmount(int a)
	{
		amount = a > 0 ? (a < 128 ? a : 128) : 0;
		
		if(amount == 0)
			destroy();
	}
	
	/**
	 * "Destroys" this ItemStack, aka removes the item and amount associated with it
	 */
	public void destroy()
	{
		item = null;
		amount = 0;
	}
	
	/**
	 * Copies this ItemStack
	 */
	public ItemStack copy()
	{
		return new ItemStack(getItem(), getAmount());
	}
}
