package bl4ckscor3.game.GameDev.world;

import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import bl4ckscor3.game.GameDev.entity.Entity;
import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.util.Utilities;

public class Map
{
	public List<Chunk> loadedChunks = new CopyOnWriteArrayList<Chunk>();
	public List<Entity> loadedEntities = new CopyOnWriteArrayList<Entity>();
	private final int chunkAmountX = 5;
	private final int chunkAmountY = 5;
	
	public Map()
	{
		checkChunks();
	}
	
	/**
	 * Renders all the chunks and entities
	 */
	public void render(Graphics g)
	{
		for(Chunk c : loadedChunks)
		{
			c.render(g);
		}
		
		for(Entity e : loadedEntities)
		{
			e.render(g);
		}
	}
	
	/**
	 * Gets called every game tick
	 */
	public void tick()
	{
		checkChunks();
		
		for(Chunk c : loadedChunks)
		{
			c.tick();
		}
		
		for(Entity e : loadedEntities)
		{
			e.tick();
		}
	}
	
	/**
	 * Loading and unloading the chunks
	 */
	public void checkChunks()
	{
		//which chunk the player is currently in
		int playerChunkX = Utilities.floor(Game.player.position.x / (double) Chunk.chunkSizeX);
		int playerChunkY = Utilities.floor(Game.player.position.y / (double) Chunk.chunkSizeY);
	
		unloadChunks(playerChunkX, playerChunkY);
		loadChunks(playerChunkX, playerChunkY);
	}
	
	/**
	 * Loads all the chunk withing the player's range
	 * @param playerChunkX - The X-Coord of the chunk the player is currently in
	 * @param playerChunkY - The Y-Coord of the chunk the player is currently in
	 */
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
	
	/**
	 * Unloads all the chunks outside of the player's range
	 * @param playerChunkX - The X-Coord of the chunk the player is currently in
	 * @param playerChunkY - The Y-Coord of the chunk the player is currently in
	 */
	private void unloadChunks(int playerChunkX, int playerChunkY)
	{
		for(Chunk c : loadedChunks)
		{
			//is the chunk too far away?
			if(c.chunkX > playerChunkX + (chunkAmountX - 1) / 2 || c.chunkX < playerChunkX - (chunkAmountX - 1) / 2 || c.chunkY > playerChunkY + (chunkAmountY - 1) / 2 || c.chunkY < playerChunkY - (chunkAmountY - 1) / 2)
				loadedChunks.remove(c);
		}
	}
	
	/**
	 * Spawns an entity at a given position
	 * @param e - The entity to spawn
	 * @param x - The x position the entity spawns at
	 * @param y - The y position the entity spawns at
	 */
	public void spawnEntity(Entity e, int x, int y)
	{
		e.position.set(x, y);
		loadedEntities.add(e);
	}
}
