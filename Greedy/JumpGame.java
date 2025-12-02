// Problem Link: https://leetcode.com/problems/jump-game

// Approach: Greedy
// We maintain a variable 'farthest' to keep track of the farthest index we can reach at any point.
// We iterate through the array and update 'farthest' with the maximum reachable index from each position.

// Code:
class Solution {
    public boolean canJump(int[] nums) {
         int maxIndex = 0;
      for(int i=0;i<nums.length;i++){
        if(i > maxIndex){
            return false;
        }
        maxIndex = Math.max(maxIndex,i+nums[i]);
      }
      return true;    
    }
}

// Time Complexity: O(n) where n is the length of the input array.
// Space Complexity: O(1) as we are using only a constant amount of extra space.