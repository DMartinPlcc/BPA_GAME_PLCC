// Daniel Martin Oct/6/2013
// Purpose: Provide extensible class for entities that are not animated but still drawn in the world.

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;


public class EntityStatic extends EntityVisible
{
	private static final long serialVersionUID = 1L;

	transient Image image = null;
	
	float r,g,b,alpha;
	
	EntityStatic()
	{
		image = new Image(Engine.m_Resource.loadTexture("res/sprites/tile/grass_1_50.tga"));
		
		/*
		ImageBuffer buffer = new ImageBuffer(image.getWidth(),image.getHeight());
		
		for (int PixelX = 0; PixelX < image.getWidth(); PixelX++)
		{
			for (int PixelY = 0; PixelY < image.getHeight(); PixelY++)
			{
				Color c = image.getColor(PixelX,PixelY);
				
				buffer.setRGBA(PixelX, PixelY, c.getRed()+100, c.getGreen(), c.getBlue(), c.getAlpha());
			}
		}
		image = buffer.getImage();
		*/
		Random rand = new Random();
		image.setImageColor(rand.nextFloat(),rand.nextFloat(),rand.nextFloat(), 255);
		
		
		x = 0;
		y = 0;
		scale = 1;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	void draw()
	{
		if (image == null)
		{
			image = new Image(Engine.m_Resource.loadTexture("res/sprites/tile/grass_1_50.tga"));
		}
		image.draw(x,y,scale);
	}
	
	void SetColor(float R, float G, float B)
	{
		r = R;
		g = G;
		b = B;
		
	}
	
	void setAlpha(float Alpha)
	{
		image.setAlpha(alpha);
	}
	
	void setTexture(String Path)
	{
		image.setTexture(Engine.m_Resource.loadTexture(Path));
	}

}
