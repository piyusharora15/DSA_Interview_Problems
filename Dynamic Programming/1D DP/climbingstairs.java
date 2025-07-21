/*  Company Tags   : Google, Amazon, OYO Rooms, Microsoft, Adobe, Flipkart, Siemens
    Leetcode Link  : https://leetcode.com/problems/climbing-stairs/
*/

// Approach 1 : Using Memoization
/*
Time Complexity: O(n) — Each value from 0 to n is calculated once.
Space Complexity: O(n) — Recursion stack + dp[] array.
*/
import java.util.*;
public class climbingstairs {
    public int climbStairs(int n) {
        HashMap<Integer,Integer> memo = new HashMap<>();
        return climbStairs(n,memo);
    }
    private int climbStairs(int n,Map<Integer,Integer> memo){
        if(n==0 || n==1){
            return 1;
        }
        if(!memo.containsKey(n)){
            memo.put(n,climbStairs(n-1,memo) + climbStairs(n-2,memo));
        }
        return memo.get(n);
    }
}

// Approach 2: Using Tabulation:
/*
Time Complexity: O(n) — Loop from 2 to n.
Space Complexity: O(n) — Due to the dp[] array.
*/

class Solution {
    public int climbStairs(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}

// Approach 3: Using Space Optimization:
/*
Time Complexity: O(n)
Space Complexity: O(1) — Just using two variables instead of an array.
*/
 class Solution {
    public int climbStairs(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int prev = 1, curr = 1;
        for(int i=2;i<=n;i++){
            int temp = curr;
            curr = prev + curr;
            prev = temp;
        }
        return curr;
    }
}
