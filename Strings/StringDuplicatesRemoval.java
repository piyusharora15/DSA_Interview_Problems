// Problem Link: https://www.geeksforgeeks.org/problems/remove-all-duplicates-from-a-given-string4321/1?page=3&category=Strings&sortBy=submissions

// Approach 1: Iterate through the string and for each character check if that particular character has occurred before it in the string. If not, add the character to the result, otherwise the character is not added to result.

// Code:
public static String removeDuplicate(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        boolean[] seen = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen[c]) {
                sb.append(c);
                seen[c] = true;
            }
        }
        return sb.toString();
    }

// Time Complexity: O(n), where n is the length of the string.
// Space Complexity: O(1), as the size of the boolean array is constant (256).

// Approach 2: Using HashSet to track seen characters.
// We can use a HashSet to keep track of characters that have already been encountered while iterating through the string. If a character is not in the HashSet, we add it to the result and mark it as seen by adding it to the HashSet.

// Code:
 public static String removeDuplicates(String s) {
        HashSet<Character> exists = new HashSet<>();
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!exists.contains(c)) {
                ans.append(c);
                exists.add(c);
            }
        }
        return ans.toString();
    }

// Time Complexity: O(n), where n is the length of the string.
// Space Complexity: O(min(n, m)), where n is the length of the string and m is the size of the character set.

// Approach 3: Using Frequency Array.
// We can use a frequency array to keep track of the occurrence of each character in the string. As we iterate through the string, we check if the character has been seen before using the frequency array. If not, we add it to the result and mark it as seen.

// Code:
    public static String removeDuplicatesUsingFrequencyArray(String s) {
            int[] freq = new int[256];
            StringBuilder ans = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (freq[c] == 0) {
                    ans.append(c);
                    freq[c]++;
                }
            }
            return ans.toString();
        }

// Time Complexity: O(n), where n is the length of the string.
// Space Complexity: O(1), as the size of the frequency array is constant (256).