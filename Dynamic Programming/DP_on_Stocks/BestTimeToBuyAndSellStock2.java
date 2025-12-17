// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii?envType=problem-list-v2&envId=dynamic-programming

// Approach 1: Memoization.
// We can buy and sell the stock multiple times. We will use a recursive function with memoization to keep track of the maximum profit we can achieve from each day, considering whether we currently hold a stock or not.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][2];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return getAns(prices, 0, 0, n, dp);
    }

    private int getAns(int[] arr, int ind, int buy, int n, int[][] dp) {
        if (ind == n) {
            return 0;
        }
        if (dp[ind][buy] != -1) {
            return dp[ind][buy];
        }
        int profit;
        if (buy == 0) {
            profit = Math.max(0 + getAns(arr, ind + 1, 0, n, dp), -arr[ind] + getAns(arr, ind + 1, 1, n, dp));
        } else {
            profit = Math.max(0 + getAns(arr, ind + 1, 1, n, dp), arr[ind] + getAns(arr, ind + 1, 0, n, dp));
        }
        dp[ind][buy] = profit;
        return profit;
    }
}

// Time Complexity: O(N), we are traversing all the days once.
// Space Complexity: O(N) for the recursion stack and O(N*2) for the dp array.

// Approach 2: Tabulation.
// We can also solve this problem using tabulation. We will create a dp table where dp[i][0] represents the maximum profit we can achieve from day i to the end if we do not hold a stock, and dp[i][1] represents the maximum profit if we hold a stock.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit;
                if (buy == 0) {
                    profit = Math.max(0 + dp[ind + 1][0], -prices[ind] + dp[ind + 1][1]);
                } else {
                    profit = Math.max(0 + dp[ind + 1][1], prices[ind] + dp[ind + 1][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }
}

// Time Complexity: O(N), we are traversing all the days once.
// Space Complexity: O(N*2) for the dp array.

// Approach 3: Space Optimization.
// We can optimize the space used in the tabulation approach by only keeping track of the next day's values instead of the entire dp table.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0) return 0;
        int[] ahead = new int[2];
        int[] curr = new int[2];
        ahead[0] = ahead[1] = 0;
        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<=1;buy++){
                int profit;
                if(buy == 0){
                    profit = Math.max(0+ahead[0],-prices[ind]+ahead[1]);
                }
                else{
                    profit = Math.max(0+ahead[1],prices[ind]+ahead[0]);
                }
                curr[buy] = profit;
            }
            ahead = curr.clone();
        }
        return curr[0];
    }
}

// Time Complexity: O(N), we are traversing all the days once.
// Space Complexity: O(1), we are using only constant space.