// Problem Link : https://leetcode.com/problems/path-sum-ii?envType=problem-list-v2&envId=tree

// Approach : Recursive DFS :
// Keep track of the current path and the remaining sum
// If a leaf node is reached, check if the remaining sum is equal to the node's value
// If not, continue to the next node

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        dfs(root,targetSum,new ArrayList<>(),ans);
        return ans;
    }
    private void dfs(TreeNode node,int remaining,List<Integer> path,List<List<Integer>> ans){
        if(node == null) return;
        path.add(node.val);
        remaining -= node.val;
        if(node.left == null && node.right == null && remaining == 0){
            ans.add(new ArrayList<>(path));
        }
        dfs(node.left,remaining,path,ans);
        dfs(node.right,remaining,path,ans);
        path.remove(path.size()-1);
    }
}

// Time Complexity : O(N + P.H) where N is the number of nodes in the tree and P is the number of valid paths and H is the height of the tree.
// Space Complexity : O(H) where H is the height of the tree.