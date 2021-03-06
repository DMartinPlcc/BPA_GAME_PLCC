// Daniel Martin Oct/6/2013
// Purpose: To create a single class for handling the game framework

/*
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
*/

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
//import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Engine extends BasicGame
{
	public static final Resource m_Resource = new Resource();
	public static GUIMaster GUIM;
	public static AppGameContainer gameContainer = null; 
	public static World gameWorld = null;
	
	EntityPlayer getPlayer()
	{
		//Don't hate the player, hate the game.
		return gameWorld.player;
	}
	
	public Engine(String gamename)
	{
		super(gamename);
	}

	
	public static long getTime()
	{
		return gameContainer.getTime();
	}
	
	public static Input getInput()
	{
		return gameContainer.getInput();
	}
	
	public static boolean isKeyDown(int code)
	{
		return gameContainer.getInput().isKeyDown(code);
	}
	
	public static boolean isKeyPressed (int code)
	{
		return gameContainer.getInput().isKeyPressed(code);
	}
	
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		
		gc.getGraphics().setBackground(new Color(50,100,255));
		gc.setShowFPS(false);
		
		GUIM = new GUIMaster();
		gameWorld = new World();
		GUIM.addGUIInstance(new TestGUI(GUIM));
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		gameWorld.update();
		gameWorld.draw(g);
	}

	public static void main(String[] args)
	{
		try
		{
			gameContainer = new AppGameContainer(new Engine("Placeholder"));
			gameContainer.setDisplayMode(1280, 720, false);			
			gameContainer.setTargetFrameRate(60);
			//gameContainer.setVSync(true);
			
			gameContainer.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void flipYAxis(boolean Y_UP)
	{
		if (Y_UP == true)
		{
			// Flip Y-Up
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Engine.gameContainer.getWidth(), 0, Engine.gameContainer.getHeight(), -1, 1);
			
		}
		else
		{
			//Flip Y-Down
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Engine.gameContainer.getWidth(), Engine.gameContainer.getHeight(), 0, -1, 1);
		}
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

}
