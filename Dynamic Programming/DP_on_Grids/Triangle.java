// Problem Link: https://leetcode.com/problems/triangle?envType=problem-list-v2&envId=wh8gjvyh

// Approach 1: Recursion + Memoization.
// We can use recursion to explore all possible paths from the top to the bottom of the triangle.To avoid redundant calculations, we can use memoization to store the results of subproblems.We create a 2D array to store the minimum path sum from each position in the triangle to the bottom. The base case is when we reach the last row of the triangle, where the minimum path sum is simply the value at that position. For other positions, we calculate the minimum path sum by adding the current position's value to the minimum of the path sums from the two positions directly below it.We store the result in the memoization array before returning it.

// Code:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return helper(triangle,0,0,dp);
    }
    private int helper(List<List<Integer>> triangle,int i,int j,int[][] dp){
        if(i == triangle.size()-1){
            return triangle.get(i).get(j);
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int left = helper(triangle,i+1,j,dp);
        int right = helper(triangle,i+1,j+1,dp);
        return dp[i][j] = triangle.get(i).get(j) + Math.min(left,right);
    }
}

// Time Complexity: O(N^2), where N is the number of rows in the triangle. Each position in the triangle is computed only once due to memoization.
// Space Complexity: O(N^2) for the memoization array and O(N) for the recursion stack, leading to a total space complexity of O(N^2).

// Approach 2: Bottom-Up Dynamic Programming.
// We can use a bottom-up dynamic programming approach to solve the problem iteratively. We start from the second-to-last row of the triangle and move upwards to the top. For each position in the triangle, we update its value to be the sum of its current value and the minimum of the two values directly below it. By the time we reach the top of the triangle, the top element will contain the minimum path sum from the top to the bottom.

// Code:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        for(int i = n-2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                int down = triangle.get(i+1).get(j);
                int diagonal = triangle.get(i+1).get(j+1);
                triangle.get(i).set(j,triangle.get(i).get(j) + Math.min(down,diagonal));
            }
        }
        return triangle.get(0).get(0);
    }
}

// Time Complexity: O(N^2), where N is the number of rows in the triangle. We iterate through each position in the triangle once.
// Space Complexity: O(1), as we are modifying the triangle in place and not using any additional data structures.

// Approach 3: Space Optimization.
// We can optimize the space complexity of the bottom-up dynamic programming approach by using a 1D array instead of modifying the triangle in place. We maintain a 1D array that represents the minimum path sums for the current row. As we iterate through the triangle from the bottom to the top, we update this array to reflect the minimum path sums for each position.We start by initializing the 1D array with the values from the last row of the triangle. Then, for each row above, we update the array by calculating the minimum path sums using the values from the current row and the values in the array representing the row below.

// Code:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for(int j = 0; j < n; j++){
            dp[j] = triangle.get(n-1).get(j);
        }
        for(int i = n-2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                int down = dp[j];
                int diagonal = dp[j+1];
                dp[j] = triangle.get(i).get(j) + Math.min(down,diagonal);
            }
        }
        return dp[0];
    }
}

// Time Complexity: O(N^2), where N is the number of rows in the triangle. We iterate through each position in the triangle once.
// Space Complexity: O(N), where N is the number of rows in the triangle. We use a 1D array to store the minimum path sums for the current row.