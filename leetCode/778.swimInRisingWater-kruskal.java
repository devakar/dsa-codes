class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int len=n*n;
        
        DisjointSet ds = new DisjointSet(len);
        
        int[][] neighbours = {{1,-1,0,0},{0,0,1,-1}};
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.v-b.v);
        
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                pq.offer(new Node(i,j,grid[i][j]));
            }
        }
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int i=cur.x;
            int j=cur.y;
            int time=cur.v;
            
            for(int k=0;k<4;k++){
                if(isValid(cur, neighbours, k, grid)) {
                    ds.union(time, grid[i+neighbours[0][k]][j+neighbours[1][k]]);
                }
            }
            
            
            if(ds.find(grid[0][0])==ds.find(grid[n-1][n-1])) {
                return time;
            }
        }
        
        return len-1;
    }
    
    boolean isValid(Node cur, int[][] neighbours, int k, int[][] grid) {
        int i=cur.x;
        int j=cur.y;
        int time=cur.v;
        int n=grid.length;
        if(i+neighbours[0][k] >= 0
          && i+neighbours[0][k] <n
          && j+neighbours[1][k] >=0
          && j+neighbours[1][k] <n
          && grid[i+neighbours[0][k]][j+neighbours[1][k]] < time)
            return true;
        
        return false;
    }
}
class Node {
    int x;
    int y;
    int v;
    
    public Node(int i, int j, int k) {
        x=i;
        y=j;
        v=k;
    }
}
class DisjointSet{
    int n;
    int[] root;
    int[] rank;
    
    public DisjointSet(int len){
        this.n=len;
        root= new int[n];
        rank=new int[n];
        
        for(int i=0;i<n;i++) {
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
        
        if(rootX!=rootY) {
            if(rank[rootX]>rank[rootY]) {
                root[rootY] = rootX;
            } else if(rank[rootX]<rank[rootY]) {
                root[rootX] = rootY;
            } else{
                root[rootY]=rootX;
                rank[rootX]++;
            }
        }
    }
}