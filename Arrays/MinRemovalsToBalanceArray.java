// Problem Link : https://leetcode.com/contest/biweekly-contest-162/problems/minimum-removals-to-balance-array/description/

// Approach: We need to find the longest subarray where the ratio of the maximum element to the minimum element is less than or equal to k. We can use a two-pointer technique to find this subarray efficiently.

class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if(n <= 1){
            return 0;
        }
        Arrays.sort(nums);
        int maxLen = 0;
        int j=0;
        for(int i=0;i<n;i++){
            while(j < n && nums[j] <= (long) nums[i] * k){
                j++;
            }
            maxLen = Math.max(maxLen,j-i);
        }
        return n - maxLen;
    }
}

// Time Complexity: O(n log n) for sorting and O(n) for the two-pointer traversal, resulting in an overall time complexity of O(n log n).
// Space Complexity: O(1) since we are using a constant amount of extra space.