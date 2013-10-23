
// Daniel Martin Oct/6/2013 // Edited: Clayton Hubbell Oct. 16th, 2013
// Purpose: Placeholder for entity, world, and other asset data representing the game world.

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	

	Camera camera;
	String worldName;
	MetaSave metaData;
	WorldTreadmill treadmill;

	long lastUpdate;
	long nextUpdate;

	World() 
	{
		camera = new Camera(0, 0);
		treadmill = new WorldTreadmill();
		//treadmill.instanceList[0][0].save("TestWorld", 0);
	}

	
	void update(GameContainer gc) 
	{
		lastUpdate = gc.getTime();
		if (lastUpdate >= nextUpdate) 
		{
			nextUpdate = lastUpdate + 500;
			
			if (gc.getInput().isKeyDown(Input.KEY_T)) 
			{
				System.out.println("Recompute [0][0] Plus 1");
				treadmill.instanceList[0][0].recomputePosition(treadmill.instanceList[0][0].precedingX+1, treadmill.instanceList[0][0].precedingY);	
			}
			
			if (gc.getInput().isKeyDown(Input.KEY_E)) 
			{
				System.out.println("Added Right!");
				treadmill.pushInstance(1,0);		
			}
			if (gc.getInput().isKeyDown(Input.KEY_Q)) 
			{
				System.out.println("Added Left!");
				treadmill.pushInstance(-1,0);
			}

			if (gc.getInput().isKeyDown(Input.KEY_X)) 
			{
				System.out.println("Added Down!");
				treadmill.pushInstance(0,-1);
			}
			if (gc.getInput().isKeyDown(Input.KEY_C)) 
			{
				System.out.println("Added Up!");
				treadmill.pushInstance(0,1);
			}
		}
		camera.update(gc);

	}

	void draw(Graphics g) 
	{
		camera.draw(g);
		treadmill.draw(g, camera.pos.x, camera.pos.y);
	}

}
