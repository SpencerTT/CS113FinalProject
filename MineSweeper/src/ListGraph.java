import java.util.*;
/*
 * A ListGraph is a class that uses an 
 * array of lists to represent edges
 */
public class ListGraph  extends AbstractGraph
{
	//Data Field
	/**
	 * An array of Lists to contain the edges 
	 * that originate with each vertex.
	 */
	private List<Edge>[]edges;
	
	//constructor
	public ListGraph(int numV, boolean directed)
	{
		super(numV, directed);
		edges = new List[numV];
		for(int i =0; i < numV; i++)
		{
			edges[i] = new LinkedList<Edge>();
		}
	}
	/**
	 * Returns an iterator to the edges that originate
	 *  from a given vertex.
	 */
	public Iterator<Edge> edgeIterator(int source)
	{
		return edges[source].iterator();
	}
	/**
	 * Get the edge between two vertices. If an edge 
	 * does not exist, an Edge with a weight 
	 * Double.POSITIVE_INFINITY is returned.
	 */
	public Edge getEdge(int source, int dest)
	{
		Edge target =
				new Edge(source, dest, Double.POSITIVE_INFINITY);
		for(Edge edge : edges[source])
		{
			if(edge.equals(target))
			{
				return edge; //Desired edge found, return it.
			}
		}
		//Assert: All edges for source checked.
		return target;
	}
	/**
	 * Insert a new edge into the graph.
	 */
	public void insert(Edge edge)
	{
		edges[edge.getSource()].add(edge);
		if(!isDirected())
		{
			edges[edge.getDestination()].add(new Edge(
					edge.getDestination(),
					edge.getSource(),
					edge.getWeight()));
		}
	}
	/**
	 * Determine whether an edge exists.
	 */
	public boolean isEdge(int source, int dest)
	{
		return edges[source].contains(new Edge(source, dest));
	}
	
}
