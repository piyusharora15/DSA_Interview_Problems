// Problem Link: https://leetcode.com/problems/interleaving-string?envType=problem-list-v2&envId=dynamic-programming

// Approach 1: Recursion + Memoization.
// We will use a 2D array to store the results of subproblems to avoid redundant calculations. We will define a recursive function that checks if the current characters of s1 and s2 can form the current character of s3. If they can, we will recursively check the next characters in s1, s2, and s3. The base case will be when we have used all characters from s1 and s2 to form s3. We will return true if we can form s3 from s1 and s2, otherwise false.

// Code:
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        
        if (n + m != l) {
            return false;
        }
        
        Boolean[][] dp = new Boolean[n + 1][m + 1];
        return helper(s1, s2, s3, 0, 0, 0, dp);
    }
    
    private boolean helper(String s1, String s2, String s3, int i, int j, int k, Boolean[][] dp) {
        if (i == s1.length() && j == s2.length() && k == s3.length()) {
            return true;
        }
        
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        
        boolean takeFromS1 = false;
        boolean takeFromS2 = false;
        
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            takeFromS1 = helper(s1, s2, s3, i + 1, j, k + 1, dp);
        }
        
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            takeFromS2 = helper(s1, s2, s3, i, j + 1, k + 1, dp);
        }
        
        dp[i][j] = takeFromS1 || takeFromS2;
        return dp[i][j];
    }
}

// Time Complexity: O(n * m), where n and m are the lengths of s1 and s2 respectively.
// Space Complexity: O(n * m) for the memoization table and O(n + m) for the recursion stack.

// Approach 2: Bottom-Up Dynamic Programming(Tabulation).
// We will create a 2D boolean array dp where dp[i][j] will be true if the first i characters of s1 and the first j characters of s2 can form the first i + j characters of s3. We will initialize dp[0][0] to true since two empty strings can form an empty string. We will then fill the dp table based on whether the current character of s3 matches with the current character of s1 or s2. We will return dp[n][m] as the final result.

// Code:
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        
        if (n + m != l) {
            return false;
        }
        
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }
        
        return dp[n][m];
    }
}

// Time Complexity: O(n * m), where n and m are the lengths of s1 and s2 respectively.
// Space Complexity: O(n * m) for the dp table.

// Approach 3: Space Optimized Dynamic Programming.
// We can optimize the space complexity of the previous approach by using a 1D array instead of a 2D array. We will use a single array dp of size m + 1, where dp[j] will represent whether the first i characters of s1 and the first j characters of s2 can form the first i + j characters of s3. We will update this array as we iterate through the characters of s1 and s2. We will return dp[m] as the final result.

// Code:
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        
        if (n + m != l) {
            return false;
        }
        
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        
        for (int j = 1; j <= m; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        
        for (int i = 1; i <= n; i++) {
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                         (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        
        return dp[m];
    }
}

// Time Complexity: O(n * m), where n and m are the lengths of s1 and s2 respectively.
// Space Complexity: O(m) for the dp array.