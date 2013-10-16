import java.util.Random;

// Daniel Martin Oct/6/2013
// Purpose: Contains an X by Y matrix of world blocks.
// Currently for testing purposes only. 

//NOTE: This only works with an image that has a pixel size of 50x50.
// ALL *BLOCK* Assets will be a power of, or equally divisible by TEN!!!
public class WorldChunk extends EntityBase
{
	private static final long serialVersionUID = 1L;
	static final int ROWS    = 10;
	static final int COLUMNS = 10;
	
	// The width/depth of the blocks/chunks that make up the class sections.
	// This would be Block width/height for the Class WorldChunk, and would be WorldChunk width/height for Worldslice.
	static final int CHILD_WIDTH  = WorldBlock.WIDTH;
	static final int CHILD_HEIGHT = WorldBlock.HEIGHT;
	
	// Width/Height of a WorldChunk as a whole.
	static final int WIDTH  = COLUMNS * CHILD_WIDTH;
	static final int HEIGHT = ROWS    * CHILD_HEIGHT;
	
	WorldBlock Blocks[][] = new WorldBlock[ROWS][COLUMNS];
	
	
	// Number of chunks/slices/etc preceding from the left/right and up/down.
	int precedingX;
	int precedingY;

	//Start Position;
	float x;
	float y;
	
	// Because all chunks are the same size, we compute the offset by multiplying the
	// total size of a single chunk's width by the total number of preceding chunks. Giving a proper offset.
	// This implies that the blocks are contiguous, however.
	WorldChunk(int PrecedingChunksX, int PrecedingChunksY)
	{			
		
		// Number of slices before this one, used for positioning the whole.
		precedingX = PrecedingChunksX;
		precedingY = PrecedingChunksY;
		
		// The starting position of this collection of objects.
		x = WIDTH  * precedingX;
		y = HEIGHT * precedingY;
		populate();
	}
	
	
	// Populate chunk with a matrix of EntityStatic(s).
	private void populate()
	{

		for (int Row = 0; Row < ROWS; Row++)
		{
			for (int Col = 0; Col < COLUMNS; Col++)
			{
				Random rand = new Random();
				
				float randFloat = rand.nextFloat();
				Blocks[Row][Col] = new WorldBlock(( Col * CHILD_WIDTH ) + x,( Row * CHILD_HEIGHT) + y,1);
				//Blocks[Row][Col].setAlpha(rand.nextInt());
				//Blocks[Row][Col].x = ( Col * CHILD_WIDTH ) + x;
				//Blocks[Row][Col].y = ( Row * CHILD_HEIGHT) + y;
				//Blocks[Row][Col].setScale(0.5f);
			}
		}	
	}
	
	void draw(float playerX, float playerY)
	{
		
		float posX_Start = (precedingX * WIDTH);
		float posX_End   = posX_Start+WIDTH;
		
		float posY_Start = (precedingY * HEIGHT);
		float posY_End   = posY_Start+HEIGHT;
		
		float CenterX = posX_Start+(WIDTH/2);
		float CenterY = posY_Start+(HEIGHT/2);
		
		

		// Player past slice center
		if (playerX > CenterX)
		{
			//System.out.println("Past Center");
		
			
			if ((posX_End < playerX))
			{
				//System.out.println("X Chunk["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}
		}
		// Player behind slice center
		else
		{
			//System.out.println("Behind Center");
			if (posX_Start > playerX+1280)
			{
				//System.out.println("X Chunk["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}	
		}
		
		
		// Player past slice center
		if (playerY > CenterY)
		{
			//System.out.println("Past Center");
		
			
			if ((posY_End < playerY))
			{
				//System.out.println("Y Chunk["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}
		}
		// Player behind slice center
		else
		{
			//System.out.println("Behind Center");
			if (posY_Start > playerY+720)
			{
				//System.out.println("Y Chunk["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}	
		}
		
	
		
		
		
		
		
		
		for (int Row = 0; Row < ROWS; Row++)
		{
			for (int Col = 0; Col < COLUMNS; Col++)
			{
				Blocks[Row][Col].draw();
			}
		}	
		

	}
}
