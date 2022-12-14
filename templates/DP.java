To solve a DP problem, we need to combine 3 things:
1. "A function or data structure that will compute/contain the answer to the problem for every given state."
2. "A recurrence relation to transition between states."
3. "Base cases, so that our recurrence relation doesn't go on infinitely"

class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        
        // An array that represents the answer to the problem for a given state
        int[] dp = new int[n + 1]; 
        dp[1] = 1; // Base cases
        dp[2] = 2; // Base cases
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // Recurrence relation
        }
        
        return dp[n];
    }
}