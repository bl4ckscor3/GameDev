package bl4ckscor3.game.gamedev.world;

import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import bl4ckscor3.game.gamedev.entity.Entity;
import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.util.Direction;
import bl4ckscor3.game.gamedev.util.Position;
import bl4ckscor3.game.gamedev.util.Utilities;

public class Map
{
	public List<Chunk> loadedChunks = new CopyOnWriteArrayList<Chunk>();
	public List<Entity> loadedEntities = new CopyOnWriteArrayList<Entity>();
	private final int chunkAmountX = 5;
	private final int chunkAmountY = 5;
	private final int seed;
	
	public Map(int s)
	{
		seed = s;
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
	 * @param tick The current tick
	 */
	public void tick(int tick)
	{
		checkChunks();
		
		for(Chunk c : loadedChunks)
		{
			c.tick(tick);
		}
		
		for(Entity e : loadedEntities)
		{
			e.tick(tick);
		}
	}
	
	/**
	 * Loads and unloads the chunks
	 */
	public void checkChunks()
	{
		//which chunk the player is currently in
		int playerChunkX = Utilities.floor(Game.player.position.x / (double)Chunk.chunkSizeX);
		int playerChunkY = Utilities.floor(Game.player.position.y / (double)Chunk.chunkSizeY);
	
		unloadChunks(playerChunkX, playerChunkY);
		loadChunks(playerChunkX, playerChunkY);
	}
	
	/**
	 * Loads all the chunk within the player's range
	 * @param playerChunkX The x coordinate of the chunk the player is currently in
	 * @param playerChunkY The y corrdinate of the chunk the player is currently in
	 */
	public void loadChunks(int playerChunkX, int playerChunkY)
	{
		//checking each row from the chunk on the left to the chunk on the right (5 chunks)
		for(int x = playerChunkX - (chunkAmountX - 1) / 2; x <= playerChunkX + (chunkAmountX - 1) / 2; x++)
		{
			for(int y = playerChunkY - (chunkAmountY - 1) / 2; y <= playerChunkY + (chunkAmountY - 1) / 2; y++)
			{
				Chunk c = new Chunk(x, y);
				
				//loaded chunk at this position?
				if(!loadedChunks.contains(c))
				{
					c.populate(seed);
					loadedChunks.add(c);
				}
			}
		}
	}
	
	/**
	 * Unloads all the chunks outside of the player's range
	 * @param playerChunkX The x coordinate of the chunk the player is currently in
	 * @param playerChunkY The y corrdinate of the chunk the player is currently in
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
	 * @param e The entity to spawn
	 * @param x The x position the entity should spawn at
	 * @param y The y position the entity should spawn at
	 */
	public void spawnEntity(Entity e, int x, int y)
	{
		e.position.set(x, y);
		loadedEntities.add(e);
	}
	
	/**
	 * Gets the entity's position within a chunk
	 * @param e The entity to check the position of
	 * @return The entity's position within the chunk
	 */
	public Position getChunkPosition(Entity e)
	{
		return getChunkPosition(e, 0, 0);
	}
	
	
	/**
	 * Gets the entity's position within a chunk
	 * @param e The entity to check the position of
	 * @param dir The Direction in which to translate the entity's position by one
	 * @return The entity's position within the chunk
	 */
	public Position getChunkPosition(Entity e, Direction dir)
	{
		int x = 0;
		int y = 0;
		
		switch(dir)
		{
			case UP:
				y = -1;
				break;
			case LEFT:
				x = -1;
				break;
			case DOWN:
				y = 1;
				break;
			case RIGHT:
				x = 1;
				break;
		}
		
		return getChunkPosition(e, x, y);
	}
	
	/**
	 * Gets the entity's position within a chunk
	 * @param e The entity to check the position of
	 * @param x The x-axis modificator of this position
	 * @param y The y-axis modificator of this position
	 * @return The entity's position within the chunk
	 */
	public Position getChunkPosition(Entity e, int x, int y)
	{
		//modified positions
		int xPos = e.position.x + x;
		int yPos = e.position.y + y;
		
		//while xPos is negative, add chunkSizeX to it to transform it into a position within a chunk
		while(xPos < 0)
			xPos += Chunk.chunkSizeX;

		//while xPos is above chunkSizeX - 1, subtract chunkSizeX from it to transform it into a position within a chunk
		while(xPos > (Chunk.chunkSizeX - 1))
			xPos -= Chunk.chunkSizeX;

		//analog to x
		while (yPos < 0)
			yPos += Chunk.chunkSizeY;
		
		//analog to x
		while(yPos > (Chunk.chunkSizeY - 1))
			yPos -= Chunk.chunkSizeY;
		
		return new Position(xPos % Chunk.chunkSizeX, yPos % Chunk.chunkSizeY);
	}
	
	/**
	 * Gets the chunk at the given chunk coordinates
	 * @param x The chunk's x coordinate
	 * @param y The chunk's y coordinate
	 * @return The chunk, null if no chunk has been found
	 */
	public Chunk getChunk(int x, int y)
	{
		for(Chunk c : loadedChunks)
		{
			if(x == c.chunkX && y == c.chunkY)
				return c;
		}
		
		return null;
	}
	
	/**
	 * Gets the chunk the given entity is in
	 * @param e The entity
	 * @return The chunk, null if none has been found (should never happen)
	 */
	public Chunk getChunk(Entity e)
	{
		return getChunk(e, 0, 0);
	}
	
	/**
	 * Gets the chunk the given entity is in
	 * @param e The entity
	 * @param dir The Direction in which to translate the entity's position by one
	 * @return The chunk, null if none has been found (should never happen)
	 */
	public Chunk getChunk(Entity e, Direction dir)
	{
		int x = 0;
		int y = 0;
		
		switch(dir)
		{
			case UP:
				y = -1;
				break;
			case LEFT:
				x = -1;
				break;
			case DOWN:
				y = 1;
				break;
			case RIGHT:
				x = 1;
				break;
		}
		
		return getChunk(e, x, y);
	}
	
	/**
	 * Gets the chunk the given entity is in
	 * @param e The entity
	 * @param x The x-axis modifier of the entity's position
	 * @param y The y-axis modifier of the entity's position
	 * @return The chunk, null if none has been found (should never happen)
	 */
	public Chunk getChunk(Entity e, int x, int y)
	{
		int modX = e.position.x + x; //modified x
		int modY = e.position.y + y; //modified y
		double divX = modX / (double)Chunk.chunkSizeX; //divided x
		double divY = modY / (double)Chunk.chunkSizeY; //divided y
		int cX = 0; //chunk pos x
		int cY = 0; //chunk pos y
		Position cP = getChunkPosition(e, x, y); //player position within chunk
		
		if(divX < 0)
		{
			if(cP.x == 0 || ((modX % Chunk.chunkSizeX) + Chunk.chunkSizeX) == Chunk.chunkSizeX) //special cases for chunk border, so the correct chunk gets selected
				cX = (int)divX;
			else
				cX = (int)divX - 1; //subtract one to get the correct position
		}
		else
			cX = (int)divX;
		
		//analog to x
		if(divY < 0)
		{
			if(cP.y == 0 || ((modY % Chunk.chunkSizeY) + Chunk.chunkSizeY) == Chunk.chunkSizeY)
				cY = (int)divY;
			else
				cY = (int)divY - 1;
		}
		else
			cY = (int)divY;
		
		for(Chunk c : loadedChunks)
		{
			if(cX == c.chunkX && cY == c.chunkY)
				return c;
		}
		
		return null;
	}
}
