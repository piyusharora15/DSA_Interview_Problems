// Problem Link: https://leetcode.com/problems/minimum-size-subarray-sum

// Approach 1: Brute Force Approach:
// Try every subarray, compute its sum, track smallest length with sum ≥ target.
// For every i from 0..n-1, run j from i..n-1, accumulate sum, when sum ≥ target update minLen.

// Code:
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum += nums[j];
                if(sum >= target){
                    ans = Math.min(ans,j-i+1);
                    break;
                }
            }
        }
        return (ans == Integer.MAX_VALUE) ? 0 : ans;
    }
}

// Time Complexity: O(n^2) in worst case, gives TLE on large inputs.
// Space Complexity: O(1)

// Approach 2: Sliding Window Approach:
// Because all numbers are positive, adding elements increases the running sum and removing from left decreases it. Expand right until sum ≥ target, then shrink left to minimize length, repeat.

// Code:
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i=0,j=0,sum=0;
        int minLen = n+1;
        while(j < n){
            sum += nums[j];
            while(sum >= target){
                minLen = Math.min(minLen,j-i+1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return minLen == n+1 ? 0 : minLen;
    }
}

// Time Complexity: O(n) as each element is visited at most twice.
// Space Complexity: O(1)