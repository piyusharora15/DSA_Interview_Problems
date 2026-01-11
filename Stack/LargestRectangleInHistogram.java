// Problem Link : https://leetcode.com/problems/largest-rectangle-in-histogram

// Brute Force Approach: 
/* 
For each index i:
-> Take height = heights[i].
-> Expand left until a bar shorter than height is found.
-> Expand right until a bar shorter than height is found.
-> Compute width = right - left + 1.
-> Area = height * width.

Keep max of all such areas.
*/

// Code :
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        for(int i=0;i<n;i++){
            int height = heights[i];
            int l=i,r=i;
            while(l-1 >= 0 && heights[l-1] >= height){
                l--;
            }
            while(r+1 < n && heights[r+1] >= height){
                r++;
            }
            int width = r-l+1;
            maxArea = Math.max(maxArea, width * height);
        }
        return maxArea;
    }
}

// Time complexity: O(n²)
// Space complexity: O(1)
// This code gives TLE.

// Optimal Approach : Precomputing Nearest Smaller to Left and Nearest Smaller to Right.
// We can precompute the nearest smaller to left and nearest smaller to right for each index using stacks.Then we can calculate the area for each index in O(1) time.We take the maximum of all such areas.

// Code:
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 0) return 0;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        // compute left[]
        for(int i=0;i<n;i++){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        st.clear();
        // compute right[]
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        int maxArea = 0;
        for(int i=0;i<n;i++){
            int width = right[i] - left[i] - 1;
            int area = heights[i] * width;
            if(area > maxArea) maxArea = area;
        }
        return maxArea;
    }
}

// Time complexity: O(n)
// Space complexity: O(n)