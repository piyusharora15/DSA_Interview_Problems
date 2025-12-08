// Problem Link: https://leetcode.com/problems/sort-colors

// Approach1: Counting Sort.
// We can count the number of 0s, 1s and 2s in the array and then overwrite the original array based on the counts.

// Code:
class Solution {
    public void sortColors(int[] nums) {
        int cnt0 = 0,cnt1 = 0,cnt2 = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 0) cnt0++;
            else if(nums[i] == 1) cnt1++;
            else cnt2++;
        }
        for(int i=0;i<cnt0;i++) nums[i] = 0;
        for(int i=cnt0;i<cnt0+cnt1;i++) nums[i] = 1;
        for(int i=cnt0+cnt1;i<nums.length;i++) nums[i] = 2;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)

// Optimal Approach: Dutch National Flag Algorithm.
// We can maintain three pointers to keep track of the positions of 0s, 1s and 2s and swap elements accordingly.
// We will have three pointers: low, mid, and high.
// low will point to the next position to place 0, mid will traverse the array, and high will point to the next position to place 2. When mid encounters a 0, we swap it with the element at low and increment both low and mid. When mid encounters a 1, we just increment mid. When mid encounters a 2, we swap it with the element at high and decrement high. We continue this process until mid exceeds high.

// Code:
class Solution {
    public void sortColors(int[] nums) {
        int low=0,mid=0,high=nums.length-1;
        while(mid <= high){
            if(nums[mid] == 0){
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                mid++;
                low++;
            }
            else if(nums[mid] == 1){
                mid++;
            }
            else{
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)