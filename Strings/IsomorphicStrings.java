// Problem Link: https://leetcode.com/problems/isomorphic-strings?envType=problem-list-v2&envId=string

// Brute Force Approach: A simple solution is to consider every character of 's1' and check if all occurrences of that character in 's1' map to the same character in 's2'.

// Code:

import java.util.HashMap;
import java.util.Map;

public static boolean areIsomorphic(String s1, String s2) {
        int n = s1.length();
        // Check every character of s1
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            // Check all occurrences of c1 in s1
            // and corresponding occurrences of c2 in s2
            for (int j = 0; j < n; j++) {
                // If we find another occurrence of c1 in s1,
                // it must match the corresponding character in s2
                if (s1.charAt(j) == c1 && s2.charAt(j) != c2) {
                    return false;
                }
                // If we find another occurrence of c2 in s2,
                // it must match the corresponding character in s1
                if (s2.charAt(j) == c2 && s1.charAt(j) != c1) {
                    return false;
                }
            }
        }
        return true;
    }

// Approach 2: Using HashMaps.
// We can use two hash maps to store the mapping of characters from 's1' to 's2' and vice versa. If we find a mismatch in the mapping, we return false.

// Code:
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!m1.containsKey(s.charAt(i))) {
                m1.put(s.charAt(i), i);
            }
            if (!m2.containsKey(t.charAt(i))) {
                m2.put(t.charAt(i), i);
            }
            if (!m1.get(s.charAt(i)).equals(m2.get(t.charAt(i))))
                return false;
        }
        return true;
    }
}

// Time Complexity: O(n), where n is the length of the strings.
// Space Complexity: O(1), since the size of the hash maps is bounded by the number of unique characters (which is at most 256 for extended ASCII).
