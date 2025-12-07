// Problem Link: https://leetcode.com/problems/3sum-closest

// Brute Force Approach: O(n^3) time complexity

// We can solve this problem using a brute force approach by checking all possible triplets in the array and calculating their sums. We will keep track of the closest sum to the target found so far.

// Code:
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    int sum = nums[i] + nums[j] + nums[k];
                    if(Math.abs(sum-target) < Math.abs(closestSum-target)){
                        closestSum = sum;
                    }
                }
            }
        }
        return closestSum;
    }
}

// Optimal Approach: Two Pointers.
// We can sort the array and use the two-pointer technique to find the closest sum in O(n^2) time complexity. We will fix one element and use two pointers to find the other two elements.

// Code:
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for(int i=0;i<n-2;i++){
            int left = i+1;
            int right = n-1;
            while(left < right){
                int sum = nums[i]+nums[left]+nums[right];
                if(Math.abs(sum-target) < Math.abs(closestSum-target)){
                    closestSum = sum;
                }
                if(sum < target){
                    left++;
                }
                else if(sum > target){
                    right--;
                }
                else{
                    return sum;
                }
            }
        }
        return closestSum;
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(1)