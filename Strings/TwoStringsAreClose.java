// Problem Link -> https://leetcode.com/problems/determine-if-two-strings-are-close

/*
Count character frequencies in both strings.
Compare:
-> If the set of characters is the same.
->If the multiset of frequencies is the same (can be rearranged via operations).
*/

// Time Complexity : O(n + 26log26) = O(n) where n = length of string (since sorting 26-sized arrays is constant)
// Space Complexity : O(1) (constant space for two 26-element arrays)

class Solution {
    public boolean closeStrings(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for(char ch: word1.toCharArray()){
            freq1[ch-'a']++;
        }
        for(char ch: word2.toCharArray()){
            freq2[ch-'a']++;
        }
        for(int i=0;i<26;i++){
            if((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] !=0 && freq2[i] == 0)){
                return false;
            }
        }
        Arrays.sort(freq1);
        Arrays.sort(freq2);
        for(int i=0;i<26;i++){
            if(freq1[i] != freq2[i]){
                return false;
            }
        }
        return true;
    }
}