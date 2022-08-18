Dijkstra

take priority queue which minHeap based on weight
take distance array initialized to max
set distance of source to 0
insert source node in priority queue
iterate until queue is not empty:	
	iterate through all neighbours:
		if distance of nighbour is greater than distance of current+neighbour weight
				replace its distance


O(E log V)

kruskals

use disjoint set

1. Sort all the edges in non-decreasing order of their weight. 
2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it. 
3. Repeat step#2 until there are (V-1) edges in the spanning tree.

Prims	
The idea is to maintain two sets of vertices. 
The first set contains the vertices already included in the MST, 
the other set contains the vertices not yet included. 
At every step, it considers all the edges that connect the two sets and 
	picks the minimum weight edge from these edges.
	After picking the edge, it moves the other endpoint of  the edge to the set containing MST. 


Bellmond Ford: For negative weighted graph

Single source shortest path to all vertices for negatively weighted graph
Steps:
1. Relax all edges at v-1 number of times
2. Check for negative cycle

Time Complexity: O(VE)


Floyd warshal: Multi source sortest path to all Steps

step:
1. take intermediate node (for k 0 to V-1)
2. take source node (for i 0 to V-1)
3. take destination node (for j 0 to V-1)
4. dist[i][j] = min(dist[i][j], dist[i][k]+dist[k][j])

Time complexity: O(V^3)


Topological Sort:
Two ways:
1. use DFS
2. use Kahns algo



