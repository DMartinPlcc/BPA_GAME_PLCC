package Deprecated;


import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;



/** manages WorldBlock groupings in some uniform manner. This is also hideous. 
 * @author Danny
 * @deprecated 
 */
public class WorldPartition<Contained_Object> 
{	
	
	
	

	
	
	private WorldPartition<?> _Parent = null;
	private WorldPartition<?> _Child = null;
	
	protected int 	rows = 1; 		
	protected int 	columns = 1;	
	protected float childWidth = 50;	
	protected float childHeight = 50;
	protected float pos_X = 0,pos_Y = 0;
	

	
	Contained_Object childElements[][] = null;
	
	// --------------CONSTRUCTOR--------------
	WorldPartition()
	{
		
	}
	WorldPartition(int Rows,int Columns,float ChildWidth,float ChildHeight,float PosX,float PosY)
	{
		rows = Rows;
		columns = Columns;
		childWidth = ChildWidth;
		childHeight = ChildHeight;
		pos_X = PosX;
		pos_Y = PosY;		
	}
	
	// --------------GET METHODS--------------
	public WorldPartition<?> getParent()
	{
		return _Parent;
	}
	public WorldPartition<?> getChild()
	{
		return _Child;
	}

	
	
	public void setParent(WorldPartition<?> Parent)
	{
		this._Parent = Parent;
	}
	public void setChild(WorldPartition<?> Child)
	{
		this._Child = Child;
	}
	
	public int getRows()
	{
		return rows;
	}
	public int getColumns()
	{
		return columns;
	}
	public float getChildWidth()
	{
		return childWidth;
	}
	public float getChildHeight()
	{
		return childHeight;
	}
	public float getPos_X()
	{
		return pos_X;
	}
	public float getPos_Y()
	{
		return pos_Y;
	}
	Vector2f getPos()
	{
		return new Vector2f(pos_X,pos_Y);
	}
	
	// --------------SET METHODS--------------
	public void setRows(int rows)
	{
		if (rows > 0)
		{
			this.rows = rows;
		}	
	}
	public void setColumns(int columns)
	{
		if(columns > 0)
		{
			this.columns = columns;
		}
		
	}
	public void setDimensions(int Rows, int Columns)
	{
		if (Rows > 0 && Columns > 0)
		{
			this.rows = Rows;
			this.columns = Columns;			
		}
	}
	
	public void inferChildDimensions()
	{
		if (_Child != null)
		{
			setChildDimensions(_Child.getTotalSizeX(),_Child.getTotalSizeY());
		}	

	}
	
	public void setChildWidth(float ChildWidth)
	{
		if (ChildWidth > 0)
		{
			this.childWidth = ChildWidth;
		}
		
	}
	public void setChildHeight(float ChildHeight)
	{
		if (ChildHeight > 0)
			this.childHeight = ChildHeight;
	}
	public void setChildDimensions(float Width, float Height)
	{
		if (Height > 0 && Width > 0)
		{
			this.childWidth = Width;
			this.childHeight = Height;
		}
	}
	
	public void setPos_X(float pos_X)
	{
		this.pos_X = pos_X;
	}
	public void setPos_Y(float pos_Y)
	{
		this.pos_Y = pos_Y;
	}
	public void setPos(float pos_X, float pos_Y)
	{
			this.pos_Y = pos_Y;
			this.pos_X = pos_X;
	}
	public void setPos(Vector2f Pos)
	{
		setPos(Pos.x,Pos.y);
	}
	
	public float getTotalSizeX()
	{
		return columns*childWidth;
	}
	public float getTotalSizeY()
	{
		return rows*childHeight;
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle(pos_X, pos_Y, getTotalSizeX(), getTotalSizeY());
	}

	// --------------UTILITY METHODS--------------
	public boolean containsPoint(float PosX, float PosY)
	{
		return getRectangle().contains(new Point(PosX, PosY));
	}
	public boolean containsPoint(Vector2f Pos)
	{
		return containsPoint(Pos.x, Pos.y);
	}
	

	WorldPartition<?> findTop()
	{
		// I hope to god this list only moves up.
		// The hell if I'm doing reference counting.
		WorldPartition<?> Temp = _Parent;
		while (Temp != null)
		{
			Temp = _Parent;
		}
		return Temp;
	}
	WorldPartition<?> findBottom()
	{
		// I hope to god this list only moves down.
		// Again, The hell if I'm doing reference counting.
		WorldPartition<?> Temp = _Child;
		while (Temp != null)
		{
			Temp = _Child;
		}
		return Temp;
	}
	
	
	@SuppressWarnings("unchecked")
	void populate()
	{

		// Slightly disgusting.
		// Do not attempt to move this entire array out of this class.
		childElements = (Contained_Object[][]) new Object[rows][columns];
		
		// Yup, this is how we infer this is the container that holds WorldBlocks
		if (_Child == null)
		{
			for (int Row = 0; Row < rows; Row++)
			{
				for (int Col = 0; Col < columns; Col++)
				{
					childElements[Row][Col] = (Contained_Object) new WorldBlock(( Col * childWidth ) + pos_X,( Row * childHeight) + pos_Y,1);
				}
			}	
		}
		else
		{
			for (int Row = 0; Row < rows; Row++)
			{
				for (int Col = 0; Col < columns; Col++)
				{
					childElements[Row][Col] = (Contained_Object) new WorldPartition<Contained_Object>(_Child.rows,_Child.columns,_Child.childWidth,_Child.childHeight,_Child.pos_X,_Child.pos_Y);
				}
			}	
		}
		

		
	}
	
	void draw()
	{
		for (int Row = 0; Row < rows; Row++)
		{
			for (int Col = 0; Col < columns; Col++)
			{
				((WorldPartition<?>) childElements[Row][Col]).draw();
			}
		}
	}


	
}
