
// Daniel Martin Oct/6/2013 // Edited: Clayton Hubbell Oct. 16th, 2013
// Purpose: Placeholder for entity, world, and other asset data representing the game world.

import java.util.Vector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	

	Camera camera;
	String worldName;
	
	WorldTreadmill treadmill;
	
	
	EntityPlayer player;
	
	long nextUpdate = 0;

	//WorldPartition<WorldBlock> 					Chunk1_Partition = new WorldPartition<WorldBlock>();	
	//WorldPartition<WorldPartition<WorldBlock>> 	Chunk2_Partition = new WorldPartition<WorldPartition<WorldBlock>>();
	
	WorldStreamer test = new WorldStreamer();
	
	World() 
	{
		player = new EntityPlayer();
		player.setPos(0, 0);
		
		//camera = new Camera(0, 0);
		treadmill = new WorldTreadmill();
		
		//treadmill.instanceList[0][0].save("TestWorld", 0);
		test.AddVerticalChunk(false);
		test.AddVerticalChunk(true);
		
		
		/*
		Chunk1_Partition.setPos(0,0);
		Chunk1_Partition.setDimensions(10,10);
		Chunk1_Partition.setChildDimensions(50,50);
		Chunk1_Partition.setParent(Chunk2_Partition);
		Chunk1_Partition.setChild(null);
		
		Chunk2_Partition.setPos(0,0);
		Chunk2_Partition.setParent(null);
		Chunk2_Partition.setChild(Chunk1_Partition);
		Chunk2_Partition.setDimensions(10,10);
		Chunk2_Partition.inferChildDimensions();
		Chunk2_Partition.populate();
		*/
	}

	
	void update() 
	{
		
		//GL11.glTranslatef(70, 100, 0);
			if (Engine.isKeyPressed(Input.KEY_T)) 
			{
				System.out.println("Recompute [0][0] Plus 1");
				treadmill.instanceList[0][0].recomputePosition(treadmill.instanceList[0][0].precedingX+1, treadmill.instanceList[0][0].precedingY);	
			}
			if (Engine.isKeyPressed(Input.KEY_E)) 
			{
				System.out.println("Added Right!");
				treadmill.pushInstance(1,0);		
			}
			if (Engine.isKeyPressed(Input.KEY_Q)) 
			{
				System.out.println("Added Left!");
				treadmill.pushInstance(-1,0);
			}

			if (Engine.isKeyPressed(Input.KEY_X)) 
			{
				System.out.println("Added Down!");
				treadmill.pushInstance(0,-1);
			}
			if (Engine.isKeyPressed(Input.KEY_C)) 
			{
				System.out.println("Added Up!");
				treadmill.pushInstance(0,1);
			}
			
			
			
			
		//camera.update(Engine.gameContainer);
			
		

	}

	void draw(Graphics g) 
	{		
		//GL11.glScalef(1, -1, 1);
		//GL11.glOrtho(0, Engine.gameContainer.getWidth(), 0, Engine.gameContainer.getHeight(), -2, 2);
		//Engine.gameContainer.getGraphics().translate(0,0);
		
		
		
		player.playerCamera.Translate();
		//treadmill.draw();
		test.draw();
		player.update();
		//Chunk2_Partition.draw();
		
		
	}

}
