class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.d-b.d);
        
        pq.offer(new Node(0,0,grid[0][0]));
        
        int[] nr = new int[] {1,-1,0,0};
        int[] nc = new int[] {0,0,-1,1};
        
        boolean[][] visited = new boolean[n][n];
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curD = cur.d;
            
            visited[curX][curY]=true;
            
            if(curX==n-1 && curY==n-1){
                return Math.max(curD, grid[curX][curY]);
            }
            
            for(int i=0;i<4;i++) {
                if(isValid(cur, i, nr, nc, n, visited)) {
                    pq.offer(new Node(
                        cur.x+nr[i], 
                        cur.y+nc[i], 
                        Math.max(curD, grid[cur.x+nr[i]][cur.y+nc[i]])));
                    
                
                }
            }
        }
        
        return -1;
    }
    
    boolean isValid(Node cur, int index, int[] nr, int[] nc, int n, boolean[][] visited) {
        if(cur.x+nr[index]>=0 
           && cur.x+nr[index]<n 
           && cur.y+nc[index]>=0 
           && cur.y+nc[index]<n 
           && !visited[cur.x+nr[index]][cur.y+nc[index]]) {
            return true;
        }
        
        return false;
    }
}

class Node{
    int x;
    int y;
    int d;
    
    public Node(int i, int j, int k) {
        x=i;
        y=j;
        d=k;
    }
}