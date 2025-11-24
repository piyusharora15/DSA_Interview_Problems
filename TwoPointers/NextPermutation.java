// Problem Link: https://leetcode.com/problems/next-permutation

// Approach: Two Pointers.
// We traverse the array from the end to find the first decreasing element.
// Then, we find the smallest element on the right side of this element that is larger than it.
// We swap these two elements and then reverse the subarray to the right of the first element's original position to get the next permutation.

// Code:
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int ind = -1;
        for(int i=n-2;i>=0;i--)
        {
            if(nums[i]<nums[i+1])
            {
                ind=i;
                break;
            }
        }
        if(ind==-1)
        {
            reverse(nums,0,n-1);
            return;
        }
        for(int i=n-1;i>ind;i--)
        {
            if(nums[i]>nums[ind])
            {
                swap(nums,i,ind);
                break;
            }
        }
        reverse(nums,ind+1,n-1);
    }
    private static void reverse(int nums[],int start,int end)
    {
        while(start<end)
        {
            swap(nums,start++,end--);
        }
    }
     private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)