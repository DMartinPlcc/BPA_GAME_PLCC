import java.util.Vector;

//ClaytonHubbell 10/23/2013
/*
 * 
 *  handle  events
 */
public class GUIElement extends EntityImage
{
	GUIElement parent = null;
	GUIElement GUIActionParent = null;
	Vector<GUIElement> GUIListeners = new Vector<GUIElement>();
	
	void sendReply(GUIAction InitialAction, GUIAction ReplyAction)
	{
		InitialAction.getSource().sendEvent(ReplyAction);
	}

	void sendEvent(GUIAction Action)
	{
		if (GUIActionParent != null)
		{
			GUIActionParent.actionPerformed(Action);
		}
	}
	
	void setActionListener(GUIElement ParentListener)
	{
		GUIActionParent = ParentListener;
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
	
	
	
	void update()
	{
		
	}
	

	GUIElement(GUIElement parentInstance)
	{
		parent = parentInstance;
	}


	
	
}
