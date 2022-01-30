
public class Edge<T> {
	private Vertex<T> node1;
	private Vertex<T> node2;
	Edge(Vertex<T> node1,Vertex<T> node2){
		//Create an edge between 2 nodes.
		this.node1=node1;
		this.node2=node2;
	}
	public Vertex<T> getNode1() {
		return node1;
	}
	public void setNode1(Vertex<T> node1) {
		this.node1 = node1;
	}
	public Vertex<T> getNode2() {
		return node2;
	}
	public void setNode2(Vertex<T> node2) {
		this.node2 = node2;
	}
}
