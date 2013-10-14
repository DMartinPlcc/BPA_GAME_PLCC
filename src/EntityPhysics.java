import org.newdawn.slick.geom.Rectangle;


public class EntityPhysics extends EntityVisible
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
	

	
	EntityPhysics(EntityVisible vis)
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
