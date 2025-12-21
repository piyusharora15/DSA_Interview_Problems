// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee

// Approach 1: Recursion + Memoization.
// We can either buy, sell or skip on each day. We keep track of whether we currently hold a stock or not using a boolean flag.
// We use a 2D array to memoize the results for each day and holding state.
// We return the maximum profit we can achieve.

// Code:
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        Integer[][] dp = new Integer[n][2];
        return getAns(prices,fee,0,1,dp);
    }
    private int getAns(int[] prices,int fee,int ind,int canBuy,Integer[][] dp){
        if(ind >= prices.length){
            return 0;
        }
        if(dp[ind][canBuy] != null){
            return dp[ind][canBuy];
        }
        int profit = 0;
        if(canBuy == 1){
            int buy = -prices[ind] + getAns(prices,fee,ind+1,0,dp);
            int skip = getAns(prices,fee,ind+1,1,dp);
            profit = Math.max(buy,skip);
        }
        else{
            int sell = prices[ind] - fee + getAns(prices,fee,ind+1,1,dp);
            int skip = getAns(prices,fee,ind+1,0,dp);
            profit = Math.max(sell,skip);
        }
        dp[ind][canBuy] = profit;
        return profit;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n) for recursion stack + O(n*2) for dp array.

// Approach 2: Tabulation.
// We can use a 2D dp array where dp[i][0] represents the maximum profit on day i when we cannot buy (i.e., we hold a stock),
// and dp[i][1] represents the maximum profit on day i when we can buy (i.e., we do not hold a stock).
// We fill this dp array iteratively from the last day to the first day.
// We return dp[0][1] as the final answer.

// Code:
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        
        for(int ind = n-1; ind >= 0; ind--){
            for(int canBuy = 0; canBuy <= 1; canBuy++){
                int profit = 0;
                if(canBuy == 1){
                    int buy = -prices[ind] + dp[ind+1][0];
                    int skip = dp[ind+1][1];
                    profit = Math.max(buy,skip);
                }
                else{
                    int sell = prices[ind] - fee + dp[ind+1][1];
                    int skip = dp[ind+1][0];
                    profit = Math.max(sell,skip);
                }
                dp[ind][canBuy] = profit;
            }
        }
        return dp[0][1];
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n*2) for dp array.

// Approach 3: Space Optimization.
// We can optimize the space used in the tabulation approach by only keeping track of the next day's values.
// We use two variables to store the next day's profits for both states (can buy and cannot buy).
// We update these variables iteratively for each day from the last to the first.
// We return the profit for day 0 when we can buy.

// Code:
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] next = new int[2];
        int[] curr = new int[2];
        
        for(int ind = n-1; ind >= 0; ind--){
            for(int canBuy = 0; canBuy <= 1; canBuy++){
                int profit = 0;
                if(canBuy == 1){
                    int buy = -prices[ind] + next[0];
                    int skip = next[1];
                    profit = Math.max(buy,skip);
                }
                else{
                    int sell = prices[ind] - fee + next[1];
                    int skip = next[0];
                    profit = Math.max(sell,skip);
                }
                curr[canBuy] = profit;
            }
            next = curr.clone();
        }
        return next[1];
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1).