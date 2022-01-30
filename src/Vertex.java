import java.util.ArrayList;

public class Vertex<T> {
	private T label;
	private ArrayList<Edge<T>> edges;
	private boolean visited;
	private int betweenness_value;
	private double closeness_value;
	private int closeness_divider;
	private Vertex<T> parent;
	private int pathlength;
	private int neighborcounter;

	public Vertex(T label) {
		this.label = label;
		edges = new ArrayList<Edge<T>>();
		visited = false;
		betweenness_value = 0;
		closeness_value = 0;
		closeness_divider = 0;
		parent = null;
		pathlength = 0;
		neighborcounter=0;
	}

	public Vertex<T> getParent() {
		return parent;
	}

	public boolean hasPrecedessor() {
		return (!(getParent()==null));
	}

	public void setParent(Vertex<T> parent) {
		this.parent = parent;
	}

	public int getBetweennes_value() {
		return betweenness_value;
	}

	public void increaseBetweennes_value() {
		betweenness_value++;
	}

	public double getCloseness_value() {
		return closeness_value;
	}
	public void addCloseness(int length) {
		closeness_divider++;
		closeness_value += (double)length / (double)closeness_divider;
	}

	public T getLabel() {
		return label;
	}

	public void setLabel(T label) {
		this.label = label;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited() {
		this.visited = true;
	}

	public void setUnVisited() {
		this.visited = false;

	}

	public boolean hasNextNeighbor() {
		boolean flag = false;
		if(neighborcounter<edges.size())
			flag=true;
		//System.out.println(flag);
		return flag;
	}

	public Vertex<T> getNextNeighbor() {
		Vertex<T> nextNeighbor=null;
		nextNeighbor=edges.get(neighborcounter).getNode2();
		neighborcounter++;
		return nextNeighbor;
	}

	public int getNeighborcounter() {
		return neighborcounter;
	}

	public void setNeighborcounter(int neighborcounter) {
		this.neighborcounter = neighborcounter;
	}

	public void connect(Vertex<T> connection) {
		// Create an edge between vertices
		Edge<T> e = new Edge<T>(this, connection);
		edges.add(e);
	}


	public int getPathlength() {
		return pathlength;
	}

	public void setPathlength(int pathlength) {
		this.pathlength = pathlength;
	}
}
