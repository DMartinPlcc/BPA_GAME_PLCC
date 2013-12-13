
public class GUIAction 
{
	enum GUIActions {RELEASE,PRESS,ROLLOVER,CLICK,SPECIAL};
	private GUIElement sender;
	private GUIActions action;
	private String actionCommand;
	
	GUIAction (GUIElement Sender, GUIActions Action)
	{
		sender = Sender;
		action = Action;
	}
	
	GUIElement getSource()
	{
		return sender;
	}
	
	boolean isSender(GUIElement CompareObject)
	{
		return sender == CompareObject;
	}

	public String getActionCommand() 
	{
		return actionCommand;
	}

	public void setActionCommand(String actionCommand) 
	{
		this.actionCommand = actionCommand;
	}

	public GUIActions getAction() 
	{
		return action;
	}

	boolean actionEquals(GUIActions Action)
	{
		return this.action == Action;
	}
}
