package bl4ckscor3.game.gamedev.game.content;

import bl4ckscor3.game.gamedev.inventory.Item;
import bl4ckscor3.game.gamedev.inventory.ItemStack;

public class Recipe
{
	private ItemStack result;
	private ItemStack[] ingredients;

	/**
	 * Creates a new recipe
	 * @param r The result of this recipe, only one
	 * @param i The ingredient needed to create the result
	 */
	public Recipe(Item r, ItemStack... i)
	{
		this(new ItemStack(r, 1), i);
	}
	
	/**
	 * Creates a new recipe
	 * @param r The result of this recipe
	 * @param i The ingredient needed to create the result
	 */
	public Recipe(ItemStack r, ItemStack... i)
	{
		result = r;
		ingredients = i;
	}
	
	/**
	 * @return The outcome of the recipe if all ingredients are available
	 */
	public ItemStack getResult()
	{
		return result;
	}
	
	/**
	 * @return All ingredients used to create this recipe's result
	 */
	public ItemStack[] getIngredients()
	{
		return ingredients;
	}
}
