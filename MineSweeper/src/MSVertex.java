public class MSVertex
{
	private boolean mine;
	private boolean flagged;
	private boolean explored;
	private int mineCount;
	private int vertexNumber;
	
	public MSVertex(int vertexNumber)
	{
		mine = false;
		flagged = false;
		explored = false;
		mineCount = 0;
		this.vertexNumber = vertexNumber;
	}
	
	public void setMine(boolean mine)
	{
		this.mine = mine;
	}
	public void setFlagged(boolean flagged)
	{
		this.flagged = flagged;
	}
	public void setExplored(boolean explored)
	{
		this.explored = explored;
	}
	public void setMineCount(int mineCount)
	{
		this.mineCount = mineCount;
	}
	
	public boolean isMine()
	{
		return mine;
	}
	public boolean isFlagged()
	{
		return flagged;
	}
	public boolean isExplored()
	{
		return explored;
	}
	public int getMineCount()
	{
		return mineCount;
	}
	public int getVertexNumber()
	{
		return vertexNumber;
	}
	public int getImage()
	{
		if(flagged)
		{
			return 10;
		}
		else if(!explored)
		{
			return 9;
		}
		else if(mine)
		{
			return 11;
		}
		else
		{
			return mineCount;
		}
	}
	
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