// Problem Link -> https://leetcode.com/problems/buddy-strings

// Approach : If the lengths of the strings are not equal, return false. If the strings are equal, check if there are any duplicate characters. If there are duplicates, return true. If the strings are not equal, find the indices where they differ. If there are exactly two indices where they differ, check if swapping the characters at those indices makes the strings equal. If it does, return true; otherwise, return false.

class Solution {
    public boolean buddyStrings(String s, String goal) {
        if(s.length() != goal.length()) return false;
        if(s.equals(goal)){
            int[] freq = new int[26];
            for(char c: s.toCharArray()){
                if(++freq[c-'a'] == 2) return true;
            }
            return false;
        }
        List<Integer> diffs = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != goal.charAt(i)){
                diffs.add(i);
                if(diffs.size() > 2) return false;
            }
        }
        if(diffs.size() != 2) return false;
        int i = diffs.get(0), j = diffs.get(1);
        return s.charAt(i) == goal.charAt(j) && s.charAt(j) == goal.charAt(i);
    }
}

// Time Complexity: O(n), where n is the length of the strings.
// Space Complexity: O(1), since we are using a fixed-size array for character frequency