package bl4ckscor3.game.gamedev.inventory;

import java.awt.Color;
import java.awt.Graphics;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Screen;

public class PlayerInventory
{
	private Slot tool;
	private Slot[] inventory = new Slot[20];
	private boolean isOpen = false;
	private final int size = 3;
	private final int completeWidth = (Main.scaleFactor.getWidth() * size + 2) * 5 - 2; //5 rows with 2 pixels space inbetween
	private final int completeHeight = (Main.scaleFactor.getHeight() * size + 2) * 4 - 2; //4 columns with 2 pixels space inbetween
	private final int startX = Main.width / 2 - completeWidth / 2; //x position of the top left corner of the inventory
	private final int startY = Main.height / 2 - completeHeight / 2; //y position of the top left corner of the inventory
	
	public PlayerInventory()
	{
		setTool(new Slot(Item.AXE));
		
		for(int i = 0; i < inventory.length; i++)
		{
			inventory[i] = new Slot();
		}
	}

	/**
	 * Adds the given amount of the given item to the inventory
	 * @param item The item to add
	 * @param amount How many of the item to add
	 */
	public void addItem(Item item, int amount)
	{
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i].getItem() == item && inventory[i].getAmount() <= 128 - amount)
			{
				inventory[i].setAmount(inventory[i].getAmount() + amount);
				return;
			}
			else if(inventory[i].getItem() == null)
			{
				inventory[i].setItem(item);
				inventory[i].setAmount(amount);
				return;
			}
		}
	}
	
	/**
	 * Renders the inventory's slots
	 * @param g The Graphics to render with
	 */
	public void render(Graphics g)
	{
		if(Screen.displayDebug)
		{
			g.drawImage(Slot.texture, 16, Main.height - 38, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);

			if(tool.getItem() != null)
				g.drawImage(tool.getItem().getTexture(), 16, Main.height - 38, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
		}
		else
		{
			g.drawImage(Slot.texture, 16, 16, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);

			if(tool.getItem() != null)
				g.drawImage(tool.getItem().getTexture(), 16, 16, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
		}
		
		if(isOpen)
		{
			int currentSlot = 0;
			
			for(int y = startY; y < startY + completeHeight; y += Main.scaleFactor.getHeight() * size + 2)
			{
				for(int x = startX; x < startX + completeWidth; x += Main.scaleFactor.getWidth() * size + 2)
				{
					Item i = inventory[currentSlot].getItem();
					
					g.drawImage(Slot.texture, x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
					
					if(i != null)
					{
						g.drawImage(i.getTexture(), x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
						g.setColor(Color.WHITE);
						g.drawString("" + inventory[currentSlot].getAmount(), x + Main.scaleFactor.getWidth() * size - 20, y + Main.scaleFactor.getHeight() * size - 5);
						g.setColor(Color.GRAY);
						g.drawString("" + inventory[currentSlot].getAmount(), x + Main.scaleFactor.getWidth() * size - 19, y + Main.scaleFactor.getHeight() * size - 4);
					}
					
					currentSlot++;
				}	
			}
			
		}
	}
	
	/**
	 * @return The tool slot
	 */
	public Slot getTool()
	{
		return tool;
	}

	/**
	 * @return The player's inventory
	 */
	public Slot[] getInventory()
	{
		return inventory;
	}
	
	/**
	 * Sets the tool slot
	 * @param t The slot to set
	 */
	public void setTool(Slot t)
	{
		tool = t;
	}
	
	/**
	 * Sets the Slot at the given position
	 * @param slot The slot to set
	 * @param item The item to set into the slot
	 * @param amount The amount of the item to set
	 */
	public void setSlot(int slot, Item item, int amount)
	{
		if(slot < inventory.length && slot >= 0)
		{
			inventory[slot].setItem(item);
			inventory[slot].setAmount(amount);
		}
	}
	
	/**
	 * @return true if this inventory is open, false otherwise
	 */
	public boolean isOpen()
	{
		return isOpen;
	}
	
	/**
	 * Opens this inventory
	 */
	public void open()
	{
		isOpen = true;
	}

	/**
	 * Closes this inventory
	 */
	public void close()
	{
		isOpen = false;
	}
}
