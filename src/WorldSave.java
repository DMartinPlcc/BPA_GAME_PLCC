public class WorldSave
{
	// Max VerticalChunks to save
	static int MAX_WORLD_XY = 100;
	
	String 	_FileName;
	String 	_WorldName;
	int 	_XOffset;
	int 	_LastAdded;
	
	VerticalChunk[] _VerticalChunks;
	
	WorldSave(String WorldName,int X_Offset)
	{
		_LastAdded = 0;
		_WorldName = WorldName;
		_XOffset = X_Offset;
		_FileName = WorldName+"_"+_XOffset+".LOL";
	}
	
	boolean addVerticalChunk(VerticalChunk VC)
	{
		if (_LastAdded < 100)
		{
			_VerticalChunks[_LastAdded++] = VC;
			return true;
		}
		// We just can't take another bite.
		return false;
	}
	
	void save()
	{
		
	}
}