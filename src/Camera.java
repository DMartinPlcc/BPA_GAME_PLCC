// Daniel Martin Oct 13, 2013
// Purpose: To aid in the camera's movement, independent of the player.

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Camera 
{	
	EntityBase parentEntity = null;
	
	

	Camera(EntityBase ParentEntity)
	{
		parentEntity = ParentEntity;
	}

	Vector2f getParentFrustrumPos()
	{
		if (parentEntity != null)
		{
			Vector2f fPos = new Vector2f();
			fPos.x = parentEntity.getPos().x-(Engine.gameContainer.getWidth()/2);
			fPos.y = parentEntity.getPos().y-(Engine.gameContainer.getHeight()/2);
			return fPos;
		}
		else
		{
			System.out.println("Invalid parent for getParentFrustrum()!");
			return new Vector2f(0,0);
		}
	}
	
	Rectangle getParentFrustrum()
	{
		if (parentEntity != null)
		{
		Vector2f VecPos = getParentFrustrumPos();
		return new Rectangle(VecPos.x, VecPos.y, Engine.gameContainer.getWidth(), Engine.gameContainer.getHeight());
		}
		else
		{
			System.out.println("Invalid parent for getParentFrustrum()!");
			return new Rectangle(0, 0, 0, 0);
		}
	}
	
	void Translate()
	{
		Vector2f newPos = getParentFrustrumPos();
		Engine.gameContainer.getGraphics().translate(-newPos.x,-newPos.y);
	}
	
}
