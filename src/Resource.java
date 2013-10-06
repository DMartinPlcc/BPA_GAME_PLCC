import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import java.util.Vector;


public class Resource
{
	class TexturePair
	{
		String  m_FilePath;
		Texture m_Texture;
	}


	Vector<TexturePair> m_TextureList = new Vector<TexturePair>();

	
	
	Texture LoadTexture(String Path) 
	{
		
		for(int i = 0;i < m_TextureList.size();i++)
		{
			TexturePair TP = m_TextureList.get(i);
			if (TP.m_FilePath == Path)
			{
				//System.out.println("Returned texture successfully: "+TP.m_Texture.getTextureID());
				return TP.m_Texture;
			}
		}
		
		try 
		{
			
			TexturePair TP = new TexturePair();
			TP.m_FilePath = Path;
			TP.m_Texture = TextureLoader.getTexture("TGA",ResourceLoader.getResourceAsStream(Path));
			m_TextureList.add(TP);
			System.out.println("Loaded texture successfully: "+TP.m_Texture.getTextureID());
			return TP.m_Texture;
			
		} 
		catch (IOException e) 
		{
			System.out.println("Texture failed to load!");
			e.printStackTrace();
			return null;
		}

	}
	
	



}

