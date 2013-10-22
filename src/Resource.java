// Daniel Martin Oct/6/2013
// Purpose: Prevent the same asset from being allocated multiple times.

import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
//import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.util.Vector;

public class Resource
{
	class TexturePair
	{
		String  filePath;
		Image image;
	}
	class SoundPair
	{
		String  filePath;
		Sound sound;
	}

	Vector<TexturePair> textureList = new Vector<TexturePair>();
	Vector<SoundPair> soundList = new Vector<SoundPair>();



	
	// Sound effect system
	Sound loadSound(String Path) 
	{
		
		for(int i = 0;i < soundList.size();i++)
		{
			SoundPair TP = soundList.get(i);
			if (TP.filePath == Path)
			{
				//System.out.println("Returned sound successfully: "+TP.m_Texture.getTextureID());
				return TP.sound;
			}
		}
		

			
			SoundPair TP = new SoundPair();
			TP.filePath = Path;
			try {
				TP.sound = new Sound(Path);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			soundList.add(TP);
			System.out.println("Loaded sound successfully: "+TP.filePath);
			return TP.sound;
			


	}
	
	


	// Loading textures 
	Image loadTexture(String Path) 
	{
		
		for(int i = 0;i < textureList.size();i++)
		{
			TexturePair TP = textureList.get(i);
			if (TP.filePath == Path)
			{
				//System.out.println("Returned texture successfully: "+TP.m_Texture.getTextureID());
				return TP.image;
			}
		}
		
		try 
		{
			
			TexturePair TP = new TexturePair();
			TP.filePath = Path;
			TP.image = new Image(TextureLoader.getTexture("TGA",ResourceLoader.getResourceAsStream(Path)));
			textureList.add(TP);
			System.out.println("Loaded texture successfully: "+TP.filePath);
			return TP.image;
			
		} 
		catch (IOException e) 
		{
			System.out.println("Texture failed to load!");
			e.printStackTrace();
			return null;
		}

	}
	
	



}

