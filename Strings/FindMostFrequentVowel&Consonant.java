// Problem Link: https://leetcode.com/problems/find-most-frequent-vowel-and-consonant?envType=daily-question&envId=2025-09-13

// Approach: Store vowels in a set, so we can quickly check if a character is a vowel or not. Maintain frequency counts in a fixed size array of length 26 for each lowercase character. Initialise two variables to keep track of the maximum frequency and the corresponding character for both vowels and consonants. Iterate through the string, updating the frequency counts and checking if the current character has a higher frequency than the previously recorded maximum. If it does, update the maximum frequency and the corresponding character. Finally, return the results based on the maximum frequencies found.

// Code:
class Solution {
    public int maxFreqSum(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));
        int[] freq = new int[26];
        int maxVowel = 0, maxConsonant = 0;
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            freq[idx]++;
            if(vowels.contains(c)){
                maxVowel = Math.max(maxVowel,freq[idx]);
            }
            else{
                maxConsonant = Math.max(maxConsonant,freq[idx]);
            }
        }
        return maxVowel + maxConsonant;
    }
}

// Time Complexity: O(n), where n is the length of the string s.
// Space Complexity: O(1), since the frequency array has a fixed size of 26