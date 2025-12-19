// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv?envType=problem-list-v2&envId=dynamic-programming

// Approach 1: Recursion + Memoization
// We can use recursion with memoization to solve this problem. The idea is to keep track of the current day, the number of transactions left, and whether we are holding a stock or not. We can then make decisions based on these states. We will use a 3D array to store the results of subproblems to avoid redundant calculations. We will explore two main choices at each step: buying/selling a stock or skipping the day. We will return the maximum profit we can achieve.

// Code:
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        Integer[][][] dp = new Integer[n][2][k+1];
        return getAns(prices,n,0,0,k,dp);
    }
    private int getAns(int[] arr,int n,int ind,int buy,int cap,Integer[][][] dp){
        if(ind == n || cap == 0){
            return 0;
        }
        if(dp[ind][buy][cap] != null){
            return dp[ind][buy][cap];
        }
        int doNothing = getAns(arr,n,ind+1,buy,cap,dp);
        int doSomething;
        if(buy == 0){
            doSomething = -arr[ind] + getAns(arr,n,ind+1,1,cap,dp);
        }
        else{
            doSomething = arr[ind] + getAns(arr,n,ind+1,0,cap-1,dp);
        }
        dp[ind][buy][cap] = Math.max(doNothing,doSomething);
        return dp[ind][buy][cap];
    }
}

// Time Complexity: O(n * 2 * k)
// Space Complexity: O(n * 2 * k) + O(n) for recursion stack

// Approach 2: Tabulation.
// We can also solve this problem using tabulation. We will create a 3D DP array where dp[i][j][l] represents the maximum profit we can achieve starting from day i, with j indicating whether we are holding a stock (1) or not (0), and l representing the number of transactions left. We will fill this DP table iteratively, considering the same choices as in the recursive approach: buying/selling a stock or skipping the day. Finally, we will return the value at dp[0][0][k], which represents the maximum profit starting from day 0 with no stock held and k transactions left.

// Code:
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][k+1];
        
        for(int ind = n-1; ind >= 0; ind--){
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <= k; cap++){
                    int doNothing = dp[ind+1][buy][cap];
                    int doSomething;
                    if(buy == 0){
                        doSomething = -prices[ind] + dp[ind+1][1][cap];
                    }
                    else{
                        doSomething = prices[ind] + dp[ind+1][0][cap-1];
                    }
                    dp[ind][buy][cap] = Math.max(doNothing,doSomething);
                }
            }
        }
        return dp[0][0][k];
    }
}

// Time Complexity: O(n * 2 * k)
// Space Complexity: O(n * 2 * k)

// Approach 3: Space Optimization.
// We can optimize the space complexity of the tabulation approach by using two 2D arrays instead of a 3D array. We will maintain two arrays, 'ahead' and 'curr', to store the results of the next day and the current day, respectively. This way, we only need to keep track of the previous day's results, reducing the space complexity significantly.

// Code:
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][k+1];
        int[][] curr = new int[2][k+1];
        
        for(int ind = n-1; ind >= 0; ind--){
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <= k; cap++){
                    int doNothing = ahead[buy][cap];
                    int doSomething;
                    if(buy == 0){
                        doSomething = -prices[ind] + ahead[1][cap];
                    }
                    else{
                        doSomething = prices[ind] + ahead[0][cap-1];
                    }
                    curr[buy][cap] = Math.max(doNothing,doSomething);
                }
            }
            ahead = curr;
        }
        return ahead[0][k];
    }
}

// Time Complexity: O(n * 2 * k)
// Space Complexity: O(2 * k)