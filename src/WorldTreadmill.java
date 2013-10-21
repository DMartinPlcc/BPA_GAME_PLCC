// Daniel Martin Oct 20, 2013
// Purpose: To load and unload WorldInstances on the fly for rendering and interactivity.

import org.newdawn.slick.Graphics;


public class WorldTreadmill implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	static int ROWS = 4;
	static int COLUMNS = 4;
	
	// After this many Instances are loaded, we push Instances back to (0,0)
	// and increment currentGeneration by 1
	// The "typewriter" resets to a new line
	static int GENERATION_CHILDREN_LIMIT = 2000;
	
	int currentGeneration;
	
	int precedingChildrenX;
	int precedingChildrenY;
	
	WorldInstance instanceList[][];
	
	WorldTreadmill() 
	{
		currentGeneration = 0;
		instanceList = new WorldInstance[ROWS][COLUMNS];
		populate();
	}

	
	class PushInstanceThread extends Thread 
	{
		int x;
		int y;
		
		PushInstanceThread(int X, int Y) 
		   {
		      // Create a new, second thread
		      super("PushInstanceThread");
		      x = X;
		      y = Y;
		      //start(); // Start the thread
		   }

		   public void run() 
		   {
		      int X = x;
		      int Y = y;
		      
		      WorldInstance[][] newInstanceList = instanceList.clone();
		     
		      
				// Is this messy?  Yes.
				// Is there a way to hande positive and negitive X/Y movement in one for loop? Yes.
				// However, it will be more costly and possibly less readable than the below behemoth of expanded code.
				if (X != 0) 
				{
					// Push X Right
					if (X > 0)
					{
						for (int numPush = 0; numPush < X; numPush++) 
						{
							for (int row = 0; row < ROWS; row++) 
							{
								for (int col = 0; col < COLUMNS; col++) 
								{
									//System.out.println("instanceList["+row+"]["+col+"]");
									if (col+1 < COLUMNS ) 
									{
										newInstanceList[row][col] = newInstanceList[row][col + 1];
									} 
									else 
									{
										int preceedX = newInstanceList[row][col - 1].precedingX + 1;
										int preceedY = newInstanceList[row][col - 1].precedingY;
										newInstanceList[row][col] = new WorldInstance(preceedX, preceedY);
									}

								}
							}
						}
						
					}
					
					// Push X Left
					else
					{
						for (int numPush = 0; numPush < Math.abs(X); numPush++) 
						{
							for (int row = ROWS-1; row >= 0; row--) 
							{
								
								for (int col = COLUMNS-1; col >= 0; col--) 
								{
									
									//System.out.println("instanceList["+row+"]["+col+"]");
									if (col != 0 ) 
									{
										newInstanceList[row][col] = newInstanceList[row][col - 1];
									} 
									else 
									{
										int preceedX = newInstanceList[row][col + 1].precedingX - 1;
										int preceedY = newInstanceList[row][col + 1].precedingY;
										newInstanceList[row][col] = new WorldInstance(preceedX, preceedY);
									}

								}
							}
						}
					}
				
				}	 
				
				if (Y != 0) 
				{
					// Push Y Up
					if (Y > 0)
					{
						for (int numPush = 0; numPush < Y; numPush++) 
						{
							for (int row = ROWS-1; row >= 0; row--)
							{
								for (int col = COLUMNS-1; col >= 0; col--)
								{

									//System.out.println("instanceList["+row+"]["+col+"]");
									if (row == 0)
									{
										int preceedX = newInstanceList[row+1][col].precedingX;
										int preceedY = newInstanceList[row+1][col].precedingY - 1;
										newInstanceList[row][col] = new WorldInstance(preceedX,preceedY);
									}
									else
									{
										newInstanceList[row][col] = newInstanceList[row-1][col];
									}
								}					
							}
						}
					}
					// Push Y Down
					else
					{
						for (int numPush = 0; numPush < Math.abs(Y); numPush++) 
						{
							for (int row = 0; row < ROWS; row++)
							{
								for (int col = 0; col < COLUMNS; col++)
								{

									//System.out.println("instanceList["+row+"]["+col+"]");
									if (row+1 == ROWS)
									{
										int preceedX = newInstanceList[row-1][col].precedingX;
										int preceedY = newInstanceList[row-1][col].precedingY + 1;
										newInstanceList[row][col] = new WorldInstance(preceedX,preceedY);
									}
									else
									{
										newInstanceList[row][col] = newInstanceList[row+1][col];
									}
								
								}					
							}
						}
					
				
					}
				}
				
				 instanceList = newInstanceList;
		   }
		   
	}

	
	void pushInstance(int X, int Y) 
	{
		PushInstanceThread pushInstanceT = new PushInstanceThread(X,Y);
		pushInstanceT.start();
	}

	void populate() 
	{
		int TempPreceedX = 0;
		int TempPreceedY = 0;

		for (int Col = 0; Col < COLUMNS; Col++) 
		{
			for (int Row = 0; Row < ROWS; Row++) 
			{
				instanceList[Row][Col] = new WorldInstance(TempPreceedX,TempPreceedY);
				TempPreceedY++;
			}
			TempPreceedY = 0;
			TempPreceedX++;

		}
	}

	void draw(Graphics g, float X, float Y) 
	{
		for (int row = 0; row < ROWS; row++) 
		{
			for (int column = 0; column < COLUMNS; column++) 
			{
				instanceList[row][column].draw(X, Y);
			}
		}
	}


}
