// Problem Link: https://leetcode.com/problems/valid-palindrome

// Approach: Two Pointers.
// We use two pointers, one starting from the beginning and the other from the end of the string.
// We move both pointers towards the center, skipping non-alphanumeric characters. 
// If the characters at both pointers do not match (ignoring case), we return false.
// If we successfully compare all characters, we return true.

// Code:
class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return true;
        int i=0,j=s.length()-1;
        while(i < j){
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if(i < j){
                char a = Character.toLowerCase(s.charAt(i));
                char b = Character.toLowerCase(s.charAt(j));
                if(a != b) return false;
                i++;
                j--;
            }
        }
        return true;
    }
}

// Time Complexity: O(n), where n is the length of the string.
// Space Complexity: O(1).