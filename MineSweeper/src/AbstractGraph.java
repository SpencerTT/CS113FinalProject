import java.util.Iterator;

/**
 * A class that extends Graph but is not fully functional
 *
 * @Authors: Spencer, Shawn, Eric
 * @version 1.0
 */

public class AbstractGraph implements Graph
{

	private int numV;
	private boolean directed;
	//Constructor
	public AbstractGraph(int numV, boolean directed)
	{
		this.numV = numV;
		this.directed = directed;
	}
	/**
	 * The number of vertices.
	 */
	@Override
	public int getNumV()
	{
		return numV;
	}
	/**
	 * Returns true if the graph is a directed graph.
	 */
	@Override
	public boolean isDirected()
	{
		return directed;
	}
	//stub
	@Override
	public void insert(Edge edge)
	{
		
	}
	//stub
	@Override
	public boolean isEdge(int source, int dest)
	{
		return false;
	}
	//stub
	@Override
	public Edge getEdge(int source, int dest)
	{
		return null;
	}
	//stub
	@Override
	public Iterator<Edge> edgeIterator(int source)
	{
		return null;
	}
}