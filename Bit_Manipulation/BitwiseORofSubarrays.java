// Problem Link : https://leetcode.com/problems/bitwise-ors-of-subarrays?envType=daily-question&envId=2025-07-31

// Brute Force Approach:
/*
-> Iterate through all subarrays and calculate the bitwise OR for each.
-> Store the results in a set to avoid duplicates.
-> Finally, return the size of the set.
*/

import java.util.HashSet;
import java.util.Set;

public int subarrayBitwiseORs(int[] arr){
    Set<Integer> resultSet = new HashSet<>();
    int n = arr.length;
    
    for (int i = 0; i < n; i++) {
        int currentOR = 0;
        for (int j = i; j < n; j++) {
            currentOR |= arr[j];
            resultSet.add(currentOR);
        }
    }
    
    return resultSet.size();
}

// Time Complexity: O(n^2) ; TLE for large inputs (e.g., n = 10⁵)
// Space Complexity: O(n) ; for storing results in a set.

// Optimal Approach: Using a Stack to Maintain Current OR Values
/*
-> Iterate through the array and maintain a set to keep track of the current OR values.
-> For each element, calculate the new OR values by OR-ing it with the elements in the set.
-> Store the results in a set to avoid duplicates.
-> Finally, return the size of the set.
*/
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> resultSet = new HashSet<>();
        Set<Integer> prev = new HashSet<>();
        for(int num: arr){
            Set<Integer> curr = new HashSet<>();
            curr.add(num);
            for(int orVal : prev){
                curr.add(orVal | num);
            }
            resultSet.addAll(curr);
            prev = curr;
        }
        return resultSet.size();
    }
}
// Time Complexity: O(n * log n) ; more efficient than brute force
// Space Complexity: O(n) ; for storing results in a set and stack