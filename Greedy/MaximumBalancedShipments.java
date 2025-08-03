// Problem Link: https://leetcode.com/contest/weekly-contest-461/problems/maximum-balanced-shipments/description/

// Problem Explanation: The problem requires us to find the maximum number of balanced shipments we can make from an array of weights. A shipment is considered balanced if the next weight in the sequence is less than the maximum weight seen so far. 

//Approach: Initialize a counter to keep track of the number of balanced shipments. Use a while loop to iterate through the weights array. For each weight, we find the maximum weight seen so far and check if the next weight is less than this maximum. If we find a balanced shipment, we increment the counter and move to the next weight after the current one. If we don't find any more balanced shipments, we break out of the loop.

class Solution {
    public int maxBalancedShipments(int[] weight) {
        int n = weight.length;
        if(n == 0){
            return 0;
        }
        int count = 0;
        int i = 0;
        while(i < n){
            int currMax = weight[i];
            int j = i+1;
            boolean balanced = false;
            while(j < n){
                currMax = Math.max(currMax,weight[j]);
                if(weight[j] < currMax){
                    count++;
                    i = j+1;
                    balanced = true;
                    break;
                }
                j++;
            }
            if(!balanced){
                break;
            }
        }
        return count;
    }
}

// Time Complexity: O(n^2) in the worst case, where n is the length of the weights array.
// Space Complexity: O(1) as we are using a constant amount of space for variables
