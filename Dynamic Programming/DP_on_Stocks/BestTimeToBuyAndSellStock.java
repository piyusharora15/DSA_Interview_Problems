// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock?envType=problem-list-v2&envId=dynamic-programming

// Brute Force Approach: Checking all possible pairs of buy and sell days.
// We take each day as a buying day and check all subsequent days as selling days to find the maximum profit.

// Code: {
    /*
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                int profit = prices[j] - prices[i];
                max = Math.max(max,profit);
            }
        }
        return max;
    }
    */

// Time Complexity: O(n^2)
// Space Complexity: O(1)

// Optimal Approach: Single Pass.
// We keep track of the minimum price encountered so far and calculate the potential profit at each step.
// We update the maximum profit whenever we find a higher potential profit.

// Code:
class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int max = 0;
        for(int price : prices){
            if(price < minPrice){
                minPrice = price;
            }
            int profit = price - minPrice;
            if(profit > max){
                max = profit;
            }
        }
        return max;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)