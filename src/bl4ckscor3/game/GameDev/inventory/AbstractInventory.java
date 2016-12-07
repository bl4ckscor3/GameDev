package bl4ckscor3.game.gamedev.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import bl4ckscor3.game.gamedev.Main;

public abstract class AbstractInventory
{
	public static final ArrayList<AbstractInventory> inventories = new ArrayList<AbstractInventory>();
	public static AbstractInventory openInventory = null; //the currently open inventory, null if none is open
	protected final int size = 3;
	protected int slotsPerRow;
	protected int slotsPerColumn;
	protected int slots;
	protected int completeWidth; //the width of slotsPerRow slots per row with 2 pixels space inbetween, the last two extra pixels left out
	protected int completeHeight; //the width of slotsPerColumn slots per column with 2 pixels space inbetween, the last two extra pixels left out
	protected int startX; //x position of the top left corner of the inventory
	protected int startY; //y position of the top left corner of the inventory
	protected Slot[] inventory;
	protected boolean isOpen = false; //if this inventory is open
	protected int selectedSlot = 0;
	protected ItemStack held;
	protected Font amountFont = new Font("Calibri", Font.BOLD, 18);
	
	/**
	 * Sets up this inventory's bounds
	 * Note: If you want your inventory to have 2 or more seperated parts, you need to override the render method and render the inventory yourself.
	 * 		 Alternatively you can call super on it and add your custom render. The size in this constructor is only for the main inventory, that
	 * 		 should show in the middle of the screen
	 * @param slotsPerRow The amount of slots that should be in one row
	 * @param slotsPerColumn The amount of slots that should be in one column
	 * @param extra The amount of extra slots, that don't belong into the main inventory
	 */
	public AbstractInventory(int spr, int spc , int extra)
	{
		slotsPerRow = spr;
		slotsPerColumn = spc;
		slots = slotsPerRow * slotsPerColumn;
		completeWidth = (Main.scaleFactor.getWidth() * size + 2) * spr - 2;
		completeHeight = (Main.scaleFactor.getHeight() * size + 2) * spc - 2;
		startX = Main.width / 2 - completeWidth / 2;
		startY = Main.height / 2 - completeHeight / 2;
		inventory = new Slot[spr * spc + extra];
		inventories.add(this);
	}
	
	/**
	 * Renders the inventory's slots
	 * @param g The Graphics to render with
	 */
	public void render(Graphics g)
	{
		if(isOpen)
		{
			int currentSlot = 0;

			if(held != null)
			{
				int x = startX + completeWidth / 2 - (Main.scaleFactor.getWidth() * size) / 2;
				int y = startY - Main.scaleFactor.getHeight() * size;

				g.setFont(amountFont);
				g.drawImage(held.getItem().getTexture(),  x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
				g.setColor(Color.GRAY);
				g.drawString("" + held.getAmount(), x + Main.scaleFactor.getWidth() * size - 29, y + Main.scaleFactor.getHeight() * size - 4);
				g.setColor(Color.WHITE);
				g.drawString("" + held.getAmount(), x + Main.scaleFactor.getWidth() * size - 30, y + Main.scaleFactor.getHeight() * size - 5);
			}

			int xx = 0;
			int yy = 0;
			
			for(int y = startY; y < startY + completeHeight; y += Main.scaleFactor.getHeight() * size + 2)
			{
				if(yy == slotsPerColumn)
					break;
				
				for(int x = startX; x < startX + completeWidth; x += Main.scaleFactor.getWidth() * size + 2)
				{
					if(xx == slotsPerRow)
						break;
					
					ItemStack stack = inventory[currentSlot].getItemStack();

					g.drawImage(selectedSlot == currentSlot ? Slot.texture_selected : Slot.texture, x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);

					if(stack != null)
					{
						g.setFont(amountFont);
						g.drawImage(stack.getItem().getTexture(), x, y, Main.scaleFactor.getWidth() * size, Main.scaleFactor.getHeight() * size, null);
						g.setColor(Color.GRAY);
						g.drawString("" + stack.getAmount(), x + Main.scaleFactor.getWidth() * size - 29, y + Main.scaleFactor.getHeight() * size - 4);
						g.setColor(Color.WHITE);
						g.drawString("" + stack.getAmount(), x + Main.scaleFactor.getWidth() * size - 30, y + Main.scaleFactor.getHeight() * size - 5);
					}

					currentSlot++;
					xx++;
				}
				
				xx = 0;
				yy++;
			}
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
	 * Picks up or drops the item in the currently selected slot
	 */
	public void selectCurrentSlot()
	{
		ItemStack inv = inventory[selectedSlot].getItemStack();

		if(held != null)
		{
			if(inv == null)
			{
				inv = new ItemStack(held.getItem(), held.getAmount());
				held = null;
			}
			else if(inv.getItem() == held.getItem())
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
	 * @return This inventory
	 */
	public final Slot[] getInventory()
	{
		return inventory;
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
	 * @return true if any inventory is open, false otherwise
	 */
	public static final boolean isInventoryOpen()
	{
		return openInventory != null;
	}
	
	/**
	 * @return true if this inventory is open, false otherwise
	 */
	public final boolean isOpen()
	{
		return isOpen;
	}
	
	/**
	 * Opens this inventory
	 */
	public final void open()
	{
		if(isInventoryOpen())
			return;
		
		isOpen = true;
		openInventory = this;
	}

	/**
	 * Closes this inventory
	 */
	public final void close()
	{
		if(!isInventoryOpen())
			return;

		isOpen = false;
		openInventory = null;
	}
	
	//vvvvvvvv navigation within inventory vvvvvvvv
	
	/**
	 * Handles pressing the up arrow
	 */
	public abstract void up();
	
	/**
	 * Handles pressing the left arrow
	 */
	public abstract void left();
	
	/**
	 * Handles pressing the down arrow
	 */
	public abstract void down();
	
	/**
	 * Handles pressing the right arrow
	 */
	public abstract void right();
}
