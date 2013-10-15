// Daniel Martin Oct/6/2013
// Purpose: Provide extensible class for entities that are not animated but still drawn in the world.

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;


public class EntityImage extends EntityPosition
{
	private static final long serialVersionUID = 1L;

	transient Image image = null;
	
	Color color;
	
	EntityImage()
	{
		color = new Color(1,1,1);
		image = Engine.m_Resource.loadTexture("res/sprites/tile/grass_1_50.tga");
		
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
		color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat(), color.a);
		
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
			image = Engine.m_Resource.loadTexture("res/sprites/tile/grass_1_50.tga");
		}
		image.draw(x, y, scale, color);
	}
	
	void SetColor(float R, float G, float B)
	{
		color = new Color(R,G,B,color.a);
	}
	
	void setAlpha(float Alpha)
	{
		color.a = Alpha;
	}
	
	void setTexture(String Path)
	{
		image = null;
		image = new Image(Engine.m_Resource.loadTexture(Path).getTexture());
	}

}
