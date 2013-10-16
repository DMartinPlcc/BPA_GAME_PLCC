//Bruce, Jacob - 10-14-13
//Defines the bounding box for the entity 
import org.newdawn.slick.geom.Rectangle;


public class EntityPhysics extends EntityPosition
{
	private static final long serialVersionUID = 1L;

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
	

	
	EntityPhysics(EntityPosition vis)
	{
		rect = new Rectangle(vis.x, vis.y, vis.width, vis.height);
		
		
	}
	
	void setScale(float scale)
	{
		super.setScale(scale);
		rect = new Rectangle(x, y, width, height);
	}
	
	boolean isColliding(EntityPhysics phys)
	{
		return rect.intersects(phys.rect);
	}
}
