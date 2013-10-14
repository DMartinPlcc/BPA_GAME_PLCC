package Deprecated;
import java.util.Vector;

public class Saveable 
{
	static final char Delimiter_Class = '@';
	static final char Delimiter_Var = '$';
	static final char Delimiter_Var_Seperator = '|';
	static final char Delimiter_Bracket_Open = '{';
	static final char Delimiter_Bracket_Closed = '}';

	
	
	String ClassName;	
	Vector<SaveKey> Keys;
	Vector<Saveable> Classes;
	int UniqueNumber;
	int UniqueChildNumber;
	
	// Don't allow the same class instance to load more than once.
	// This is add classes that already exist.
	// I don't feel that any class should have to repeatedly load instances.
	// Loading should happen once, around the class construction phase.
	private boolean Loaded; 
	
	Saveable()
	{
		ClassName = "Saveable";
		 Keys = new Vector<SaveKey>(0);
		 Classes = new Vector<Saveable>(0);
		 UniqueNumber = -2000000000;
		 UniqueChildNumber = UniqueNumber;
		 Loaded = false;
		 SaveKey("UniqueNumber",UniqueNumber);
	}
	
	Saveable(Saveable SaveData)
	{
		this();
		SetSaveData(SaveData);
	}
	
	Saveable(String SaveDataString)
	{
		this();
		Load(SaveDataString);
	}
	
	class SaveKey
	{				
		String 	Name;
		String 	Value;
		
		SaveKey(String name, String value)
		{
			Name = name;
			Value = value;
		}
	}
	
	void SetClassName(String className)
	{
		ClassName = className;
	}
	
	void Load(String SaveDataString)
	{
		Save();
		SetSaveData(ReadClass(SaveDataString));
		
		/*
		if (!Loaded)
		{
			Saveable Save = new Saveable();
			Save.ReadClass(SaveDataString);
			SetSaveData(Save);
			Loaded = true;
		}
		else
		{
			System.out.println("Saveable: Class attempted to load save data more than once!");
			System.out.println("Classes should load data for construction purposes!");
		}*/
	}
	
	boolean IsDelimiter(char C)
	{
		switch(C)
		{
		case 	 Delimiter_Class:
			return true;
	
		case Delimiter_Var:
			return true;
			
		case Delimiter_Var_Seperator:
			return true;
			
		case Delimiter_Bracket_Open:
			return true;
			
		case Delimiter_Bracket_Closed:
			return true;
			
		default:
			return false;
		}
		
	}
	
	// Continue reading until any delimiter is found. Option to ignore first "IgnoreNumDelimiters" Delimiters
	String DelimiterSubstring(String Str, int IgnoreNumDelimiters)
	{
		String FinalString = "";
		for(int i = 0; i < Str.length(); i++)
		{
			char C = Str.charAt(i);
			FinalString += C;
			
			if (IsDelimiter(C))
			{
				if (IgnoreNumDelimiters > 0)
				{
					if (i != 0)
					{
						--IgnoreNumDelimiters;
					}
				}
				else
				{
					break;
				}
			}


		}
		//System.out.println(FinalString);
		return FinalString.trim();
	}
	
	// Continue reading until reach "StopDelimiter" character
	String DelimiterSubstring(String Str, char StopDelimiter)
	{
		String FinalString = "";
		for(int i = 0; i < Str.length(); i++)
		{
			char C = Str.charAt(i);
			FinalString += C;
			
			if (IsDelimiter(C) && C == StopDelimiter)
			{
				break;
			}
		}
		return FinalString;
	}
	
	Saveable ReadClass(String Data)
	{
		//System.out.println(Data);
		Saveable Save = new Saveable();
		Data = Data.trim();
		int OpenBrackets = 0;
		
		for (int i = 0; i < Data.length(); i++)
		{
			char CChar = Data.charAt(i);
			if (CChar == Delimiter_Class)
			{
				++OpenBrackets;
				String CName = DelimiterSubstring(Data.substring(i,Data.length()),0);
				//System.out.println("ClassName: "+ClassName);
				
				if (OpenBrackets > 1)
				{
					// Read up until the next closed bracket.
					String ChildClassData = DelimiterSubstring(Data.substring(i,Data.length()),Delimiter_Bracket_Closed);
					
					//System.out.println("Child Start");
					//System.out.println(ChildClassData);
					//System.out.println("Child End");
					Saveable ChildSave = ReadClass(ChildClassData);
					if (ChildSave != null)
					{	
						Save.SaveClass(ChildSave);
					}
				}
				else 
				{
					Save.ClassName = CName;
				}
				
			}
			else if (CChar == Delimiter_Var)
			{
				String KeyString = DelimiterSubstring(Data.substring(i,Data.length()),1);
				SaveKey K = ReadKeyString(KeyString);
				if (K != null)
				{
					Save.SaveKey(K);
				}
			}
			else if (CChar == Delimiter_Bracket_Closed)
			{
				--OpenBrackets;
			}
		}
		
		return Save;
		
	}
	SaveKey ReadKeyString(String Line)
	{
		String Name, Value;	
		int DelimiterPos = Line.indexOf(Delimiter_Var_Seperator);
		
		if (DelimiterPos == -1)
		{
			System.out.println("Saveable: ReadKeyString(): Invalid string divider: "+Line);
			return null;
		}
		
		
		Name  = Line.substring(1,DelimiterPos);
		Value = Line.substring(DelimiterPos+1,Line.length()-2);
		
		//System.out.println("Name: "+Name);
		System.out.println("Value: "+Value);
		SaveKey K = new SaveKey(Name,Value);
		
		System.out.println("Added SaveKey("+Name+","+Value+").");
		return K;
	}
	
	String[] GetLines(String Str, char Delimiter)
	{
		Vector<String> StringVec = new Vector<String>();
		
		for (int i = 0; i < Str.length(); i++)
		{
			int Pos = Str.indexOf(Delimiter, i);
			if(Pos != -1)
			{	
				StringVec.add(Str.substring(0,Pos).trim());
				Str = Str.substring(Pos,Str.length()-1);
			}
			else
			{
				break;
			}		
		}
		
		return  StringVec.toArray(new String[StringVec.size()]);
	}
	
	
	void SetSaveData(Saveable SaveData)
	{
		ClassName = SaveData.ClassName;
		Keys = SaveData.Keys;
		Classes = SaveData.Classes;
	}
	
	void SaveKey(String Name, String Value)
	{
		boolean Exists = false;
		for (int i = 0; i < Keys.size(); i++)
		{
			
			if (Keys.get(i).Name.equalsIgnoreCase(Name))
			{
				Keys.set(i, new SaveKey(Name,Value));
				Exists = true;
				break;
			}
		}
		if (!Exists)
		{
			Keys.add(new SaveKey(Name, Value));
		}
	}
	void SaveKey(SaveKey Key)
	{
		SaveKey(Key.Name,Key.Value);
	}
	void SaveKey(String Name, int Value)
	{
		SaveKey(Name,Integer.toString(Value));
	}
	void SaveKey(String Name, float Value)
	{
		SaveKey(Name,Float.toString(Value));
	}
	void SaveKey(String Name, double Value)
	{
		SaveKey(Name,Double.toString(Value));
	}
	
	void SaveRemoveKey(String Name)
	{
		for (int i = 0; i < Keys.size(); i++)
		{		
			if (Keys.get(i).Name.equalsIgnoreCase(Name))
			{
				Keys.remove(i);
				return;
			}
		}
	}
	
	void SaveClass(Saveable SaveData)
	{
		if (SaveData.equals(this))
		{
			System.out.println("Saveable: SaveClass() Attempted to save a copy of itself into itself. Method ignored.");
			return;
		}
		SaveData.Save();
		
		for (int i = 0; i < Classes.size(); i++)
		{
			Saveable S = Classes.get(i);
			if (SaveData.GetInt("UniqueNumber") == S.GetInt("UniqueNumber"))
			{
				S.SetSaveData(SaveData);
				System.out.println("Unique number matches. Overwriting save data.");
				return;
			}
		}
		//System.out.println("Creating new class save data.");
		
		SaveData.UniqueNumber = ++UniqueChildNumber;
		SaveData.SaveKey("UniqueNumber",UniqueChildNumber);
		
		Classes.add(SaveData);
	}	
	void SaveClass(Saveable SaveData[])
	{
		for (int i = 0; i < SaveData.length; i++)
			SaveClass(SaveData[i]);
	}

	String GetString(String KeyName)
	{
		for (int i = 0; i < Keys.size(); i++)
		{
			SaveKey K = Keys.get(i);
			if (K.Name.equalsIgnoreCase(KeyName))
			{
				return K.Value.trim();
			}
		}
		return "0";
	}
	int    GetInt(String KeyName)
	{
		return Integer.parseInt(GetString(KeyName));
	}
	float  GetFloat(String KeyName)
	{
		return Float.parseFloat(GetString(KeyName));
	}
	double GetDouble(String KeyName)
	{
		return Double.parseDouble(GetString(KeyName));
	}
	
	Saveable[] GetClasses()
	{
		return Classes.toArray(new Saveable[Classes.size()]);
	}
	Saveable[] GetClass(String Name)
	{
		Vector<Saveable> Saves = new Vector<Saveable>();
		for (int i = 0; i < Classes.size(); i++)
		{
			Saveable Save = Classes.get(i);
			if (Save != null)
			{
				if (Save.ClassName.equalsIgnoreCase(Name))
				{
					Saves.add(Save);
				}
			}
		}
		return Saves.toArray(new Saveable[Saves.size()]);
	}
	
	
	
	String GetSaveData()
	{
		Save();

		
		//@ClassName
		if (ClassName.equalsIgnoreCase(""))
		{
			System.out.println("GetSaveData: SaveClassName == \"\"!");
			//return "";
		}
		
		String SaveData;
		
		SaveData =  Delimiter_Class + ClassName+"\n";
		SaveData += "{\n";
		
		for (int i = 0; i < Keys.size(); i++)
		{
			SaveKey K = Keys.get(i);			
			String Name = K.Name;
			String Value = K.Value.trim();
			
			
			SaveData +=   Delimiter_Var+Name+Delimiter_Var_Seperator+ Value+  "\n";
			
		}
		
		for (int i = 0; i < Classes.size(); i++)
		{
			SaveData += "\n"+Classes.get(i).GetSaveData();
		}
		
		SaveData += "}\n";
		
		return SaveData;
	}

	void Save()
	{
		
	}
	
}
