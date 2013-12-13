import java.util.Stack;

//created by: Chubbell, october, 23rd, 2013
//to create a basic set up for a menu object
/* needs:
 * swap between instances
 */
public class GUIMaster extends GUIElement
{	
	Stack <GUIInstance> instanceStack = new Stack<GUIInstance>();
	
	GUIMaster() 
	{
		super(null);
	}


	public void addGUIInstance(GUIInstance newInstance)
	{
		instanceStack.push(newInstance);
	}
	
	public void destroyInstance(GUIInstance destroyableInstance)
	{
		destroyableInstance.Cleanup();
		instanceStack.removeElement(destroyableInstance);
	}
	
	public GUIInstance popInstance()
	{
		instanceStack.peek().Cleanup();
		return instanceStack.pop();
	}
	

	
	private GUIInstance peekInstance()
	{
		return instanceStack.peek();
	}	
	
	public void update()
	{
		for(GUIInstance in: instanceStack)
		{
			in.update();
		}
	}
}
