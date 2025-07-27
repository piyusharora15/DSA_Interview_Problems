// Problem Link : https://leetcode.com/problems/count-hills-and-valleys-in-an-array

// Approach : 
/*
The algorithm uses two pointers, 'i' and 'j', to compare adjacent elements in the array.
It iterates through the array, looking for "hill" or "valley" patterns.
A hill/valley is counted when the element at 'j' is higher/lower than both its non-equal neighbors.
The count is incremented for each valid hill or valley found, and 'i' is updated to 'j' after each count.
*/

class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;
        int n = nums.length;
        int i = 0; // points to non-equal neighbour on the left-hand side
        int j = 1; // points to non-equal neighbour on the right-hand side(j+1)
        while(j + 1 < n){
            if((nums[i] < nums[j] && nums[j] > nums[j+1]) || (nums[i] > nums[j] && nums[j] < nums[j+1])){
                count++;
                i = j;
            }
            j++;
        }
        return count;
    }
}

// Time Complexity : O(n)
// Space Complexity : O(1)