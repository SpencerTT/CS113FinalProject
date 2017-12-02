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
		totalMines = (int) (fieldLength * fieldLength * fieldDensity);
		correctFlags = 0;
		totalFlags = 0;
		field = new MSVertex[fieldLength][fieldLength];
		adjList = new ArrayList<LinkedList<MSVertex>>();
		populateField();
		placeMines();
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
	
	private void setMineCount()
	{
		for(int x = 0; x < fieldLength; x++)
		{
			for(int y = 0; y < fieldLength; y++)
			{
				MSVertex current = field[x][y];
			}
		}
	}
	
	private void setAdjList()
	{
		
	}
	
	public void setCorrectFlags(int correctFlags)
	{
		this.correctFlags = correctFlags;
	}
	public void setTotalFlags(int totalFlags)
	{
		this.totalFlags = totalFlags;
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
}