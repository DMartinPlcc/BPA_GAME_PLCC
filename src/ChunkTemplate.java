import org.newdawn.slick.geom.*;

public class ChunkTemplate
{
		static final int 	ROWS 			= 1;
		static final int 	COLUMNS			= 1; 
		
		static final int 	CHILD_WIDTH 	= WorldBlock.WIDTH;
		static final int 	CHILD_HEIGHT 	= WorldBlock.HEIGHT;
		static final int 	WIDTH 			= CHILD_WIDTH	* COLUMNS;
		static final int 	HEIGHT 			= CHILD_HEIGHT 	* ROWS;
		
		Vector2f 			startPosition 	= null;	
		WorldBlock[][] 		blocks 			= null;
		
		
		ChunkTemplate(Vector2f StartPosition)
		{
			startPosition = StartPosition;	
			populateBlocks();
			//System.out.println("ChunkTemplate: [Rows: "+_ROWS+" | Columns: "+_COLUMNS+" ]");
			
		}
		ChunkTemplate (float X, float Y)
		{
			this(new Vector2f(X,Y));
		}

	
		void shiftBlocks(float X, float Y)
		{
			for (int Row = 0; Row < ROWS; Row++)
			{
				for (int Col = 0; Col < COLUMNS; Col++)
				{
					if (blocks[Row][Col] != null)
					{
						blocks[Row][Col].addPos(X, Y);
					}
				}
			}
		}
		
		void populateBlocks()
		{
			blocks 	= new WorldBlock[ROWS][COLUMNS];
			
			for (int Row = 0; Row < ROWS; Row++)
			{
				for (int Col = 0; Col < COLUMNS; Col++)
				{
					blocks[Row][Col] = new WorldBlock(( Col * WIDTH ) + startPosition.x,( Row * HEIGHT) + startPosition.y);
				}
			}
		}
		
		void draw()
		{
			for (int Row = 0; Row < ROWS; Row++)
			{
				for (int Col = 0; Col < COLUMNS; Col++)
				{
					blocks[Row][Col].draw();
				}
			}
		}
		
		
}

