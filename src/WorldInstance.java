import java.util.Vector;

public class WorldInstance 
{
	static int MAX_FLOAT = 2000000000; // Two billion
	static int MaxChildrenX = (int) (MAX_FLOAT/((WorldBlock.Width*WorldChunk.Columns)*WorldSlice.Columns));
	static int MaxChildrenY = (int) (MAX_FLOAT/((WorldBlock.Height*WorldChunk.Rows)*WorldSlice.Rows));
	

	static final int Rows = 10;
	static final int Columns = 10;
		
	int Preceding;
	
	// Name or Instance file. Ex WorldName_0_0.txt
	// Name_X_Y
	String 	Instance_FileName;
	int 	Instance_X;
	int 	Instance_Y;
		
	WorldSlice Slices[][] = new WorldSlice[Rows][Columns];
		
	
	
	private void Populate()
	{
		Preceding = 0;
		for (int Row = Rows; Row < Rows; Row++)
		{
			for (int Col = 0; Col < Columns; Col++)
			{
				Slices[Row][Col] = new WorldSlice(Preceding++,Preceding++);
			}
		}
	}
		
	void Slice_PushRight(int Slices)
	{
		for (int Row = 0; Row < Rows-Slices; Row++)
		{
			for (int Col = 0; Col < Columns; Col++)
			{
				if ((Columns-Slices) > Col)
				{
						
				}
			}
		}
	}
		
}
	
