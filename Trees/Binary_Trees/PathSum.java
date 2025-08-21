// Problem Link : https://leetcode.com/problems/path-sum

// Approach 1 : Recursive DFS:
// Check if the current node is null
// If it is, check if the remaining sum is 0
// If the current node is a leaf, check if the remaining sum is equal to the node's value
// Recursively check the left and right subtrees with the updated remaining sum

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasSum(root,0,targetSum);
    }
    private boolean hasSum(TreeNode node,int currSum,int targetSum){
        if(node == null) return false;
        currSum += node.val;
        if(node.left == null && node.right == null){
            return currSum == targetSum;
        }
        return hasSum(node.left,currSum,targetSum) || hasSum(node.right,currSum,targetSum);
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(H) where H is the height of the tree

// Approach 2 : Iterative BFS using a Queue :
// Use a queue to perform a level-order traversal of the tree
// At each step, keep track of the current node and the remaining sum
// If a leaf node is reached, check if the remaining sum is equal to the node's value
// If not, continue to the next node

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasSum(root,0,targetSum);
    }
    private boolean hasSum(TreeNode node,int currSum,int targetSum){
        if(node == null) return false;
        currSum += node.val;
        if(node.left == null && node.right == null){
            return currSum == targetSum;
        }
        return hasSum(node.left,currSum,targetSum) || hasSum(node.right,currSum,targetSum);
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(H) where H is the height of the tree