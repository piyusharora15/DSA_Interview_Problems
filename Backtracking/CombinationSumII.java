// Problem Link: https://leetcode.com/problems/combination-sum-ii

// Approach: Backtracking with sorting and skipping duplicates.
// We first sort the candidates to handle duplicates easily. We use a backtracking approach to explore all possible combinations, skipping over duplicates to ensure each combination is unique.

// Code:
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        findCombinations(0,candidates,target,ans,new ArrayList<>());
        return ans;
    }
     static void findCombinations(int ind,int[] arr,int target,List<List<Integer>> ans,List<Integer> ds){
        if(target == 0){
            ans.add(new ArrayList<>(ds));
            return;
        }
        for(int i=ind;i<arr.length;i++){
            if(i>ind && arr[i] == arr[i-1])
            continue;
            if(arr[i]>target)
            break;
            ds.add(arr[i]);
            findCombinations(i+1,arr,target-arr[i],ans,ds);
            ds.remove(ds.size()-1);  
    }
     }
}

// Time Complexity: O(2^n) in the worst case, where n is the number of candidates.
// Space Complexity: O(k) where k is the maximum depth of the recursion tree.