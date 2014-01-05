import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Input;


public class EntityPlayer extends EntityBaseCharacter
{
	private static final long serialVersionUID = 1L;
	Camera playerCamera = null;
	
	
	EntityPlayer()
	{
		setBaseHealth(10);
		setScale(5);
		playerCamera = new Camera(this);
	}
	
	EntityPlayer(Vector2f Pos)
	{
		setBaseHealth(10);
		setPos(Pos);
		setScale(5);
		playerCamera = new Camera(this);
	}
	
	
	void update()
	{
		
		if (Engine.isKeyDown(Input.KEY_D))
		{
			addPos(1,0);
		}
		if (Engine.isKeyDown(Input.KEY_W))
		{
			addPos(0,-1);
		}
		if (Engine.isKeyDown(Input.KEY_A))
		{
			addPos(-1,0);
		}
		if (Engine.isKeyDown(Input.KEY_S))
		{
			addPos(0,1);
		}
		
		//System.out.println("Pos("+getPos().x+","+getPos().y+")");
		//playerCamera.setPos(getPos());
		
		
		draw();
		Engine.gameContainer.getGraphics().drawString("("+pos.x+","+pos.y+")", pos.x,pos.y);
		Engine.gameContainer.getGraphics().drawString("Y Up = Down!", pos.x,pos.y+10);


	}

}
