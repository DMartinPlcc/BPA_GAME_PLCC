import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WorldInstance implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	
	//static int MAX_FLOAT = 2000000000; // Two billion
	//static int MaxChildrenX = (int) (MAX_FLOAT/((WorldBlock.Width*WorldChunk.Columns)*(WorldSlice.Columns*WorldSlice.Width)));
	//static int MaxChildrenY = (int) (MAX_FLOAT/((WorldBlock.Height*WorldChunk.Rows)*(WorldSlice.Rows*WorldSlice.Height)));
	

	static final int ROWS 		=  2;
	static final int COLUMNS 	=  2;
	
	static final int CHILD_WIDTH  = WorldSlice.WIDTH;
	static final int CHILD_HEIGHT = WorldSlice.HEIGHT;
	
	// Width/Height of the Slice as a whole.
	static final int WIDTH  = COLUMNS * CHILD_WIDTH;
	static final int HEIGHT = ROWS    * CHILD_HEIGHT;
	
	
	
	int precedingX;
	int precedingY;
	
	int precedingChildrenX;
	int precedingChildrenY;
	
	//Start Position;
	float x;
	float y;
	
	// Name or Instance file. Ex WorldName_0_0.txt
	// Name_X_Y
	String 	instance_FileName;
	int 	instance_X;
	int 	instance_Y;
		
	WorldSlice slices[][] = new WorldSlice[ROWS][COLUMNS];
		
	
	WorldInstance(WorldInstance Instance)
	{
		slices = Instance.slices.clone();
		precedingX = Instance.precedingX;
		precedingY = Instance.precedingY;
		
		precedingChildrenX = Instance.precedingChildrenX;
		precedingChildrenY = Instance.precedingChildrenY;
		
		x = Instance.x;
		y = Instance.y;
	}
	WorldInstance(int PrecedingInstancesX,int PrecedingInstancesY)
	{
		computeOffsets(PrecedingInstancesX,PrecedingInstancesY);
		populate();
	}
	
	void save(String WorldName,int Generation)
	{
		new File("res/save/"+WorldName).mkdirs();
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("res/save/"+WorldName+"/"+"WorldInstance_"+Generation+"_"+precedingX+"_"+precedingY+".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /save/"+WorldName+"/"+"WorldInstance_"+Generation+"_"+precedingX+"_"+precedingY+".ser");
	      }catch(IOException i)
	      {
	  
	          i.printStackTrace();
	      }
		
	}
		
	void computeOffsets(int PrecedingInstancesX,int PrecedingInstancesY)
	{
		precedingX = PrecedingInstancesX;
		precedingY = PrecedingInstancesY;
				
		precedingChildrenX = (int) (precedingX * COLUMNS); 
		precedingChildrenY = (int) (precedingY * ROWS);
		
		x = WIDTH  * precedingX;
		y = HEIGHT * precedingY;
	}
	
	void recomputePosition(int PrecedingInstancesX,int PrecedingInstancesY)
	{	
		computeOffsets(PrecedingInstancesX,PrecedingInstancesY);
		
		int TempPreceedX = precedingChildrenX;
		int TempPreceedY = precedingChildrenY;
		
		for (int Col = 0; Col < COLUMNS; Col++)
		{
			for (int Row = 0; Row < ROWS; Row++)
			{
				slices[Row][Col].recomputePosition(TempPreceedX,TempPreceedY);
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
				slices[Row][Col] = new WorldSlice(TempPreceedX,TempPreceedY);
				TempPreceedY++;
			}
			TempPreceedY = precedingChildrenY;
			TempPreceedX++;

		}	
	}
		
	void draw(float playerX,float playerY)
	{

		/*
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
				//System.out.println("X Instance["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}
		}
		// Player behind slice center
		else
		{
			//System.out.println("Behind Center");
			if (posX_Start > playerX+1280)
			{
				//System.out.println("X Instance["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}	
		}
		
		
		// Player past slice center
		if (playerY > CenterY)
		{
			//System.out.println("Past Center");
		
			
			if ((posY_End < playerY))
			{
				//System.out.println("Y Instance["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}
		}
		// Player behind slice center
		else
		{
			//System.out.println("Behind Center");
			if (posY_Start > playerY+720)
			{
				//System.out.println("Y Instance["+precedingY+"]["+precedingX+"] Skipped");
				return;
			}	
		}
		
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
	
