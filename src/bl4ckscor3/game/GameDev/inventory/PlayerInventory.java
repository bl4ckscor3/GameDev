package bl4ckscor3.game.gamedev.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.game.Screen;

public class PlayerInventory
{
	private final int size = 3;
	private final int completeWidth = (Main.scaleFactor.getWidth() * size + 2) * 5 - 2; //5 slots per row with 2 pixels space inbetween, the last two extra pixels left out
	private final int completeHeight = (Main.scaleFactor.getHeight() * size + 2) * 4 - 2; //4 slots per column with 2 pixels space inbetween, the last two extra pixels left out
	private final int startX = Main.width / 2 - completeWidth / 2; //x position of the top left corner of the inventory
	private final int startY = Main.height / 2 - completeHeight / 2; //y position of the top left corner of the inventory
	private Slot tool;
	private Slot[] inventory = new Slot[20];
	private boolean isOpen = false;
	private int selectedSlot = 0;
	private ItemStack held;

	public PlayerInventory()
	{
		setTool(new Slot(new ItemStack(Item.AXE, 1)));

		for(int i = 0; i < inventory.length; i++)
		{
			inventory[i] = new Slot(new ItemStack(Item.WOOD, Game.r.nextInt(64) + 1)); //can't be 0
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
			ItemStack stack = inventory[i].getItemStack();

			if(stack.getItem() == item && stack.getAmount() <= 128 - amount)
			{
				stack.setAmount(stack.getAmount() + amount);
				return;
			}
			else if(stack.getItem() == null)
			{
				stack.setItem(item);
				stack.setAmount(amount);
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
			g.drawImage(Slot.texture, 16, Main.height - 54, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);

			if(tool.getItemStack().getItem() != null)
				g.drawImage(tool.getItemStack().getItem().getTexture(), 16, Main.height - 54, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
		}
		else
		{
			g.drawImage(Slot.texture, 16, 16, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);

			if(tool.getItemStack().getItem() != null)
				g.drawImage(tool.getItemStack().getItem().getTexture(), 16, 16, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
		}

		if(isOpen)
		{
			int currentSlot = 0;

			if(held != null)
			{
				int x = startX + completeWidth / 2 - (Main.scaleFactor.getWidth() * size) / 2;
				int y = startY - Main.scaleFactor.getHeight() * size;

				g.drawImage(held.getItem().getTexture(),  x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
				g.setColor(Color.WHITE);
				g.drawString("" + held.getAmount(), x + Main.scaleFactor.getWidth() * size - 20, y + Main.scaleFactor.getHeight() * size - 5);
				g.setColor(Color.GRAY);
				g.drawString("" + held.getAmount(), x + Main.scaleFactor.getWidth() * size - 19, y + Main.scaleFactor.getHeight() * size - 4);
			}

			for(int y = startY; y < startY + completeHeight; y += Main.scaleFactor.getHeight() * size + 2)
			{
				for(int x = startX; x < startX + completeWidth; x += Main.scaleFactor.getWidth() * size + 2)
				{
					ItemStack stack = inventory[currentSlot].getItemStack();

					g.drawImage(selectedSlot == currentSlot ? Slot.texture_selected : Slot.texture, x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);

					if(stack != null)
					{
						g.drawImage(stack.getItem().getTexture(), x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
						g.setColor(Color.WHITE);
						g.drawString("" + stack.getAmount(), x + Main.scaleFactor.getWidth() * size - 20, y + Main.scaleFactor.getHeight() * size - 5);
						g.setColor(Color.GRAY);
						g.drawString("" + stack.getAmount(), x + Main.scaleFactor.getWidth() * size - 19, y + Main.scaleFactor.getHeight() * size - 4);
					}

					currentSlot++;
				}	
			}
		}
	}

	/**
	 * Picks up or drops the item in the currently selected slot
	 */
	public void selectCurrentSlot()
	{
		ItemStack inv = inventory[selectedSlot].getItemStack();

		if(held != null)
		{
			if(inv.getItem() == held.getItem())
			{
				if(inv.getAmount() + held.getAmount() <= 128)
				{
					inv.setAmount(inv.getAmount() + held.getAmount());
					held = null;
				}
				else if(inv.getAmount() + held.getAmount() > 128)
				{
					int previousAmount = inv.getAmount();

					inv.setAmount(128);
					held.setAmount(held.getAmount() - (128 - previousAmount));
				}
			}
			else if(inv.getItem() == null)
			{
				inv.setItem(held.getItem());
				inv.setAmount(held.getAmount());
				held = null;
			}
		}
		else if(inv.getItem() != null)
		{
			held = inv.copy();
			inventory[selectedSlot].destroy();
			return;
		}
		
		inventory[selectedSlot].setItemStack(inv);
	}

	/**
	 * Modifies the held stack by 1 each time this method is called
	 * @param key The key that is pressed (up or down)
	 */
	public void modifyStack(int key)
	{
		ItemStack inv = inventory[selectedSlot].getItemStack();

		if(key == KeyEvent.VK_DOWN)
		{
			if(inv != null && held != null && inv.getItem() == held.getItem())
			{
				if(inv.getAmount() + 1 <= 128)
				{
					inv.setAmount(inv.getAmount() + 1);
					held.setAmount(held.getAmount() - 1);

					if(held.getAmount() == 0)
						held = null;
				}
			}
			else if(inv == null)
			{
				inv = new ItemStack(held.getItem(), 1);
				held.setAmount(held.getAmount() - 1);

				if(held.getAmount() == 0)
					held = null;
			}
		}
		else if(key == KeyEvent.VK_UP)
		{
			if(inv != null && held != null && inv.getItem() == held.getItem())
			{
				inv.setAmount(inv.getAmount() - 1);
				held.setAmount(held.getAmount() + 1);

				if(inv.getAmount() == 0)
				{
					inventory[selectedSlot].destroy();
					return;
				}
			}
			else if(held == null && inv.getItem() != null)
			{
				held = new ItemStack(inv.getItem(), 1);
				inv.setAmount(inv.getAmount() - 1);
			}
		}
		
		inventory[selectedSlot].setItemStack(inv);
	}

	/**
	 * Handles pressing the up arrow
	 */
	public void up()
	{
		if(selectedSlot < 5)
			selectedSlot += 15;
		else
			selectedSlot -= 5;
	}

	/**
	 * Handles pressing the left arrow
	 */
	public void left()
	{
		if(selectedSlot % 5 == 0)
			selectedSlot += 4;
		else
			selectedSlot--;
	}

	/**
	 * Handles pressing the down arrow
	 */
	public void down()
	{
		if(selectedSlot > 14)
			selectedSlot -= 15;
		else
			selectedSlot += 5;
	}

	/**
	 * Handles pressing the right arrow
	 */
	public void right()
	{
		if((selectedSlot + 1) % 5 == 0)
			selectedSlot -= 4;
		else
			selectedSlot++;
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
			inventory[slot].getItemStack().setItem(item);
			inventory[slot].getItemStack().setAmount(amount);
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
