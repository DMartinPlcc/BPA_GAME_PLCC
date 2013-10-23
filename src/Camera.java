// Daniel Martin Oct 13, 2013
// Purpose: To aid in the camera's movement, independent of the player.

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Camera extends EntityPosition
{
	private static final long serialVersionUID = 1L;
	
	Camera(float X, float Y)
	{
		setPos(X,Y);
	}
	void draw(Graphics g)
	{
		g.translate(-pos.x,-pos.y);
	
	}
	
	void update(GameContainer gc)
	{
		int MoveSpeed = 10;
		
		if (gc.getInput().isKeyDown(Input.KEY_D))
		{
			pos.x += MoveSpeed;
		}
		else if (gc.getInput().isKeyDown(Input.KEY_A))
		{
			pos.x -= MoveSpeed;
		}
		if (gc.getInput().isKeyDown(Input.KEY_S))
		{
			pos.y += MoveSpeed;
		}
		else if (gc.getInput().isKeyDown(Input.KEY_W))
		{
			pos.y -= MoveSpeed;
		}
		
	}
}
