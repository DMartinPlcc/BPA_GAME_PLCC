// Daniel Martin Oct/6/2013
// Purpose: Contains a set of WorldChunks representing a vertical slice of the game world, one column wide.

//   Slice         Slice
//------------  ------------
//   Chunk1    |    Chunk1   |
//   Chunk2    |    Chunk2   |
//   Chunk3    |    Chunk3   |
public class WorldSlice 
{
	// How many chunks we want per row.
	static int NUM_ROW = 10;
	
	// How many WorldChunks wide we per column. (Don't change this from 1, code will be reworked)
	static int NUM_COL = 1;
	
	static float SLICE_WIDTH = WorldChunk.CHUNK_WIDTH  * NUM_COL;
	static float SLICE_DEPTH = WorldChunk.CHUNK_HEIGHT * NUM_ROW;
	
	float Begin_X;
	float Begin_Y;
	
	int PrecedingSlices;

	WorldChunk WorldChunks[][] = new WorldChunk[NUM_ROW][NUM_COL];
	
	WorldSlice(int NumPrecedingSlices)
	{
		PrecedingSlices = NumPrecedingSlices;
		
		// Offset new slices so we don't have overlapping chunks/blocks.
		Begin_X = SLICE_WIDTH*NumPrecedingSlices;
		Begin_Y = 0;
	}
	
	void Populate()
	{
		for (int Row = 0; Row < NUM_ROW; Row++)
		{
			for (int Col = 0; Col < NUM_COL; Col++)
			{
				// Create offset, use Row to specify how many chunks are layered under each other.
				WorldChunks[Row][Col] = new WorldChunk(PrecedingSlices,Row);
				WorldChunks[Row][Col].Populate();

			}
		}	
	}
	
	void Draw()
	{
		for (int Row = 0; Row < NUM_ROW; Row++)
		{
			for (int Col = 0; Col < NUM_COL; Col++)
			{
				WorldChunks[Row][Col].Draw();

			}
		}	
	}
	

}


