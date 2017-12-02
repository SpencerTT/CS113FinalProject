public class MSVertex
{
	private boolean mine;
	private boolean flagged;
	private int mineCount;
	private int vertexNumber;
	
	public MSVertex(int vertexNumber)
	{
		mine = false;
		flagged = false;
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
	public int getMineCount()
	{
		return mineCount;
	}
	public int getVertexNumber()
	{
		return vertexNumber;
	}
	
	public String toString()
	{
		if (mine)
		{
			return "X";
		}
		else if (flagged)
		{
			return "O";
		}
		else
		{
			return "" + mineCount;
		}
	}
}