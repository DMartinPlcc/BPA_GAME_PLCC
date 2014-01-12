// Daniel Martin Oct/6/2013
// Purpose: Provide extensible class for entities that are not animated but still drawn in the world.

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.fills.GradientFill;
//import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.ShapeRenderer;


public class EntityImage extends EntityPhysics
{
	private static final long serialVersionUID = 1L;

	transient Image image = null;

	boolean visable = true;
	
	EntityImage()
	{
		image = Engine.m_Resource.loadTexture("res/sprites/tile/block_1_20.tga");
		setPos(0,0);
		setWidth(image.getWidth());
		setHeight(image.getHeight());
	}
	EntityImage(String TexturePath)
	{
		//"res/sprites/tile/block_1_20.tga"
		image = Engine.m_Resource.loadTexture(TexturePath);
		setPos(0,0);
		setWidth(image.getWidth());
		setHeight(image.getHeight());
		
		
		
		//color = new Color(1,1,1);
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
		//Random rand = new Random();
		//color = new Color(0,rand.nextFloat(),rand.nextFloat()/3, color.a);
		//GF =  new GradientFill(0, 0, color.brighter(), 10, 10, color);
	}
	
	void draw()
	{
		if (image == null)
		{
			image = Engine.m_Resource.loadTexture("res/sprites/tile/grass_1_50.tga");
		}
		//image.draw(pos.x, pos.y, scale, color);

		if (visable)
		{
			ShapeRenderer.textureFit(this.getBoundingBox(),image);
		}
		//Engine.gameContainer.getGraphics().texture(getBoundingBox(), image);

	}
	
	/*
	void SetColor(float R, float G, float B)
	{
		color = new Color(R,G,B,color.a);
	}
	
	
	void setAlpha(float Alpha)
	{
		color.a = Alpha;
	}
	*/
	void setTexture(String Path)
	{
		image = null;
		image = new Image(Engine.m_Resource.loadTexture(Path).getTexture());

	}

}
