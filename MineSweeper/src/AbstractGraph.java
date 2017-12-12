import java.util.Iterator;

public class AbstractGraph implements Graph
{

	private int numV;
	private boolean directed;
	
	public AbstractGraph(int numV, boolean directed)
	{
		this.numV = numV;
		this.directed = directed;
	}
	
	@Override
	public int getNumV()
	{
		return numV;
	}

	@Override
	public boolean isDirected()
	{
		return directed;
	}

	@Override
	public void insert(Edge edge)
	{
		
	}

	@Override
	public boolean isEdge(int source, int dest)
	{
		return false;
	}

	@Override
	public Edge getEdge(int source, int dest)
	{
		return null;
	}

	@Override
	public Iterator<Edge> edgeIterator(int source) {
		return null;
	}
	
}