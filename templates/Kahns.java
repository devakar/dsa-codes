/**
Kahn's algo

Steps:
1. Either calculate indegree or outdegree of every node depending on the requirement
2. Select source with zero indegree/outdegree, and push it into queue
3. Perform BFS, pop a node from queue and reduce indegree/outdegree of its neighboring nodes
4. If indegree/outdegree of any neighboring node becomes zero, push that node in queue
4. Store in result if indegree/outdegree of a node becomes zero
5. If count of visited nodes is not equals to number of nodes, then there is cycle and not possible to get topological order


Time complexity: O(V+E)
*/

import java.util.*;

class Graph{
	int V;
	ArrayList<ArrayList<Integer>> adjList;
	int[] indegree;


	public Graph(int n){
		V = n;
		adjList  = new ArrayList<>(n);
		for(int i=0;i<n;i++) {
			adjList.add(i, new ArrayList<>());
		}

		indegree = new int[n];
		Arrays.fill(indegree, 0);
	} 

	public void addEdge(int src, int dest) {
		adjList.get(src).add(dest);
		indegree[dest]++;
	}

	public List<Integer> getTopologicalSortedOrder(){
		List<Integer> result = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();

		for(int i=0;i<V;i++){
			if(indegree[i]==0) {
				q.offer(i);
			}
		}

		int visited =0;

		while(!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);

			for(Integer i : adjList.get(cur)) {
				indegree[i]--;
				if(indegree[i]==0) {
					q.offer(i);
				}
			}

			visited++;
		}


		if(visited!=V) {
			System.out.println("Graph has cycle");
			return new ArrayList<>();
		}

		return result;
	}
}



class Kahns{
	public static void main(String[] args) {
		Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        for(Integer i: g.getTopologicalSortedOrder()) {
        	System.out.println(i);
        }

	}
}