public class WorldSave
{
	// Max VerticalChunks to save
	static int MAX_WORLD_XY = 50;
	
	String 	fileName;
	String 	worldName;
	int 	xOffset;
	int 	lastAdded;
	
	VerticalChunk[] _VerticalChunks;
	
	WorldSave(String WorldName,int XOffset)
	{
		lastAdded = 0;
		worldName = WorldName;
		xOffset = XOffset;
		fileName = WorldName+"_"+xOffset+".LOL";
	}
	
	boolean addVerticalChunk(VerticalChunk VC)
	{
		if (lastAdded < 100)
		{
			_VerticalChunks[lastAdded++] = VC;
			return true;
		}
		// Else, we just can't take another bite.
		return false;
	}
	
	void save()
	{
		// 1. Find all game entities that are within this world's volume.
		// 2. Send event to entities to cleanup();
		// 3. Save worldname, blocks, and entity data.
		// 4. Finish Serialization 
		
	}
	
	void load()
	{
		//Whatever prep work that must be done after a serialized file worldsave is loaded.
	}
}