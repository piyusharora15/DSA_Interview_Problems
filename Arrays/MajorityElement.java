// Problem Link: https://leetcode.com/problems/majority-element

// Approach 1: HashMap to count occurrences.
// We can use a HashMap to count the occurrences of each element in the array.
// The element with the count greater than n/2 is the majority element.

// Code:
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Map<Integer,Integer> mpp = new HashMap<>();
        for(int i=0;i<n;i++){
            mpp.put(nums[i],mpp.getOrDefault(nums[i],0) + 1);
        }
        for(Map.Entry<Integer,Integer> it: mpp.entrySet()){
            if(it.getValue() > (n/2)){
                return it.getKey();
            }
        }
        return -1;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)

// Approach 2: Boyer-Moore Voting Algorithm.
// This algorithm works by maintaining a count and a candidate for the majority element. We increment the count when we see the candidate and decrement it when we see a different element. When the count reaches zero, we change the candidate to the current element. At the end, the candidate will be the majority element.

// Code:
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int cnt = 0,ele = 0;
        for(int i=0;i<n;i++){
            if(cnt == 0){
                cnt = 1;
                ele = nums[i];
            }
            else if(nums[i] == ele) cnt++;
            else cnt--;
        }
        return ele;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)