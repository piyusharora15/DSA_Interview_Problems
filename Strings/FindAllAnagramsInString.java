// Problem Link: https://leetcode.com/problems/find-all-anagrams-in-a-string?envType=problem-list-v2&envId=hash-table

// Brute Force Approach: For every substring s[i...i+m-1] (m=|p|), sort it and compare it with pSorted. If they are equal, then add i to the result list.

// Code:
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if(m > n) return res;
        char[] pSorted = p.toCharArray();
        Arrays.sort(pSorted);
        for(int i=0;i<=n-m;i++){
            char[] sub = s.substring(i,i+m).toCharArray();
            Arrays.sort(sub);
            if(Arrays.equals(pSorted,sub)) res.add(i);
        }
        return res;
    }
}

// Time Complexity: O(n * m log m) where n is the length of string s and m is the length of string p.
// Space Complexity: O(m) for storing the sorted version of string p and the substring.

// Approach 2: Frequency Count per substring.
// For each start 'i', compute frequency array of substring s[i...i+m-1] and compare it with frequency array of p.

// Code:
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if(m > n) return res;
        int[] pCount = new int[26];
        for(char c : p.toCharArray()) pCount[c - 'a']++;
        for(int i=0;i<=n-m;i++){
            int[] w = new int[26];
            for(int j=i;j<i+m;j++) w[s.charAt(j) - 'a']++;
            if(Arrays.equals(pCount,w)) res.add(i);
        }
        return res;
    }
}

// Time Complexity: O(n * m) where n is the length of string s and m is the length of string p.
// Space Complexity: O(1) since the size of frequency arrays is constant (26 for lowercase English letters).

// Approach 3: Sliding Window with two frequency arrays.
// Maintain a sliding window of size m and update the frequency array for the current window.

// Code:
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if(m > n) return res;
        int[] pCount = new int[26];
        int[] wCount = new int[26];
        for(char c : p.toCharArray()) pCount[c - 'a']++;
        for(int i=0;i<m;i++) wCount[s.charAt(i) - 'a']++;
        if(Arrays.equals(pCount,wCount)) res.add(0);
        for(int i=m;i<n;i++){
            wCount[s.charAt(i) - 'a']++;
            wCount[s.charAt(i-m) - 'a']--;
            if(Arrays.equals(pCount,wCount)) res.add(i-m+1);
        }
        return res;
    }
}

// Time Complexity: O(n) where n is the length of string s.
// Space Complexity: O(1) since the size of frequency arrays is constant (26 for lowercase English letters).

// Optimal Approach: Sliding Window with a single frequency array and a match count.
// Maintain a single frequency array and a count of how many characters have the required frequency.

// Code:
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if(m > n) return res;
        int[] count = new int[26];
        for(char c : p.toCharArray()) count[c - 'a']++;
        for(int i=0;i<m;i++) count[s.charAt(i) - 'a']--;
        int matches = 0;
        for(int x : count) if(x == 0) matches++;
        if(matches == 26) res.add(0);
        for(int i=m;i<n;i++){
            int in = s.charAt(i) - 'a';
            int out = s.charAt(i-m) - 'a';
            if(count[in] == 0) matches--;
            count[in]--;
            if(count[in] == 0) matches++;
            if(count[out] == 0) matches--;
            count[out]++;
            if(count[out] == 0) matches++;
            if(matches == 26) res.add(i-m+1);
        }
        return res;
    }
}

// Time Complexity: O(n) where n is the length of string s.
// Space Complexity: O(1) since the size of frequency array is constant (26 for lowercase English letters).