// Problem Link: https://leetcode.com/problems/reverse-string?envType=problem-list-v2&envId=string

// Approach 1: Two Pointer In-Place Swap.
// Place left at 0 and right at n-1. Swap s[left] and s[right], then left++, right--, repeat until left >= right. In-place, constant extra memory.

// Code:
class Solution {
    public void reverseString(char[] s) {
        int left=0,right=s.length-1;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)