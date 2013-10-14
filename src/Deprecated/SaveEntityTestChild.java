package Deprecated;

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
		SetClassName("SaveEntityTestChild");
		X = 1; 
		Y = 2; 
		Z = 3;
	}
	
	void Save()
	{
		SaveKey("X",X);
		SaveKey("Y",Y);
		SaveKey("Z",Z);
	}
	
	

}
