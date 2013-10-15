// Daniel Martin Oct 8
// Purpose: A placeholder for world blocks. The extending class will most likely be changed from EntityStatic.

public class WorldBlock extends EntityImage
{
	private static final long serialVersionUID = 1L;
	
	// Width/Height of a WorldBlock as a whole.
	static final int WIDTH  = 20;
	static final int HEIGHT = 20;
	
	WorldBlock(float X, float Y, float Scale)
	{
		x = X;
		y = Y;
		scale = Scale;
	}

}
