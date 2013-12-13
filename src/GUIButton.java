//Clayton Hubbell 10/25/2013
public class GUIButton extends GUIElement
{
	boolean isPressed;
	
	GUIButton(GUIInstance parentElement)
	{
		super(parentElement);
		isPressed = false;
		this.setScale(10);
	}
	
	void update()
	{
		
		draw();
	}
}
