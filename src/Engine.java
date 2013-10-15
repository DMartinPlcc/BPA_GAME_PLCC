// Daniel Martin Oct/6/2013
// Purpose: To create a single class for handling the game framework
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Engine extends BasicGame
{
	World worldInstance;
	public static final Resource m_Resource = new Resource();
	

	public Engine(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		gc.getGraphics().setBackground(new Color(50,100,255));
		
		worldInstance = new World();

		
		//Instance3 = new WorldInstance(0,1); // Bottom Left
		//Instance4 = new WorldInstance(1,1); // Bottom Right
		/*
		WInstance = new World();
	
		World S = WInstance;
	      try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("res/world.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(S);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	      
	      World SNew = null;

	      try
	      {
	         FileInputStream fileIn = new FileInputStream("res/world.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         SNew = (World) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	      WInstance = SNew;
	      */
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		

		
		//WInstance.Draw();
		worldInstance.update(gc);
		worldInstance.draw(g);

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer Game = new AppGameContainer(new Engine("Placeholder"));
			Game.setDisplayMode(1280, 720, false);			
			Game.setTargetFrameRate(100);
			//Game.setVSync(true);
			Game.start();

		}
		catch (SlickException ex)
		{
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	

}
