// Problem Link: https://leetcode.com/problems/gas-station

// Approach: Greedy.
// We first check if the total gas is less than the total cost. If it is, we return -1 as it's impossible to complete the circuit.
// If the total gas is sufficient, we then iterate through the gas stations, maintaining a current tank balance.
// If at any point the tank balance drops below zero, we reset the starting station to the next station and reset the tank balance.

// Code:
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
       for(int i=0;i<gas.length;i++){
        totalGas += gas[i];
        totalCost += cost[i];
       } 
       if(totalGas < totalCost){
        return -1;
       }
       int currentGas = 0, start = 0;
       for(int i=0;i<gas.length;i++){
        currentGas += gas[i] - cost[i];
        if(currentGas < 0){
            currentGas = 0;
            start = i+1;
        }
       }
       return start;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)