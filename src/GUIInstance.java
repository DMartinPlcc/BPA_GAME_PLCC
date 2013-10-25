import java.util.Vector;

//Clayton Hubbell 10/23/2013
//

public class GUIInstance {

	Vector <GUIElement>elementList = new Vector<GUIElement>();
	
	GUIMaster parent;
	
	GUIInstance(GUIMaster parentInstance)
	{
		parent = parentInstance;
	}
	
	public void update()
	{
		for(GUIElement en: elementList)
		{
			en.update();
		}
	}
}
