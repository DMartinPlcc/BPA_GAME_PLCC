
public class WorldInstance implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	
	//static int MAX_FLOAT = 2000000000; // Two billion
	//static int MaxChildrenX = (int) (MAX_FLOAT/((WorldBlock.Width*WorldChunk.Columns)*(WorldSlice.Columns*WorldSlice.Width)));
	//static int MaxChildrenY = (int) (MAX_FLOAT/((WorldBlock.Height*WorldChunk.Rows)*(WorldSlice.Rows*WorldSlice.Height)));
	

	static final int ROWS 		=  1;
	static final int COLUMNS 	=  2;
	
	static final int CHILD_WIDTH  = WorldSlice.WIDTH;
	static final int CHILD_HEIGHT = WorldSlice.HEIGHT;
	
	// Width/Height of the Slice as a whole.
	static final int WIDTH  = COLUMNS * CHILD_WIDTH;
	static final int HEIGHT = ROWS    * CHILD_HEIGHT;
	
	
	
	int precedingX;
	int precedingY;
	

	static int SLICES_X  = WorldSlice.WIDTH  * COLUMNS;
	static int SLICES_Y  = WorldSlice.HEIGHT * ROWS;
	
	int precedingChildrenX;
	int precedingChildrenY;
	

	
	// Name or Instance file. Ex WorldName_0_0.txt
	// Name_X_Y
	String 	instance_FileName;
	int 	instance_X;
	int 	instance_Y;
		
	WorldSlice slices[][] = new WorldSlice[ROWS][COLUMNS];
		
	
	WorldInstance(int PrecedingInstancesX,int PrecedingInstancesY)
	{
		
		precedingX = PrecedingInstancesX;
		precedingY = PrecedingInstancesY;
		
		precedingChildrenX = precedingX * SLICES_X;
		precedingChildrenY = precedingY * SLICES_Y;
		
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
				slices[Row][Col] = new WorldSlice(TempPreceedX,TempPreceedY);
				System.out.println();
				TempPreceedY++;

			}
			TempPreceedY = precedingChildrenY;
			TempPreceedX++;

		}	
	}
		
	
	void draw(float playerX,float playerY)
	{
		
		/*
		
		float absPlayerX = Math.abs(playerX);
		float absPlayerY = Math.abs(playerY);
		
		float posX_Start = (precedingX * WIDTH);
		float posX_End   = (precedingX * WIDTH)+WIDTH;
		
		float posY_Start = (precedingY * HEIGHT);
		float posY_End   = (precedingY * HEIGHT)+HEIGHT;
		
		float CenterX = posX_Start+(WIDTH/2);
		float CenterY = posY_Start+(HEIGHT/2);
		
		
		*/
		
		
		for (int Row = 0; Row < ROWS; Row++)
		{
			for (int Col = 0; Col < COLUMNS; Col++)
			{				
				slices[Row][Col].draw(playerX,playerY);
			}
		}
	}
		
}
	
