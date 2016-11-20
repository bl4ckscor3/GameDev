package bl4ckscor3.game.gamedev.world.content;

public enum Material
{
	GRASS("grass"),
	SAND("sand"),
	WATER_NORMAL("water_normal"),
	WATER_DEEP("water_deep"),
	BRIDGE("bridge"),
	TREE("tree");

	/** The ID of the Material (used to determine which material it is)*/
	private String resourceID;

	private Material(String rID)
	{
		resourceID = rID;
	}
	
	/**
	 * Returns the resourceID of the Material
	 * @return The resource ID
	 */
	public String getResourceID()
	{
		return resourceID;
	}
}
