// Problem Link: https://leetcode.com/problems/longest-palindromic-subsequence?envType=problem-list-v2&envId=dynamic-programming

// Brute Force Approach: Using Recursion.
// The idea is to check for every character from the start and end of the string.
// If they are equal, we add 2 to the result and move both pointers towards the center.
// If they are not equal, we move either the start pointer or the end pointer and take the maximum result.
// Time Complexity: O(2^n) where n is the length of the string.
// Space Complexity: O(n) for the recursion stack.

// Code:
class Solution {
    public int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int left, int right) {
        // Base case: if the pointers cross each other
        if (left > right) {
            return 0;
        }
        // Base case: if the pointers are at the same character
        if (left == right) {
            return 1;
        }
        // If the characters at both pointers are equal
        if (s.charAt(left) == s.charAt(right)) {
            return 2 + helper(s, left + 1, right - 1);
        } else {
            // If the characters are not equal, move either pointer
            return Math.max(helper(s, left + 1, right), helper(s, left, right - 1));
        }
    }
}

// Approach 2: Using Dynamic Programming (Memoization).
// The idea is to use a 2D array to store the results of subproblems.
// Time Complexity: O(n^2) where n is the length of the string.
// Space Complexity: O(n^2) for the DP array and O(n) for the recursion stack.

// Code:
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return lps(s, 0, n - 1, memo);
    }

    public int lps(String s, int low, int high, int[][] memo) {
        if (low > high)
            return 0;
        if (low == high)
            return 1;
        if (memo[low][high] != -1)
            return memo[low][high];
        if (s.charAt(low) == s.charAt(high))
            return memo[low][high] = 2 + lps(s, low + 1, high - 1, memo);
        return memo[low][high] = Math.max(lps(s, low, high - 1, memo), lps(s, low + 1, high, memo));
    }
}

// Approach 3: Using Dynamic Programming (Tabulation).
// The idea is to fill a 2D array in a bottom-up manner.
// Time Complexity: O(n^2) where n is the length of the string.
// Space Complexity: O(n^2) for the DP array.

// Code:
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=n-1;i>=0;i--){
            for(int j=i;j<n;j++){
                if(i == j){
                    dp[i][j] = 1;
                    continue;
                }
                if(s.charAt(i) == s.charAt(j)){
                    if(i+1 == j) dp[i][j] = 2;
                    else dp[i][j] = dp[i+1][j-1] + 2;
                }
                else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}