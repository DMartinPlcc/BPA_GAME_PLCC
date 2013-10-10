import java.util.Vector;

public class Saveable 
{
	static final char Delimiter_Class = '@';
	static final char Delimiter_Var = '$';
	static final char Delimiter_Var_Seperator = ' ';
	static final char Delimiter_Class_Nested = '&';
	enum SaveType {VARIABLE,CLASS};
	
	
	String SaveClassName;	
	Vector<SaveKey> Members = new Vector<SaveKey>();
	
	
	
	Saveable()
	{
		SaveClassName = "Saveable";
	}
	
	Saveable(Saveable SaveData)
	{
		SetSaveData(SaveData);
	}
	
	Saveable(String SaveDataString)
	{
		ReadSaveData(SaveDataString);
	}
	
	void ReadSaveData(String SaveDataString)
	{
		// Parse a GetSaveData() string.
		
				String[] DataLines = GetLines(SaveDataString,'\n');
				for(int DataLine = 0; DataLine < DataLines.length; DataLine++)
				{
					boolean IsNestedVariable = false;
					if(DataLines[DataLine].charAt(0) == Delimiter_Class)
					{
						IsNestedVariable = false;
						SaveClassName = DataLines[DataLine].substring(1,DataLines[DataLine].length()-1);
					}
					else if (DataLines[DataLine].charAt(0) == Delimiter_Class_Nested)
					{
						IsNestedVariable = true;
						int NestedLines = 0;
						int NestedVars = DataLine;
						for (; NestedVars < DataLines.length; NestedVars++)
						{
							if (DataLines[DataLine].charAt(0) == Delimiter_Var)
							{
								NestedLines++;
							}
							else
							{
								break;
							}
						}

						// Create a sub array containing the nested class' data.
						String NestedClassLines[] = new String[NestedLines];
						for(int i = 0; DataLine < NestedVars;i++)
						{
							NestedClassLines[i] = DataLines[DataLine+i];
						}
						
						// I don't know what to do with this data just yet.
						// I guess we'll add them back into SaveKeys.
						ParseNestedClass(NestedClassLines);
						
						// Skip over all the nested class' variables.
						DataLine = NestedVars;
						
					}			
					else if (DataLines[DataLine].charAt(0) == Delimiter_Var)
					{
						if (!IsNestedVariable)
						{
							SaveSetKey( ParseKeyString(DataLines[DataLine]));
						}
					}
				}	
	}


	String[] GetLines(String Str, char Delimiter)
	{
		Vector<String> StringVec = new Vector<String>();
		
		for (int i = 0; i < Str.length(); i++)
		{
			int Pos = Str.indexOf(Delimiter, i);
			if(Pos != -1)
			{	
				StringVec.add(Str.substring(0,Pos));
				Str = Str.substring(Pos,Str.length()-1);
			}
			else
			{
				break;
			}		
		}
		
		return  StringVec.toArray(new String[StringVec.size()]);
	}
	
	SaveKey ParseKeyString(String Line)
	{
		String Name, Value;
		int DelimiterPos = Line.indexOf(Delimiter_Var_Seperator);
		
		if (DelimiterPos == -1)
		{
			System.out.println("Saveable: ParseKeyString(): Invalid string: "+Line);
			return null;
		}
		Name  = Line.substring(1,DelimiterPos);
		Line  = Line.substring(DelimiterPos+1,Line.length()-1);
		Value = Line;
		
		SaveKey K = new SaveKey(Name,Value,SaveType.VARIABLE);
		
		System.out.println("Added SaveKey("+Name+","+Value+").");
		return K;
	}
	
	void 	ParseNestedClass(String[] Lines)
	{
		String TotalData = "";
		for (int Line = 0; Line < Lines.length; Line++ )
		{
			TotalData += Lines[Line]+'\n';
		}
		SaveAddClass(TotalData);
	
	}
	

	class SaveKey
	{		
		SaveType KeyType;
		
		String 	Name;
		String 	Value;
		
		
		SaveKey(String name, String value,SaveType saveType)
		{
			Name = name;
			Value = value;
			KeyType = saveType;
		}
	}

	void SetSaveClassName(String Name)
	{
		SaveClassName = Name;
	}
	
	
	void SetSaveData(Saveable SaveData)
	{
		SaveClassName = SaveData.SaveClassName;
		Members = SaveData.Members;
	}
	
	void SaveSetKey(String Name, String Value)
	{
		boolean Exists = false;
		for (int i = 0; i < Members.size(); i++)
		{
			
			if (Members.get(i).Name.equalsIgnoreCase(Name))
			{
				Members.set(i, new SaveKey(Name,Value,SaveType.VARIABLE));
				Exists = true;
				break;
			}
		}
		if (!Exists)
		{
			Members.add(new SaveKey(Name, Value,SaveType.VARIABLE));
		}
	}
	
	
	void SaveSetKey(SaveKey Key)
	{
		SaveSetKey(Key.Name,Key.Value);
	}
	void SaveSetKey(String Name, int Value)
	{
		SaveSetKey(Name,Integer.toString(Value));
	}
	void SaveSetKey(String Name, float Value)
	{
		SaveSetKey(Name,Float.toString(Value));
	}
	void SaveSetKey(String Name, double Value)
	{
		SaveSetKey(Name,Double.toString(Value));
	}

	String GetString(String KeyName)
	{
		for (int i = 0; i < Members.size(); i++)
		{
			SaveKey K = Members.get(i);
			if (K.Name.equalsIgnoreCase(KeyName))
			{
				return K.Value;
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
	
	
	void SaveAddClass(String Data)
	{
		String Name = "";
		int Pos = 0;
		
		Pos = Data.indexOf('\n',0);
		if (Pos != -1)
		{
			Name = Data.substring(1,Pos);
		}
		else
		{
			System.out.println("SaveAddClass: Data does not have @ClassName descriptor!!!");
		}
	
		Members.add(new SaveKey(Name, Data,SaveType.CLASS));
	}
	
	void SaveAddClass(Saveable SaveData)
	{
		SaveData.Save();
		SaveAddClass(SaveData.GetSaveData(true));
	}
	
	void SaveAddClass(Saveable SaveData[])
	{
		for (int i = 0; i < SaveData.length; i++)
		{
			SaveData[i].Save();
			SaveAddClass(SaveData[i]);
		}
	}

	String GetSaveData(boolean IsNestedClass)
	{
		Save();
		//@ClassName
		if (SaveClassName.equalsIgnoreCase(""))
		{
			System.out.println("GetSaveData: SaveClassName == \"\"!");
			//return "";
		}
		
		String SaveData;
		if (IsNestedClass)
			SaveData = Delimiter_Class_Nested+SaveClassName+"\n";
		else
			SaveData = Delimiter_Class+SaveClassName+"\n";
		
		for (int i = 0; i < Members.size(); i++)
		{
			SaveKey K = Members.get(i);			
			String Name = K.Name;
			String Value = K.Value;
			
			if (K.KeyType == SaveType.VARIABLE)
			{		
				// &TestVar1 "String data here"
				// &TestVar2 "String data here 2"
				SaveData += Delimiter_Var+Name+Delimiter_Var_Seperator+"\""+Value+"\""+"\n";
			}
			else
			{
				SaveData += K.Value+"\n";
			}
			

		}
		
		return SaveData;
	}

	void Save()
	{
		
	}
	
}
