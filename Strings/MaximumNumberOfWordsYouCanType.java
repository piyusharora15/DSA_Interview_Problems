// Problem Link: https://leetcode.com/problems/maximum-number-of-words-you-can-type?envType=daily-question&envId=2025-09-15

// Approach 1:Boolean Array.
// Build boolean broken[26] from brokenLetters. For each word, iterate characters and break early if you see a broken letter.

// Code:
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for(char c : brokenLetters.toCharArray()){
            broken[c - 'a'] = true;
        }
        int count = 0;
        String[] words = text.split(" ");
        for(String w : words){
            boolean ok = true;
            for(char ch: w.toCharArray()){
                if(broken[ch - 'a']){
                    ok = false;
                    break;
                }
            }
            if(ok) count++;
        }
        return count;
    }
}
// Time Complexity: O(n*m) where n is number of words and m is average length of each word.
// Space Complexity: O(1) since broken array is of fixed size 26.

// Approach 2: HashSet.
// Build a HashSet of broken letters. For each word, iterate characters and break early if you see a broken letter.
// Code:
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> broken = new HashSet<>();
        for(char c : brokenLetters.toCharArray()) broken.add(c);
        int count = 0;
        for(String w : text.split(" ")){
            boolean ok = true;
            for(char ch : w.toCharArray()){
                if(broken.contains(ch)){
                    ok = false;
                    break;
                }
            }
            if(ok) count++;
        }
        return count;
    }
}
// Time Complexity: O(n*m) where n is number of words and m is average length of each word.
// Space Complexity: O(k) where k is number of broken letters.