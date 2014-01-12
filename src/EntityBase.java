
import java.util.Vector;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/** 
 * EntityBase serves as the generic entity base. This class describes the entity's filter type, intention, and allows for basic serialization. 
 * @author DanielMartin
 * @author Bruce
 * @author Jacob
 * @category Entity
 * @param pos A 2D coordinate position.
 * @param scale A floating point scale representation of the entity.
 * @param width Entity width.
 * @param height Entity height.
 * @since Oct/10/2013.
 */

public class EntityBase extends Saveable
{	
	private static final long serialVersionUID = 1L;
	
	private int health 		= 0;
	private int baseHealth	= 0;
	private int maxHealth 	= 0;
	
	private float scale 	 = 1;
	private float baseWidth  = 0;
	private float baseHeight = 0;
	
	private Vector2f lastPos = new Vector2f(0,0);
	private Rectangle boundingBox = new Rectangle(0,0,0,0);
	
	Vector<EntityBase>  attachmentList = null;
	
	Vector2f attachmentOffset = null;
	
 	EntityBase()
	{
 		attachmentList = new Vector<EntityBase>();
 		attachmentOffset = new Vector2f(0,0);
 		
		setupBaseHealth(10);
		setPos(0,0);
		scale = 1;
		baseWidth = 0;
		baseHeight = 0;
		boundingBox = new Rectangle(0,0,0,0); 
	}

	float getScale()
	{
		return scale;
	}
	
	void setBaseWidth(float BaseWidth)
	{
		baseWidth = BaseWidth;
	}
	void setBaseHeight(float BaseHeight)
	{
		baseHeight = BaseHeight;
	}
	
	void setPos(float X, float Y)
	{
		setPos(new Vector2f(X,Y));
	}
	void setPos(Vector2f Pos)
	{
		lastPos = boundingBox.getLocation();
		boundingBox.setLocation(Pos);
		
		for(EntityBase Attachment : attachmentList)
		{
			Attachment.setPos(getPos().add(Attachment.getAttachmentOffset()));
		}
	}
	
	void setPosX(float X)
	{
		setPos(new Vector2f(X,getPosY()));
	}
	void setPosY(float Y)
	{
		setPos(new Vector2f(getPosX(),Y));
	}
	
	Vector2f getPos()
	{
		return boundingBox.getLocation();
	}
	float getPosX()
	{
		return getPos().x;
	}
	float getPosY()
	{
		return getPos().y;
	}
	
	Vector2f getLastPos()
	{
		return lastPos;
	}
	void addPos(Vector2f AddPos)
	{
		setPos(getPos().add(AddPos));
	}
	void addPos(float X, float Y)
	{
		addPos(new Vector2f(X,Y));
	}
	
	void addPosX(float X)
	{
		Vector2f nValue = getPos();
		nValue.x += X;
		setPos(nValue);

	}
	void addPosY(float Y)
	{
		Vector2f nValue = getPos();
		nValue.y += Y;
		setPos(nValue);
	}
	
	void setScale(float Scale)
	{
		scale = Scale;
		boundingBox.setWidth(baseWidth*Scale);
		boundingBox.setHeight(baseHeight*Scale);
	}
	
	/** Return width and height to their initial defaults. */
	void defaultScale()
	{
		scale = 1;
		boundingBox.setWidth(baseWidth);
		boundingBox.setHeight(baseHeight);
	}
	
	void setWidth(float Width)
	{
		if (getWidth() == 0)
		{
			setBaseWidth(Width);
		}
		boundingBox.setWidth(Width);
	}
	void setHeight(float Height)
	{
		if (getHeight() == 0)
		{
			setBaseHeight(Height);
		}
		boundingBox.setHeight(Height);
	}
	
	float getHeight()
	{
		return boundingBox.getHeight();
	}
	float getWidth()
	{
		return boundingBox.getWidth();
	}
	
	float getBaseWidth()
	{
		return baseWidth;
	}
	float getBaseHeight()
	{
		return baseHeight;
	}
	
	boolean inRadius(Vector2f Pos, float Radius)
	{
		return boundingBox.intersects(new Circle(Pos.x, Pos.y, Radius ));
	}
	boolean inRadus(EntityBase Entity, float Radius)
	{
		return inRadius(Entity.getPos(), Radius);
	}
	
	/*** Return The (absolute) distance from a position to this entity.*/
	float distance(Vector2f Pos)
	{
		return getPos().distance(Pos);
	}
	/*** Return The (absolute) distance from another entity to this entity.*/
	float distance(EntityBase Entity)
	{
		return distance(Entity.getPos());
	}


	void update() 
	{

	}
	
	public void setupBaseHealth(int BaseHealth)
	{
		baseHealth = BaseHealth;
		health = baseHealth;
		maxHealth = baseHealth;
	}
	
	Rectangle getBoundingBox()
	{
		return boundingBox;
	}

	void setHealth(int Health)
	{
		if (maxHealth == 0)
		{
			maxHealth = Health;
		}
		if (baseHealth == 0)
		{
			baseHealth = Health;
		}
		
		
		if (health < Health)
		{
			health = maxHealth;
		}
		else
		{
			health = Health;
		}
		
	}	
	void setBaseHealth(int BaseHealth)
	{
		if (baseHealth > maxHealth)
		{
			maxHealth = BaseHealth;
		}
		baseHealth = BaseHealth;
	}	
	void setMaxHealth(int MaxHealth)
	{
		maxHealth = MaxHealth;
	}
	
	int  getHealth()
	{
		return health;
	}	
	int  getBaseHealth()
	{
		return baseHealth;
	}	
	int  getMaxHealth()
	{
		return maxHealth;
	}
	
	boolean intersects(Shape shape)
	{
		return boundingBox.intersects(shape);
	}
	
	boolean intersects(EntityBase EBase)
	{
		return intersects(EBase.getBoundingBox());
	}

	boolean isMovingRight()
	{
		if (getLastPos().x < getPosX())
		{
			return true;
		}
		return false;
	}
	
	boolean isMovingUp()
	{
		if (getLastPos().y < getPosY())
		{
			return true;
		}
		return false;
	}
	
	boolean isMovingBothAxis()
	{
		if (lastPos.x != getPosX() && lastPos.y != getPosY())
		{
			return true;
		}
		return false;
	}
	void addAttachment(EntityBase attachment)
	{
		attachmentList.add(attachment);
	}
	
	Vector2f getAttachmentOffset()
	{
		return attachmentOffset;
	}
	
	Vector2f getMidpointLeft()
	{
		return new Vector2f(boundingBox.getMinX(),boundingBox.getMaxY()-(boundingBox.getHeight()/2));
	}
	
	Vector2f getMidpointRight()
	{		
		return new Vector2f(boundingBox.getMaxX(),boundingBox.getMaxY()-(boundingBox.getHeight()/2));
	}
	
	Vector2f getMidpointTop()
	{
		return new Vector2f(boundingBox.getMaxX()-(getWidth()/2),boundingBox.getMaxY());
	}
	Vector2f getMidpointBottom()
	{
		return  new Vector2f(boundingBox.getMaxX()-(boundingBox.getWidth()/2),boundingBox.getMinY());
	}

	Vector2f getCenter()
	{
		return new Vector2f(boundingBox.getMinX()+(getWidth()/2),boundingBox.getMinY()+(getHeight()/2));
	}
	
	boolean isRightOf(EntityBase otherEntity)
	{
		Rectangle physBox = otherEntity.getBoundingBox();
		if(boundingBox.getMaxX() > physBox.getMinX() && boundingBox.getMaxX() > physBox.getMaxX())
		{
			return true;
		}
		return false;
	}
	
	boolean isAboveOf(EntityBase otherEntity)
	{
		if((boundingBox.getMinY() + (otherEntity.getHeight()/2)) >= otherEntity.getBoundingBox().getMaxY())
		{
			return true;
		}
		return false;	
	}
}
