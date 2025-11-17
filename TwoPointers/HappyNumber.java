// Problem Link: https://leetcode.com/problems/happy-number?envType=problem-list-v2&envId=two-pointers

// Approach 1: HashSet to detect cycle.
// Compute the next value repeatedly and store seen numbers in a HashSet. If you ever see 1, return true. If you see a previously-seen number (not 1), you found a cycle → false.

// Code:
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> st = new HashSet<>();
        while(n != 1 && !st.contains(n)){
            st.add(n);
            n = next(n);
        }
        return n == 1;
    }
    private int next(int n){
        int sum = 0;
        while(n > 0){
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }
}

// Time Complexity: O(log n)
// Space Complexity: O(log n)

// Approach 2: Floyd's Cycle-Finding Algorithm.
// Use two pointers (slow and fast) to detect cycle. If they meet at 1, return true. If they meet at any other number, return false.

// Code:
class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do{
            slow = next(slow);
            fast = next(next(fast));
        }
        while(slow != fast);
        return slow == 1;
    }
    private int next(int n){
        int sum = 0;
        while(n > 0){
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }
}

// Time Complexity: O(log n)
// Space Complexity: O(1)