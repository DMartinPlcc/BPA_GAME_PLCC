import java.util.Vector;

//Clayton Hubbell 10/23/2013
//

public abstract class GUIInstance extends GUIElement
{
	
	GUIInstance(GUIMaster parentInstance)
	{
		super(parentInstance);
	}
	
	public void update()
	{
		for(GUIElement en: GUIListeners)
		{
			en.update();
		}
	}
	
	/** Called when the GUIInstance is popped off of the GUIMaster's stack list. The code implemented here should do final touches, such as saving entered form data or state. */
	abstract void Cleanup();

}
