import java.util.Vector;
import org.lwjgl.util.vector.Vector2f;


// Programmer: Daniel Martin 11/15/2013
// Purpose:    To hold a list of all non-world entities and perform basic utility operations on them.
public class EntityList 
{
	Vector<EntityBase> gameEntities = null;
	
	public EntityList()
	{
		gameEntities = new Vector<EntityBase>();
	}
	

	public Vector<EntityBase> getEntities(Vector2f Pos, float Radius, EntityBase.EntityType FilterType)
	{
		//Return a vector of all entities that are within a radius of a position, and match a generic identifier.
		// Some entities don't have a position, they are ignored when searching for radius.
		// Logical filters ignore radius and position. You will receive a list of ALL LOGICAL entities on the map if you specify the LOGCIAL filter.
		// Specifying a Radius of -1 will ignore radius, and search all available entities matching the specified filter type.
		
		Vector<EntityBase> entList = new Vector<EntityBase>();
		for(EntityBase E : gameEntities)
		{
			if (E.entityType.equals(FilterType) || FilterType.equals(EntityBase.EntityType.ALL))
			{
				if (Radius == -1 || FilterType.equals(EntityBase.EntityType.LOGICAL))
				{
					entList.add(E);
					continue;
				}
				
				if (E instanceof EntityPosition)
				{
					EntityPosition RadiusTest = (EntityPosition) E;
					if (RadiusTest.inRadius(Pos, Radius))
					{
						entList.add(RadiusTest);
					}
				}

			}
		}
		return entList;
	}
	
	public Vector<EntityBase> getEntities(EntityBase.EntityType Type)
	{
		return getEntities(new Vector2f(0,0), -1, Type);
	}
	
}
