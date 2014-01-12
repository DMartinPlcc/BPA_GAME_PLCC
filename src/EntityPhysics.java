//Bruce, Jacob - 10-14-13
//Defines the bounding box for the entity 


import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * The basis class for entities that must collide with, and react to other worldly entities.
 * @author Bruce
 * @author Jacob
 * @since  10/14/13
 * @see EntityPosition
 * @category Entity
 */
public class EntityPhysics extends EntityBase
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
	private static final long serialVersionUID = 1L;
		
	enum collisionRules {NONE,BASIC,PLAYER,ITEM,GEOMETRY,ALL};
	float bounce 	= 0.5f;
	float mass 		= 1.0f;
	float friction 	= 1.0f;
	collisionRules collisionRule = collisionRules.ALL;
	Vector2f velocity;
	
	
	EntityPhysics()
	{
		super();
		velocity = new Vector2f(0,0);
	}
	
	// Subtract if positive, add if negitive.
	void degradeVelocity(float Amount)
	{
		if (velocity.x > -1 && velocity.x < 1)
		{
			velocity.x = 0;
		}
		else if (velocity.x > 0)
		{
			velocity.sub(new Vector2f(Amount,0));
		}
		else
		{
			velocity.add(new Vector2f(Amount,0));
		}
		
		
		if (velocity.y > -1 && velocity.y < 1)
		{
			velocity.y = -2;
		}
		else if (velocity.y > 0)
		{
			velocity.sub(new Vector2f(0,Amount));
		}
		else
		{
			velocity.add(new Vector2f(0,Amount));
		}
	}
	
	
	void clampVelocity(Vector2f ValLow, Vector2f ValHigh)
	{
		if (ValLow.x > ValHigh.x || ValLow.y > ValHigh.y )
		{
			System.out.println("clampVelocity() - ValLow > ValHigh!");
			return;
		}
		
		if(velocity.x > ValHigh.x)
		{
			velocity.x = ValHigh.x;
		}
		else if (velocity.x < ValLow.x)
		{
			velocity.x = ValLow.x;
		}
		
		if(velocity.y > ValHigh.y)
		{
			velocity.y = ValHigh.y;
		}
		else if (velocity.y < ValLow.y)
		{
			velocity.y = ValLow.y;
		}
		
		
	}
	void simulate()
	{
		System.out.println(velocity.x+", "+velocity.y);
		addPos(velocity);
		
		//air friction
		degradeVelocity(0.5f);
		
		if (velocity.y > 0)
		{
			velocity.y -= 1f;
		}
		clampVelocity(new Vector2f(-10,-10), new Vector2f(10,10));
		
		
		
	}
	
	void handleCollision(EntityPhysics otherEntity)
	{
		if (this.collisionRule == collisionRules.NONE)
		{
			return;
		}
		else if ( this.collisionRule == collisionRules.PLAYER && otherEntity.collisionRule == collisionRules.ITEM)
		{
			// Pickup the entity and add to inventory
		}
			
		if (intersects(otherEntity))
		{
		
			// Get midpoint distances, compare them to find which side of the bounding box 
			// we hit to properly set position so collision doesn't go through the entity.
			Rectangle physBox 	= otherEntity.getBoundingBox();		
			float Dist_Top 		= this.getMidpointTop().distance(otherEntity.getCenter());
			float Dist_Bottom 	= this.getMidpointBottom().distance(otherEntity.getCenter());
			float Dist_Left 	= this.getMidpointRight().distance(otherEntity.getCenter());
			float Dist_Right 	= this.getMidpointLeft().distance(otherEntity.getCenter());
			
			
			
			if (Dist_Top < Dist_Bottom && Dist_Top < Dist_Left && Dist_Top < Dist_Right)
			{
				//Top of entity hits bottom of other entity
				this.velocity.y = 0;
				this.setPosY(physBox.getMinY()-this.getHeight());
				
			}
			else if (Dist_Bottom < Dist_Left && Dist_Bottom < Dist_Right)
			{
				// Bottom of Entity hits top of other entity
				this.velocity.y = 0;
				this.setPosY(physBox.getMaxY());
				
				
			}
			else if (Dist_Left < Dist_Right)
			{
				//Right of entity hits left of other entity
				this.velocity.x = 0;
				this.setPosX(otherEntity.getBoundingBox().getMinX()-this.getWidth());
				
				
			}
			else
			{
				// Left of entity hits right of other entity
				this.velocity.x = 0;
				this.setPosX(physBox.getMaxX());
				
			}
		
		}
	}
}
