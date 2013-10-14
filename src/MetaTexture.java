//Daniel Martin Oct/6/2013
// Purpose: To avoid copying texture pixel data for each instance of an object that uses an already allocated texture.
// Previously, the pixel data would be copied to each instance of a sprite. With this method, one texture is shared among many sprites.

import org.newdawn.slick.opengl.Texture;


public class MetaTexture 
{
	int textureID;
	int height;
	int width;
	
	MetaTexture(Texture T)
	{
		textureID = T.getTextureID();
		height = T.getTextureHeight();
		width = T.getTextureWidth();
	}
}
