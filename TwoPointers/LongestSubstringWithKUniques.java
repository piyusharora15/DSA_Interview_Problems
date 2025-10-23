// Problem Link: https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1?page=1&category=two-pointer-algorithm&sortBy=submissions

// Brute Force Approach: Using Nested Loops and HashSet.
// Check all substrings and use a HashSet to count unique characters. Whenever the count exceeds k, break and move to the next starting point. Track the maximum length found.

// Time Complexity: O(n^2) in the worst case, where n is the length of the string.
// Space Complexity: O(k) for the HashSet storing unique characters.

// Code:
class Solution {
    public int longestKSubstr(String s, int k) {
        int ans = -1;
        Set<Character> st = new HashSet<>();
        for(int i=0;i<s.length();i++){
            st.clear();
            for(int j=i;j<s.length();j++){
                st.add(s.charAt(j));
                if(st.size() == k){
                    ans = Math.max(ans,j-i+1);
                }
                if(st.size() > k) break;
            }
        }
        return ans;
    }
}

// Better Approach: Prefix Sum + Binary Search.
// Start by building a prefix sum array that counts the occurrences of each character up to each index. For each starting index, use binary search to find the farthest ending index where the number of unique characters is exactly k.
// Time Complexity: O(n log n) due to binary search for each starting index.
// Space Complexity: O(n) for the prefix sum array.

// Code:
class Solution {
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        int[][] vec = new int[n][26];
        for(int i=0;i<n;i++){
            vec[i][s.charAt(i) - 'a']++;
            if(i > 0){
                for(int j=0;j<26;j++){
                    vec[i][j] += vec[i-1][j];
                }
            }
        }
        int ans = -1;
        for(int i=0;i<n;i++){
            int low=i,high=n-1,currAns=-1;
            while(low <= high){
                int mid = (low + high) / 2;
                int count = 0;
                int[] freq = Arrays.copyOf(vec[mid],26);
                if(i > 0){
                    for(int j=0;j<26;j++){
                        freq[j] -= vec[i-1][j];
                    }
                }
                for(int j=0;j<26;j++){
                    if(freq[j] > 0) count++;
                }
                if(count == k){
                    currAns = mid - i + 1;
                    low = mid + 1;
                }
                else if(count < k){
                    low = mid+1;
                }
                else{
                    high = mid - 1;
                }
            }
            if(currAns != -1){
                ans = Math.max(ans,currAns);
            }
        }
        return ans;
    }
}

// Optimal Approach: Using Sliding Window with frequency counting.
// Use two pointers to maintain a sliding window. Expand the right pointer to include more characters and update their frequencies. If the number of unique characters exceeds k, move the left pointer to reduce the window size until there are k unique characters again. Track the maximum length of valid windows.
// Time Complexity: O(n) where n is the length of the string.
// Space Complexity: O(1) since the character set is fixed (assuming only lowercase letters).

// Code:
class Solution {
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        int i=0,j=0;
        int cnt = 0;
        int maxi = -1;
        int[] freq = new int[26];
        while(j < n){
            freq[s.charAt(j) - 'a']++;
            if(freq[s.charAt(j) - 'a'] == 1) cnt++;
            while(cnt > k){
                freq[s.charAt(i) - 'a']--;
                if(freq[s.charAt(i) - 'a'] == 0) cnt--;
                i++;
            }
            if(cnt == k){
                maxi = Math.max(maxi,j-i+1);
            }
            j++;
        }
        return maxi;
    }
}