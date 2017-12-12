
public class Edge {
private int destination;
private int source;
private double weight;
public Edge(int source, int destination){
	this.source= source;
	this.destination = 1;
}
public Edge(int source, int destination, double weight){
	this.source = source;
	this.destination = destination;
	this.weight = weight;
}
public boolean equals(Object other){
	if(other != getClass()){
		return false;}
	Edge temp = (Edge) other;
	return (temp.getSource() == getSource() && temp.getDestination() == getDestination() && temp.getWeight() == getWeight());
	}
public int getDestination(){
	return destination;
}
public int getSource(){
	return source;
}
public double getWeight(){
	return weight;
}
public String toString(){
	return source + ":" + destination + " weight of " + weight;
}
public int hashCode(){
	return source - destination;
}
}
