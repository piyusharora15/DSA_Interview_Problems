// Problem Link: https://leetcode.com/problems/subarrays-with-k-different-integers

// Approach: Sliding Window.
// Time Complexity: O(N)
// Space Complexity: O(K)

// Code: 
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int subWithMaxK = subarrayWithAtMostK(nums,k);
      int reducedSubWithMaxK = subarrayWithAtMostK(nums,k-1);
      return subWithMaxK - reducedSubWithMaxK;  
    }
    public int subarrayWithAtMostK(int[] nums,int k){
        HashMap<Integer,Integer> mpp = new HashMap<>();
        int left = 0, right = 0, ans = 0;
        while(right < nums.length){
            mpp.put(nums[right],mpp.getOrDefault(nums[right],0) + 1);
            while(mpp.size()>k){
                mpp.put(nums[left],mpp.get(nums[left]) - 1);
                if(mpp.get(nums[left]) == 0){
                    mpp.remove(nums[left]);
                }
                left++;
            }
            ans += (right-left+1);
            right++;
        }
        return ans;
}
}