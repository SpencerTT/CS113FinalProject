
public class Edge {
private int destination;
private int source;
private double weight;
/**
 * Contructs an edge with the given source and destination and a default weight of 1.
 * @param source the node from which the edge leaves
 * @param destination the node to which the edge ends
 */
public Edge(int source, int destination){
	this.source= source;
	this.destination = destination;
	this.weight = 1;
}
/**
 * Constructs an edge with the given source, destination, and weight
 * @param source the node from which the edge leaves
 * @param destination the node to which the edge ends
 * @param weight the weight of the edge
 */
public Edge(int source, int destination, double weight){
	this.source = source;
	this.destination = destination;
	this.weight = weight;
}
/**
 * Determines the equality of this edge and the object in its parameter by comparing source, destination and weight of the 
 * two objects.
 * @param other the object to test equality with the calling edge
 */
public boolean equals(Object other){
	if(other != getClass()){
		return false;}
	Edge temp = (Edge) other;
	return (temp.getSource() == getSource() && temp.getDestination() == getDestination() && temp.getWeight() == getWeight());
	}
/**
 * Accessor for destination
 * @return the destination for this edge
 */
public int getDestination(){
	return destination;
}
/**
 * Accessor for source
 * @return the source for this edge
 */
public int getSource(){
	return source;
}
/**
 * Accessor for weight
 * @return the weight
 */
public double getWeight(){
	return weight;
}
/**
 * returns a String instance of the edge
 * @return the source, destination and weight of the edge as a String
 */
public String toString(){
	return source + ":" + destination + " weight of " + weight;
}
/**
 * finds the hashCode of the edge by subtracting the destination from the source
 * @return this edge's hashCode
 */
public int hashCode(){
	return source - destination;
}
}
