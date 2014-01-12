import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Vector;

import org.newdawn.slick.geom.Vector2f;

/** Creates vertical chunks of the world, adding them to a linked list, and handles drawing and saving.
 * 
 * @author Danny
 *
 */
public class WorldStreamer
{
	LinkedList<VerticalChunk> worldDeque = new LinkedList<VerticalChunk>();
	
	private WorldSave 	instanceLeft;
	private WorldSave 	instanceRight;
	private String 		worldName;
	
	static final int MAX_CHUNKS = WorldSave.MAX_WORLD_XY;
	private int totalLeft = 0;
	private int totalRight = 0;
	
	
	public WorldStreamer()
	{
		// The first call adds a chunk at (0,0) regardless of if it's added to the left or right.
		addVerticalChunk(true); 
	}
	
	int getOffset(boolean isPositive)
	{
		if (worldDeque.size() > 0)
		{
			
			//System.out.println("PeekFirst: "+worldDeque.peekFirst().relativeOffsetX+" | PeekLast: "+worldDeque.peekLast().relativeOffsetX+"");
			if (isPositive == true)
			{
				//System.out.println("Positive");
				return worldDeque.peekFirst().relativeOffsetX+1;
			}
			if (!isPositive)
			{
				//System.out.println("Negitive");
				return worldDeque.peekLast().relativeOffsetX-1;
			}
			
		}
		//System.out.println("WorldStreamer: getOffset() - returned 0.");
		return 0;
	}
		
	void addVerticalChunk(boolean isRight)
	{
		if(isRight == true)
		{
			addTotalRight();
			worldDeque.addFirst(new VerticalChunk(getOffset(true)));
		}
		else
		{
			addTotalLeft();
			worldDeque.addLast(new VerticalChunk(getOffset(false)));
		}
	}

	VerticalChunk popVerticalChunk(boolean isRight)
	{
		if(isRight == true)
		{
			return worldDeque.pollFirst();
		}
		else
		{
			return worldDeque.pollLast();
		}
	}
	
	void addTotalLeft()
	{
		totalLeft++;
		
		/*
		if (totalLeft >= 100)
		{
			// Shift the entire world WORLDSAVE units to the right
			// Save instanceLeft
			// Save instanceRight, then destroy.
			// Swap instanceLeft with instanceRight
			// Create/load new InstanceLeft
			// I'M PROBABLY WRONG AND CRAZY, I'LL THINK OF THE LOGIC LATER DAMNIT!
			
			totalLeft = 0;
			instanceLeft.save();
			instanceLeft = instanceRight;
			instanceLeft = new WorldSave(instanceLeft.worldName,instanceLeft.xOffset);
		}
		*/
	}
	
	void addTotalRight()
	{
		totalRight++;
	}
	
	void draw()
	{
		for(VerticalChunk vChunk : worldDeque)
		{
			// Using the player's camera's frustrum (viewing volume) and see if it can "see" this VerticalChunk
			if (vChunk.getBoundingBox().intersects(Engine.gameWorld.player.camera.getParentFrustrum()))
			{
				vChunk.draw();
			}
		}
	}
	
	Vector<EntityBase> getBlocksInRadius(Vector2f Pos, float Radius)
	{
		Vector<EntityBase> Blocks = new Vector<EntityBase>();

		

		
		for (VerticalChunk VC : worldDeque)
		{
			//System.out.println("Added VerticalChunk!");
			Blocks.addAll(VC.getBlocksInRadius(Pos, Radius));
		}
		
		return Blocks;
		
		
	}
}
