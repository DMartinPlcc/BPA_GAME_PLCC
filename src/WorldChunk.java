// Daniel Martin Oct/6/2013
// Purpose: Contains an X by Y matrix of world blocks.
// Currently for testing purposes only. 

//NOTE: This only works with an image that has a pixel size of 50x50.
// ALL *BLOCK* Assets will be a power of, or equally divisible by TEN!!!
public class WorldChunk
{
	//Size of a single block. Total Chunk width computed by NUM_COL*BLOCKSIZE_X 
	static float BLOCKSIZE_X = 50;
	static float BLOCKSIZE_Y = 50;
	
	static int NUM_ROW = 10;
	static int NUM_COL = 10;
	
	static float CHUNK_WIDTH  = NUM_COL * BLOCKSIZE_X;
	static float CHUNK_HEIGHT = NUM_ROW * BLOCKSIZE_Y;
	
	float Begin_X;
	float Begin_Y;
	
	
	
	EntityStatic Blocks[][] = new EntityStatic[NUM_ROW][NUM_COL];
	
	// Because all chunks are the same size, we compute the offset by multiplying the
	// total size of a single chunk's width by the total number of chunks. Giving a proper offset.
	WorldChunk(int NumPrecedingChunks)
	{
		Begin_X = CHUNK_WIDTH*(NumPrecedingChunks);
		Begin_Y = 0;//CHUNK_HEIGHT;//CHUNK_HEIGHT*(NumPrecedingChunks+1);
		
		//System.out.println("Begin_X: "+Begin_X+" | ChunkWidth: "+CHUNK_WIDTH);
		//System.out.println("Begin_Y: "+Begin_Y+" | ChunkHeight: "+CHUNK_HEIGHT);
	}
	
	// Y_Offset by chunk size. Used to put chunks under chunks.
	WorldChunk(int NumPrecedingChunks,float Y_Offset)
	{
		// Offset new slices so we don't have overlapping chunks/blocks.
		Begin_X = CHUNK_WIDTH*(NumPrecedingChunks);
		Begin_Y = Y_Offset*CHUNK_HEIGHT;
		
		//System.out.println("Begin_X: "+Begin_X+" | ChunkWidth: "+CHUNK_WIDTH);
		//System.out.println("Begin_Y: "+Begin_Y+" | ChunkHeight: "+CHUNK_HEIGHT);
	}
	
	
	
	
	// Populate chunk with a matrix of EntityStatic(s).
	void Populate()
	{

		for (int Row = 0; Row < NUM_ROW; Row++)
		{
			for (int Col = 0; Col < NUM_COL; Col++)
			{
				Blocks[Row][Col] = new EntityStatic();
				Blocks[Row][Col].m_X = ( Col * BLOCKSIZE_X) + Begin_X;
				Blocks[Row][Col].m_Y = ( Row * BLOCKSIZE_Y) + Begin_Y;
				Blocks[Row][Col].SetScale(1);
			}
		}	
	}
	
	void Draw()
	{
		for (int Row = 0; Row < NUM_ROW; Row++)
		{
			for (int Col = 0; Col < NUM_COL; Col++)
			{
				Blocks[Row][Col].draw();
			}
		}	
		

	}
}
