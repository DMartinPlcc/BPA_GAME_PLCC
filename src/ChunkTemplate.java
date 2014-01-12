import java.util.Vector;

import org.newdawn.slick.geom.*;

public class ChunkTemplate
{
		static final int 	ROWS 			= 20;
		static final int 	COLUMNS			= 20; 
		
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

		Rectangle getBoundingBox()
		{
			return new Rectangle(startPosition.x,startPosition.y-HEIGHT,WIDTH,HEIGHT);
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
					blocks[Row][Col] = new WorldBlock(( Col * CHILD_WIDTH) + startPosition.x,(( -Row * CHILD_HEIGHT) + startPosition.y));
					//System.out.println("("+ ((Col * CHILD_WIDTH) + startPosition.x)+","+ (( Row * CHILD_HEIGHT) + startPosition.y)+")");
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
			
			//Rectangle bBox = getBoundingBox();
			//bBox.grow(-20,-40);
			//Engine.gameContainer.getGraphics().fill(bBox);
			
		}
		
		Vector<EntityBase> getBlocksInRadius(Vector2f Pos, float Radius)
		{
			Vector<EntityBase> BlockList = new Vector<EntityBase>();
			Circle radCircle = new Circle(Pos.x,Pos.y,Radius);
			
			for (WorldBlock[] WRow : blocks)
			{
				for (WorldBlock WBlock : WRow)
				{
					if (radCircle.intersects(WBlock.getBoundingBox()))
					{
						BlockList.add(WBlock);
						//System.out.println("Added block!");
					}
				}
			}
			/*
			for (int Row = 0; Row < ROWS; Row++)
			{
				for (int Col = 0; Col < COLUMNS; Col++)
				{
					if (radCircle.contains(blocks[Row][Col].getBoundingBox()))
					{
						BlockList.add(blocks[Row][Col]);
					}
				}
			}*/
			
			return BlockList;
		}
		
}

