// Daniel Martin Oct/6/2013
// Purpose: Contains a set of WorldChunks representing a vertical slice of the game world, one column wide.

//   Slice         Slice
//------------  ------------
//   Chunk1    |    Chunk1   |
//   Chunk2    |    Chunk2   |
//   Chunk3    |    Chunk3   |
public class WorldSlice 
{
	static final int Rows    = 2;
	static final int Columns = 10;
	
	// The width/depth of the blocks/chunks that make up the class sections.
	// This would be Block width/height for the Class WorldChunk, and would be WorldChunk width/height for Worldslice.
	static final float ChildWidth  = WorldChunk.Width;
	static final float ChildHeight = WorldChunk.Height;
	
	// Width/Height of the Slice as a whole.
	static final float Width  = Columns * ChildWidth;
	static final float Height = Rows    * ChildHeight;

	// Array containing all WorldChunks that make up a WorldSlice.
	WorldChunk 	WorldChunks[][] = new WorldChunk[Rows][Columns];;	
	
	// Number of chunks/slices/etc preceding from the left/right and up/down.
	int PrecedingX;
	int PrecedingY; //(This actually isn't used anywhere currently.) There isn't a proper ability to layer slices yet.
	
	// Number of chunks from the left/right or up/down. 
	int ProceedingChildrenX; //(PrecedingX * (Width  / ChildWidth ))
	int ProceedingChildrenY; //(PrecedingY * (Height / ChildHeight))
	
	//Starting Position.
	float X;
	float Y;
	

	
	WorldSlice(int PrecedingSlicesX, int PrecedingSlicesY)
	{			

		// Number of slices before this one, used for positioning the whole.
		PrecedingX = PrecedingSlicesX;
		PrecedingY = PrecedingSlicesY;
		
		// The starting position of this collection of objects.
		X = Width  * PrecedingX;
		Y = Height * PrecedingY;
		
		ProceedingChildrenX = (int) (PrecedingX * (Width/ChildWidth)); //Watch out for integer overflow if we have too many chunks.
		ProceedingChildrenY = (int) (PrecedingY * (Width/ChildHeight));// We will have to do instancing to avoid this more likely or limit level size.
		
		Populate();
	}
		
	private void Populate()
	{
		for (int Row = 0; Row < Rows; Row++)
		{
			for (int Col = 0; Col < Columns; Col++)
			{
				// Create offset, use Row to specify how many chunks are layered under each other.
				WorldChunks[Row][Col] = new WorldChunk(ProceedingChildrenX+Col,Row);
				
			}
		}	
	}
	
	void Draw()
	{
		for (int Row = 0; Row < Rows; Row++)
		{
			for (int Col = 0; Col < Columns; Col++)
			{
				WorldChunks[Row][Col].Draw();
			}
		}	
	}
	

}


