
public class SaveEntityTestChild extends Saveable
{
	int 	X;
	float  	Y;
	double 	Z;
	
	SaveEntityTestChild(Saveable SaveData)
	{
		SetSaveData(SaveData);
		
		X = GetInt("X");
		Y = GetFloat("Y");
		Z = GetDouble("Z");
	}
	
	SaveEntityTestChild()
	{
		SetSaveClassName("SaveEntityTestChild");
		X = 1; 
		Y = 2; 
		Z = 3;
	}
	
	void Save()
	{
		SaveSetKey("X",X);
		SaveSetKey("Y",Y);
		SaveSetKey("Z",Z);
	}
	
	

}
