// Daniel Martin Oct 13, 2013
// Purpose: To aid in the camera's movement, independent of the player.

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Camera 
{	
	EntityBase parentEntity = null;
	
	

	Camera(EntityBase ParentEntity)
	{
		parentEntity = ParentEntity;
	}

	Vector2f getParentFrustrum()
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
	
	void Translate()
	{
		Vector2f newPos = getParentFrustrum();
		Engine.gameContainer.getGraphics().translate(-newPos.x,-newPos.y);
		
	}
	
}
