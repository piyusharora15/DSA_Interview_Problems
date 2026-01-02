// Problem Link: https://leetcode.com/problems/unique-paths

// Approach 1: Recursion with Memoization.
// We can use a 2D array to store the results of subproblems to avoid redundant calculations.We define a recursive function that calculates the number of unique paths to reach a cell (i, j) from the top-left corner (0, 0). The base case is when we reach the first row or first column, where there is only one way to reach any cell in those rows/columns. For other cells, the number of unique paths to reach (i, j) is the sum of the unique paths to reach the cell directly above it (i-1, j) and the cell directly to the left of it (i, j-1).

// Code:
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return helper(m-1,n-1,dp);
    }
    private int helper(int i,int j,int[][] dp){
        if(i == 0 && j == 0) return 1;
        if(i < 0 || j < 0) return 0;
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int up = helper(i-1,j,dp);
        int left = helper(i,j-1,dp);
        return dp[i][j] = up + left;
    }
}

// Time Complexity: O(m*n)
// Space Complexity: O(m*n) for the dp array + O(m+n) for the recursion stack.

// Approach 2: Dynamic Programming (Bottom-Up).
// We can use a 2D array to iteratively compute the number of unique paths to each cell in the grid. We initialize the first row and first column to 1, as there is only one way to reach any cell in those rows/columns. For other cells, we fill the dp array by summing the values from the cell directly above and the cell directly to the left.

// Code:
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return helper(m,n,dp);
    }
    private int helper(int m,int n,int[][] dp){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if(i > 0){
                    up = dp[i-1][j];
                }
                if(j > 0){
                    left = dp[i][j-1];
                }
                dp[i][j] = up + left;
            }
        }
        return dp[m-1][n-1];
    }
}

// Time Complexity: O(m*n)
// Space Complexity: O(m*n) for the dp array.