/**
Single source shortest path from source to all vertices
Applicable for positive weighted graph
Step:
1. Take priority queue which uses weight for priority, insert the source in the queue
2. iterate until queue is non-empty
	a. from poped item of queue, relax all neighboring edges and add in queue if possible

Time complexity: O(E log V)

*/

import java.util.*;

class DijkstraAlgo{
	int V;
	Graph g;
	int[] dist;
	int[] parent;

	public DijkstraAlgo(int v, Graph g) {
		V =v;
		this.g = g;

		dist = new int[V];
		parent = new int[V];

		for(int i=0;i<V;i++){
			dist[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}

		dist[0]=0;
	}

	void shortestPath() {
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.weight-b.weight);
		pq.offer(new Node(0, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			for(Node adj : g.adjList.get(cur.vertex)) {

				if(dist[cur.vertex] != Integer.MAX_VALUE
					&& dist[adj.vertex] > dist[cur.vertex] + adj.weight) {

					dist[adj.vertex] = dist[cur.vertex] + adj.weight;
					parent[adj.vertex] = cur.vertex;

					pq.offer(new Node(adj.vertex, dist[adj.vertex]));
				}
			}

			
		}
	}
}

class Node {
	int vertex;
	int weight;

	public Node(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
}

class Graph{
	ArrayList<ArrayList<Node>> adjList;
	int V;

	public Graph(int V) {
		this.V = V;
		adjList = new ArrayList<>(V);

		for(int i=0;i<V;i++) {
			adjList.add(i, new ArrayList<>());
		}
	}

	public void addEdge(int src, int dest, int weight) {
		adjList.get(src).add(new Node(dest, weight));
	}

}

class Dijkstra{
	public static void main(String[] args) {
		Graph g = new Graph(9);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8);
		g.addEdge(1, 7, 11);
		g.addEdge(1, 0, 7);
		g.addEdge(2, 1, 8);
		g.addEdge(2, 3, 7);
		g.addEdge(2, 8 ,2);
		g.addEdge(2, 5, 4);
		g.addEdge(3, 2, 7);
		g.addEdge(3, 4, 9);
		g.addEdge(3, 5, 14);
		g.addEdge(4, 3, 9);
		g.addEdge(4, 5, 10);
		g.addEdge(5 ,4, 10);
		g.addEdge(5, 6, 2);
		g.addEdge(6, 5, 2);
		g.addEdge(6, 7, 1);
		g.addEdge(6, 8, 6);
		g.addEdge(7, 0, 8);
		g.addEdge(7, 1, 11);
		g.addEdge(7, 6, 1);
		g.addEdge(7, 8, 7);
		g.addEdge(8, 2, 2);
		g.addEdge(8, 6, 6);
		g.addEdge(8, 7, 1);

		DijkstraAlgo algo = new DijkstraAlgo(9, g);

		algo.shortestPath();

		for(int i=0;i<algo.dist.length; i++) {
			System.out.println("Shortest distance from 0 to "+i+" is : "+algo.dist[i]);
		}

	}
}