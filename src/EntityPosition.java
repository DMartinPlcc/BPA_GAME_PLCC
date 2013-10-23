import org.lwjgl.util.vector.Vector2f;

// Daniel Martin Oct/6/2013
// Purpose: Basic extensible class for entities requiring position in the world.

public class EntityPosition extends EntityBase
{
	private static final long serialVersionUID = 1L;
	
	Vector2f pos = new Vector2f(0,0);
	float scale;
	float width,height;
	
	void setPos(float X, float Y)
	{
		
		pos.x = X;
		pos.y = Y;
	}
	
	void setPos(float X, float Y, float Scale)
	{
		setPos(X,Y);
		scale = Scale;
	}
	
	void setScale(float Scale)
	{
		scale = Scale;
		width *= scale;
		height *= scale;
	}
	
}
