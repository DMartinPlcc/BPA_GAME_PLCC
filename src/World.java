// Daniel Martin Oct/6/2013
// Purpose: Placeholder for entity, world, and other asset data representing the game world.
public class World 
{
	static int SLICE_COUNT = 1;
	
	// When the player reaches this slice, create additional slices.
	static int WorldBuffer = SLICE_COUNT/2;
	
	// number of horizontal world segments
	WorldSlice Slices[] = new WorldSlice[SLICE_COUNT];
	WorldMetaData m_MetaData;
	
	int NumDeletedSlices;
	
	World()
	{
		for(int i = 0; i < SLICE_COUNT; i++)
		{
			Slices[i] = new WorldSlice(i,0);
		}
	}
	void PushBackSlices()
	{
		// Check math
		WorldSlice NewArray[] = new WorldSlice[SLICE_COUNT];
		
		for(int i = 0; i < SLICE_COUNT-WorldBuffer; i++)
		{
			NewArray[i] = Slices[i+1];
		}
		
		CreateNewSlices();
	}
	
	void CreateNewSlices()
	{
		// Check math
		for (int Slice = SLICE_COUNT-WorldBuffer; Slice < SLICE_COUNT; Slice++ )
		{
			Slices[Slice] = new WorldSlice(Slices[Slice-1].PrecedingX,0);
		}
	}
	
	void Draw()
	{
		for (int Slice = 0; Slice < SLICE_COUNT; Slice++ )
		{
			Slices[Slice].Draw();
		}
	}
	
}
