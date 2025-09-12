// Problem Link: https://leetcode.com/problems/vowels-game-in-a-string?envType=daily-question&envId=2025-09-12

// Approach: Alice loses immediately if there are no vowels, because she has no valid first move. If there is at least one vowel, Alice always has a way to win. Why? On her first move, she can always choose a substring with an odd number of vowels.Even if the total vowel count is even, Alice can remove a smaller odd-length vowel-containing substring. After that, Bob may or may not be able to make a move, but eventually Alice will again have the chance to remove vowels.Since the problem only asks if Alice can win (not whether she always does), the presence of at least one vowel guarantees that there is a scenario where Alice wins.
// So the problem reduces to: Does the string contain at least one vowel? If yes, return true. Otherwise, return false.

// Code:
class Solution {
    public boolean doesAliceWin(String s) {
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        }
        return false;
    }
}

// Time Complexity: O(n) where n is the length of the string s, as we may need to check each character in the worst case.
// Space Complexity: O(1) since we are using a constant amount of extra space.