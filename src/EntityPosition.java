// Daniel Martin Oct/6/2013
// Purpose: Basic extensible class for entities requiring position in the world.

public class EntityPosition extends EntityBase
{
	private static final long serialVersionUID = 1L;
	
	float x,y,scale;
	float width,height;
	
	void setPos(float X, float Y)
	{
		x = X;
		y = Y;
	}
	
	void setPos(float X, float Y, float Scale)
	{
		x = X;
		y = Y;
		scale = Scale;
	}
	
	void setScale(float Scale)
	{
		scale = Scale;
		width *= scale;
		height *= scale;
	}
	
}
