/**
MST
1. Cost
2. Path (Edge List of MST)

Steps:
1. Sort All Edges as per weight, Edge (source, destination, weight)
2. Iterate through edges, add edge in MST if it doesn't form a cycle
		a. detect cycle by using union-find

Time complexity:
1. Sort all edges: O(E log E)
2. Iterate through all edges or vertices: O(V+E)
	a. At each iteration, find and union operation: O(log V)
3. Overall: O(E log E)+ O(V+E)*O(log V) = 	O(E log E)+ O(E log V) = O(E log V) or O(E log E)

*/
import java.util.*;		

class Edge {
	int src;
	int dest;
	int weight;

	public Edge(int s, int d, int w){
		src=s;
		dest=d;
		weight=w;
	}
}

class DisjointSet{
	int n;
	int[] root;
	int[] rank;

	public DisjointSet(int n){
		this.n=n;
		root = new int[n];
		rank = new int[n];

		for(int i=0;i<n;i++){
			root[i]=i;
			rank[i]=1;
		}
	} 

	int find(int x) {
		while(x!=root[x]) {
			root[x] = root[root[x]];
			x=root[x];
		}
		return x;
	}

	void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if(rootX!=rootY){
			if(rank[rootX] > rank[rootY]){
				root[rootY] = rootX;
			} else if(rank[rootX] < rank[rootY]) {
				root[rootX] =rootY;
			} else {
				root[rootY] = rootX;
				rank[rootX]++;
			}
		}
	}
}

class KruskalMST{
	int v;
	int e;
	List<Edge> edges;
	List<Edge> mst;
	int mstCost;
	DisjointSet ds;

	public KruskalMST(int v, int e) {
		this.v = v;
		this.e = e;
		edges = new ArrayList<Edge>();
		mst = new ArrayList<Edge>();
		ds = new DisjointSet(v);
		mstCost = 0;
	}

	public void addEdge(int s, int d, int w) {
		Edge e = new Edge(s, d, w);
		edges.add(e);
	}

	public void sortEdgesByWeight() {
		Collections.sort(edges, (e1, e2)->e1.weight-e2.weight);
	}

	public List<Edge> getMST() {
		if(mst!=null && mst.size()!=0) {
			return mst;
		}

		createMST();
		return mst;
	}

	public int getMstCost(){
		if(mstCost!=0) {
			return mstCost;
		}

		return findMstCost();
	}

	private int findMstCost(){
		createMST();
		return mstCost;
	}

	private void createMST(){
		sortEdgesByWeight();

		int i=0, j=0;

		while(j<v-1) {
			Edge e = edges.get(i);
			i++;

			if(ds.find(e.src) != ds.find(e.dest)) {
				this.mst.add(e);
				this.mstCost+=e.weight;
				ds.union(e.src, e.dest);
				j++;
			}
		}
	}
}

class Kruskal{
	public static void main(String[] args){
		KruskalMST kruskal = new KruskalMST(4, 5);
		kruskal.addEdge(0,1,10);
		kruskal.addEdge(0,2,6);
		kruskal.addEdge(0,3,5);
		kruskal.addEdge(1,3,15);
		kruskal.addEdge(2,3,4);
		System.out.println(kruskal.getMstCost());

		for(Edge e : kruskal.getMST()) {
			System.out.println("src : "+e.src+" dest : "+e.dest+" weight : "+e.weight);
		}
	}
}		