// Problem Link : https://leetcode.com/problems/path-sum-iii

// Approach : Recursive DFS
// Keep track of the current path and the number of valid paths
// If a leaf node is reached, check if the remaining sum is equal to the node's value
// If not, continue to the next node

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        return countPaths(root,targetSum) + pathSum(root.left,targetSum) + pathSum(root.right,targetSum);
    }
    private int countPaths(TreeNode node,long targetSum){
        if(node == null) return 0;
        int count = 0;
        if(node.val == targetSum) count++;
        count += countPaths(node.left,targetSum - node.val);
        count += countPaths(node.right,targetSum - node.val);
        return count;
    }
}

// Time Complexity : O(N^2) where N is the number of nodes in the tree
// Space Complexity : O(H) where H is the height of the tree

// Approach 2 : Prefix Sum and HashMap.
// Use a HashMap to store the prefix sums and their frequencies
// At each node, calculate the current prefix sum
// Check if there is a prefix sum that equals the current prefix sum minus the target sum
// If found, it means there is a valid path

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long,Integer> prefix = new HashMap<>();
        prefix.put(0L,1);
        return dfs(root,0,targetSum,prefix);
    }
    private int dfs(TreeNode node,long sum,int target,Map<Long,Integer> prefix){
        if(node == null) return 0;
        sum += node.val;
        int res = prefix.getOrDefault(sum-target,0);
        prefix.put(sum,prefix.getOrDefault(sum,0) + 1);
        res += dfs(node.left,sum,target,prefix);
        res += dfs(node.right,sum,target,prefix);
        prefix.put(sum,prefix.get(sum) - 1);
        return res;
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(N) where N is the number of nodes in the tree