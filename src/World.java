import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

// Daniel Martin Oct/6/2013
// Purpose: Placeholder for entity, world, and other asset data representing the game world.
public class World implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;	
	static int ROWS = 1;
	static int COLUMNS = 1;
	
	MetaSave metaData;
	
	
	WorldInstance instanceList[][];
	
	WorldInstance testInstance;
	
	Camera camera;
	
	String worldName;
	int numDeletedInstances;
	
	World()
	{
		camera = new Camera(0,0);
		testInstance = new WorldInstance(0,0);
	}
	
	void update(GameContainer gc)
	{
		camera.update(gc);
	}
	
	void draw(Graphics g)
	{
		camera.draw(g);
		testInstance.draw(camera.x,camera.y);
		camera.draw(g);
	}
	
}
