import java.util.Iterator;

public interface Graph
{
	
	int getNumV();
	
	boolean isDirected();
	
	void insert(Edge edge);
	
	boolean isEdge(int source, int dest);
	
	E getEdge(int source, int dest);
	
	Iterator<Edge> edgeIterator(int source);
}