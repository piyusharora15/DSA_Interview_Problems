// Problem Link: https://leetcode.com/problems/scramble-string?envType=problem-list-v2&envId=dynamic-programming

// Approach 1: Recursion with Memoization.
// We use a 3D array to store results of subproblems to avoid redundant calculations.We check all possible partitions of the strings and see if they can form a scramble.We consider both cases: without swapping and with swapping.We store the results in the memoization array to optimize future calls.We return the result based on the memoization array.We also check if the character counts match before proceeding with further checks.We use a helper function to count characters in substrings.

// Code:
class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        // 3D memoization array
        Boolean[][][] memo = new Boolean[n][n][n + 1];
        return isScrambleHelper(s1, s2, 0, 0, n, memo);
    }

    private boolean isScrambleHelper(String s1, String s2, int i1, int i2, int length, Boolean[][][] memo) {
        // Check if result is already computed
        if (memo[i1][i2][length] != null) {
            return memo[i1][i2][length];
        }

        // Base case: if substrings are equal
        if (s1.substring(i1, i1 + length).equals(s2.substring(i2, i2 + length))) {
            memo[i1][i2][length] = true;
            return true;
        }

        // Check character counts
        if (!haveSameCharacterCounts(s1, s2, i1, i2, length)) {
            memo[i1][i2][length] = false;
            return false;
        }

        // Try all possible partitions
        for (int split = 1; split < length; split++) {
            // Case 1: No swap
            if (isScrambleHelper(s1, s2, i1, i2, split, memo) &&
                isScrambleHelper(s1, s2, i1 + split, i2 + split, length - split, memo)) {
                memo[i1][i2][length] = true;
                return true;
            }
            // Case 2: With swap
            if (isScrambleHelper(s1, s2, i1, i2 + length - split, split, memo) &&
                isScrambleHelper(s1, s2, i1 + split, i2, length - split, memo)) {
                memo[i1][i2][length] = true;
                return true;
            }
        }

        memo[i1][i2][length] = false;
        return false;
    }

    private boolean haveSameCharacterCounts(String s1, String s2, int i1, int i2, int length) {
        int[] count = new int[26];
        for (int i = 0; i < length; i++) {
            count[s1.charAt(i1 + i) - 'a']++;
            count[s2.charAt(i2 + i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }}
// Time Complexity: O(n^4) due to the three nested loops and substring comparisons.
// Space Complexity: O(n^3) for the memoization array.

// Approach 2: Bottom-Up Dynamic Programming.
// We use a 3D DP array to store results of subproblems iteratively.We initialize the DP array for substrings of length 1.We fill the DP table for increasing lengths of substrings, checking all possible partitions and both cases (with and without swapping).We return the result from the DP table for the full strings.

// Code:
class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if(n != s2.length()) return false;
        boolean[][][] dp = new boolean[n+1][n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[1][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for(int len=2;len<=n;len++){
            for(int i=0;i+len<=n;i++){
                for(int j=0;j+len<=n;j++){
                    for(int k=1;k<len;k++){
                        if(dp[k][i][j] && dp[len-k][i+k][j+k]){
                            dp[len][i][j] = true;
                            break;
                        }
                        if(dp[k][i][j+len-k] && dp[len-k][i+k][j]){
                            dp[len][i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[n][0][0];
    }
}

// Time Complexity: O(n^4) due to the three nested loops and substring comparisons.
// Space Complexity: O(n^3) for the DP array.