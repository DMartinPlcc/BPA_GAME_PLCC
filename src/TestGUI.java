
public class TestGUI extends GUIInstance
{
	GUIButton ButtonTest = new GUIButton(this);

	TestGUI(GUIMaster parentInstance) 
	{
		super(parentInstance);
		// TODO Auto-generated constructor stub
		this.addActionListener(ButtonTest);
		
		
	}

	@Override
	void Cleanup() 
	{

	}

}
