// Problem Link : https://leetcode.com/contest/weekly-contest-461/problems/trionic-array-i/description/

// Approach: We will use a brute force method to find the three segments. We will use three pointers p, q, r to find the three segments. We will check if the first segment is strictly increasing, the second segment is strictly decreasing and the third segment is strictly increasing.

class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if(n < 4){
            return false;
        }
        for(int p=1;p<=n-3;p++){
            for(int q=p+1;q<=n-2;q++){
                boolean segment1 = isStrictlyIncreasing(nums,0,p);
                if(!segment1){
                    continue;
                }
                boolean segment2 = isStrictlyDecreasing(nums,p,q);
                if(!segment2){
                    continue;
                }
                boolean segment3 = isStrictlyIncreasing(nums,q,n-1);
                if(segment3){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isStrictlyIncreasing(int[] nums,int start, int end){
        if(start >= end){
            return true;
        }
        for(int i=start;i<end;i++){
            if(nums[i] >= nums[i+1]){
                return false;
            }
        }
        return true;
    }
    private boolean isStrictlyDecreasing(int[] nums,int start,int end){
        if(start >= end){
            return true;
        }
        for(int i=start;i<end;i++){
            if(nums[i] <= nums[i+1]){
                return false;
            }
        }
        return true;
    }
}

// Time Complexity: O(n^3)
// Space Complexity: O(1)