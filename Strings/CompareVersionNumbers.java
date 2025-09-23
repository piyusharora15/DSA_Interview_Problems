// Problem Link: https://leetcode.com/problems/compare-version-numbers?envType=daily-question&envId=2025-09-23

// Approach 1: Split + Parse to Integer.
// Split both strings on the '.' character to get individual version components.
// Parse each component to an integer to handle leading zeros and compare them directly.

// Code:
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] a = version1.split("\\.");
        String[] b = version2.split("\\.");
        int n = Math.max(a.length,b.length);
        for(int i=0;i<n;i++){
            int x = i < a.length ? Integer.parseInt(a[i]) : 0;
            int y = i < b.length ? Integer.parseInt(b[i]) : 0;
            if(x < y) return -1;
            if(x > y) return 1;
        }
        return 0;
    }
}

// Time Complexity: O(max(m, n)) where m and n are the lengths of version1 and version2 respectively.
// Space Complexity: O(m + n) for storing the split components of both version strings.

// Approach 2: Split + Strip Leading Zeros + Compare Length + Lexicographical Comparison
// Split both strings on the '.' character to get individual version components.
// Strip leading zeros from each component to ensure accurate comparison.
// Compare the lengths of the stripped components first. If they differ, the longer one is greater.
// If lengths are equal, perform a lexicographical comparison of the components.

// Code: 
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] a = version1.split("\\.");
        String[] b = version2.split("\\.");
        int n = Math.max(a.length,b.length);
        for(int i=0;i<n;i++){
            String s1 = i < a.length ? stripLeadingZeros(a[i]) : "0";
            String s2 = i < b.length ? stripLeadingZeros(b[i]) : "0";
            if(s1.length() != s2.length()) return s1.length() > s2.length() ? 1 : -1;
            int cmp = s1.compareTo(s2);
            if(cmp != 0) return cmp > 0 ? 1 : -1;
        }
        return 0;
    }
    private String stripLeadingZeros(String s){
        int i=0;
        while(i < s.length() && s.charAt(i) == '0') i++;
        return i == s.length() ? "0" : s.substring(i);
    }
}

// Time Complexity: O(max(m, n)) where m and n are the lengths of version1 and version2 respectively.
// Space Complexity: O(m + n) for storing the split components of both version strings.

// Optimal Approach: Two Pointer Technique.
// Use two pointers to traverse both version strings simultaneously.
// Extract each version component on-the-fly without splitting the strings.
// Parse each component to an integer to handle leading zeros and compare them directly.
// This approach avoids the overhead of splitting and storing components, making it more space-efficient.

// Code:
class Solution {
    public int compareVersion(String version1, String version2) {
        int n1 = version1.length();
        int n2 = version2.length();
        int i=0,j=0;
        while(i < n1 || j < n2){
            int start1 = i;
            while(i < n1 && version1.charAt(i) != '.') i++;
            int end1 = i;
            int start2 = j;
            while(j < n2 && version2.charAt(j) != '.') j++;
            int end2 = j;
            int s1 = start1;
            while(s1 < end1 && version1.charAt(s1) == '0') s1++;
            int s2 = start2;
            while(s2 < end2 && version2.charAt(s2) == '0') s2++;
            int len1 = end1 - s1;
            int len2 = end2 - s2;
            if(len1 != len2) return len1 > len2 ? 1 : -1;
            for(int k=0;k<len1;k++){
                char c1 = version1.charAt(s1+k);
                char c2 = version2.charAt(s2+k);
                if(c1 != c2) return c1> c2 ? 1 : -1;
            }
            i++;
            j++;
        }
        return 0;
    }
}

// Time Complexity: O(max(m, n)) where m and n are the lengths of version1 and version2 respectively.
// Space Complexity: O(1) as we are using only a constant amount of extra space.