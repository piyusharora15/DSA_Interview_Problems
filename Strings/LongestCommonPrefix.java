// Problem Link: https://leetcode.com/problems/longest-common-prefix

// Approach 1: Horizontal Scanning(Iteratively shrink prefix)
// Start with the first string as the candidate prefix. Compare it with each other string and shorten the prefix until it matches the current string.

// Code:
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for(int i=1;i<strs.length;i++){
            while(!strs[i].startsWith(prefix)){
                prefix = prefix.substring(0,prefix.length() - 1);
                if(prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

// Time Complexity: O(S) where S is the sum of all characters in all strings.
// Space Complexity: O(1)

// Approach 2: Vertical scanning (char-by-char across strings)

// Check characters one column at a time (index 0, index 1, ...) across all strings until a mismatch occurs.

// Code:
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        for(int i=0;i<strs[0].length();i++){
            char c = strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(i == strs[j].length() || strs[j].charAt(i) != c){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
}

// Time Complexity: O(S) where S is the sum of all characters in all strings.
// Space Complexity: O(1)