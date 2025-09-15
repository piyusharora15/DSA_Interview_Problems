// Problem Link: https://leetcode.com/problems/vowel-spellchecker?envType=daily-question&envId=2025-09-14

/* Brute Force Approach: Idea: For each query, scan wordlist in order and check rules in order: 
-> Check exact equality (string.equals).
-> Check lowercased equality.
-> Check vowel-error by comparing strings after devoweling on the fly.
*/

class VowelSpellchecker {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactWords = new HashSet<>();
        Map<String, String> caseInsensitiveWords = new HashMap<>();
        Map<String, String> vowelErrorWords = new HashMap<>();

        for (String word : wordlist) {
            exactWords.add(word);
            String lowerWord = word.toLowerCase();
            caseInsensitiveWords.putIfAbsent(lowerWord, word);
            String devoweledWord = devowel(lowerWord);
            vowelErrorWords.putIfAbsent(devoweledWord, word);
        }

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (exactWords.contains(query)) {
                result[i] = query;
            } else {
                String lowerQuery = query.toLowerCase();
                if (caseInsensitiveWords.containsKey(lowerQuery)) {
                    result[i] = caseInsensitiveWords.get(lowerQuery);
                } else {
                    String devoweledQuery = devowel(lowerQuery);
                    result[i] = vowelErrorWords.getOrDefault(devoweledQuery, "");
                }
            }
        }

        return result;
    }

    private String devowel(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

// Time Complexity: O(m * n) where m is the number of queries and n is the number of words in the wordlist.
// Space Complexity: O(n) for storing the sets and maps.

/*  Optimal Approach: precompute three structures:
-> exactSet (HashSet) for O(1) exact lookups,
-> caseInsensitiveMap (lowercased -> first original),
-> devowelMap (lowercased with vowels replaced by * -> first original).
Why it works: Preprocessing lets each query be answered with up to 2 hash lookups and one normalization pass (lowercase + devowel), i.e. O(length of word) per query.
*/
/*
Iterate wordlist in order; for each word w:
put w into exactSet.
let low = w.toLowerCase(Locale.ROOT); caseInsensitiveMap.putIfAbsent(low, w) to preserve first occurrence.
let dev = devowel(low) where devowel replaces a/e/i/o/u by *; devowelMap.putIfAbsent(dev, w).

For each query:
if exactSet.contains(query) → return query.
else if caseInsensitiveMap contains query.toLowerCase() → return mapped value.
else if devowelMap contains devowel(query.toLowerCase()) → return mapped value.
else return "".
*/

// Code:
class Solution {
    Set<String> exactWords = new HashSet<>();
    Map<String,String> caseMap = new HashMap<>();
    Map<String,String> vowelMap = new HashMap<>();
    private String toLower(String s){
        return s.toLowerCase();
    }
    private String maskVowels(String s){
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            if(isVowel(c)){
                sb.append("*");
            }
            else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    private String checkForMatch(String query){
        if(exactWords.contains(query)){
            return query;
        }
        String lowerquery = toLower(query);
        if(caseMap.containsKey(lowerquery)){
            return caseMap.get(lowerquery);
        }
        String maskedQuery = maskVowels(lowerquery);
        if(vowelMap.containsKey(maskedQuery)){
            return vowelMap.get(maskedQuery);
        }
        return "";
    }
    public String[] spellchecker(String[] wordlist, String[] queries) {
        exactWords.clear();
        caseMap.clear();
        vowelMap.clear();
        for(String word : wordlist){
            exactWords.add(word);
            String lowerword = toLower(word);
            caseMap.putIfAbsent(lowerword,word);
            String maskedWord = maskVowels(lowerword);
            vowelMap.putIfAbsent(maskedWord,word);
        }
        String[] result = new String[queries.length];
        for(int i=0;i<queries.length;i++){
            result[i] = checkForMatch(queries[i]);
        }
        return result;
    }
}

// Time Complexity: O(n + m * k) where n is the number of words in wordlist, m is the number of queries, and k is the average length of the words.
// Space Complexity: O(n) for the sets and maps used for preprocessing.