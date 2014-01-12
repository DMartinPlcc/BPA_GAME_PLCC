import java.util.Vector;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;


public class VerticalChunk
{
	private ChunkTemplate[] chunksY;
	
	static final int 	MAX_CHUNKS_Y 	= 40;
	static final int 	CHILD_WIDTH 	= ChunkTemplate.WIDTH;
	static final int 	CHILD_HEIGHT 	= ChunkTemplate.HEIGHT;
	static final int 	WIDTH 			= CHILD_WIDTH;
	static final int 	HEIGHT 			= CHILD_HEIGHT * MAX_CHUNKS_Y;
		
	int relativeOffsetX;
	int pixelOffsetX;
	

	VerticalChunk(int OffsetX)
	{
		chunksY = new ChunkTemplate[MAX_CHUNKS_Y];
		relativeOffsetX = OffsetX;
		
		pixelOffsetX = relativeOffsetX * WIDTH;		
		populateChunks();
	}
	
	private void populateChunks()
	{
		for (int i = 0; i < MAX_CHUNKS_Y; i++)
		{
			chunksY[i] = new ChunkTemplate(pixelOffsetX, -i * ChunkTemplate.HEIGHT);
		}
		
	}
	
	Rectangle getBoundingBox()
	{
		return new Rectangle(pixelOffsetX,-HEIGHT,WIDTH,HEIGHT);
	}
	
	void draw()
	{
		for (ChunkTemplate CTemplate : chunksY)
		{
			if (Engine.gameWorld.player.camera.getParentFrustrum().intersects(CTemplate.getBoundingBox()) == true)
			{
				CTemplate.draw();
			}
		}
	}
	
	
	Vector<EntityBase> getBlocksInRadius(Vector2f Pos, float Radius)
	{
		Vector<EntityBase> Blocks = new Vector<EntityBase>();
		// Use witchcraft to do an offset estimation.
		
	

		
		Circle radCircle = new Circle(Pos.x, Pos.y, Radius);
		
		for (ChunkTemplate CTemplate : chunksY)
		{

			if (radCircle.intersects(CTemplate.getBoundingBox()))
			{
				//System.out.println("Added ChunkTemplate!");
				Blocks.addAll(CTemplate.getBlocksInRadius(Pos, Radius));
			}

		}
		
		return Blocks;
		
		
	}
	
}