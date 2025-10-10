// Problem Link: https://leetcode.com/problems/longest-palindromic-substring?envType=problem-list-v2&envId=dynamic-programming

// Naive Approach: Recursion + Memoization.
// Generate all substrings and check if they are palindrome or not.
// If they are palindrome, then check for the maximum length of the substring.
// Memoize the overlapping subproblems to avoid recomputation.
// Time Complexity: O(n^3) (n^2 for generating all substrings and
// O(n) for checking if the substring is palindrome or not.)
// Space Complexity: O(n^2) (for memoization table)

// Code:
class Solution {
    private int[][] t;
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        t = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(t[i],-1);
        }
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(solve(s,i,j) && j-i+1 > maxLen){
                    start = i;
                    maxLen = j-i+1;
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
    private boolean solve(String s,int l,int r){
        if(l >= r){
            return true;
        }
        if(t[l][r] != -1){
            return t[l][r] == 1;
        }
        if(s.charAt(l) == s.charAt(r)){
            t[l][r] = solve(s,l+1,r-1) ? 1 : 0;
        }
        else{
            t[l][r] = 0;
        }
        return t[l][r] == 1;
    }
}

// Approach 2: Bottom Up Approach(Tabulation).
// Create a 2D table to store the results of subproblems.
// Fill the table in a bottom-up manner.
// Iterate all possible lengths of substrings.
// For each length, iterate all possible starting indices.
// Check if the substring is palindrome or not.
// If it is palindrome, then check for the maximum length of the substring.
// Time Complexity: O(n^2)
// Space Complexity: O(n^2) (for the table)

// Code:

class Solution {
    static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;
        for(int i=0;i<n;i++)
        dp[i][i] = true;
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                if(maxLen < 2){
                    start = i;
                    maxLen = 2;
                }
            }
        }
        for(int k=3;k<=n;k++){
            for(int i=0;i<n-k+1;i++){
                int j = i+k-1;
                if(dp[i+1][j-1] && s.charAt(i) == s.charAt(j)){
                    dp[i][j] = true;
                    if(k > maxLen){
                        start = i;
                        maxLen = k;
                    }
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
}

// Optimal Approach: Expand Around Center.
// A palindrome mirrors around its center.
// We can expand around the center and check for the maximum length of the palindrome.
// We can have 2n-1 centers (n odd length palindromes and n-1 even length palindromes).
// For each center, expand around the center and check for the maximum length of the palindrome.
// Time Complexity: O(n^2)
// Space Complexity: O(1)

// Code:
class Solution {
    static String longestPalindrome(String s) {
        int n = s.length();
        if(n == 0) return "";
        int start = 0, end = 0;
        for(int i=0;i<n;i++){
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start,end+1);
    }
    private static int expandAroundCenter(String s,int left,int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }
}