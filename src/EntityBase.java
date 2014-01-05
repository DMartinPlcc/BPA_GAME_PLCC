import org.lwjgl.util.vector.Vector2f;

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
	
	int health;
	int baseHealth;
	int maxHealth;
	
	Vector2f pos = new Vector2f(0,0);
	float scale;
	float width,height;
	
	private float baseWidth, baseHeight;
	
	
	
	EntityBase()
	{
		SetupBaseHealth(10);
		setPos(0,0,0);
		baseWidth = 0;
		baseHeight = 0;
		width = 0;
		height = 0;
	}
	
	EntityBase(int BaseHealth)
	{
		this();
		SetupBaseHealth(BaseHealth);
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
		pos.x = X;
		pos.y = Y;
	}
	
	void setPos(float X, float Y, float Scale)
	{
		setPos(X,Y);
		scale = Scale;
	}
	
	void setPos(Vector2f Position)
	{
		pos = Position;
	}
	
	Vector2f getPos()
	{
		return pos;
	}
	
	void addPos(Vector2f AddPos)
	{
		setPos(pos.x+AddPos.x,pos.y+AddPos.y);
	}
	
	void addPos(float X, float Y)
	{
		pos.x += X;
		pos.y += Y;
	}
	
	
	void setScale(float Scale)
	{
		scale = Scale;
		width = baseWidth * scale;
		height = baseHeight * scale;
	}
	
	void setWidth(float Width)
	{
		width = Width;
	}
	void setHeight(float Height)
	{
		height = Height;
	}
	
	float getBaseWidth()
	{
		return baseWidth;
	}
	float getBaseHeight()
	{
		return baseHeight;
	}
	
	/** Return width and height to their initial defaults. */
	void useBaseDimensions()
	{
		width = baseWidth;
		height = baseHeight;
	}
	
	boolean inRadius(Vector2f Pos, float Radius)
	{
		return ( (Math.abs(Pos.x - this.pos.x) <= Radius ) && (Math.abs(Pos.y - this.pos.y) <= Radius ));
	}
	
	boolean inRadus(EntityBase Entity, float Radius)
	{
		return inRadius(Entity.pos, Radius);
	}
	
	/*** Return The (absolute) distance from a position to this entity.*/
	float distance(Vector2f Pos)
	{
		return (float) Math.sqrt( Math.pow((Pos.x - this.pos.x), 2) + Math.pow((Pos.y - this.pos.y), 2));
	}
	
	/*** Return The (absolute) distance from another entity to this entity.*/
	float distance(EntityBase Entity)
	{
		return distance(Entity.pos);
	}


	void update() 
	{

	}
	
	public void SetupBaseHealth(int BaseHealth)
	{
		baseHealth = BaseHealth;
		health = baseHealth;
		maxHealth = baseHealth;
	}
	

	void setHealth(int Health)
	{
		health = Health;
	}	
	void setBaseHealth(int BaseHealth)
	{
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
	
	

}
