// Problem Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii

// Optimal Approach: Modified Binary Search.
/*
At each step compare nums[mid] with nums[right]:
If nums[mid] > nums[right] → minimum is in (mid+1 .. right] → set left = mid + 1.
Else if nums[mid] < nums[right] → minimum is in [left .. mid] → set right = mid.
Else (nums[mid] == nums[right]) → ambiguous because duplicates hide which side is sorted → do right-- to shrink the window by 1 (safe but can degrade to linear time).
Stop when left == right; return nums[left].

Why this is correct: If nums[mid] > nums[right] the rotation point is to the right; if nums[mid] < nums[right] the min is at mid or to the left. When equal, you cannot tell which side contains the pivot, but decrementing right preserves the minimum in the remaining window.
*/

// Code:
class Solution {
    public int findMin(int[] nums) {
        int l = 0,r = nums.length-1;
        while(l < r){
            while(l < r && nums[l] == nums[l+1]) l++;
            while(r < l && nums[r] == nums[r-1]) r--;
            int mid = l + (r-l) / 2;
            if(nums[mid] < nums[r]){
                r = mid;
            }
            else if(nums[mid] > nums[r]){
                l = mid+1;
            }
            else{
                r--;
            }
        }
        return nums[l];
    }
}

// T.C: O(log n) in average case, O(n) in worst case due to duplicates.
// S.C: O(1)