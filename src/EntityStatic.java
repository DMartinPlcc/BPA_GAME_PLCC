import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;


public class EntityStatic extends EntityVisable
{
	Image m_Image;
	
	float m_R,m_G,m_B;
	float m_Alpha;
	
	EntityStatic()
	{
		m_Image = new Image(Engine.m_Resource.LoadTexture("res/sprites/tile/grass_1_50.tga"));
		m_X = 0;
		m_Y = 0;
		m_Scale = 1;
	}
	
	void draw()
	{
		m_Image.draw(m_X,m_Y,m_Scale);
	}
	
	void SetColor(float R, float G, float B)
	{
		m_R = R;
		m_G = G;
		m_B = B;
		
	}
	
	void SetAlpha(float Alpha)
	{
		m_Image.setAlpha(m_Alpha);

		

		//Texture T;
		//T.
		//m_Image.setTexture(texture);
	}
	
	void SetTexture(String Path)
	{
		m_Image.setTexture(Engine.m_Resource.LoadTexture(Path));
	}

}
