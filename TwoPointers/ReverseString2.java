// Problem Link: https://leetcode.com/problems/reverse-string-ii

// Approach: Two Pointers.
// We iterate through the string in chunks of size 2k. For each chunk, we reverse the first k characters using two pointers. We then append the remaining characters as they are. This approach ensures that we efficiently reverse the required segments of the string while maintaining the order of the other characters.

// Code:
class Solution {
    public String reverseStr(String s, int k) {
        if(s == null || s.length() == 0 || k == 0) return s;
        char[] ch = s.toCharArray();
        int n = ch.length;
        for(int i=0;i<n;i+=2*k){
            int left = i;
            int right = Math.min(i+k-1,n-1);
            while(left < right){
                char temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++;
                right--;
            }
        }
        return new String(ch);
    }
}

// Time Complexity: O(n), where n is the length of the string. We traverse the string once.
// Space Complexity: O(n) for the character array used to store the string characters.