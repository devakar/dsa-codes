public class UnionFind {
    // Constructor of Union-find. The size is the length of the root array.
    private int[] root;
    private int[] rank;
    int count; //if need to count connected component

    public UnionFind(int size) {}
    public int find(int x) {}
    public void union(int x, int y) {}
    public boolean connected(int x, int y) {}
}

public UnionFind(int size) {
    root = new int[size];
    rank = new int[size];
    count = size;
    for (int i = 0; i < size; i++) {
        root[i] = i;
        rank[i] =1
    }
}

//always try to use path compression and rank by union

public int find(int x) {
    if (x == root[x]) {
        return x;
    }
    return root[x] = find(root[x]);
}

public int find(int x){
    while (root[x]!=x){
        root[x]=root[root[x]]
        x=root[x]
    }
    return x
}

public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX != rootY) {
        if (rank[rootX] > rank[rootY]) {
            root[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            root[rootX] = rootY;
        } else {
            root[rootY] = rootX;
            rank[rootX] += 1;
        }
        count--;
    }
}


public boolean connected(int x, int y) {
    return find(x) == find(y);
}

public int connectedComponents() {
	return count;
}


======================================================================


// Qucik find
public int find(int x) {
    return root[x];
}

//union for qucik find
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY) {
        for (int i = 0; i < root.length; i++) {
            if (root[i] == rootY) {
                root[i] = rootX;
            }
        }
    }
} 

// find: O(1), union: O(n), connected: O(1), constructor: O(n) for qucik find approach

//for quick union
public int find(int x) {
    while (x != root[x]) {
        x = root[x];
    }
    return x;
}


//basic for quick union
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY) {
        root[rootY] = rootX;
    }
}

// find: O(n), union: O(n), connected: O(n), constructor: O(n) for qucik union approach



//union by rank and quick union
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY) {
        if (rank[rootX] > rank[rootY]) {
            root[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            root[rootX] = rootY;
        } else {
            root[rootY] = rootX;
            rank[rootX] += 1;
        }
    }
}

// find: O(log n), union: O(log n), connected: O(log n), constructor: O(n) for qucik union and union by rank approach

// path compression and quick union
public int find(int x) {
    if (x == root[x]) {
        return x;
    }
    return root[x] = find(root[x]);
}

// find: O(log n), union: O(log n), connected: O(log n), constructor: O(n) for qucik union and path compression approach



public boolean connected(int x, int y) {
    return find(x) == find(y);
}

