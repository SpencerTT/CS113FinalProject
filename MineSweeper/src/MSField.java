import java.util.ArrayList;
import java.util.LinkedList;

public class MSField
{
	private int fieldLength;
	private double fieldDensity;
	private int totalMines;
	private int correctFlags;
	private int totalFlags;
	private MSVertex[][] field;
	private ArrayList<LinkedList<MSVertex>> adjList;
	
	public MSField(int fieldLength, double fieldDensity)
	{
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
	
	private void populateField()
	{
		for(int x = 0; x < fieldLength; x++)
		{
			for(int y = 0; y < fieldLength; y++)
			{
				field[x][y] = new MSVertex(x * fieldLength + y);
			}
		}
	}
	
	private void placeMines()
	{
		for(int z = 0; z < totalMines; z++)
		{
			int x = (int) (Math.random() * fieldLength);
			int y = (int) (Math.random() * fieldLength);
			MSVertex current = field[x][y];
			if (current.isMine() == false)
			{
				current.setMine(true);
			}
			else
			{
				z--;
			}
		}
	}
	
	private void setAdjList()
	{
		for(int x = 0; x < fieldLength; x++)
		{
			for(int y = 0; y < fieldLength; y++)
			{
				LinkedList<MSVertex> adjCurrent = new LinkedList<MSVertex>();
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
	
	private void setMineCount()
	{
		for(int x = 0; x < fieldLength; x++)
		{
			for(int y = 0; y < fieldLength; y++)
			{
				int mineCount = 0;
				MSVertex current = field[x][y];
				LinkedList<MSVertex>currentAdj = adjList.get(x * fieldLength + y);
				for(MSVertex currentVertex : currentAdj)
				{
					if(currentVertex.isMine())
					{
						mineCount++;
					}
				}
				current.setMineCount(mineCount);
			}
		}
	}
	
	public boolean exploreVertex(int x, int y)
	{
		MSVertex current = field[x][y];
		if (current.isMine())
		{
			return false;
		}
		else
		{
			exploreBreadthFirst(x, y);
			return true;
		}
	}
	
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
	
	public void setCorrectFlags(int correctFlags)
	{
		this.correctFlags = correctFlags;
	}
	public void setTotalFlags(int totalFlags)
	{
		this.totalFlags = totalFlags;
	}
	
	public int getLength()
	{
		return fieldLength;
	}
	public double getDensity()
	{
		return fieldDensity;
	}
	public int getTotalMines()
	{
		return totalMines;
	}
	public int getCorrectFlags()
	{
		return correctFlags;
	}
	public int getTotalFlags()
	{
		return totalFlags;
	}
	
	public MSVertex getMSVertex(int x, int y)
	{
		return field[x][y];
	}
	
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