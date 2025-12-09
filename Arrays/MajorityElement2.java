// Problem Link: https://leetcode.com/problems/majority-element-ii?envType=problem-list-v2&envId=array

// Approach 1: HashMap.
// We can use a HashMap to count the occurrences of each element in the array.
// Then, we can iterate through the HashMap to find all elements that appear more than n/3 times.

// Code:
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        Map<Integer,Integer> mpp = new HashMap<>();
        int min = (int) (n/3) + 1;
        for(int i=0;i<n;i++){
            mpp.put(nums[i],mpp.getOrDefault(nums[i],0) + 1);
            if(mpp.get(nums[i]) == min){
                ans.add(nums[i]);
            }
            if(ans.size() == 2) break;
        }
        return ans;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)

// Approach 2: Boyer-Moore Voting Algorithm.
// We can use the Boyer-Moore Voting Algorithm to find the majority elements.
// Since we are looking for elements that appear more than n/3 times, there can be at most 2 such elements.
// We can maintain two candidate elements and their counts while iterating through the array.
// After finding the candidates, we need to verify their counts in a second pass.

// Code:
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int cnt1 = 0,cnt2 = 0,el1 = Integer.MIN_VALUE,el2 = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(cnt1 == 0 && el2 != nums[i]){
                cnt1 = 1;
                el1 = nums[i];
            }
            else if(cnt2 == 0 && el1 != nums[i]){
                cnt2 = 1;
                el2 = nums[i];
            }
            else if(nums[i] == el1) cnt1++;
            else if(nums[i] == el2) cnt2++;
            else{
                cnt1--;
                cnt2--;
            }
        }
        List<Integer> ls = new ArrayList<>();
        cnt1 = 0;
        cnt2 = 0;
        for(int i=0;i<n;i++){
            if(nums[i] == el1) cnt1++;
            if(nums[i] == el2) cnt2++;
        }
        int mini = (int) (n/3) + 1;
        if(cnt1 >= mini) ls.add(el1);
        if(cnt2 >= mini) ls.add(el2);
        return ls;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)