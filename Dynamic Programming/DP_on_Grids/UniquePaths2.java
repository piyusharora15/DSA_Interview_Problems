// Problem Link: https://leetcode.com/problems/unique-paths-ii

// Approach 1: Recursion with Memoization.
// We use a 2D array to store the results of subproblems to avoid redundant calculations.We check for obstacles and base cases to ensure valid paths.We recursively explore the two possible directions (down and right) from each cell.We return the total number of unique paths from the start to the destination.

// Code:
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return helper(m-1,n-1,obstacleGrid,dp);
    }
    private int helper(int i,int j,int[][] matrix,int[][] dp){
        if(i < 0 || j < 0 || matrix[i][j] == 1) return 0;
        else if(i == 0 && j == 0) return 1;
        if(dp[i][j] != -1) return dp[i][j];
        int up = helper(i-1,j,matrix,dp);
        int left = helper(i,j-1,matrix,dp);
        return dp[i][j] = up + left;
    }
}

// Time Complexity: O(m*n)
// Space Complexity: O(m*n) for the dp array and O(m+n) for the recursion stack.

// Approach 2: Tabulation (Bottom-Up DP).
// We create a 2D dp array where each cell represents the number of unique paths to that cell.We initialize the starting cell and fill the dp array iteratively, considering obstacles and summing paths from the top and left cells.We return the value in the bottom-right cell as the result.

// Code:
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int n = obstacleGrid.length;
        int[][] dp = new int[n][m];
        return helper(m,n,obstacleGrid,dp);
    }
    private int helper(int m,int n,int[][] matrix,int[][] dp){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }
                int up=0,left=0;
                if(i > 0){
                    up = dp[i-1][j];
                }
                if(j > 0){
                    left = dp[i][j-1];
                }
                dp[i][j] = up + left;
            }
        }
        return dp[n-1][m-1];
    }
}

// Time Complexity: O(m*n)
// Space Complexity: O(m*n) for the dp array.