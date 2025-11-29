// Problem Link: https://leetcode.com/problems/3sum

// Approach: Sorting + Two Pointers.
// We first sort the array to make it easier to avoid duplicates and use the two-pointer technique.
// For each element, we fix it and use two pointers to find pairs that sum to the negative of the fixed element.

// Code:
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
            if(i!=0 && nums[i]==nums[i-1])
            continue;
            int j = i+1;
            int k = nums.length-1;
            while(j<k)
            {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum<0)
                {
                    j++;
                }
                else if(sum>0)
                {
                    k--;
                }
                else
                {
                    List<Integer> temp= Arrays.asList(nums[i],nums[j],nums[k]);
                    ans.add(temp);
                    j++;
                    k--;
                    while((j<k) && nums[j]==nums[j-1])
                    j++;
                    while((j<k) && nums[k]==nums[k+1])
                    k--;  
                }
            }
        }
        return ans;
    }
}

// Time Complexity: O(N^2) where N is the number of elements in the array.
// Space Complexity: O(1) if we don't consider the output list.