// Problem Link: https://leetcode.com/problems/search-in-rotated-sorted-array?envType=problem-list-v2&envId=binary-search

// Brute Force Approach: Scan each element and check if it is equal to target or not.
// Time Complexity: O(N)
// Space Complexity: O(1)

// Code: 
public int search(int[] nums, int target) {
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] == target) {
            return i;
        }
    }
    return -1;
}

// Approach 2: Two Pass Binary Search.
// Pivot Index is the index of the smallest element in the array.
// First we will find the pivot index using binary search.
// Then we will check in which part of the array the target lies and perform binary search in that part.
// Time Complexity: O(logN)
// Space Complexity: O(1)

// Code:
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int pivot = findPivot(nums);
        int l,r;
        if(target >= nums[pivot] && target <= nums[n-1]){
            l = pivot;
            r = n-1;
        }
        else{
            l = 0;
            r = pivot - 1;
        }
        while(l <= r){
            int mid = l + (r-l) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target) l = mid+1;
            else r = mid-1;
        }
        return -1;
    }
    private int findPivot(int[] nums){
        int l = 0, r = nums.length-1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] > nums[r]) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}

// Optimal Approach: One Pass Binary Search.
// We will modify the binary search to check which part of the array is sorted and then check if the target lies in that part or not.
// If it lies in that part, we will search in that part else we will search in  the other part.
// Time Complexity: O(logN)
// Space Complexity: O(1)

// Code:
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] == target) return mid;
            if(nums[l] <= nums[mid]){
                if(nums[l] <= target && target <= nums[mid]){
                    r = mid - 1;
                }
                else{
                    l = mid + 1;
                }
            }
            else{
                if(nums[mid] < target && target <= nums[r]){
                    l = mid + 1;
                }
                else{
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}