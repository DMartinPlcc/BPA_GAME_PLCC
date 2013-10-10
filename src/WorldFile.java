
public class WorldFile 
{
	static int MAX_FLOAT = 2000000000; // Two billion
	static int MaxChildrenX = (int) (MAX_FLOAT/((WorldBlock.Width*WorldChunk.Columns)*WorldSlice.Columns));
	static int MaxChildrenY = (int) (MAX_FLOAT/((WorldBlock.Height*WorldChunk.Rows)*WorldSlice.Rows));
	
	String 	Instance_FileName;
	int 	Instance_X;
	int 	Instance_Y;
}
