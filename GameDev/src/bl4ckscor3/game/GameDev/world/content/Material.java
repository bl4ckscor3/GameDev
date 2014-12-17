package bl4ckscor3.game.GameDev.world.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Material
{
	public static final Material GRASS = new Material("grass");
	public static final Material SAND = new Material("sand");
	public static final Material WATER_NORMAL = new Material("water_normal");
	public static final Material WATER_DEEP = new Material("water_deep");
	private String resourceID;
	private List<String> resourceIDs = new ArrayList<String>();
	
	public Material(String resourceID)
	{
		this.resourceID = resourceID;
		resourceIDs.add(resourceID);
	}
	
	public String getResourceID(int i)
	{
		return resourceIDs.get(i);
	}

	public String getResourceID(Material mat)
	{
		return mat.resourceID;
	}
	
	public String getRandomResourceID()
	{
		Random r = new Random();
		
		return resourceIDs.get(r.nextInt(resourceIDs.size()));
	}
}
