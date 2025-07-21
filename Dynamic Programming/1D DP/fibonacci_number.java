/*
    Company Tags : Amazon, Microsoft, OYO Rooms, Snapdeal, MakeMyTrip, Goldman Sachs, MAQ Software, Adobe
    Leetcode Link : https://leetcode.com/problems/fibonacci-number/
*/

// Approach 1 -> Using Recursion:
/*
Time Complexity: O(2ⁿ) ; because it solves the same subproblems again and again — exponential time.
Space Complexity: O(n) ; due to recursion call stack depth.
*/  class Solution{
    public int fib(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        if(n <= 1)
        return n;
        return dp[n] = fib(n-1) + fib(n-2);
    }
}
// Approach 2 -> Using Memoization:
/* 
Time Complexity: O(n) ; each subproblem is solved only once and stored.
Space Complexity: O(n) ; for recursion stack + dp[] array. 
*/
    class Solution{
    public int fib(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        if(n >= 1){
        dp[1] = 1;
        }
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    }
// Approach 3 -> Using Tabulation:
/*
Time Complexity: O(n) ; loop runs once from 2 to n.
Space Complexity: O(n) ; for the dp[] array.
*/
    class Solution{
    public int fib(int n) {
        int prev2 = 0;
        int prev = 1;
        if(n == 0 || n == 1){
        return n;
        }
        for(int i=2;i<=n;i++){
            int cur_i = prev2 + prev;
            prev2 = prev;
            prev = cur_i;
        }
        return prev;
    }
    }
// Approach 4 -> Using Space Optimization:
/*
Time Complexity: O(n) ; we are simply running an iterative loop.
Space Complexity: O(1) ; as we are not using any extra space.
*/
    class Solution{
    public int fib(int n) {
        int prev2 = 0;
        int prev = 1;
        if(n == 0 || n == 1){
        return n;
        }
        for(int i=2;i<=n;i++){
            int cur_i = prev2 + prev;
            prev2 = prev;
            prev = cur_i;
        }
        return prev;
    }
    }