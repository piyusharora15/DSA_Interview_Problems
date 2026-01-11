// Problem Link: https://www.geeksforgeeks.org/problems/immediate-smaller-element1142/1

// Brute Force Approach: We can use two nested loops to find the next smaller element for each element in the array.We iterate through each element and for each element, we check the elements to its right until we find a smaller element or reach the end of the array.

// Code:
import java.util.*;
class Solution {
    public ArrayList<Integer> nextSmallerElement(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++){
            int nextSmaller = -1;
            for(int j=i+1;j<n;j++){
                if(arr.get(j) < arr.get(i)){
                    nextSmaller = arr.get(j);
                    break;
                }
            }
            result.add(nextSmaller);
        }
        return result;
    }
}

// Time complexity: O(n²)
// Space complexity: O(1)

// Optimal Approach: Monotonic Stack.
// We can use a stack to keep track of the elements for which we haven't found the next smaller element yet. We iterate through the array from right to left, and for each element, we pop elements from the stack until we find a smaller element or the stack becomes empty. The top of the stack will be the next smaller element for the current element.

// Code:
class Solution {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<n;i++) res.add(-1);
        Stack<Integer> st = new Stack<>();
        for(int i=n-1;i>=0;i--){
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