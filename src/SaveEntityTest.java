
public class SaveEntityTest extends Saveable 
{
	int X;
	float Y;
	SaveEntityTestChild ChildArray[] = new SaveEntityTestChild[10];
	
	SaveEntityTest()
	{
		SetSaveClassName("SaveEntityTest");
		X = 0;
		Y = 10;
		for (int i = 0; i < ChildArray.length; i++)
		{
			ChildArray[i] = new SaveEntityTestChild();
		}
	}
	
	SaveEntityTest(Saveable SaveData)
	{
		SetSaveData(SaveData);
		SetSaveClassName("SaveEntityTest");
		X = GetInt("X");
		Y = GetFloat("Y");
	}
	
	void Save()
	{
		SaveSetKey("X",X);
		SaveSetKey("Y",Y);
		
		//SETC.Save();
		SaveAddClass(ChildArray);
	}
	

}
