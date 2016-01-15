package bl4ckscor3.game.GameDev.world.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Material
{
	//all the materials
	public static final Material GRASS = new Material("grass");
	public static final Material SAND = new Material("sand");
	public static final Material WATER_NORMAL = new Material("water_normal");
	public static final Material WATER_DEEP = new Material("water_deep");
	//no more materials
	/** The ID of the Material (used to determine which material it is)*/
	private String resourceID;
	/** A list of all known IDs*/
	private List<String> resourceIDs = new ArrayList<String>();
	
	public Material(String resourceID)
	{
		this.resourceID = resourceID;
		resourceIDs.add(resourceID);
	}
	
	/**
	 * Returns the resourceID at the given position of the array
	 * @param i - The array position to get the ID from
	 */
	public String getResourceID(int i)
	{
		return resourceIDs.get(i);
	}

	/**
	 * Returns the resourceID of the Material
	 * @return The resource ID
	 */
	public String getResourceID()
	{
		return resourceID;
	}
	
	/**
	 * Returns a random loaded resourceID
	 */
	public String getRandomResourceID()
	{
		Random r = new Random();
		
		return resourceIDs.get(r.nextInt(resourceIDs.size()));
	}
}
