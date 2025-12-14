// Problem Link: https://leetcode.com/problems/contains-duplicate-ii?envType=problem-list-v2&envId=sliding-window

// Approach 1: HashMap.
// We can use a HashMap to store the elements and their latest indices as we iterate through the array.
// For each element, we check if it already exists in the HashMap and if the difference between the current index and the stored index is less than or equal to k.
// If both conditions are met, we return true. If we finish iterating through the array without finding such a pair, we return false.

// Code:
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> mpp = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(mpp.containsKey(nums[i])){
                int prev = mpp.get(nums[i]);
                if(i - prev <= k){
                    return true;
                }
            }
            mpp.put(nums[i],i);
        }
        return false;
    }
}

// Time Complexity: O(n), where n is the number of elements in the array. We traverse the array once.
// Space Complexity: O(min(n, k)), where n is the number of elements in the array and k is the size of the sliding window. In the worst case, we may store up to k elements in the HashMap.

// Approach 2: Sliding Window with HashSet.
// We can use a HashSet to maintain a sliding window of size k.
// As we iterate through the array, we add each element to the HashSet.
// If the size of the HashSet exceeds k, we remove the element that is k positions behind the current element.
// If we encounter an element that is already in the HashSet, we return true.

// Code:
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> st = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(st.contains(nums[i])){
                return true;
            }
            st.add(nums[i]);
            if(st.size() > k){
                st.remove(nums[i-k]);
            }
        }
        return false;
    }
}

// Time Complexity: O(n), where n is the number of elements in the array. We traverse the array once.
// Space Complexity: O(min(n, k)), where n is the number of elements in the array and k is the size of the sliding window. In the worst case, we may store up to k elements in the HashSet.