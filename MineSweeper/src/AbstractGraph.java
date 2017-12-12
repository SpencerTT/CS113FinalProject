import java.util.Iterator;

public class AbstractGraph implements Graph
{

	private int numV;
	private boolean directed;
	
	@Override
	public int getNumV() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insert(Edge edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEdge(int source, int dest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Edge getEdge(int source, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Edge> edgeIterator(int source) {
		// TODO Auto-generated method stub
		return null;
	}
	
}