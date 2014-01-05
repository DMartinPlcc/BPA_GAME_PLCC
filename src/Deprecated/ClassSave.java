package Deprecated;

import java.util.Vector;

/**
 * @deprecated
 * @author Danny
 *
 */
final public class ClassSave 
{

	static final char Delimiter_Class = '@';
	static final char Delimiter_Open = '{';
	static final char Delimiter_Closed = '}';
	static final char Delimiter_Variable = '$';
	static final char Delimiter_Variable_Seperator = '|';
	static final char Delimiter_Endline = '\n';
	static final char Delimiter_Quote = '\"';
	
	
	
	static final int UniqueNum_Start = -200000000;
	static final String UniqueNum = "[Unique_Identifier]";
	
	
	private  final Vector<Key> Keys;
	private  final Vector<ClassSave> Classes;
	  String ClassName;
	private int ChildUniqueNum;
	
	ClassSave(String className)
	{
		ClassName 		= className;
		Keys 			= new Vector<Key>();
		Classes 		= new Vector<ClassSave>();
		ChildUniqueNum 	= UniqueNum_Start;
		SaveKey(UniqueNum,UniqueNum_Start);
	}

	ClassSave(ClassSave Save)
	{		
		this.ClassName 	= new String(Save.ClassName);
		
		
		Vector<Key> V = new Vector<Key>();

		Vector<ClassSave> C = new Vector<ClassSave>();
		
		for (int i = 0; i < Save.Keys.size(); i++)
		{
			Key K = Save.Keys.get(i);
			System.out.println("Constructor: KeyName: "+K.Name);
			System.out.println("Constructor: KeyValue: "+K.Value);
			V.add(new Key(K.Name,K.Value));
		}
		for (int i = 0; i < Save.Classes.size(); i++)
		{
			C.add(new ClassSave(Save.Classes.get(i)));
		}
		
		this.Keys 		=  V;
		this.Classes 	=  C;
		
		this.SaveKey(UniqueNum,Save.GetString(UniqueNum));
		this.ChildUniqueNum = Save.ChildUniqueNum;
	}
	

	class Key
	{
		String Name,Value;
		
		Key(String name, String value)
		{
			Name = name;
			Value = value;
		}
	}
	

	
	static boolean IsDelimiter(char C)
	{
		switch(C)
		{
		case Delimiter_Class:
			return true;
			
		case Delimiter_Open:
			return true;
			
		case Delimiter_Closed:
			return true;
			
		case Delimiter_Variable:
			return true;
			
		case Delimiter_Variable_Seperator:
			return true;
		
		default:
			return false;
		}
	}

	static String GetDelimited(String Data,char Delimiter)
	{	
		int Pos = Data.indexOf(Delimiter);
		if (Pos > -1)
		{
			return Data.substring(0,Pos);
		}
		else
		{
			System.out.println("GetDelimitedString: Failed to find delimiter ["+Delimiter+"]");
			return Data;
		}		
	}
	
	static String[] GetStringLines(String Total)
	{
		Vector<String> Lines = new Vector<String>();
		Total = Total.trim()+'\n';
		int Pos = Total.indexOf('\n');
		
		while (Pos > -1)
		{
			//System.out.println("StringLine: "+Total.substring(0, Pos));
			Lines.add(Total.substring(0, Pos));
			Total = Total.substring(Pos+1,Total.length());
			Pos = Total.indexOf('\n');
		}
		
		return Lines.toArray(new String[Lines.size()]);

		
	}
	
	static String RemoveChars(String Data, char Remove)
	{
		String ReturnString = "";
		for (int i = 0; i < Data.length(); i++)
		{
			char C = Data.charAt(i);
			if (C != Remove)
			{
				ReturnString += C;
			}
		}
		
		return ReturnString;
	}
	
	Key ReadKey(String Data)
	{
		Data = Data.trim();
		String Name,Value;
		
		int SeperatorPos = Data.indexOf(Delimiter_Variable_Seperator);
		if (SeperatorPos < 0)
		{
			System.out.println("ReadKey(): Failed, could not find seperator in: "+Data);
			return null;
		}
		
		int Start = IsDelimiter(Data.charAt(0)) ? 1 : 0;
		Name = Data.substring(Start,SeperatorPos);
		Value = RemoveChars(Data.substring(SeperatorPos+1,Data.length()),Delimiter_Quote);
		
		System.out.println("Added Key("+Name+","+Value+")");
		return new Key(Name,Value);
		
	}
	
	ClassSave ReadClass(String Data)
	{
		ClassSave ReturnClass = new ClassSave("ClassSave");
		
		String Lines[] = GetStringLines(Data);
		int Brackets = 0;
		
		for(int Line = 0; Line < Lines.length; Line++ )
		{
			String CurrentLine = Lines[Line];
			char Char0 = CurrentLine.charAt(0);
			
			//System.out.println("Line: "+CurrentLine);
	
			
			if (Char0 == Delimiter_Class)
			{
				
				String classname = CurrentLine.substring(1,CurrentLine.length());
				if (Brackets == 0)
				{
					ReturnClass.ClassName = classname;
				}
				else
				{
					//ClassSave NestedClass = ReadClass();
					// Search all each bracket until ending brackets are zero.
					// Need to get exact class data size
					int NestedBrackets = 0;
					int StartLine = Line+1;			
					String NestedClassData = "";
					
					for (int i = StartLine ; i < Lines.length;i++)
					{
						
						if (i != StartLine && NestedBrackets == 0)
						{
							break;
						}
						
						char C = Lines[i].charAt(0);
						if (C == Delimiter_Open)
						{
							++NestedBrackets;
						}
						else if (C == Delimiter_Closed)
						{
							--NestedBrackets;
						}
						++Line;
						NestedClassData += Lines[i]+Delimiter_Endline;
					}
					
					
					ClassSave NestedClass = ReadClass(NestedClassData);
					NestedClass.ClassName = classname;
					//System.out.println("NestedClassData:\n"+NestedClassData);
					if (NestedClass != null)
					{
						ReturnClass.AppendClass(NestedClass);
					}

				}
			}
			else if (Char0 == Delimiter_Open)
			{
				++Brackets;
			}
			else if(Char0 == Delimiter_Variable)
			{
				Key TempKey = ReadKey(CurrentLine);
				if (TempKey != null)
				{
					ReturnClass.SaveKey(TempKey);
				}
				else
				{
					System.out.println("ReadClass: Failed to read Key.");
				}
			}
			else if (Char0 == Delimiter_Closed)
			{
				--Brackets;
			}
			
		}
		

		return ReturnClass;
		
	}
	
	boolean MatchesUnique(ClassSave Save1,ClassSave Save2)
	{
		if (Save1.GetInt(UniqueNum) == Save2.GetInt(UniqueNum))
		{
			return true;
		}
		return false;	
	}
	
	
	private void AppendClass(ClassSave SaveData)
	{
		ClassSave Save = new ClassSave(SaveData);
		Classes.add(Save);
	}
	
	void SaveClass(ClassSave SaveData)
	{
		
		ClassSave Save = new ClassSave(SaveData);
		

		for (int C = 0; C < Classes.size(); C++)
		{
			ClassSave Temp = Classes.get(C);
			if(MatchesUnique(Temp,Save))
			{
				System.out.println("SaveClass: Updating duplicate class.");
				Classes.set(C, Save);
				return;
			}
		}
		
		Save.SaveKey(UniqueNum, ChildUniqueNum);
		SaveData.SaveKey(UniqueNum, ChildUniqueNum);
		ChildUniqueNum += 1;
		System.out.println("SaveClass: Adding new class.");
		
		Classes.add(Save);
	}
	void SaveKey(String name, String value)
	{
		for (int K = 0; K < Keys.size(); K++)
		{
			Key TempKey = Keys.get(K);
			if (TempKey.Name.equalsIgnoreCase(name))
			{
				TempKey.Value = value;
				return;
			}
		}
		Keys.add(new Key(name,value));
	}
	
	void SaveKey(Key Key)
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
	
	
	String GetData()
	{
		Save();
		String Data = "";
		
		Data += Delimiter_Class+ClassName+Delimiter_Endline;
		Data += Delimiter_Open;
		Data += Delimiter_Endline;
		
		for (int K = 0; K < Keys.size(); K++)
		{
			Key TempKey = Keys.get(K);
			if (TempKey != null)
			{
				String Value = RemoveChars(TempKey.Value.trim(), Delimiter_Quote);
	
				Data += Delimiter_Variable+TempKey.Name.trim() + Delimiter_Variable_Seperator;
				Data += Delimiter_Quote + Value + Delimiter_Quote + Delimiter_Endline;
			}
		}
		for (int C = 0; C < Classes.size();C++)
		{
			ClassSave TempClass = Classes.get(C);
			if (TempClass != null)
			{
				Data += TempClass.GetData();
			}
		}
		
		Data += Delimiter_Closed;
		Data += Delimiter_Endline;	
		return Data;
	}
	
		
	String GetString(String KeyName)
	{
		for (int i = 0; i < Keys.size(); i++)
		{
			Key K = Keys.get(i);
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
	
	void Save()
	{
		
	}
	
	
	
	
	
}
