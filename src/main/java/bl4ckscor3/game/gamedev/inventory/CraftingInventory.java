package bl4ckscor3.game.gamedev.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.content.Recipe;

public class CraftingInventory extends AbstractInventory
{
	/** Contains all recipes*/
	public static final ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	/** Determines which recipe out of the array should be in the top left slot of the inventory*/
	private int currentTopLeftSlot = 0;
	/** Determines the slot currently being rendered*/
	private int currentSlot = 0;
	/** Holds the first slot that does not contain a recipe*/
	private int firstNull = Integer.MAX_VALUE;
	
	public CraftingInventory(int spr, int spc, int extra)
	{
		super(spr, spc, recipes.size() - (spr * spc)); //extra = recipes that don't fit on the page

		for(int i = 0; i < inventory.length; i++)
		{
			inventory[i] = new Slot(recipes.get(i).getResult());
		}
	}

	/**
	 * Sets up recipes, new ones are to be added here, too
	 * Note: ALWAYS call this method BEFORE constructing the class
	 */
	public static void setupRecipes()
	{
		recipes.add(new Recipe(new ItemStack(Item.BRIDGE, 4), new ItemStack(Item.WOOD, 8))); //4 bridges out of 8 pieces of wood
	}

	/**
	 * Renders the inventory's slots
	 * @param g The Graphics to render with
	 */
	public void render(Graphics g)
	{
		if(isOpen)
		{
			//shitty hack because the navcode doesn't work for every amount of recipes
			if(selectedSlot >= firstNull)
				selectedSlot = firstNull - 1;
			else if(selectedSlot < 0)
				selectedSlot = 0;
			
			ItemStack[] ingredients = recipes.get(selectedSlot).getIngredients();
			int x = startX;
			int y = startY - Main.scaleFactor.getHeight() * size - 2;
			
			for(int i = 0; i < ingredients.length; i++)
			{
				g.drawImage(ingredients[i].getItem().getTexture(),  x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
				g.setColor(Color.GRAY);
				g.drawString("" + ingredients[i].getAmount(), x + Main.scaleFactor.getWidth() * size - 29, y + Main.scaleFactor.getHeight() * size - 4);
				g.setColor(Color.WHITE);
				g.drawString("" + ingredients[i].getAmount(), x + Main.scaleFactor.getWidth() * size - 30, y + Main.scaleFactor.getHeight() * size - 5);
				x += Main.scaleFactor.getWidth() * size + 2;
			}
			
			int xx = 0;
			int yy = 0;

			currentSlot = currentTopLeftSlot;
			
			outer:
			for(y = startY; y < startY + completeHeight; y += Main.scaleFactor.getHeight() * size + 2)
			{
				if(yy == slotsPerColumn)
					break;

				for(x = startX; x < startX + completeWidth; x += Main.scaleFactor.getWidth() * size + 2)
				{
					if(xx == slotsPerRow)
						break;

					try
					{
						ItemStack stack = inventory[currentSlot].getItemStack();

						g.drawImage(selectedSlot == currentSlot ? Slot.texture_selected : Slot.texture, x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
						
						g.setFont(amountFont);
						g.drawImage(stack.getItem().getTexture(), x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
						g.setColor(Color.GRAY);
						g.drawString("" + stack.getAmount(), x + Main.scaleFactor.getWidth() * size - 29, y + Main.scaleFactor.getHeight() * size - 4);
						g.setColor(Color.WHITE);
						g.drawString("" + stack.getAmount(), x + Main.scaleFactor.getWidth() * size - 30, y + Main.scaleFactor.getHeight() * size - 5);
						currentSlot++;
						xx++;
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						firstNull = currentSlot;
						break outer;
					}
				}

				xx = 0;
				yy++;
			}
		}
	}

	@Override
	public void selectCurrentSlot()
	{
		
	}
	
	@Override
	public void up()
	{
		if(selectedSlot >= currentTopLeftSlot && selectedSlot < currentTopLeftSlot + slotsPerRow && currentTopLeftSlot - slotsPerRow >= 0) //top row
			currentTopLeftSlot -= slotsPerRow;

		if(selectedSlot - slotsPerRow >= 0)
			selectedSlot -= slotsPerRow;
	}

	@Override
	public void left()
	{
		if(selectedSlot % slotsPerRow == 0)
		{
			if(selectedSlot + slotsPerRow - 1 < inventory.length)
				selectedSlot += slotsPerRow - 1;
			else
				selectedSlot += slotsPerRow - (slots - ((inventory.length - currentTopLeftSlot) % slots) + 1); //slotsPerRow - missing slots to fill the complete rectangle
		}
		else
			selectedSlot--;
	}

	@Override
	public void down()
	{
		if(selectedSlot < currentTopLeftSlot + slotsPerRow * slotsPerColumn && selectedSlot >= (currentTopLeftSlot + slotsPerRow * slotsPerColumn) - slotsPerRow) //bottom row
		{
			if(currentTopLeftSlot + slots < inventory.length - 1)
			{
				currentTopLeftSlot += slotsPerRow;

				if(selectedSlot + slotsPerRow < inventory.length)
					selectedSlot += slotsPerRow;
			}
		}
		else
		{
			if(selectedSlot + slotsPerRow < inventory.length)
				selectedSlot += slotsPerRow;
		}
	}

	@Override
	public void right()
	{
		if((selectedSlot + 1) % slotsPerRow == 0)
			selectedSlot -= slotsPerRow - 1;
		else
		{
			if(selectedSlot + 1 < inventory.length)
				selectedSlot++;
			else
				selectedSlot -= slotsPerRow - (slots - ((inventory.length - currentTopLeftSlot) % slots) + 1);
		}
	}
}
