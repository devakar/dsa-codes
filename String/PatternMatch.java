class Solution {
    public boolean isMatch(String s, String p) {
        int n1=s.length();
        int n2=p.length();
        if(n1 ==0 &&n2 ==0)
            return true;
        if(n2==0)
            return false;
        
        int i=0;
        int j=0;
        int x=0;
        int y=-1;
        
        while(i<n1) {
            System.out.println("i : "+i+" j : "+j);
            if(j<n2 && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')) {
                i++;
                j++;
            }
            
            else if(j<n2 && p.charAt(j)=='*') {
                x=i;
                y=j;
                j++;
            }
            
            else if(y>=0) {
                x=x+1;
                i=x;
                j=y;
            }
            
            else {
                return false;
            }
        }
        
        if(i!=n1)
            return false;
        
        while(j<n2 && p.charAt(j)=='*')
            j++;
        
        return j==n2;
        
    }
}