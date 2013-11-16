/** 
 * EntityBase serves as the generic entity base. This class describes the entity's filter type, intention, and allows for basic serialization. 
 * @author DanielMartin
 * @category Entity
 * @since Oct/10/2013.
 * @see EntityType
 * @see EntityIntention
 */

public class EntityBase implements java.io.Serializable
{	
	
	/*** This describes the general filter type of an entity.
	 * @param ITEM includes weapons, armor, and other various drops. 
	 * @param PROJECTILE includes arrows, and magic.
	 * @param ENEMY   describes AI controlled antagonists. 
	 * @param LOGICAL describes entities that fulfill a utility purpose, these entities aren't drawn or have a definite position.
	 */
	enum EntityType {ALL, ITEM, PROJECTILE, ENEMY, LOGICAL};
	
	/*** An entity's intention is the disposition towards the player, or the reason for its existence.
	* @param HOSTILE
	* @param FRIENDLY
	* @param NEUTRAL
	* @param TRAP is not player seeking or actively dangerous (but is proximally dangerous), such a mine or bomb.
	*/
	enum EntityIntention {HOSTILE, FRIENDLY, NEUTRAL, TRAP};	
	private static final long serialVersionUID = 1L;
	
	/** @see EntityType  */
	EntityType entityType;
	/** @see EntityIntention  */
	EntityIntention entityIntention;
	
	EntityBase()
	{
		// Assume this entity does not draw, have a world position, or do anything exceptionally special.
		entityType = EntityType.LOGICAL;
		entityIntention = EntityIntention.NEUTRAL;
	}
	
}
