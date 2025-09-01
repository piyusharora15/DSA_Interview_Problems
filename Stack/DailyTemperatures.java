// Problem Link : https://leetcode.com/problems/daily-temperatures

// Brute Force Approach : For each day 'i', look at all future days 'j > i'. Find the first day where 'temperatures[j] > temperatures[i]' and record 'j-i', else 0.

// Code:

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(temperatures[j] > temperatures[i]){
                    res[i] = j-i;
                    break;
                }
            }
        }
        return res;
    }
}

// Time Complexity : O(n^2), nested loops (TLE).
// Space Complexity : O(n), for the result array.

// Optimal Approach : Instead of comparing each day with every future array, maintain a stack of indices of days whose answers are unresolved. Use a monotonic decreasing stack of temperatures : when the current temperature is higher than what's on top of the stack, we've found the next warmer day.

// Code :

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> st = new Stack<>();
        int[] result = new int[n];
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty() && temperatures[i] >= temperatures[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()){
                result[i] = 0;
            }
            else{
                result[i] = st.peek() - i;
            }
            st.push(i);
        }
        return result;
    }
}

// Time Complexity: O(n), each element is pushed and popped from the stack at most once.
// Space Complexity: O(n), for the result array.