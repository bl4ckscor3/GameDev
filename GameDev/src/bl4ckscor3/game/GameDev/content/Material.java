package bl4ckscor3.game.GameDev.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Material
{
	public static final Material GRASS = new Material("grass");
	private List<String> resourceIDs = new ArrayList<String>();
	
	public Material(String resourceID)
	{
		resourceIDs.add(resourceID);
	}
	
	public String getResourceID(int i)
	{
		return resourceIDs.get(i);
	}
	
	/**
	 * for noise generation
	 */
	public String getRandomResourceID()
	{
		Random r = new Random();
		
		return resourceIDs.get(r.nextInt(resourceIDs.size()));
	}
}
