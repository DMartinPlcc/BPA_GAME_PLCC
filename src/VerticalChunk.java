import org.newdawn.slick.geom.Vector2f;


public class VerticalChunk
{
	static final int MAX_CHUNKS_Y = 1;
	private ChunkTemplate[] chunksY;
	
	int relativeOffsetX;
	int pixelOffsetX;
	
	VerticalChunk(int OffsetX)
	{
		chunksY = new ChunkTemplate[MAX_CHUNKS_Y];
		relativeOffsetX = OffsetX;
		
		pixelOffsetX = relativeOffsetX * ChunkTemplate.WIDTH;		
		populateChunksY();
	}
	
	private void populateChunksY()
	{
		for (int i = 0; i < MAX_CHUNKS_Y; i++)
		{
			chunksY[i] = new ChunkTemplate(pixelOffsetX, i * ChunkTemplate.HEIGHT);
		}
		
	}
	
	
	void draw()
	{
		for (ChunkTemplate CTemplate : chunksY)
		{
			CTemplate.draw();
		}
	}
	
	
}