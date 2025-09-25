// Problem Link: https://leetcode.com/problems/search-in-rotated-sorted-array-ii?envType=problem-list-v2&envId=binary-search

// Approach 1: Linear Search.
// Time Complexity: O(n)
// Space Complexity: O(1)

// Code:
class Solution {
    public boolean search(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}

// Approach 2: Two Step: Find Pivot and then do Binary Search.
// Find Pivot: The pivot is the index of the smallest element in the array. Then binary search is performed in the appropriate subarray.
// Time Complexity: O(log n) in average case, O(n) in worst case (when there are many duplicates)
// Space Complexity: O(1)

// Code:
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        int pivot = findPivotWithDuplicates(nums);
        if(target >= nums[pivot] && target <= nums[n-1]){
            return binarySearch(nums,pivot,n-1,target);
        }
        else{
            return binarySearch(nums,0,pivot-1,target);
        }
    }
    private int findPivotWithDuplicates(int[] nums){
        int l = 0,r = nums.length - 1;
        while(l < r){
            while(l < r && nums[l] == nums[l+1]) l++;
            while(r < l && nums[r] == nums[r-1]) r--;
            int mid = l + (r - l) / 2;
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
        return l;
    }
    private boolean binarySearch(int[] nums,int l,int r,int target){
        while(l <= r){
            int mid = l + (r-l) / 2;
            if(nums[mid] == target) return true;
            if(nums[mid] < target) l = mid+1;
            else r = mid - 1;
        }
        return false;
    }
}

// Approach 3: Modified Binary Search without finding pivot.
// With duplicates, we need to handle the case where nums[mid] == nums[left] == nums[right] by incrementing left and decrementing right.
// Time Complexity: O(log n) in average case, O(n) in worst case (when there are many duplicates)
// Space Complexity: O(1)

// Code:
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                l++;
                r--;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}