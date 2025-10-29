// Problem Link: https://leetcode.com/problems/balanced-binary-tree?envType=problem-list-v2&envId=tree

// Approach 1: Naive Recursion.
// We calculate the height of left and right subtrees for every node and check the balance condition.
// Time Complexity: O(N^2) in the worst case (unbalanced tree).
// Space Complexity: O(H) where H is the height of the tree due to recursion stack.

// Code:
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int lh = height(root.left);
        int rh = height(root.right);
        if(Math.abs(lh-rh) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int height(TreeNode node){
        if(node == null) return 0;
        return 1 + Math.max(height(node.left),height(node.right));
    }
}

// Approach 2: Optimized Recursion.
// We calculate the height of the tree while checking the balance condition in a single traversal.
// Time Complexity: O(N) where N is the number of nodes in the tree.
// Space Complexity: O(H) where H is the height of the tree due to recursion stack.

// Code:
class Solution {
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }
    private int checkHeight(TreeNode node){
        if(node == null) return 0;
        int leftH = checkHeight(node.left);
        if(leftH == -1) return -1;
        int rightH = checkHeight(node.right);
        if(rightH == -1) return -1;
        if(Math.abs(leftH - rightH) > 1) return -1;
        return 1 + Math.max(leftH,rightH);
    }
}