// Problem Link: https://leetcode.com/problems/minimum-path-sum?envType=problem-list-v2&envId=wh8gjvyh

// Approach 1: Recursion + Memoization.
// We can use a 2D array to store the minimum path sum from each cell to the bottom-right corner. We start from the top-left corner and recursively calculate the minimum path sum for each cell by considering the right and down moves. We store the results in the 2D array to avoid redundant calculations. We return the value stored in the top-left cell as the final result.

// Code:
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return helper(n-1,m-1,grid,dp);
    }
    public int helper(int i,int j,int[][] grid,int[][] dp){
        if(i == 0 && j == 0) return grid[0][0];
        if(i < 0 || j < 0) return (int) 1e9;
        if(dp[i][j] != -1) return dp[i][j];
        int up = grid[i][j] + helper(i-1,j,grid,dp);
        int left = grid[i][j] + helper(i,j-1,grid,dp);
        return dp[i][j] = Math.min(up,left);
    }
}

// Time Complexity: O(n*m).
// Space Complexity: O(n*m) + O(n+m) (for recursion stack).

// Approach 2: Tabulation.
// We can use a 2D array to store the minimum path sum from the top-left corner to each cell. We iterate through the grid and for each cell, we calculate the minimum path sum by considering the values from the top and left cells. We fill the 2D array iteratively and return the value stored in the bottom-right cell as the final result.

// Code:
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                }
                else{
                    int up = grid[i][j];
                    if(i > 0) up += dp[i-1][j];
                    else up += (int) 1e9;
                    int left = grid[i][j];
                    if(j > 0) left += dp[i][j-1];
                    else left += (int) 1e9;
                    dp[i][j] = Math.min(up,left);
                }
            }
        }
        return dp[n-1][m-1];
    }
}

// Time Complexity: O(n*m).
// Space Complexity: O(n*m).

// Approach 3: Space Optimization.
// We can optimize the space complexity by using a single array to store the minimum path sum for the current row. We iterate through the grid and for each cell, we calculate the minimum path sum by considering the values from the top and left cells. We update the single array iteratively and return the value stored in the last cell of the array as the final result.

// Code:
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] prev = new int[m];
        for(int i=0;i<n;i++){
            int[] temp = new int[m];
            for(int j=0;j<m;j++){
                if(i == 0 && j == 0){
                    temp[j] = grid[i][j];
                }
                else{
                    int up = grid[i][j];
                    if(i > 0){
                        up += prev[j];
                    }
                    else{
                        up += (int) 1e9;
                    }
                    int left = grid[i][j];
                    if(j > 0){
                        left += temp[j-1];
                    }
                    else{
                        left +=(int) 1e9;
                    }
                    temp[j] = Math.min(up,left);
                }
            }
            prev = temp;
        }
        return prev[m-1];
    }
}

// Time Complexity: O(n*m).
// Space Complexity: O(m).