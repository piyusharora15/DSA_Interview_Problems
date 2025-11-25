// Problem Link: https://leetcode.com/problems/spiral-matrix

// Approach: Simulate the spiral traversal by maintaining boundaries for rows and columns.Traverse the matrix in a spiral order by adjusting the boundaries after each complete traversal of a side.

// Code:
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
         List<Integer> ans = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0, left = 0, bottom = n-1, right = m-1;
        while(top<=bottom && left<=right)
        {
            for(int i=left;i<=right;i++)
            ans.add(matrix[top][i]);
            top++;
            for(int i=top;i<=bottom;i++)
            ans.add(matrix[i][right]);
            right--;
            if(top<=bottom)
            {
                for(int i=right;i>=left;i--)
                ans.add(matrix[bottom][i]);
                bottom--;
            }
            if(left<=right)
            {
                for(int i=bottom;i>=top;i--)
                ans.add(matrix[i][left]);
                left++;
            }
        }
        return ans;
    }
}

// TC: O(N*M)
// SC: O(1)