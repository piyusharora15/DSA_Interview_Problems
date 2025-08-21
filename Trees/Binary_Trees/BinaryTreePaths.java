// Problem Link : https://leetcode.com/problems/binary-tree-paths

// Approach : Recursive DFS
// Keep track of the current path
// If a leaf node is reached, add the current path to the result
// If not, continue to the next node

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root,"",ans);
        return ans;
    }
    private void dfs(TreeNode node,String path,List<String> ans){
        if(node == null) return;
        path += node.val;
        if(node.left == null && node.right == null){
            ans.add(new String(path));
        }
        path = path + "->";
        dfs(node.left,path,ans);
        dfs(node.right,path,ans);
        path.substring(path.length());
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(H) where H is the height of the tree