import org.lwjgl.util.vector.Vector2f;


/** 
 * EntityPosition describes an entity that holds a worldly position. This entity is not drawable, and serves as a utility-esq class for entities that need position coordinates.
 * @author DanielMartin
 * @since Oct/6/2013
 * @category Entity
 * @param pos A 2D coordinate position.
 * @param scale A floating point scale representation of the entity.
 * @param width Entity width.
 * @param height Entity height.
 * @see EntityBase
 *
 */


public class EntityPosition extends EntityBase
{
	private static final long serialVersionUID = 1L;
	
	Vector2f pos = new Vector2f(0,0);
	float scale;
	float width,height;
	
	EntityPosition()
	{
		setPos(0,0,0);
		width = 0;
		height = 0;
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
	
	void setScale(float Scale)
	{
		scale = Scale;
		width *= scale;
		height *= scale;
	}
	
	void shiftPos(float X, float Y)
	{
		pos.x += X;
		pos.y += Y;
	}
	
	boolean inRadius(Vector2f Pos, float Radius)
	{
		return ( (Math.abs(Pos.x - this.pos.x) <= Radius ) && (Math.abs(Pos.y - this.pos.y) <= Radius ));
	}
	
	boolean inRadus(EntityPosition Entity, float Radius)
	{
		return inRadius(Entity.pos, Radius);
	}
	
	/*** Return The (absolute) distance from a position to this entity.*/
	float distance(Vector2f Pos)
	{
		return (float) Math.sqrt( Math.pow((Pos.x - this.pos.x), 2) + Math.pow((Pos.y - this.pos.y), 2));
	}
	
	/*** Return The (absolute) distance from another entity to this entity.*/
	float distance(EntityPosition Entity)
	{
		return distance(Entity.pos);
	}
	
}
