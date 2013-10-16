import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

//import java.util.Deque;

// Daniel Martin Oct/6/2013 // Edited: Clayton Hubbell Oct. 16th, 2013
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
	
	long lastUpdate;
	long nextUpdate;
	

	World()
	{
		camera = new Camera(0,0);
		instanceList = new WorldInstance[ROWS][COLUMNS];
		populate();
	}
	
	void pushInstance(int X, int Y)
	{
		//push right
		if(X == 1)
		{
			
				instanceList[0][0] = instanceList[0][1];				
				instanceList[0][1] = new WorldInstance(instanceList[0][1].precedingX+1,instanceList[0][1].precedingY);
						
				instanceList[1][0] = instanceList[1][1];				
				instanceList[1][1] = new WorldInstance(instanceList[1][1].precedingX+1,instanceList[1][1].precedingY);
			
		}
		else // push left
			if(X == -1)
			{
				instanceList[0][1] = instanceList[0][0];
				instanceList[0][0] = new WorldInstance(instanceList[0][0].precedingX-1,instanceList[0][0].precedingY);		
				
				
				instanceList[1][1] = instanceList[1][0];
				instanceList[1][0] = new WorldInstance(instanceList[1][0].precedingX-1,instanceList[1][0].precedingY);			
				
			}
		//push up
		if (Y == 1)
		{
			instanceList[1][0] = instanceList[0][0];		
			instanceList[0][0] =  new WorldInstance(instanceList[0][0].precedingX,instanceList[0][0].precedingY-1);
			
			
			instanceList[1][1] = instanceList[0][1];		
			instanceList[0][1] =  new WorldInstance(instanceList[0][1].precedingX,instanceList[0][1].precedingY-1);
		
		}
		else//push down
			if(Y == -1)
			{
				instanceList[0][0] = instanceList[1][0];
				instanceList[1][0] = new WorldInstance(instanceList[1][0].precedingX,instanceList[1][0].precedingY+1);		
				
				
				instanceList[0][1] = instanceList[1][1];
				instanceList[1][1] = new WorldInstance(instanceList[1][1].precedingX,instanceList[1][1].precedingY+1);				
				
			}
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
		lastUpdate = gc.getTime();
		if (lastUpdate >= nextUpdate)
		{
			nextUpdate = lastUpdate + 500;
			if (gc.getInput().isKeyDown(Input.KEY_E))
			{
				System.out.println("Added Right!");
				pushInstance(1,0);
			}
			if (gc.getInput().isKeyDown(Input.KEY_Q))
			{
				System.out.println("Added Left!");
				pushInstance(-1,0);
			}
			
			if (gc.getInput().isKeyDown(Input.KEY_X))
			{
				System.out.println("Added Down!");
				pushInstance(0,-1);
			}
			if (gc.getInput().isKeyDown(Input.KEY_C))
			{
				System.out.println("Added Up!");
				pushInstance(0,1);
			}
			
			
		}
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
