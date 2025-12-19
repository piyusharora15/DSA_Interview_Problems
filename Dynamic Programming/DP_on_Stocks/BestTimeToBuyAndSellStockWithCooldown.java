// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown

// Approach 1: Recursion + Memoization.
// We can either buy, sell or cooldown on a given day. We use a 2D dp array to store the maximum profit at each day with the state of whether we can buy or not. We recursively calculate the maximum profit for each state and store it in the dp array to avoid recomputation. We return the maximum profit we can achieve starting from day 0 with the ability to buy.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return getAns(prices,0,1,dp);
    }
    private int getAns(int[] prices,int ind,int canBuy,int[][] dp){
        if(ind >= prices.length){
            return 0;
        }
        if(dp[ind][canBuy] != -1){
            return dp[ind][canBuy];
        }
        int profit = 0;
        if(canBuy == 1){
            int buy = -prices[ind] + getAns(prices,ind+1,0,dp);
            int skip = getAns(prices,ind+1,1,dp);
            profit = Math.max(buy,skip);
        }
        else{
            int sell = prices[ind] + getAns(prices,ind+2,1,dp);
            int skip = getAns(prices,ind+1,0,dp);
            profit = Math.max(sell,skip);
        }
        dp[ind][canBuy] = profit;
        return profit;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n) for dp array + O(n) for recursion stack = O

// Approach 2: Tabulation.
// We can use a 2D dp array to store the maximum profit at each day with the state of whether we can buy or not. We iterate through the days in reverse order and calculate the maximum profit for each state based on the decisions we can make (buy, sell, or cooldown). Finally, we return the maximum profit we can achieve starting from day 0 with the ability to buy.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+2][2];
        
        for(int ind = n-1; ind >= 0; ind--){
            for(int canBuy = 0; canBuy <= 1; canBuy++){
                int profit = 0;
                if(canBuy == 1){
                    int buy = -prices[ind] + dp[ind+1][0];
                    int skip = dp[ind+1][1];
                    profit = Math.max(buy,skip);
                }
                else{
                    int sell = prices[ind] + dp[ind+2][1];
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
// Space Complexity: O(n)

// Approach 3: Space Optimization.
// We can optimize the space used in the tabulation approach by only keeping track of the next two days' profits instead of the entire dp array. We use two 1D arrays to store the profits for the next day and the day after that. We iterate through the days in reverse order and calculate the maximum profit for each state based on the decisions we can make (buy, sell, or cooldown). Finally, we return the maximum profit we can achieve starting from day 0 with the ability to buy.

// Code:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] next1 = new int[2];
        int[] next2 = new int[2];
        
        for(int ind = n-1; ind >= 0; ind--){
            int[] curr = new int[2];
            for(int canBuy = 0; canBuy <= 1; canBuy++){
                int profit = 0;
                if(canBuy == 1){
                    int buy = -prices[ind] + next1[0];
                    int skip = next1[1];
                    profit = Math.max(buy,skip);
                }
                else{
                    int sell = prices[ind] + next2[1];
                    int skip = next1[0];
                    profit = Math.max(sell,skip);
                }
                curr[canBuy] = profit;
            }
            next2 = next1;
            next1 = curr;
        }
        return next1[1];
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)