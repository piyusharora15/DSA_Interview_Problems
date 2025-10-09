// Problem Link: https://leetcode.com/problems/palindromic-substrings?envType=problem-list-v2&envId=dynamic-programming

// Brute Force Approach: Check all substrings and check if they are palindromic.
// We can use two nested loops to generate all substrings and a helper function to check if a substring is palindromic.
// Time Complexity: O(n^3)
// Space Complexity: O(1)

// Code:
class Solution {
    public int countSubstrings(String s) {
       int n = s.length();
       int count = 0;
       for(int i=0;i<n;i++){
        for(int j=i;j<n;j++){
            if(check(s,i,j)){
                count++;
            }
        }
       } 
       return count;
    }
    public boolean check(String s,int i,int j){
        if(i >= j){
            return true;
        }
        if(s.charAt(i) == s.charAt(j)){
            return check(s,i+1,j-1);
        }
        return false;
    }
}

// Approach 2: Memoization.
// We can use a 2D array to store the results of previously computed substrings.
// We can use a helper function to check if a substring is palindromic and store the result in the 2D array.
// Time Complexity: O(n^2)
// Space Complexity: O(n^2)

// Code:
class Solution {
    public int countSubstrings(String s) {
       int n = s.length();
       int count = 0;
       int[][] dp = new int[n][n];
       for(int i=0;i<n;i++){
        for(int j=i;j<n;j++){
            if(check(s,i,j,dp)){
                count++;
            }
        }
       } 
       return count;
    }
    public boolean check(String s,int i,int j,int[][] dp){
        if(i >= j){
            return true;
        }
        if(dp[i][j] != 0){
            return dp[i][j] == 1;
        }
        if(s.charAt(i) == s.charAt(j)){
            dp[i][j] = check(s,i+1,j-1,dp) ? 1 : -1;
            return dp[i][j] == 1;
        }
        dp[i][j] = -1;
        return false;
    }
}

// Approach 3: Bottom-Up Dynamic Programming.
// We can use a 2D array to store the results of previously computed substrings.
// We can fill the 2D array in a bottom-up manner.
// We can use two nested loops to generate all substrings and fill the 2D array.
// Time Complexity: O(n^2)
// Space Complexity: O(n^2)

// Code:
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int L = 1; L <= n; L++) {
            for (int i = 0; i + L <= n; i++) {
                int j = i + L - 1;
                if (i == j) {
                    dp[i][i] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}

// Optimal Approach: Expand Around Center.
// We can use two nested loops to generate all possible centers of palindromic substrings.
// We can expand around each center to count all palindromic substrings.
// Time Complexity: O(n^2)
// Space Complexity: O(1)

// Code:
class Solution {
    private int count = 0;
    public int countSubstrings(String s) {
        int n = s.length();
        count = 0;
        for(int i=0;i<n;i++){
            check(s,i,i,n);
            check(s,i,i+1,n);
        }
        return count;
    }
    private void check(String s,int i,int j,int n){
        while(i >= 0 && j < n && s.charAt(i) == s.charAt(j)){
            count++;
            i--;
            j++;
        }
    }
}