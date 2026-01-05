// Problem Link: https://leetcode.com/problems/minimum-falling-path-sum?envType=problem-list-v2&envId=wh8gjvyh

// Approach 1: Recursion + Memoization.
// We can use a 2D dp array to store the minimum falling path sum for each cell.
// The base case will be the first row, where the minimum falling path sum is the value of the cell itself.For other cells, we can calculate the minimum falling path sum by considering the three possible cells from the previous row (directly above, above left, and above right) and adding the current cell's value to the minimum of those three values.We will return the minimum falling path sum from the last row.

// Code:
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        int minSum = Integer.MAX_VALUE;
        for (int col = 0; col < m; col++) {
            minSum = Math.min(minSum, helper(0, col, matrix, dp));
        }
        return minSum;
    }

    private int helper(int row, int col, int[][] matrix, int[][] dp) {
        if (col < 0 || col == matrix[0].length) {
            return Integer.MAX_VALUE;
        }
        if (row == matrix.length - 1) {
            return matrix[row][col];
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int down = helper(row + 1, col, matrix, dp);
        int downLeft = helper(row + 1, col - 1, matrix, dp);
        int downRight = helper(row + 1, col + 1, matrix, dp);
        int ans = matrix[row][col] + Math.min(down, Math.min(downLeft, downRight));
        dp[row][col] = ans;
        return ans;
    }
}

// Time Complexity: O(N*M)
// Space Complexity: O(N*M) + O(N) for recursion stack

// Approach 2: Tabulation.
// We can use a 2D dp array to store the minimum falling path sum for each cell.The base case will be the first row, where the minimum falling path sum is the value of the cell itself.For other cells, we can calculate the minimum falling path sum by considering the three possible cells from the previous row (directly above, above left, and above right) and adding the current cell's value to the minimum of those three values.We will return the minimum falling path sum from the last row.

// Code:
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int col = 0; col < m; col++) {
            dp[0][col] = matrix[0][col];
        }
        for (int row = 1; row < n; row++) {
            for (int col = 0; col < m; col++) {
                int down = dp[row - 1][col];
                int downLeft = (col > 0) ? dp[row - 1][col - 1] : Integer.MAX_VALUE;
                int downRight = (col < m - 1) ? dp[row - 1][col + 1] : Integer.MAX_VALUE;
                dp[row][col] = matrix[row][col] + Math.min(down, Math.min(downLeft, downRight));
            }
        }
        int minSum = Integer.MAX_VALUE;
        for (int col = 0; col < m; col++) {
            minSum = Math.min(minSum, dp[n - 1][col]);
        }
        return minSum;
    }
}

// Time Complexity: O(N*M)
// Space Complexity: O(N*M)

// Approach 3: Space Optimization.
// We can optimize the space complexity by using a single array to store the minimum falling path sum for the previous row instead of a 2D dp array.We will update this array for each row and finally return the minimum falling path sum from the last row.

// Code:
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] prev = new int[m];
        for (int col = 0; col < m; col++) {
            prev[col] = matrix[0][col];
        }
        for (int row = 1; row < n; row++) {
            int[] curr = new int[m];
            for (int col = 0; col < m; col++) {
                int down = prev[col];
                int downLeft = (col > 0) ? prev[col - 1] : Integer.MAX_VALUE;
                int downRight = (col < m - 1) ? prev[col + 1] : Integer.MAX_VALUE;
                curr[col] = matrix[row][col] + Math.min(down, Math.min(downLeft, downRight));
            }
            prev = curr;
        }
        int minSum = Integer.MAX_VALUE;
        for (int col = 0; col < m; col++) {
            minSum = Math.min(minSum, prev[col]);
        }
        return minSum;
    }
}

// Time Complexity: O(N*M)
// Space Complexity: O(M)