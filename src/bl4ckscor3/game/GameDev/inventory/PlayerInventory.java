package bl4ckscor3.game.gamedev.inventory;

import java.awt.Graphics;

import bl4ckscor3.game.gamedev.Main;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.game.Screen;

public class PlayerInventory extends AbstractInventory
{
	/** The slot in which the tool is in*/
	public Slot tool;

	public PlayerInventory()
	{
		super(5, 4, 0);

		tool = new Slot(new ItemStack(Item.AXE, 1));

		for(int i = 0; i < inventory.length; i++)
		{
			inventory[i] = new Slot(new ItemStack(Item.WOOD, Game.r.nextInt(64) + 1)); //+ 1 => can't be 0
		}
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);
		
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
	}

	@Override
	public void up()
	{
		if(selectedSlot < 5)
			selectedSlot += 15;
		else
			selectedSlot -= 5;
	}

	@Override
	public void left()
	{
		if(selectedSlot % 5 == 0)
			selectedSlot += 4;
		else
			selectedSlot--;
	}

	@Override
	public void down()
	{
		if(selectedSlot > 14)
			selectedSlot -= 15;
		else
			selectedSlot += 5;
	}

	@Override
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
	 * Sets the tool slot
	 * @param t The slot to set
	 */
	public void setTool(Slot t)
	{
		tool = t;
	}
}
