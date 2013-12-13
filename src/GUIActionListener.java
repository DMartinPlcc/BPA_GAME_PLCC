
import java.util.Vector;

public class GUIActionListener 
{

	GUIElement GUIParent = null;
	Vector<GUIElement> GUIListeners = new Vector<GUIElement>();
	
	void SendEvent(GUIAction Action)
	{
		if (GUIParent != null)
		{
			GUIParent.actionPerformed(Action);
		}
	}
	
	void setActionListener(GUIElement ParentListener)
	{
		GUIParent = ParentListener;
	}
	
	void addActionListener(GUIElement Element)
	{
		GUIListeners.add(Element);
	}
	
	void removeActionListener(GUIElement Listener)
	{
		GUIListeners.remove(Listener);
	}
	
	void actionPerformed(GUIAction Action)
	{
		
	}
	

}
