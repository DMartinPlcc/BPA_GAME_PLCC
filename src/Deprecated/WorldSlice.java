import org.lwjgl.util.vector.Vector2f;



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
	

	WorldSlice(WorldSlice Slice)
	{
		worldChunks = Slice.worldChunks.clone();
		precedingX = Slice.precedingX;
		precedingY = Slice.precedingY;
		
		precedingChildrenX = Slice.precedingChildrenX;
		precedingChildrenY = Slice.precedingChildrenY;

		x = Slice.x;
		y = Slice.y;
	}
	
	WorldSlice(int PrecedingSlicesX, int PrecedingSlicesY)
	{			
		computeOffsets(PrecedingSlicesX,PrecedingSlicesY);
		populate();
	}
	
	void computeOffsets(int PrecedingSlicesX, int PrecedingSlicesY)
	{
		// Number of slices before this one, used for positioning the whole.
		precedingX = PrecedingSlicesX;
		precedingY = PrecedingSlicesY;
		
		// The starting position of this collection of objects.

		x = WIDTH  * precedingX;
		y = HEIGHT * precedingY;
		
		precedingChildrenX = (int) (precedingX * (COLUMNS)); //Watch out for integer overflow if we have too many chunks.
		precedingChildrenY = (int) (precedingY * (ROWS));// We will have to do instancing to avoid this more likely or limit level size.
	
	}
		
	void ShiftPos(float X, float Y)
	{
		for (int Col = 0; Col < COLUMNS; Col++)
		{
			
			for (int Row = 0; Row < ROWS; Row++)
			{
				worldChunks[Row][Col].shiftPos(X,Y);
			}
		}
	}
	void recomputePosition(int PrecedingSlicesX, int PrecedingSlicesY)
	{
		computeOffsets(PrecedingSlicesX,PrecedingSlicesY);
		
		int TempPreceedX = precedingChildrenX;
		int TempPreceedY = precedingChildrenY;
		
		for (int Col = 0; Col < COLUMNS; Col++)
		{
			
			for (int Row = 0; Row < ROWS; Row++)
			{
				worldChunks[Row][Col].recomputePosition(TempPreceedX,TempPreceedY);
				TempPreceedY++;
			}
			TempPreceedY = precedingChildrenY;
			TempPreceedX++;
		}	
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
				TempPreceedY++;

			}
			TempPreceedY = precedingChildrenY;
			TempPreceedX++;

		}	
	}
	
	void draw()
	{		
		

		float posX_Start = (precedingX * WIDTH);
		float posX_End   = posX_Start+WIDTH;
		
		float posY_Start = (precedingY * HEIGHT);
		float posY_End   = posY_Start+HEIGHT;
		
		float CenterX = posX_Start+(WIDTH/2);
		float CenterY = posY_Start+(HEIGHT/2);
		
		
		Vector2f fPos = Engine.gameWorld.player.playerCamera.getParentFrustrum();
		float playerX = fPos.x;
		float playerY = fPos.y;
		// Player past slice center
		if (playerY  > CenterX)
		{
			//System.out.println("Past Center");
		
			
			if ((posX_End < playerX))
			{
				//System.out.println("X Slice["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}
		}
		// Player behind slice center
		else
		{
			//System.out.println("Behind Center");
			if (posX_Start > playerX+1280)
			{
				//System.out.println("X Slice["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}	
		}
		
		
		// Player past slice center
		if (playerY > CenterY)
		{
			//System.out.println("Past Center");
		
			
			if ((posY_End < playerY))
			{
				//System.out.println("Y Slice["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}
		}
		// Player behind slice center
		else
		{
			//System.out.println("Behind Center");
			if (posY_Start > playerY+720)
			{
				//System.out.println("Y Slice["+precedingY+"]["+precedingX+"] Skipped");
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


