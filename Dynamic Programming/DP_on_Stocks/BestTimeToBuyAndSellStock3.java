// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii?envType=problem-list-v2&envId=dynamic-programming

// Approach 1: Memoization.
// We can make at most two transactions. We use a 3D DP array to store the maximum profit at each day, with the number of transactions left and whether we can buy or sell on that day.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        Integer[][][] dp = new Integer[n][2][3];
        return getAns(prices, n, 0, 0, 2, dp);
    }

    private int getAns(int[] arr, int n, int ind, int buy, int cap, Integer[][][] dp) {
        if (ind == n || cap == 0) {
            return 0;
        }
        if (dp[ind][buy][cap] != null) {
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

// Time Complexity: O(N*2*3) where N is the number of days.
// Space Complexity: O(N*2*3) for the DP array + O(N) for the recursion stack.

// Approach 2: Tabulation.
// We can convert the memoization approach to tabulation by filling the DP table iteratively. We start from the last day and move backwards, calculating the maximum profit for each state. We use a 3D DP array to store the maximum profit at each day, with the number of transactions left and whether we can buy or sell on that day.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][3];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    int doNothing = dp[ind + 1][buy][cap];
                    int doSomething;
                    if (buy == 0) {
                        doSomething = -prices[ind] + dp[ind + 1][1][cap];
                    } else {
                        doSomething = prices[ind] + dp[ind + 1][0][cap - 1];
                    }
                    dp[ind][buy][cap] = Math.max(doNothing, doSomething);
                }
            }
        }
        return dp[0][0][2];
    }
}

// Time Complexity: O(N*2*3) where N is the number of days.
// Space Complexity: O(N*2*3) for the DP array.

// Approach 3: Space Optimization.
// We can optimize the space used in the tabulation approach by only keeping track of the next day's values. Since the current day's values only depend on the next day's values, we can use two 2D arrays to store the current and next day's profits. We update these arrays iteratively as we move backwards through the days. We use two 2D arrays to store the maximum profit at each day, with the number of transactions left and whether we can buy or sell on that day.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][3];
        int[][] curr = new int[2][3];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    int doNothing = ahead[buy][cap];
                    int doSomething;
                    if (buy == 0) {
                        doSomething = -prices[ind] + ahead[1][cap];
                    } else {
                        doSomething = prices[ind] + ahead[0][cap - 1];
                    }
                    curr[buy][cap] = Math.max(doNothing, doSomething);
                }
            }
            ahead = curr;
        }
        return ahead[0][2];
    }
}

// Time Complexity: O(N*2*3) where N is the number of days.
// Space Complexity: O(2*3) for the two 2D arrays.