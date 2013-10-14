import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

// Daniel Martin Oct 13, 2013
// Purpose: To aid in the camera's movement, independent of the player.

public class Camera extends EntityVisible
{
	private static final long serialVersionUID = 1L;
	
	Camera(float X, float Y)
	{
		x = X;
		y = Y;
	}
	void draw(Graphics g)
	{
		g.translate(x,y);
	
	}
	
	void update(GameContainer gc)
	{
		int MoveSpeed = 5;
		
		if (gc.getInput().isKeyDown(Input.KEY_D))
		{
			x -= MoveSpeed;
		}
		else if (gc.getInput().isKeyDown(Input.KEY_A))
		{
			x += MoveSpeed;
		}
		if (gc.getInput().isKeyDown(Input.KEY_S))
		{
			y -= MoveSpeed;
		}
		else if (gc.getInput().isKeyDown(Input.KEY_W))
		{
			y += MoveSpeed;
		}
		
	}
}
