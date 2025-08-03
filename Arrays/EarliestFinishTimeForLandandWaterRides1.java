// Problem Link : https://leetcode.com/contest/biweekly-contest-162/problems/earliest-finish-time-for-land-and-water-rides-i/description/


// Approach: We need to consider all possible pairs of land and water rides. For each pair, we calculate the finish time and keep track of the minimum finish time across all pairs.

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinishTime = Integer.MAX_VALUE;
        for(int i=0;i<landStartTime.length;i++){
            for(int j=0;j<waterStartTime.length;j++){
                int landFinishTime = landStartTime[i] + landDuration[i];
                int waterStart = Math.max(landFinishTime,waterStartTime[j]);
                int totalFinishTime = waterStart + waterDuration[j];
                minFinishTime = Math.min(minFinishTime,totalFinishTime);
            }
        }
        for(int j=0;j<waterStartTime.length;j++){
            for(int i=0;i<landStartTime.length;i++){
                int waterFinishTime = waterStartTime[j] + waterDuration[j];
                int landStart = Math.max(waterFinishTime,landStartTime[i]);
                int totalFinishTime = landStart + landDuration[i];
                minFinishTime = Math.min(minFinishTime,totalFinishTime);
            }
        }
        return minFinishTime;
    }
}

// Time Complexity: O(n * m) where n is the number of land rides and m is the number of water rides.
// Space Complexity: O(1) since we are using only a few extra variables for calculations