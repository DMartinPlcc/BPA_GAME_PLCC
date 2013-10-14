// Daniel Martin Oct/6/2013
// Purpose: Prevent the same asset from being allocated multiple times.
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import java.util.Vector;


public class Resource
{
	class TexturePair
	{
		String  filePath;
		Texture texture;
	}


	Vector<TexturePair> textureList = new Vector<TexturePair>();

	
	
	Texture loadTexture(String Path) 
	{
		
		for(int i = 0;i < textureList.size();i++)
		{
			TexturePair TP = textureList.get(i);
			if (TP.filePath == Path)
			{
				//System.out.println("Returned texture successfully: "+TP.m_Texture.getTextureID());
				return TP.texture;
			}
		}
		
		try 
		{
			
			TexturePair TP = new TexturePair();
			TP.filePath = Path;
			TP.texture = TextureLoader.getTexture("TGA",ResourceLoader.getResourceAsStream(Path));
			textureList.add(TP);
			System.out.println("Loaded texture successfully: "+TP.texture.getTextureID());
			return TP.texture;
			
		} 
		catch (IOException e) 
		{
			System.out.println("Texture failed to load!");
			e.printStackTrace();
			return null;
		}

	}
	
	



}

