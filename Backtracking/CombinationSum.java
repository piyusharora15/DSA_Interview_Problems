// Problem Link: https://leetcode.com/problems/combination-sum

// Approach: Backtracking.
// We explore all possible combinations of numbers that can sum up to the target.
// We use a recursive function to build combinations and backtrack when the sum exceeds the target.

// Code:
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0, candidates, target, ans, new ArrayList<>());
        return ans;
    }
    private void findCombinations(int ind, int[] arr, int target, List<List<Integer>> ans, List<Integer> ds) {
        if (ind == arr.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(ds));
            }
            return;
        }
        if (arr[ind] <= target) {
            ds.add(arr[ind]);
            findCombinations(ind, arr, target - arr[ind], ans, ds);
            ds.remove(ds.size() - 1);
        }
        findCombinations(ind + 1, arr, target, ans, ds); 
    }
}

// Time Complexity: O(2^t * k) where t is the target and k is the average length of each combination.
// Space Complexity: O(k) for the recursion stack and the temporary list used to store combinations.