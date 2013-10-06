import org.newdawn.slick.opengl.Texture;


// Purpose: To avoid copying texture pixel data for each instance of an object that uses an already allocated texture.
// Previously, the pixel data would be copied to each instance of a sprite. With this method, one texture is shared among many sprites.
public class MetaTexture 
{
	int m_TextureID;
	int m_Height;
	int m_Width;
	
	MetaTexture(Texture T)
	{
		m_TextureID = T.getTextureID();
		m_Height = T.getTextureHeight();
		m_Width = T.getTextureWidth();
	}
}
