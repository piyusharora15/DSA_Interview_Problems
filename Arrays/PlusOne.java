// Problem Link: https://leetcode.com/problems/plus-one

// Approach: Start from the last digit of the array and add one. If there is a carry, propagate it to the next digit. If all digits are processed and there is still a carry, create a new array with an additional digit.

// Code:
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        ++digits[i];
        return digits;
      }
      digits[i] = 0;
    }

    int[] ans = new int[digits.length + 1];
    ans[0] = 1;
    return ans;
    }
}

// Time Complexity: O(n) where n is the number of digits in the array.
// Space Complexity: O(1) if no new array is created, otherwise O(n)