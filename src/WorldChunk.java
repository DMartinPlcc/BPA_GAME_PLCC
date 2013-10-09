// Daniel Martin Oct/6/2013
// Purpose: Contains an X by Y matrix of world blocks.
// Currently for testing purposes only. 

//NOTE: This only works with an image that has a pixel size of 50x50.
// ALL *BLOCK* Assets will be a power of, or equally divisible by TEN!!!
public class WorldChunk
{
	static final int Rows    = 10;
	static final int Columns = 10;
	
	// The width/depth of the blocks/chunks that make up the class sections.
	// This would be Block width/height for the Class WorldChunk, and would be WorldChunk width/height for Worldslice.
	static final float ChildWidth  = WorldBlock.Width;
	static final float ChildHeight = WorldBlock.Height;
	
	// Width/Height of a WorldChunk as a whole.
	static final float Width  = Columns * ChildWidth;
	static final float Height = Rows    * ChildHeight;
	
	WorldBlock Blocks[][] = new WorldBlock[Rows][Columns];
	
	
	// Number of chunks/slices/etc preceding from the left/right and up/down.
	int PrecedingX;
	int PrecedingY;
	
	//int ProceedingChildrenX; (There isn't a real use for this right now here.)
	//int ProceedingChildrenY; (There are so many blocks, we could overflow an integer's size depending on how the world is implemented.)
	
	//Start Position;
	float X;
	float Y;
	
	// Because all chunks are the same size, we compute the offset by multiplying the
	// total size of a single chunk's width by the total number of preceding chunks. Giving a proper offset.
	// This implies that the blocks are contiguous, however.
	WorldChunk(int PrecedingChunksX, int PrecedingChunksY)
	{			
		
		// Number of slices before this one, used for positioning the whole.
		PrecedingX = PrecedingChunksX;
		PrecedingY = PrecedingChunksY;
		
		// The starting position of this collection of objects.
		X = Width  * PrecedingX;
		Y = Height * PrecedingY;
		Populate();
	}
	
	
	// Populate chunk with a matrix of EntityStatic(s).
	private void Populate()
	{

		for (int Row = 0; Row < Rows; Row++)
		{
			for (int Col = 0; Col < Columns; Col++)
			{
				Blocks[Row][Col] = new WorldBlock();
				Blocks[Row][Col].m_X = ( Col * ChildWidth ) + X;
				Blocks[Row][Col].m_Y = ( Row * ChildHeight) + Y;
				Blocks[Row][Col].SetScale(0.5f);
			}
		}	
	}
	
	void Draw()
	{
		for (int Row = 0; Row < Rows; Row++)
		{
			for (int Col = 0; Col < Columns; Col++)
			{
				Blocks[Row][Col].draw();
			}
		}	
		

	}
}
