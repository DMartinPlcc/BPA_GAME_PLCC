
// Daniel Martin Oct/6/2013 // Edited: Clayton Hubbell Oct. 16th, 2013
// Purpose: Placeholder for entity, world, and other asset data representing the game world.

import java.util.Vector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL31;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

public class World implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	Camera camera;
	String worldName;

	EntityPlayer player;
	Vector<EntityBase> entityList = null;
	
	long nextUpdate = 0;

	WorldStreamer worldStream = new WorldStreamer();
	
	WorldBlock physTestBlock = new WorldBlock(0,40);
	
	WorldBlock attachmentTest = new WorldBlock(0,0);
	
	World() 
	{
		player = new EntityPlayer();
		player.setPos(0, 50);
		
		for (int i = 0; i < 2; i++)
		{
			worldStream.addVerticalChunk(false);
			worldStream.addVerticalChunk(true);
		}
		
		//attachmentTest.setPos(player.getMidpointTop());
		attachmentTest.attachmentOffset = player.getMidpointTop().negate();
		
		player.addAttachment(attachmentTest);
		
		
	}

	
	void update() 
	{
		
		//GL11.glTranslatef(70, 100, 0);
		
		//Physics Testing
		//physTestBlock.handleCollision(player);
		player.handleCollision(physTestBlock);
		
		if(Engine.isKeyPressed(Input.KEY_F1))
		{
			System.out.println("VChunk Left");
			worldStream.addVerticalChunk(false);
		}
		if(Engine.isKeyPressed(Input.KEY_F2))
		{
			System.out.println("VChunk Right");
			worldStream.addVerticalChunk(true);
		}
		
		if(Engine.isKeyPressed(Input.KEY_F5))
		{
			System.out.println("Radius grab, Grow!");
			
			for(EntityBase EBase : getEntityList(player.getPos(),500))
			{
				EntityImage imageBase = (EntityImage) EBase;
				if(imageBase != null)
				{
					imageBase.setScale(2);
				}
			}
		}
		if(Engine.isKeyPressed(Input.KEY_F6))
		{
			System.out.println("Radius grab, Shrink!");
			
			for(EntityBase EBase : getEntityList(player.getPos(),500))
			{
				EntityImage imageBase = (EntityImage) EBase;
				if(imageBase != null)
				{
					imageBase.setScale(0.5f);
				}
			}
		}
		if(Engine.isKeyPressed(Input.KEY_F7))
		{
			System.out.println("Radius grab, Default!");
			
			for(EntityBase EBase : getEntityList(player.getPos(),500))
			{
				EntityImage imageBase = (EntityImage) EBase;
				if(imageBase != null)
				{
					imageBase.setScale(1);
				}
			}
		}
		
		if(Engine.isKeyPressed(Input.KEY_F8))
		{
			System.out.println("Radius grab, Invisible!");
			
			for(EntityBase EBase : getEntityList(player.getPos(),500))
			{
				EntityImage imageBase = (EntityImage) EBase;
				if(imageBase != null)
				{
					imageBase.visable = false;
				}
			}
		}
		
		if(Engine.isKeyPressed(Input.KEY_F9))
		{
			System.out.println("Radius grab, Visible!");
			
			for(EntityBase EBase : getEntityList(player.getPos(),500))
			{
				EntityImage imageBase = (EntityImage) EBase;
				if(imageBase != null)
				{
					imageBase.visable = true;
				}
			}
		}
		

		
		if(Engine.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			System.out.println("("+Engine.getInput().getMouseX()+","+Engine.getInput().getMouseY()+")");
			
		}
		
		for(EntityBase EBase : getEntityList(player.getPos(),player.getWidth()))
		{
			EntityPhysics EPhys = (EntityPhysics) EBase;
			if (EPhys != null)
			{
				player.handleCollision(EPhys);
			}
		}

	}

	void draw(Graphics g) 
	{		
		//GL11.glScalef(1, -1, 1);
		
		Engine.flipYAxis(true);		
		player.camera.Translate();
		
		
		//Rectangle bBox = player.camera.getParentFrustrum();
		//bBox.grow(-100, -100);
		//Engine.gameContainer.getGraphics().fill(bBox);
		
		
		worldStream.draw();
		physTestBlock.draw();
		player.update();
		player.simulate();
		attachmentTest.draw();
		

		
		//Engine.gameContainer.getGraphics().fill(player.camera.getParentFrustrum());
		//System.out.println("("+player.getPosX()+","+player.getPosY()+")");
		

		
		
		
	}

	
	Vector<EntityBase> getEntityList(Vector2f Pos, float Radius)
	{
		Vector<EntityBase> EntityList = new Vector<EntityBase>();
		//Let's start off with getting all the possible WorldBlocks.
		
		EntityList.addAll(worldStream.getBlocksInRadius(Pos, Radius));
		return EntityList;
	}
}
