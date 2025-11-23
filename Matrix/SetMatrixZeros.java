// Problem Link: https://leetcode.com/problems/set-matrix-zeroes

// Approach: Using first row and first column as markers to indicate which rows and columns should be set to zero.Then, iterating through the matrix to set the appropriate rows and columns to zero based on these markers.

// Code:
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false, firstCol = false;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 0) {
                    if (i == 0)
                        firstRow = true;
                    if (j == 0)
                        firstCol = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
        for (int i = 1; i < matrix.length; i++)
            for (int j = 1; j < matrix[0].length; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
        if (firstRow)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        if (firstCol)
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
    }
}

// TC: O(m*n)
// SC: O(1)