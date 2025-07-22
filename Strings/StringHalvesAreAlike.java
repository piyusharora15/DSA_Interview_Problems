// You're given a string s of even length. Split it into two halves: Left half → s[0...n/2 - 1] ; Right half → s[n/2...n-1] ; Determine if both halves contain the same number of vowels. Vowels are: 'a', 'e', 'i', 'o', 'u' (case insensitive)

// Approach 1: Two-Pointer & Vowel Count : 
// Use two pointers — one from the start and one from the middle — and count vowels in both halves.

public boolean halvesAreAlike(String s) {
    String vowels = "aeiouAEIOU";
    int leftCount = 0, rightCount = 0;
    int n = s.length();
    
    for (int i = 0; i < n / 2; i++) {
        if (vowels.indexOf(s.charAt(i)) != -1) leftCount++;
        if (vowels.indexOf(s.charAt(i + n / 2)) != -1) rightCount++;
    }

    return leftCount == rightCount;
}

// Time Complexity : O(n) → Single pass through string
// Space Complexity : O(1) space (just counters + string lookup)

// Approach 2:  Using Set for Fast Lookup.
// Same logic, but use a HashSet for O(1) lookup instead of indexOf().

public boolean halvesAreAlike(String s) {
    Set<Character> vowels = new HashSet<>(Arrays.asList(
        'a','e','i','o','u','A','E','I','O','U'));
    
    int leftCount = 0, rightCount = 0;
    int n = s.length();
    
    for (int i = 0; i < n / 2; i++) {
        if (vowels.contains(s.charAt(i))) leftCount++;
        if (vowels.contains(s.charAt(i + n / 2))) rightCount++;
    }

    return leftCount == rightCount;
}

// Time Complexity: O(n)
// Space Complexity: O(1) — constant space (HashSet of vowels).

// Approach 3: One-Pass Difference Counter.
// Instead of counting separately, maintain a single diff: ; +1 if vowel in left half ; -1 if vowel in right half ; If diff == 0 at the end → both halves are alike.

public boolean halvesAreAlike(String s) {
    Set<Character> vowels = new HashSet<>(Arrays.asList(
        'a','e','i','o','u','A','E','I','O','U'));
    
    int diff = 0;
    int n = s.length();
    
    for (int i = 0; i < n / 2; i++) {
        if (vowels.contains(s.charAt(i))) diff++;
        if (vowels.contains(s.charAt(i + n / 2))) diff--;
    }

    return diff == 0;
}
 // Time Complexity: O(n); Space Complexity: O(1)