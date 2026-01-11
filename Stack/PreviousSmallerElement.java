// Problem Link: https://www.geeksforgeeks.org/problems/previous-smaller-element/1

// Brute Force Approach:
// We traverse the array from left to right. For each element, we look for the first smaller element to its left by checking all previous elements one by one.

// Code:
import java.util.*;
class Solution {
    public static List<Integer> previousSmallerElement(int[] arr) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int prevSmaller = -1; // Default value if no smaller element is found
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    prevSmaller = arr[j];
                    break;
                }
            }
            result.add(prevSmaller);
        }
        return result;
    }
}

// Time complexity: O(n²)
// Space complexity: O(1)

// Optimal Approach: Monotonic Stack.
// We use a stack to keep track of elements for which we haven't found a smaller element yet. As we traverse the array, we pop elements from the stack until we find a smaller element or the stack becomes empty. The top of the stack will be the previous smaller element for the current element.

// Code:
class Solution {
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<n;i++) res.add(-1);
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty() && st.peek() >= arr[i]){
                st.pop();
            }
            if(!st.isEmpty()){
                res.set(i,st.peek());
            }
            st.push(arr[i]);
        }
        return res;
    }
}

// Time complexity: O(n)
// Space complexity: O(n)