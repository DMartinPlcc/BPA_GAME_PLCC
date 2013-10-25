//Bruce, Jacob - 10-14-13
//Defines the bounding box for the entity 

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.geom.Rectangle;

public class EntityPhysics extends EntityPosition
{
	public class PhysicalProperties
	{
		float bounce,mass,friction;
		PhysicalProperties()
		{
			bounce = 0;
			mass = 0;
			friction = 0;
		}
		PhysicalProperties(float Bounce)
		{
			this();
			bounce = Bounce;
		}
		PhysicalProperties(float Bounce, float Mass)
		{
			this(Bounce);
			mass = Mass;
		}
		PhysicalProperties(float Bounce, float Mass, float Friction)
		{
			this(Bounce, Mass);
			friction = Friction;
		}
	}
	
	Rectangle rect;
	PhysicalProperties physProperties;
	Vector2f velocity;
	enum collisionRules {NONE,PLAYER,GEOMETRY,ALL};
	private static final long serialVersionUID = 1L;
	
	EntityPhysics()
	{
		super();
		
		rect = new Rectangle(1,1,1,1);
		physProperties = new PhysicalProperties(0);
		velocity = new Vector2f(0,0);
	}
	
	EntityPhysics(EntityPosition vis)
	{
		rect = new Rectangle(vis.pos.x, vis.pos.y, vis.width, vis.height);
		physProperties = new PhysicalProperties(1);
		
	}
	
	void setScale(float scale)
	{
		super.setScale(scale);
		rect = new Rectangle(pos.x, pos.y, width, height);
	}
	
	boolean isColliding(EntityPhysics phys)
	{
		return rect.intersects(phys.rect);
	}
	
	void standardCollision(EntityPhysics phys)
	{
		if (isColliding(phys))
		{
			this.pos.y = phys.height;
			this.velocity.y = 0;
		}
	}
	
	void handleCollision(EntityPhysics phys)
	{
		standardCollision(phys);
	}
}
