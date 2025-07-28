// Problem Link: https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and

// Approach : 
/*After observing the AND property and the problem statement, the goal is to find the length of the longest subarray that contains only the maximum element in the array.
Track Maximum Value: We maintain a variable maxVal to track the current maximum value encountered in the array.
Track Streak: As we traverse the array, we count the length of consecutive occurrences of maxVal (streak).
Update Maximum Streak: If we find an element equal to maxVal, we increase the streak. If the element exceeds maxVal, we reset the streak and start counting for the new maximum value.
Result: We continuously update the result with the longest streak of the maximum value encountered.
*/

// Time Complexity : O(n)
// Space Complexity : O(1)

class Solution {
    public int longestSubarray(int[] nums) {
        int maxVal = 0;
        int result = 0;
        int streak = 0;
        for(int num : nums){
            if(num > maxVal){
                maxVal = num;
                result = 0;
                streak = 0;
            }
            if(maxVal == num){
                streak++;
            }
            else{
                streak = 0;
            }
            result = Math.max(result,streak);
        }
        return result;
    }
}