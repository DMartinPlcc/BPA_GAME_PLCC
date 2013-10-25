import java.util.Vector;

//created by: Chubbell, october, 23rd, 2013
//to create a basic set up for a menu object
/* needs:
 * swap between instances
 */
public class GUIMaster {


	Vector<GUIInstance> instanceList = new Vector<GUIInstance>();
	GUIInstance currentInstance = null;
	
	public GUIInstance getNewInstance()
	{
		GUIInstance newInstance = new GUIInstance(this);
		instanceList.add(newInstance);
		
		return newInstance;	
	}
	
	public void destroyInstance(GUIInstance destroyableInstance)
	{
		instanceList.removeElement(destroyableInstance);
	}
	
	public void update()
	{
		for(GUIInstance in: instanceList)
		{
			in.update();
		}
	}
	private void getInstance(GUIInstance instance)
	{
				currentInstance = instance;
	}
}
