package Deprecated;

public class SaveEntityTest extends Saveable 
{
	int X;
	float Y;
	SaveEntityTestChild ChildArray[] = new SaveEntityTestChild[10];
	
	SaveEntityTest()
	{
		SetClassName("SaveEntityTest");
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
		SetClassName("SaveEntityTest");
		X = GetInt("X");
		Y = GetFloat("Y");
	}
	
	void Save()
	{
		SaveKey("X",X);
		SaveKey("Y",Y);
		
		//SETC.Save();
		SaveClass(ChildArray);
	}
	

}
