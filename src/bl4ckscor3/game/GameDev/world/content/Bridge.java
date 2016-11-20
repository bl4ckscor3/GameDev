package bl4ckscor3.game.gamedev.world.content;

import bl4ckscor3.game.gamedev.game.Game;
import bl4ckscor3.game.gamedev.util.Position;
import bl4ckscor3.game.gamedev.util.TextureManager;
import bl4ckscor3.game.gamedev.world.Chunk;

public class Bridge extends PlaceableObject
{
	/** The path of the bridge textures*/
	private static final String texturePath = "objects/bridge/";
	
	/**
	 * Sets up an object that can be placed in the world
	 * @param c The Chunk this PlaceableObject is in
	 * @param pos The x and y coordinates within the chunk that this object is placed in
	 */
	public Bridge(Chunk c, Position pos)
	{
		this(c, pos, "");
	}
	
	/**
	 * Sets up an object that can be placed in the world
	 * @param chunk The Chunk this PlaceableObject is in
	 * @param pos The x and y coordinates within the chunk that this object is placed in
	 * @param appendix The appendix to the file name
	 * @param path Subfolder to load the texture from
	 */
	public Bridge(Chunk c, Position pos, String appendix)
	{
		super(Material.BRIDGE, c, pos, appendix, "bridge/");
	}

	/**
	 * Gets called every game tick
	 */
	public void tick()
	{
		Position up = pos.clone(0, -1);
		Position left = pos.clone(-1, 0);
		Position down = pos.clone(0, 1);
		Position right = pos.clone(1, 0);
		boolean isUp = false;
		boolean isLeft = false;
		boolean isDown = false;
		boolean isRight = false;
		Chunk c;
		PlaceableObject po;
		Tile t;
		
		//checking up
		if(up.y == -1)
		{
			c = Game.map.getChunk(chunk.chunkX, chunk.chunkY - 1);
			up.modify(0, 16);
		}
		else
			c = chunk;
		
		if(c == null) //the chunk is unloaded
			return;
		
		po = c.getPlaceableObject(up);
		t = c.getTile(up);

		if((po != null && po.getMaterial() == Material.BRIDGE) || t.getMaterial() == Material.SAND)
			isUp = true;
		
		//checking left
		if(left.x == -1)
		{
			c = Game.map.getChunk(chunk.chunkX - 1, chunk.chunkY);
			left.modify(16, 0);
		}
		else
			c = chunk;

		if(c == null) //the chunk is unloaded
			return;
		
		po = c.getPlaceableObject(left);
		t = c.getTile(left);
		
		if((po != null && po.getMaterial() == Material.BRIDGE) || t.getMaterial() == Material.SAND)
			isLeft = true;

		//checking down
		if(down.y == 16)
		{
			c = Game.map.getChunk(chunk.chunkX, chunk.chunkY + 1);
			down.modify(0, -16);
		}
		else
			c = chunk;

		if(c == null) //the chunk is unloaded
			return;
		
		po = c.getPlaceableObject(down);
		t = c.getTile(down);

		if((po != null && po.getMaterial() == Material.BRIDGE) || t.getMaterial() == Material.SAND)
			isDown = true;
		
		//checking right
		if(right.x == 16)
		{
			c = Game.map.getChunk(chunk.chunkX + 1, chunk.chunkY);
			right.modify(-16, 0);
		}
		else
			c = chunk;

		if(c == null) //the chunk is unloaded
			return;
		
		po = c.getPlaceableObject(right);
		t = c.getTile(right);
		
		if((po != null && po.getMaterial() == Material.BRIDGE) || t.getMaterial() == Material.SAND)
			isRight = true;

		//setting the correct textures
		if(isUp && isLeft && isDown && isRight)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_intersection_full", texturePath);
		else if(isRight && isUp && isLeft)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_intersection_up", texturePath);
		else if(isUp && isLeft && isDown)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_intersection_left", texturePath);
		else if(isLeft && isDown && isRight)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_intersection_down", texturePath);
		else if(isDown && isRight && isUp)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_intersection_right", texturePath);
		else if(isUp && isLeft)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_corner_tl", texturePath);
		else if(isLeft && isDown)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_corner_bl", texturePath);
		else if(isDown && isRight)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_corner_br", texturePath);
		else if(isRight && isUp)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_corner_tr", texturePath);
		//the following two make sure that the bridges always stay connected and that nothing like this happens |-, but instead this happens --
		else if(isLeft || isRight)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_horizontal", texturePath);
		else if(isUp || isDown)
			texture = TextureManager.loadTextureFromPath(material.getResourceID() + "_vertical", texturePath);
	}
}
