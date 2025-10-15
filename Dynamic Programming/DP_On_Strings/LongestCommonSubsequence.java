// Problem Link: https://leetcode.com/problems/longest-common-subsequence?envType=problem-list-v2&envId=dynamic-programming

// Brute Force Approach (Recursion):
// We can solve this problem using recursion by checking all possible subsequences of both strings and finding the longest common subsequence among them. The time complexity of this approach is O(2^(m+n)), where m and n are the lengths of the two strings.

// Code:
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        return lcs(text1, text2, m, n);
    }

    public int lcs(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return 1 + lcs(s1, s2, m - 1, n - 1);
        else
            return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
    }
}

// Approach 2: Memoization.
// We can optimize the brute force approach using memoization to store the results of subproblems and avoid redundant calculations. The time complexity of this approach is O(m*n), where m and n are the lengths of the two strings.

// Code:
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            Arrays.fill(memo[i],-1);
        }
        return lcs(text1,text2,m,n,memo);
    }
    public int lcs(String s1,String s2,int m,int n,int[][] memo){
        if(m == 0 || n == 0) return 0;
        if(memo[m][n] != -1) return memo[m][n];
        if(s1.charAt(m-1) == s2.charAt(n-1)){
            return memo[m][n] = 1 + lcs(s1,s2,m-1,n-1,memo);
        }
        return memo[m][n] = Math.max(lcs(s1,s2,m,n-1,memo),lcs(s1,s2,m-1,n,memo));
    }
}

// Approach 3: Tabulation (Bottom-Up DP).
// We can also solve this problem using a bottom-up dynamic programming approach. We create a 2D array to store the lengths of the longest common subsequences of all prefixes of the two strings. The time complexity of this approach is O(m*n), where m and n are the lengths of the two strings.

// Code:
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}