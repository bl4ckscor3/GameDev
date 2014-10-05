package bl4ckscor3.game.GameDev.world;

import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.game.GameDev.entity.Player;

public class Map
{
	private List<Chunk> loadedChunks = new ArrayList<Chunk>();
	private final int chunkAmountX = 5;
	private final int chunkAmountY = 5;
	private Player player;
	
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
		int playerChunkX = player.posX / Chunk.chunkSizeX;
		int playerChunkY = player.posY / Chunk.chunkSizeY;
	
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
					System.out.println("load chunk");
					Chunk c = new Chunk(x, y);
					
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
			if(c.chunkX > playerChunkX + (chunkAmountX - 1) / 2 && c.chunkX < playerChunkX - (chunkAmountX - 1) / 2 || c.chunkY > playerChunkY + (chunkAmountY - 1) / 2 && c.chunkY < playerChunkY - (chunkAmountY - 1) / 2)
			{
				System.out.println("unload chunk");
			}
		}
	}
}
