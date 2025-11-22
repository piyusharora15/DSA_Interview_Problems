// Problem Link: https://leetcode.com/problems/rotate-image

// Approach: First, we transpose the matrix by swapping elements across the diagonal.
// Then, we reverse each row to achieve the 90-degree clockwise rotation.

// Time Complexity: O(n^2), where n is the number of rows (or columns) in the matrix.
// Space Complexity: O(1), as the rotation is done in place without using extra space

// Code:
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
       for(int i=0;i<n-1;i++)
       {
        for(int j=i+1;j<n;j++)
        {
            int temp = 0;
            temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
       }
       for(int i=0;i<n;i++)
       {
        for(int j=0;j<n/2;j++)
        {
            int temp = 0;
            temp = matrix[i][j];
            matrix[i][j] = matrix[i][n-1-j];
            matrix[i][n-1-j] = temp;
        }
       }
    }
}