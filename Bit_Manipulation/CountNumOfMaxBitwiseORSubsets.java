// Problem Link -> https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets?envType=daily-question&envId=2025-07-28

// Approach 1 -> Using Simple Recursion to find all subsets.
/*
This approach uses recursion to explore all possible subsets of the array. It calculates the bitwise OR of each subset and counts how many subsets have an OR value equal to the maximum OR value of the entire array.
At each step, the function either includes or excludes the current element (nums[idx]), recursively calculates the OR value, and checks if it matches the maximum OR.
*/

// Time Complexity :  O(2^n) since it explores all subsets.
// Space Complexity: O(1) (excluding the recursion stack).

class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0;
        for(int num : nums){
            maxOr |= num;
        }
        int currOr = 0;
        return countSubsets(0,currOr,nums,maxOr);
    }
    private int countSubsets(int idx,int currOr,int[] nums,int maxOr){
        if(idx == nums.length){
            if(currOr == maxOr)
               return 1;
            return 0;        
        }
        int takeCount = countSubsets(idx+1,currOr | nums[idx],nums,maxOr);
        int notTakeCount = countSubsets(idx+1,currOr,nums,maxOr);
        return takeCount + notTakeCount;
    }
}

// Approach 2 : (Memoizing to store subproblems result).
/*

This is an optimized version of the recursive approach, where we store the results of subproblems (using a memoization table) to avoid recalculating the OR values for the same states.
The table t[idx][currOr] stores the result of subsets from index idx onward with the current OR value currOr. This avoids recomputation and speeds up the process.
*/

// Time Complexity : O(n * maxOr) where maxOr is the maximum OR value achievable.
// Space Complexity: O(n * maxOr) due to the memoization table.

class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0;
        for(int num : nums){
            maxOr |= num;
        }
        int n = nums.length;
        int[][] t = new int[n+1][maxOr+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=maxOr;j++){
                t[i][j] = -1;
            }
        }
        int currOr = 0;
        return countSubsets(0,currOr,nums,maxOr,t);
    }
    private int countSubsets(int idx,int currOr,int[] nums,int maxOr,int[][] t){
        if(idx == nums.length){
            if(currOr == maxOr)
            return t[idx][currOr] = 1; // Found one subset
            return t[idx][currOr] = 0;
        }
        if(t[idx][currOr] != -1){
            return t[idx][currOr];
        }
        // Take nums[idx] :
        int takeCount = countSubsets(idx+1,currOr | nums[idx],nums,maxOr,t);
        // Not take nums[idx] :
        int notTakeCount = countSubsets(idx+1,currOr,nums,maxOr,t);
        return t[idx][currOr] = takeCount + notTakeCount;
    }
}