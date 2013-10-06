import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Engine extends BasicGame
{
	public static final Resource m_Resource = new Resource();
	
	//EntityStatic SEnt;
	WorldChunk Chunk[] = new WorldChunk[5];
	
	public Engine(String gamename)
	{
		super(gamename);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		//SEnt = new EntityStatic();
		//SEnt.SetPos(2,2,0.05f);
		for (int i = 0; i < 5; i++)
		{
			Chunk[i] = new WorldChunk(i);
			Chunk[i].PopulateChunk();
		}
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//g.drawString("Test!", 100, 100);
		//SEnt.draw();
		for (int i = 0; i < 5; i++)
		{
			Chunk[i].Draw();
		}
		g.setBackground(new Color(255,255,255));
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer Game = new AppGameContainer(new Engine("Placeholder"));
			Game.setDisplayMode(1024, 512, false);
			Game.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	

}
