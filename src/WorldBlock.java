import java.util.Random;

import org.newdawn.slick.geom.Rectangle;

// Daniel Martin Oct 8
// Purpose: A placeholder for world blocks. The extending class will most likely be changed from EntityStatic.

public class WorldBlock extends EntityImage
{
	private static final long serialVersionUID = 1L;
	
	// Width/Height of a WorldBlock as a whole.
	static final int WIDTH  = 20;
	static final int HEIGHT = 20;
	
	WorldBlock(WorldBlock Block)
	{
		setPos(Block.getPos());
		setScale(Block.getScale());
		setWidth(WIDTH);
		setHeight(HEIGHT);

		image = Block.image;
		//color = Block.color;
	}
	
	WorldBlock(float X, float Y, float Scale)
	{
		setPos(X,Y);
		setScale(Scale);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		
		setTexture("res/sprites/tile/block_1_20.tga");
		Random rand = new Random();
		
		switch (rand.nextInt(3))
		{
		case 0:
			setTexture("res/sprites/tile/block_1_20_grass.tga");
			break;
		case 1:
			setTexture("res/sprites/tile/block_1_20_dirt.tga");
			break;
		case 2:
			setTexture("res/sprites/tile/block_1_20_stone.tga");
			break;
		}
		
	}

	public WorldBlock(float X, float Y)
	{
		this(X,Y,1);
	}

}
