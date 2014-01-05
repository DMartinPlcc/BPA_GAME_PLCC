import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;


public class WorldWriter
{

	//Streamwriter write block by block to fill screen exactly, saving blocks and destructing blocks the player passes?
	
	WorldBlock blockList[][] = null;
	
	
	// This is used to help determine the part of the world to draw.
	Camera frustrumCamera = null; 
	
	
	class Chunk
	{
		int rows,columns;
		int beginX,beginY;
		
		WorldBlock blocks[][] = null;
		
		int getTotalBlocks()
		{
			return rows*columns;
		}
		
		float getMaxX()
		{
			return columns*WorldBlock.WIDTH;
		}
		float getMaxY()
		{
			return rows*WorldBlock.HEIGHT;
		}
		
		Rectangle getRectangle()
		{
			return new Rectangle(beginX, beginY, getMaxX(), getMaxY());
		}
		
		boolean BinarySearch(Vector2f Position)
		{
			
			return true;
		}

		
		
	}
	
	
	
}
