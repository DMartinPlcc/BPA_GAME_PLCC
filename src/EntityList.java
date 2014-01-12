import java.util.Vector;

import org.newdawn.slick.geom.Vector2f;



// Programmer: Daniel Martin 11/15/2013
// Purpose:    To hold a list of all non-world entities and perform basic utility operations on them.
public class EntityList 
{
	Vector<EntityBase> gameEntities = null;
	
	public EntityList()
	{
		gameEntities = new Vector<EntityBase>();
	}
	

	public Vector<EntityBase> getEntities(Vector2f Pos, float Radius)
	{
		//Return a vector of all entities that are within a radius of a position, and match a generic identifier.
		
		Vector<EntityBase> entList = new Vector<EntityBase>();
		for(EntityBase E : gameEntities)
		{	
			if (E instanceof EntityBase)
			{
				EntityBase RadiusTest = (EntityBase) E;
				if (RadiusTest.inRadius(Pos, Radius))
				{
					entList.add(RadiusTest);
				}
			}

		}
		
		return entList;
	}
	
}
