/**
Single source shortest path to all vertices for negatively weighted graph
Steps:
1. Relax all edges at v-1 number of times
2. Check for negative cycle

Time Complexity: O(VE)
*/

import java.util.*;

class Graph{
	ArrayList<Edge> edges;
	ArrayList<ArrayList<Node>> adjList;
	int V;
	int E;

	public Graph(int V, int E) {
		this.V = V;
		this.E = E;
		edges = new ArrayList<>(E);
		adjList = new ArrayList<>(V);
		for(int i=0;i<V;i++){
			adjList.add(i, new ArrayList<>());
		}
	}

	public void addEdge(int src, int dest, int weight) {
		adjList.get(src).add(new Node(dest, weight));
		edges.add(new Edge(src, dest, weight));

	}
}

class Node{
	int dest;
	int weight;

	public Node(int dest, int weight){
		this.dest = dest;
		this.weight = weight;
	}
}

class Edge{
	int src; 
	int dest;
	int weight;

	public Edge(int src, int dest, int weight){
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
}

class BellmonFordAlgo{
	Graph g;
	int V;
	int E;
	int[] dist;
	int[] parent;

	public BellmonFordAlgo(int V, int E, Graph g) {
		this.V = V;
		this.E = E;
		this.g = g;

		dist = new int[V];
		parent = new int[V];

		for(int i=0;i<V;i++){
			dist[i] = Integer.MAX_VALUE;
			parent[i]=-1;
		}
		dist[0]=0;
	}

	public int[] getShortestPathCost() {
		for(int i=1;i<V;i++) {
			for(int j=0;j<E;j++) {
				Edge e = g.edges.get(j);
			
				if(dist[e.src] != Integer.MAX_VALUE
					&& dist[e.dest]>dist[e.src]+e.weight) {

					//System.out.println("Edge src : "+e.src+" dest : "+e.dest+" weight : "+e.weight);
					dist[e.dest]=dist[e.src]+e.weight;
					parent[e.dest] = e.src;
				}
			}
		}

		for(int j=0;j<E;j++) {
			Edge e = g.edges.get(j);

			if(dist[e.src] != Integer.MAX_VALUE
					&& dist[e.dest]>dist[e.src]+e.weight) {
				System.out.println("Graph has negative weighted cycle");
				return new int[0];
			}
		}

		return dist;
	}

}

class BellmonFord{

	public static void main(String[] args){
		Graph g = new Graph(5, 8);
		g.addEdge(0, 1, -1);
		g.addEdge(0, 2, 4);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 2);
		g.addEdge(1, 4, 2);
		g.addEdge(3, 2, 5);
		g.addEdge(3, 1, 1);
		g.addEdge(4, 3, -3);

		BellmonFordAlgo algo = new BellmonFordAlgo(5, 8, g);
		algo.getShortestPathCost();
		for(int i=0;i<algo.dist.length;i++) {
			System.out.println("Path Cost from 0 to "+i+" : "+algo.dist[i]);
		}
		
	}
}