import java.util.ArrayList;
import java.util.LinkedList;
/**
 * 
 * Creates a MineSweeper field with the given length and density.
 *  Contains instances for the length and density of the field, the total and correct flags,
 *  as well as an array of MSVertex and an arrayList of adjacencies. 
 *
 * @Authors: Spencer, Shawn, Eric
 * @version 1.0
 */
public class MSField
{
	private int fieldLength; // to determine size
	private double fieldDensity; // mine density of field
	private int totalMines; // count of the totalMines
	private int correctFlags;
	private int totalFlags;
	private MSVertex[][] field;// field to hold the tiles
	private ArrayList<LinkedList<MSVertex>> adjList; // adjacency list
	/**
	 * Constructor creates a MSField with given properties
	 * @param fieldLength the size of the field
	 * @param fieldDensity ratio of mines in the field
	 */
	public MSField(int fieldLength, double fieldDensity)
	{
		// sets the field variables
		this.fieldLength = fieldLength;
		this.fieldDensity = fieldDensity;
		this.totalMines = (int) (fieldLength * fieldLength * fieldDensity); 
		this.correctFlags = 0;
		this.totalFlags = 0;
		this.field = new MSVertex[fieldLength][fieldLength];
		this.adjList = new ArrayList<LinkedList<MSVertex>>();
		populateField();
		placeMines();
		setAdjList();
		setMineCount();
	}
	/**
	 * populates the field with tiles
	 */
	private void populateField()
	{
		for(int x = 0; x < fieldLength; x++)
		{
			for(int y = 0; y < fieldLength; y++)
			{
				field[x][y] = new MSVertex(x * fieldLength + y); // populates the field 
			}
		}
	}
	/**
	 * Places each mine in a random position in the field
	 */
	private void placeMines()
	{
		for(int z = 0; z < totalMines; z++)
		{
			// determine the (x, y) position of the new mine
			int x = (int) (Math.random() * fieldLength);
			int y = (int) (Math.random() * fieldLength); // with random number generator
			MSVertex current = field[x][y];
			if (current.isMine() == false)
			{
				current.setMine(true); // set the position to a mine
			}
			else
			{
				z--;
			}
		}
	}
	/**
	 * Creates an adjacency list for each tile in the field and adds all adjacencies to that list
	 */
	private void setAdjList()
	{
		for(int x = 0; x < fieldLength; x++)
		{
			for(int y = 0; y < fieldLength; y++)
			{
				LinkedList<MSVertex> adjCurrent = new LinkedList<MSVertex>(); // adjacency list
				//Upper Left
				if (x != 0 && y != 0)
				{
					adjCurrent.add(field[x-1][y-1]);
				}
				//Up
				if (x != 0)
				{
					adjCurrent.add(field[x-1][y]);
				}
				//Upper Right
				if (x != 0 && y != fieldLength - 1)
				{
					adjCurrent.add(field[x-1][y+1]);
				}
				//Left
				if (y != 0)
				{
					adjCurrent.add(field[x][y-1]);
				}
				//Right
				if (y != fieldLength - 1)
				{
					adjCurrent.add(field[x][y+1]);
				}
				//Lower Left
				if (x != fieldLength - 1 && y != 0)
				{
					adjCurrent.add(field[x+1][y-1]);
				}
				//Low
				if (x != fieldLength - 1)
				{
					adjCurrent.add(field[x+1][y]);
				}
				//Lower Right
				if (x != fieldLength - 1 && y != fieldLength - 1)
				{
					adjCurrent.add(field[x+1][y+1]);
				}
				adjList.add(adjCurrent);
			}
		}
	}
	/**
	 * Sets the amount of adjacent mines for each by searching the adjacency list for mines
	 * at that position.
	 */
	private void setMineCount()
	{
		for(int x = 0; x < fieldLength; x++)
		{
			for(int y = 0; y < fieldLength; y++)
			{
				int mineCount = 0;
				MSVertex current = field[x][y];
				LinkedList<MSVertex>currentAdj = adjList.get(x * fieldLength + y);
				for(MSVertex currentVertex : currentAdj) // search the list for mines
				{
					if(currentVertex.isMine())
					{
						mineCount++; // increment the total mine count
					}
				}
				current.setMineCount(mineCount);
			}
		}
	}
	/**
	 * Explores the tile at x,y. Sets the tile at x,y to explored and returns true if the tile is not a mine
	 * @return false if the tile is not a mine or true if it is a mine
	 */
	public boolean exploreVertex(int x, int y)
	{
		MSVertex current = field[x][y];
		if (current.isMine())
		{
			current.setExplored(true);
			return false;
		}
		else
		{
			exploreBreadthFirst(x, y);
			return true;
		}
	}
	/**
	 * Performs the BreadthFirstSearch for a tile exploration
	 * @param x the x coordinate of the explored tile
	 * @param y the y coordinate of the explored tile
	 */
	private void exploreBreadthFirst(int x, int y)
	{
		LinkedList<MSVertex> queue = new LinkedList<MSVertex>();
		
		MSVertex startVertex = field[x][y];
		queue.offer(startVertex);
		while (!queue.isEmpty())
		{
			MSVertex current = queue.poll();
			current.setExplored(true);
			if(current.getMineCount() == 0)
			{
				LinkedList<MSVertex>currentAdj = adjList.get(current.getVertexNumber());
				for(MSVertex currentVertex : currentAdj)
				{
					if (currentVertex.isExplored() == false)
					{
						if (currentVertex.getMineCount() == 0)
						{
							queue.offer(currentVertex);
						}
						else
						{
							currentVertex.setExplored(true);
						}
					}
				}
			}
			
		}
	}
	/**
	 * Setter to set the amount of correct flags
	 * @param the number of correct flags
	 */
	public void setCorrectFlags(int correctFlags)
	{
		this.correctFlags = correctFlags;
	}
	/**
	 * * Setter to set the amount of total flags
	 * @param the number of flags
	 */
	 
	public void setTotalFlags(int totalFlags)
	{
		this.totalFlags = totalFlags;
	}
	/**
	 * accessor for length
	 * @return the field length
	 */
	public int getLength()
	{
		return fieldLength;
	}
	/**
	 * accessor for density
	 * @return the fieldDensity
	 */
	public double getDensity()
	{
		return fieldDensity;
	}
	/**
	 * accessor for totalMines
	 * @return the total number of mines
	 */
	public int getTotalMines()
	{
		return totalMines;
	}
	/**
	 * accessor for the correct flags
	 * @return the number of correct flags
	 */
	public int getCorrectFlags()
	{
		return correctFlags;
	}
	/**
	 * accessor for total flags
	 * @return the total number of flags
	 */
	public int getTotalFlags()
	{
		return totalFlags;
	}
	/**
	 * accessor that returns a reference to a vertext at (x,y)
	 * @param x the horizontal position of the vertex
	 * @param y the vertical position of the vertex
	 * @return the vertex in the MSField at (x,y)
	 */
	public MSVertex getMSVertex(int x, int y)
	{
		return field[x][y];
	}
	/**
	 * returns a String of the field
	 * @return a String reference of MSField
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for(int y = 0; y < fieldLength; y++)
		{
			sb.append("|" + y);
		}
		sb.append("|\n");
		for(int x = 0; x < fieldLength; x++)
		{
			sb.append(x);
			for(int y = 0; y < fieldLength; y++)
			{
				sb.append("|" + field[x][y].toString());
			}
			sb.append("|\n");
		}
		return sb.toString();
	}
}