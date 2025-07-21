// Problem Link -> https://www.geeksforgeeks.org/problems/geek-jump/1

// Approach 1 : Using Memoization:
// Time Complexity: O(n) — Each index is visited only once.
// Space Complexity: O(n) — For dp[] + recursion stack.

import java.util.Arrays;
public class Solution {
    int minCost(int[] height) {
        int n = height.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return solve(n,height,dp);
    }
    public int solve(int n,int[] height,int[] dp){
        if(n == 1)
        return 0;
        if(n == 2)
        return Math.abs(height[n-1]-height[n-2]);
        if(dp[n] != -1)
        return dp[n];
        int jumpOne = solve(n-1,height,dp) + Math.abs(height[n-1] - height[n-2]);
        int jumpTwo = solve(n-2,height,dp) + Math.abs(height[n-1] - height[n-3]);
        return dp[n] = Math.min(jumpOne,jumpTwo);
    }
}

// Approach 2: Using Tabulation:

// Time Complexity: O(n) — Single pass.
// Space Complexity: O(n) — For the dp[] array.

class Solution {
    int minCost(int[] height) {
        int n = height.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1;i<n;i++){
            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = dp[i-1] + Math.abs(height[i] - height[i-1]);
            if(i > 1){
                jumpTwo = dp[i-2] + Math.abs(height[i] - height[i-2]);
            }
            dp[i] = Math.min(jumpOne,jumpTwo);
        }
        return dp[n-1];
    }
}

// Approach 3: Using Space Optimization:

// Time Complexity: O(n) — Loop through array once.
// Space Complexity: O(1) — Only 2 variables used (prev and prev2).


class Solution {
    int minCost(int[] height) {
        int n = height.length;
        int prev = 0,prev2 = 0;
        for(int i=1;i<n;i++){
            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = prev + Math.abs(height[i] - height[i-1]);
            if(i > 1){
            jumpTwo = prev2 + Math.abs(height[i] - height[i-2]);
            }
            int cur_i = Math.min(jumpOne,jumpTwo);
            prev2 = prev;
            prev = cur_i;
        }
        return prev;
    }
}