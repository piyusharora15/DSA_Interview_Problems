// Problem Link: https://leetcode.com/problems/container-with-most-water

// Approach: Two Pointers + Greedy.
// We use two pointers, one at the start and one at the end of the array.
// We calculate the area formed between the two pointers and update the maximum area if needed.
// We then move the pointer pointing to the shorter line towards the other pointer, as moving the longer line won't help in finding a larger area.
// We repeat this process until the two pointers meet.

// Code:
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n - 1;
        int maxArea = 0;
        while(i < j){
            int width = j - i;
            int heights = Math.min(height[i],height[j]);
            maxArea = Math.max(maxArea,(width * heights));
            if(height[i] > height[j]){
                j--;
            }
            else{
                i++;
            }
        }
        return maxArea;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)