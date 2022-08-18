import java.util.*;

class Graph {
	int n;
	ArrayList<LinkedList<Integer>> adj;

	public Graph(int n) {
		this.n = n;
		this.adj = new ArrayList<LinkedList<Integer>>(n);

		for(int i=0;i<n;i++) {
			adj.add(new LinkedList<Integer>());
		}
	}

	// directed graph
	public void addEdgeForDirected(int source, int destination) {
		adj.get(source).add(destination);
	}

	// undirected graph
	public void addEdgeForUndirected(int source, int destination) {
		adj.get(source).add(destination);
		adj.get(destination).add(source);
	}

	public void dfs() {
		if(n==0)
			return;
		boolean visited[] = new boolean[n];

		for(int i=0;i<n;i++)
			visited[i]=false;

		for(int i=0;i<n;i++) {
			if(!visited[i])
				dfsUtil(i, visited);
		}
		System.out.println();
	}

	private void dfsUtil(int index, boolean visited[]) {
		System.out.print(index+" ");
		visited[index] = true;
		for(Integer i : adj.get(index)) {
			if(!visited[i])
				dfsUtil(i, visited);
		}
	}

	public boolean isCycleForDirected() {
		if(n==0)
			return false;

		boolean visited[] = new boolean[n];
		boolean recStack[] = new boolean[n];

		for(int i =0;i<n;i++) {
			visited[i] = false;
			recStack[i] = false;
		}

		for(int i=0;i<n;i++) {
			if(isCycleForDirectedUtil(i, visited, recStack))
				return true;
		}

		return false;
	}

	private boolean isCycleForDirectedUtil(int index, boolean visited[], boolean recStack[]) {
		if(recStack[index])
			return true;
		if(visited[index])
			return false;

		visited[index] = true;
		recStack[index] = true;

		for(Integer i : adj.get(index)) {
			if(isCycleForDirectedUtil(i, visited, recStack))
				return true;
		}

		recStack[index] = false;
		return false;
	}

	public boolean isCycleForUndirected() {
		if(n==0)
			return false;

		boolean visited[] = new boolean[n];
		for(int i=0;i<n;i++)
			visited[i]=false;
		int parent = -1;

		for(int i=0;i<n;i++) {
			if(!visited[i] && isCycleForUndirectedUtil(i, visited, parent))
				return true;
		}

		return false;
	}

	private boolean isCycleForUndirectedUtil(int index, boolean visited[], int parent) {
		visited[index] = true;

		for(Integer i : adj.get(index)) {
			if(!visited[i]) {
				if(isCycleForUndirectedUtil(i, visited, index))
					return true;
			} else {
				if(i != parent) 
					return true;
			}
		}

		return false;
	}

	public Stack<Integer> topologicalSort() {
		Stack<Integer> result = new Stack<>();
		if(n==0)
			return result;

		boolean visited[] = new boolean[n];
		for(int i=0;i<n;i++)
			visited[i]=false;

		for(int i=0;i<n;i++) {
			if(!visited[i])
				topologicalSortUtil(i, visited, result);
		}

		return result;
	}

	private void topologicalSortUtil(int index, boolean visited[], Stack<Integer> result) {
		visited[index] = true;

		for(Integer i : adj.get(index)) {
			if(!visited[i])
				topologicalSortUtil(i, visited, result);
		}

		result.push(index);
	}

	public static void printStack(Stack<Integer> s) {
		while(!s.isEmpty())
			System.out.print(s.pop() + " ");
		System.out.println();
	} 

	public static void main(String args[]) {
		Graph g = new Graph(6);
        g.addEdgeForDirected(5, 2);
        g.addEdgeForDirected(5, 0);
        g.addEdgeForDirected(4, 0);
        g.addEdgeForDirected(4, 1);
        g.addEdgeForDirected(2, 3);
        g.addEdgeForDirected(3, 1);
 
        
        System.out.println(
            "Following is Depth First Traversal");
 
        g.dfs();

		Graph g1 = new Graph(4);
        g1.addEdgeForDirected(0, 1);
        g1.addEdgeForDirected(0, 2);
        g1.addEdgeForDirected(1, 2);
        g1.addEdgeForDirected(2, 0);
        g1.addEdgeForDirected(2, 3);
        g1.addEdgeForDirected(3, 3);
        if (g1.isCycleForDirected())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");
 
        Graph g2 = new Graph(3);
        g2.addEdgeForDirected(0, 1);
        g2.addEdgeForDirected(1, 2);
        if (g2.isCycleForDirected())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        Graph g3 = new Graph(5);
        g3.addEdgeForUndirected(1, 0);
        g3.addEdgeForUndirected(0, 2);
        g3.addEdgeForUndirected(2, 1);
        g3.addEdgeForUndirected(0, 3);
        g3.addEdgeForUndirected(3, 4);
        if (g3.isCycleForUndirected())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");
 
        Graph g4 = new Graph(3);
        g4.addEdgeForUndirected(0, 1);
        g4.addEdgeForUndirected(1, 2);
        if (g4.isCycleForUndirected())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");


        printStack(g.topologicalSort());

        Graph g5 = new Graph(3);
        g5.addEdgeForDirected(0, 1);
        g5.addEdgeForDirected(0, 2);
        g5.addEdgeForDirected(1, 0);
        printStack(g5.topologicalSort());

	}
}