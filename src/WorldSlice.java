// Daniel Martin Oct/6/2013
// Purpose: Contains a set of WorldChunks representing a vertical slice of the game world, one column wide.

//   Slice         Slice
//------------  ------------
//   Chunk1    |    Chunk1   |
//   Chunk2    |    Chunk2   |
//   Chunk3    |    Chunk3   |
public class WorldSlice implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	static final int ROWS    = 5;
	static final int COLUMNS = 5;
	
	// The width/depth of the blocks/chunks that make up the class sections.
	// This would be Block width/height for the Class WorldChunk, and would be WorldChunk width/height for Worldslice.
	static final int CHILD_WIDTH  = WorldChunk.WIDTH;
	static final int CHILD_HEIGHT = WorldChunk.HEIGHT;
	
	// Width/Height of the Slice as a whole.
	static final int WIDTH  = COLUMNS * CHILD_WIDTH;
	static final int HEIGHT = ROWS    * CHILD_HEIGHT;

	// Array containing all WorldChunks that make up a WorldSlice.
	WorldChunk 	worldChunks[][] = new WorldChunk[ROWS][COLUMNS];;	
	
	// Number of chunks/slices/etc preceding from the left/right and up/down.
	int precedingX;
	int precedingY; //(This actually isn't used anywhere currently.) There isn't a proper ability to layer slices yet.
	
	// Number of chunks from the left/right or up/down. 
	int precedingChildrenX; //(PrecedingX * (Width  / ChildWidth ))
	int precedingChildrenY; //(PrecedingY * (Height / ChildHeight))
	
	//Starting Position.
	float x;
	float y;
	

	
	WorldSlice(int PrecedingSlicesX, int PrecedingSlicesY)
	{			

		// Number of slices before this one, used for positioning the whole.
		precedingX = PrecedingSlicesX;
		precedingY = PrecedingSlicesY;
		
		// The starting position of this collection of objects.

		x = WIDTH  * precedingX;
		y = HEIGHT * precedingY;
		
		precedingChildrenX = (int) (precedingX * (COLUMNS)); //Watch out for integer overflow if we have too many chunks.
		precedingChildrenY = (int) (precedingY * (ROWS));// We will have to do instancing to avoid this more likely or limit level size.
		
		//System.out.println("ProceedingChildrenX: "+ProceedingChildrenX);
		//System.out.println("ProceedingChildrenY: "+ProceedingChildrenY);		
		
		
		System.out.println("Created Slice:");
		System.out.println("Width: "+WIDTH);
		System.out.println("PreceedingX: "+precedingX);
		System.out.println("PositionX: "+x);
		
		populate();
	}
		
	private void populate()
	{
		int TempPreceedX = precedingChildrenX;
		int TempPreceedY = precedingChildrenY;
		
		for (int Col = 0; Col < COLUMNS; Col++)
		{
			
			for (int Row = 0; Row < ROWS; Row++)
			{
				
				// Create offset, use Row to specify how many chunks are layered under each other.
				worldChunks[Row][Col] = new WorldChunk(TempPreceedX,TempPreceedY);
				System.out.println();
				TempPreceedY++;

			}
			TempPreceedY = precedingChildrenY;
			TempPreceedX++;

		}	
	}
	
	void draw(float playerX, float playerY)
	{		
		
		float absPlayerX = Math.abs(playerX);
		float absPlayerY = Math.abs(playerY);
		
		float posX_Start = (precedingX * WIDTH);
		float posX_End   = (precedingX * WIDTH)+WIDTH;
		
		float posY_Start = (precedingY * HEIGHT);
		float posY_End   = (precedingY * HEIGHT)+HEIGHT;
		
		float CenterX = posX_Start+(WIDTH/2);
		float CenterY = posY_Start+(HEIGHT/2);
		
		
		System.out.println();
		System.out.println("PlayerPos("+playerX+","+playerY+")");
		System.out.println("Center("+CenterX+","+CenterY+")");
		System.out.println("Start ("+posX_Start+","+posY_Start+")");
		System.out.println("End   ("+posX_End+","+posY_End+")");
		System.out.println();
		
		System.out.println("PlayerPos: "+playerX);
		System.out.println("Center: "+CenterX);
		// Player past center
		if (playerX > CenterX)
		{
			System.out.println("Past Center");
		
			
			if ((posX_End < playerX))
			{
				System.out.println("Slice["+precedingX+"] Skipped");
				return;
			}
		}
		// Player behind center
		else
		{
			System.out.println("Behind Center");
			if (posX_Start > playerX+1280)
			{
				System.out.println("Slice["+precedingX+"] Skipped");
				return;
			}	
		}
		
	
		
		/*
		if (sliceX_End < playerX-1280)
		{
			System.out.println("Slice Left: NODRAW");
			return;
		}
		*/
		
		/*
		if (  (sliceY_End > playerY))
		{	
			return;
		}
		
		if ((sliceY_Start < playerY-720))
		{
			return;
		}
		*/
		
		
		/*
		else if (Math.abs(((y+HEIGHT)-playerY)) > 720)
		{
			System.out.println("Not Drawing Slice Y:");
			System.out.println("Slice Pos: "+(y));
			System.out.println("Player Pos: "+playerY);
			return;
		}
		*/
		for (int Row = 0; Row < ROWS; Row++)
		{
			for (int Col = 0; Col < COLUMNS; Col++)
			{
				worldChunks[Row][Col].draw(playerX,playerY);
			}
		}	
	}
	

}


