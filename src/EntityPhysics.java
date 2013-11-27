//Bruce, Jacob - 10-14-13
//Defines the bounding box for the entity 

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.geom.Rectangle;

/**
 * The basis class for entities that must collide with, and react to other worldly entities.
 * @author Bruce
 * @author Jacob
 * @since  10/14/13
 * @see EntityPosition
 * @category Entity
 */
public class EntityPhysics extends EntityPosition
{
	/** 
	 * Generic information on how the entity should react to stimuli.
	 * @param bounce Entity bounce scale.
	 * @param mass Entity "weight", dependent upon gravity.
	 * @param friction Entity friction, resistance to movement.
	 * @author Bruce
	 * @author Jacob
	 *
	 */
	public class PhysicalProperties
	{
		float bounce,mass,friction;
		collisionRules collisionRule;
		
		PhysicalProperties()
		{
			collisionRule = collisionRules.ALL;
			bounce = 0;
			mass = 0;
			friction = 0;
		}
		PhysicalProperties(float Bounce, float Mass, float Friction)
		{
			this();
			bounce = Bounce;
			mass = Mass;
			friction = Friction;
		}
	}
	
	PhysicalProperties physProperties;
	Vector2f velocity;
	enum collisionRules {NONE,PLAYER,ITEM,GEOMETRY,ALL};
	private static final long serialVersionUID = 1L;
	
	Rectangle getRect()
	{
		return new Rectangle(pos.x,pos.y, height, width);
	}
	
	EntityPhysics()
	{
		super();
		physProperties = new PhysicalProperties();
		velocity = new Vector2f(0,0);
	}
	
	EntityPhysics(EntityPosition vis)
	{
		physProperties = new PhysicalProperties();
	}
	
	void setScale(float scale)
	{
		super.setScale(scale);
	}
	
	boolean isColliding(EntityPhysics phys)
	{
		return getRect().intersects(phys.getRect());
	}
	
	
	void handleCollision(EntityPhysics phys)
	{
		if (isColliding(phys))
		{
			this.pos.y = phys.height;
			this.velocity.y = 0;
		}
	}
}
