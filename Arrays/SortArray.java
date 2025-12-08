// Problem Link: https://leetcode.com/problems/sort-an-array

// Approach: Implementing Merge Sort algorithm to sort an array of integers.

// We define a method sortArray that takes an integer array as input and returns the sorted array.We use a helper method mergeSort to recursively divide the array into halves until we reach single-element arrays, which are inherently sorted. We then use another helper method merge to combine these sorted halves back together in sorted order.

// Code:
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return; // single element
        }
        int mid = left + (right - left) / 2;
        // sort left half
        mergeSort(nums, left, mid);
        // sort right half
        mergeSort(nums, mid + 1, right);
        // merge both halves
        merge(nums, left, mid, right);
    }
     private void merge(int[] nums, int left, int mid, int right) {
        // temporary array size = number of elements between left and right
        int[] temp = new int[right - left + 1];
        int i = left;      // pointer for left half
        int j = mid + 1;   // pointer for right half
        int k = 0;         // pointer for temp
        // merge elements in sorted order
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        // copy remaining elements of left half (if any)
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        // copy remaining elements of right half (if any)
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        // copy back from temp to original array
        for (int t = 0; t < temp.length; t++) {
            nums[left + t] = temp[t];
        }
    }
}
// Time Complexity: O(n log n) due to the divide-and-conquer nature of merge sort.
// Space Complexity: O(n) for the temporary array used during merging.