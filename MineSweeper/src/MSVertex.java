/**
 * Creates a single Vertex for the MSField to work with
 * 
 * @Authors: Spencer, Shawn, Eric
 * @version 1.0
 */
public class MSVertex
{
	private boolean mine; // false if the vertex is not a mine
	private boolean flagged; 
	private boolean explored;
	private int mineCount;
	private int vertexNumber; 
	/**
	 * Constructs an unexplored, unflagged, and disarmed mine vertex.
	 * @param vertexNumber the id number of this vertex
	 */
	public MSVertex(int vertexNumber)
	{
		mine = false;
		flagged = false;
		explored = false;
		mineCount = 0;
		this.vertexNumber = vertexNumber;
	}
	/**
	 * sets mine to the parameter
	 * @param mine arms a vertex if true, disarms if false
	 */
	public void setMine(boolean mine)
	{
		this.mine = mine;
	}
	/**
	 * sets flagged to the parameter
	 * @param flagged sets a flag to the tile if true
	 */
	public void setFlagged(boolean flagged)
	{
		this.flagged = flagged;
	}
	/**
	 * sets the explored instance to the parameter
	 * @param explored sets the tile to explored if true
	 */
	public void setExplored(boolean explored)
	{
		this.explored = explored;
	}
	/**
	 * sets the mineCount
	 * @param mineCount the current number of mines
	 */
	public void setMineCount(int mineCount)
	{
		this.mineCount = mineCount;
	}
	/**
	 * accessor for mine
	 * @returns true if the tile is a mine and false if it is not
	 */
	public boolean isMine()
	{
		return mine;
	}
	/**
	 * accessor for flagged
	 * @return true if the tile is flagged
	 */
	public boolean isFlagged()
	{
		return flagged;
	}
	/**
	 * accessor for explored
	 * @returns true if the tile has been explored
	 */
	public boolean isExplored()
	{
		return explored;
	}
	/**
	 * accessor for mineCount
	 * @return the currnent mineCount
	 */
	public int getMineCount()
	{
		return mineCount;
	}
	/**
	 * accessor for vertexNumber
	 * @return the id number for this vertex
	 */
	public int getVertexNumber()
	{
		return vertexNumber;
	}
	/**
	 * accessor for image
	 * @return the image dependent upon the booleans explored, flagged, and mine
	 */
	public int getImage()
	{
		if(flagged)
		{
			return 10; // flag image
		}
		else if(!explored)
		{
			return 9; // explored tile
		}
		else if(mine)
		{
			return 11; // mine
		}
		else
		{
			return mineCount; // adjacent mine count
		}
	}
	/**
	 * accessor for the final image, in the event of a win or a game over.
	 * @return the final image for this vertex
	 */
	public int getFinalImage()
	{
		if(flagged && mine)
		{
			return 12;
		}
		else if(flagged)
		{
			return 10;
		}
		else if(mine)
		{
			return 11;
		}
		else if(!explored)
		{
			return 9;
		}
		else
		{
			return mineCount;
		}
	}
	/**
	 * returns a string instance of an MSVertex
	 */
	public String toString()
	{
		if (mine)
		{
			return "X";
		}
		else if (flagged)
		{
			return "F";
		}
		else if (!explored)
		{
			return "?";
		}
		else
		{
			return "" + mineCount;
		}
	}
	public boolean equals(MSVertex other)
	{
		return (getVertexNumber() == other.getVertexNumber());
	}
}