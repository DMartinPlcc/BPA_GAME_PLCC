import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;



public class EntityPlayer extends EntityBaseCharacter
{
	private static final long serialVersionUID = 1L;
	Camera camera = null;
	
	
	EntityPlayer()
	{
		setBaseHealth(10);
		setScale(2);
		camera = new Camera(this);
	}

	
	void update()
	{
		int speedMod = 1;
		if (Engine.isKeyDown(Input.KEY_LSHIFT))
		{
			speedMod = 10;
		}
		if (Engine.isKeyDown(Input.KEY_D))
		{
			addPos(1*speedMod,0);
		}
		if (Engine.isKeyDown(Input.KEY_W))
		{
			addPos(0,1*speedMod);
		}
		if (Engine.isKeyDown(Input.KEY_A))
		{
			addPos(-1*speedMod,0);
		}
		if (Engine.isKeyDown(Input.KEY_S))
		{
			addPos(0,-1*speedMod);
		}
		
		if (Engine.isKeyDown(Input.KEY_F1))
		{
			setPos(33,40);
		}
		
		if (Engine.isKeyDown(Input.KEY_LEFT))
		{
			velocity.add(new Vector2f(-1,0));
		}
		if (Engine.isKeyDown(Input.KEY_RIGHT))
		{
			velocity.add(new Vector2f(1,0));
		}
		if (Engine.isKeyDown(Input.KEY_UP))
		{
			velocity.add(new Vector2f(0,1));
		}
		if (Engine.isKeyPressed(Input.KEY_DOWN))
		{
			velocity.add(new Vector2f(0,-1));
		}
		
		if (Engine.isKeyPressed(Input.KEY_SPACE))
		{
			velocity.add(new Vector2f(0,5));
		}
		//System.out.println("Pos("+getPos().x+","+getPos().y+")");
		//playerCamera.setPos(getPos());
		
		
		draw();
		Engine.flipYAxis(false);
		Engine.gameContainer.getGraphics().drawString("("+getPosX()+","+getPosY()+")",getPosX(),getPosY());
		

	}

}
