// Problem Link: https://leetcode.com/problems/largest-odd-number-in-string

// Approach 1: Brute Force: Check all substrings.
// Enumerate all substrings; keep only those that end with an odd digit; and choose the numerically largest one(length first, then lexicographic tie break).

// Code:
class Solution {
    public String largestOddNumber(String num) {
        String best = "";
        for (int i = 0; i < num.length(); i++) {
            for (int j = i; j < num.length(); j++) {
                char last = num.charAt(j);
                if (((last - '0') & 1) == 1) {
                    String cand = num.substring(i, j + 1);
                    if (cand.length() > best.length() || (cand.length() == best.length() && cand.compareTo(best) > 0)) {
                        best = cand;
                    }
                }
            }
        }
        return best;
    }
}

// Time Complexity: O(n^3) where n is the length of the input string num. (TLE)
// Space Complexity: O(n) for the substring storage.

// Optimal Approach: One pass right to left scan.
// scan from the end; the first odd digit you hit (from the right) defines the longest prefix ending at that digit, which is the largest odd substring.

// Code:
class Solution {
    public String largestOddNumber(String num) {
        for(int i=num.length() - 1;i>=0;i--){
            int d = num.charAt(i) - '0';
            if((d & 1) == 1){
                return num.substring(0,i+1);
            }
        }
        return "";
    }
}

// Time Complexity: O(n) where n is the length of the input string num.
// Space Complexity: O(1) if we ignore the output string storage.