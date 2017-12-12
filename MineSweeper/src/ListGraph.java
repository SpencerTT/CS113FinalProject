import java.util.*;
/*
 * A ListGraph is a class that uses an 
 * array of lists to represent edges
 */
public class ListGraph  implements Graph
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
		super(numV, directed)
		edges = new List[numV];
		for(int i =0; i < numV; i++)
		{
			edges[i] = new LinkedList<Edge>();
		}
	}
	
	
}
