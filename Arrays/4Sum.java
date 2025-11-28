// Problem Link: https://leetcode.com/problems/4sum

// Approach: Sorting + Two Pointers
// We sort the array and then use two nested loops to fix the first two numbers.
// For the remaining two numbers, we use the two-pointer technique to find pairs that sum up to the required value.

// Code:
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<n;i++)
        {
            if(i>0 && nums[i]==nums[i-1])
            continue;
            for(int j=i+1;j<n;j++)
            {
                if(j>i+1 && nums[j]==nums[j-1])
                continue;
                int k=j+1;
                int l=n-1;
                while(k<l)
                {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if(sum == target)
                    {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        ans.add(temp);
                        k++;
                        l--;
                        while(k<l && nums[k]==nums[k-1])
                        k++;
                        while(k<l && nums[l]==nums[l+1])
                        l--;
                    }
                    else if(sum<target)
                    k++;
                    else
                    l--;
                }
            }
        }
        return ans;
    }
}

// Time Complexity: O(n^3)
// Space Complexity: O(1) (excluding the space for the output list)