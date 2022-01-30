
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UndirectedGraph<T> {
	private HashMap<T, Vertex<T>> vertices;
	private HashMap<T, T> edges;
	private Queue<Vertex<T>> vertexqueue = new LinkedList<>();
	private Queue<Vertex<T>> path = new LinkedList<>();

	public UndirectedGraph() {
		this.vertices = new HashMap<T, Vertex<T>>();
		this.edges = new HashMap<T, T>();
	}

	public void addConnection(T node1, T node2) {
		if (vertices.get(node1) == null) {
			Vertex<T> tempnode = new Vertex<T>(node1);
			vertices.put(node1, tempnode);
		}
		if (vertices.get(node2) == null) {
			Vertex<T> tempnode = new Vertex<T>(node2);
			vertices.put(node2, tempnode);
		}
		if ((edges.containsKey(node1) && ((edges.get(node1).equals(node2)))
				|| (edges.containsKey(node2) && (edges.get(node2).equals(node1))))) {
			System.out.println("Those nodes are already connected!");
		} else {
			edges.put(node1, node2);
			Vertex<T> tempnode = new Vertex<T>(node2);
			vertices.get(node1).connect(tempnode);
			tempnode = new Vertex<T>(node1);
			vertices.get(node2).connect(tempnode);
		}
	}

	public int getShortestPath(Vertex<T> startVertex, Vertex<T> targetVertex) {
		resetNeighborcounter();
		vertexqueue.clear();
		path.clear();
		boolean done = false;
		vertices.get(startVertex.getLabel()).setVisited();
		vertexqueue.add(startVertex);
		Vertex<T> frontVertex = null;
		Vertex<T> nextNeighbor = null;
		if (vertices.get(targetVertex.getLabel()).isVisited()) {
			//If the vertex is already visited by the source, just check vertex attributes for shortest path.
		} else {
			while (!done && !vertexqueue.isEmpty()) {
				frontVertex = vertexqueue.remove();
				while (!done && vertices.get(frontVertex.getLabel()).hasNextNeighbor()) {
					nextNeighbor = vertices.get(frontVertex.getLabel()).getNextNeighbor();
					if (!(vertices.get(nextNeighbor.getLabel()).isVisited())) {
						vertices.get(nextNeighbor.getLabel()).setVisited();
						vertices.get(nextNeighbor.getLabel())
								.setPathlength(vertices.get(frontVertex.getLabel()).getPathlength() + 1);
						vertices.get(nextNeighbor.getLabel()).setParent(frontVertex);

					}
					vertexqueue.add(nextNeighbor);
					if (vertices.get(nextNeighbor.getLabel()).getLabel()
							.equals(vertices.get(targetVertex.getLabel()).getLabel())) {
						done = true;
					}

				}
			}
		}
		path.add(targetVertex);
		Vertex<T> vertex = targetVertex;
		while (vertices.get(vertex.getLabel()).hasPrecedessor()) {
			vertex = vertices.get(vertex.getLabel()).getParent();
			path.add(vertex);
		}
		return targetVertex.getPathlength();

	}

	public Iterable<Vertex<T>> vertices() {
		return vertices.values();
	}


	public void getCentralityMetrics() {
		Iterable<Vertex<T>> vertices1 = vertices();
		Queue<Vertex<T>> traversalqueue1 = new LinkedList<>();
		Queue<Vertex<T>> traversalqueue2 = new LinkedList<>();
		Stack<Vertex<T>> tempstack=new Stack<>();
		//Reversing the trace queue using stack to get maximum amount of nodes traced in minimum time.
		for (Vertex<T> e : vertices1) {
			traversalqueue1.add(e);
			tempstack.push(e);
		}
		for (int i = 0; i < tempstack.size(); i++) {
			traversalqueue2.add(tempstack.pop());
		}
		int size = traversalqueue1.size() - 1;
		traversalqueue2.remove();
		// Finding every metrice for every node.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < traversalqueue2.size(); j++) {
				Vertex<T> temp1 = traversalqueue1.peek();
				Vertex<T> temp2 = traversalqueue2.peek();
				//System.out
						//.println("Checking the shortest path between" + temp1.getLabel() + " and " + temp2.getLabel());
				int pathLength = getShortestPath(temp1, temp2);
				// Closeness metrice
				vertices.get(temp1.getLabel()).addCloseness(pathLength);
				vertices.get(temp2.getLabel()).addCloseness(pathLength);

				// Betweenness metrice
				if (path.size() >= 3) {
					path.remove();
					while (path.size() > 1) {
						Vertex<T> temp = path.remove();
						vertices.get(temp.getLabel()).increaseBetweennes_value();
					}

				}
				traversalqueue2.add(traversalqueue2.remove());

			}
			resetVertexLoopAttributes();
			traversalqueue2.poll();
			traversalqueue1.remove();

		}
	}

	public void resetNeighborcounter() {
		for (Vertex<T> e : vertices()) {
			vertices.get(e.getLabel()).setNeighborcounter(0);
		}

	}

	public void resetVertexLoopAttributes() {
		for (Vertex<T> e : vertices()) {
			vertices.get(e.getLabel()).setPathlength(0);
			vertices.get(e.getLabel()).setNeighborcounter(0);
			vertices.get(e.getLabel()).setParent(null);
			vertices.get(e.getLabel()).setUnVisited();
		}

	}

	public void getMetrics() {
		for (Vertex<T> e : vertices()) {
			System.out.println("Name: " + e.getLabel() + "Closeness: " + e.getCloseness_value() + "Betweenness: "
					+ e.getBetweennes_value());
		}
	}

	public void findMaxMetrics(int file) {
		int betweenness = 0;
		double closeness = 0;
		T maxnode1 = null;
		T maxnode2 = null;
		for (Vertex<T> e : vertices()) {
			if (vertices.get(e.getLabel()).getBetweennes_value() >= betweenness) {
				betweenness = vertices.get(e.getLabel()).getBetweennes_value();
				maxnode1 = vertices.get(e.getLabel()).getLabel();
			}
			if (vertices.get(e.getLabel()).getCloseness_value() >= closeness) {
				closeness = vertices.get(e.getLabel()).getCloseness_value();
				maxnode2 = vertices.get(e.getLabel()).getLabel();
			}
		}
		if (file == 0) {
			System.out.println("Zachary Karate Club Network – " + maxnode1 + " " + betweenness);
			System.out.println("Zachary Karate Club Network – " + maxnode2 + " " + closeness);
			System.out.println();
		} else {
			System.out.println("Facebook Social Network – " + maxnode1 + " " + betweenness);
			System.out.println("Facebook Social Network – " + maxnode2 + " " + closeness);
		}

	}
}
