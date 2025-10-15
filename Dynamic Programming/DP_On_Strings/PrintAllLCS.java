// Problem Link: https://www.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1

// Approach: Print all LCS using DP + Backtracking
// We will use DP to find the length of LCS and then we will use backtracking to find all the LCS.

// Code:

class Solution {
    private String s1;
    private String s2;
    public List<String> allLCS(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        Set<String> set = new HashSet<>();
        Map<String, Boolean> visited = new HashMap<>();
        backtrack(m,n, set, "", dp, visited);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }
    public void backtrack(int m, int n, Set<String> set, String str, int[][] dp, Map<String, Boolean> visited){
        if(m == 0 || n ==0){
            set.add(new StringBuilder(str).reverse().toString());
            return;
        }
        String key = m + " " + str + " " + n;
        if(visited.containsKey(key)) return;
        visited.put(key,true);
        if(s1.charAt(m-1) == s2.charAt(n-1)){
            backtrack(m-1, n-1, set, str+s1.charAt(m-1), dp, visited);
            return;
        }
        if(dp[m][n-1] == dp[m-1][n]){
            backtrack(m, n-1, set, str, dp, visited);
            backtrack(m-1, n, set, str, dp, visited);
        } else{
            if(dp[m][n-1] > dp[m-1][n]){
                backtrack(m, n-1, set, str, dp, visited);
            } else{
                backtrack(m-1, n, set, str, dp, visited);
            }
        }
        
        return;
    }
}


// Time Complexity: O(n * m * L) where n and m are lengths of the two strings and L is the length of the LCS.
// Space Complexity: O(n * m + K) where K is the number of unique LCS sequences stored in the set.