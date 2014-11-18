package bl4ckscor3.game.GameDev.world;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import bl4ckscor3.game.GameDev.entity.Player;
import bl4ckscor3.game.GameDev.util.Utilities;

public class Map
{
	public List<Chunk> loadedChunks = new CopyOnWriteArrayList<Chunk>();
	private final int chunkAmountX = 5;
	private final int chunkAmountY = 5;
	public Player player;
	
	public Map(Player p)
	{
		player = p;
		checkChunks();
	}
	
	/**
	 * loading/unloading chunks
	 */
	public void checkChunks()
	{
		//which chunk the player is currently in
		int playerChunkX = Utilities.floor(player.posX / (double) Chunk.chunkSizeX);
		int playerChunkY = Utilities.floor(player.posY / (double) Chunk.chunkSizeY);
	
		unloadChunks(playerChunkX, playerChunkY);
		loadChunks(playerChunkX, playerChunkY);
	}
	
	public void loadChunks(int playerChunkX, int playerChunkY)
	{
		//checking each row from the chunk on the left to the chunk on the right (5 chunks)
		for(int x = playerChunkX - (chunkAmountX - 1) / 2; x <= playerChunkX + (chunkAmountX - 1) / 2; x++)
		{
			for(int y = playerChunkY - (chunkAmountY - 1) / 2; y <= playerChunkY + (chunkAmountY - 1) / 2; y++)
			{
				//loaded chunk at this position?
				if(!loadedChunks.contains(new Chunk(x, y)))
				{
					Chunk c = new Chunk(x, y);
					
					c.populate();
					loadedChunks.add(c);
				}
			}
		}
	}
	
	private void unloadChunks(int playerChunkX, int playerChunkY)
	{
		for(Chunk c : loadedChunks)
		{
			//is the chunk too far away?
			if(c.chunkX > playerChunkX + (chunkAmountX - 1) / 2 || c.chunkX < playerChunkX - (chunkAmountX - 1) / 2 || c.chunkY > playerChunkY + (chunkAmountY - 1) / 2 || c.chunkY < playerChunkY - (chunkAmountY - 1) / 2)
				loadedChunks.remove(c);
		}
	}
}
