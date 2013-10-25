//Clayton Hubbell 10/25/2013
//Creates text to be placed on screen
import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;


public class GUIText extends EntityPhysics{
	
	TrueTypeFont textFont;
	
	String text;
	
	GUIText()
	{
		Font f = new Font("Times New Roman", Font.BOLD, 24);
		textFont = new TrueTypeFont(f, false);		
		text = "Not Specified";
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String newText)
	{
		text = newText;
	}
	
	public void draw(float x, float y)
	{
		textFont.drawString(x, y, text);
	}
}
