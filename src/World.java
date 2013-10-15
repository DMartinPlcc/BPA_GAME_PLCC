import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

// Daniel Martin Oct/6/2013
// Purpose: Placeholder for entity, world, and other asset data representing the game world.
public class World implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;	
	static int ROWS = 2;
	static int COLUMNS = 2;
	
	MetaSave metaData;
	WorldInstance instanceList[][];
	
	Camera camera;
	int precedingChildrenX; 
	int precedingChildrenY; 
	
	String worldName;
	int numDeletedInstances;
	

	World()
	{
		camera = new Camera(0,0);
		instanceList = new WorldInstance[ROWS][COLUMNS];
		populate();
	}
	
	
	void populate()
	{
		int TempPreceedX = 0;
		int TempPreceedY = 0;
		
		for (int Col = 0; Col < COLUMNS; Col++)
		{
			
			for (int Row = 0; Row < ROWS; Row++)
			{
				
				// Create offset, use Row to specify how many chunks are layered under each other.
				//System.out.println("worldInstance["+Row+"]["+Col+"] = ("+TempPreceedX+","+TempPreceedY+")");
				instanceList[Row][Col] = new WorldInstance(TempPreceedX,TempPreceedY);
				TempPreceedY++;

			}
			TempPreceedY = 0;
			TempPreceedX++;

		}	
	}
	
	void update(GameContainer gc)
	{
		camera.update(gc);
	}
	
	void draw(Graphics g)
	{
		camera.draw(g);
		for (int row = 0; row < ROWS; row++)
		{
			for (int column = 0; column < COLUMNS; column++)
			{
				
				instanceList[row][column].draw(camera.x, camera.y);
				
			}
		}
	}
	
}
