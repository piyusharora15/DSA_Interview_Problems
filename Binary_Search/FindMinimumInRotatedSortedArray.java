// Problem Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array?envType=problem-list-v2&envId=binary-search

// Brute Force Approach: Linear Search.
// Time Complexity: O(N)
// Space Complexity: O(1)

// Code:
class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }
}

// Optimal Approach: Binary Search.
/*
At any step, check nums[mid] vs nums[right].
If nums[mid] > nums[right] → minimum is in (mid+1 .. right].
Else (nums[mid] <= nums[right]) → minimum is in [left .. mid] (including mid).
Loop until left == right — that index holds the minimum.
*/

// Code:
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n-1;
        while(l < r){
            int mid = l + (r-l) / 2;
            if(nums[mid] > nums[r]) l = mid+1;
            else r = mid;
        }
        return nums[l];
    }
}

// Time Complexity: O(logN)
// Space Complexity: O(1)