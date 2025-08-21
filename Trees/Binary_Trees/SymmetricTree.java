// Problem Link : https://leetcode.com/problems/symmetric-tree

// Approach 1 : Recursive DFS :
// Check if the left and right subtrees are mirror images of each other
// If both subtrees are null, return true
// If one of the subtrees is null, return false
// If the values of the root nodes are different, return false
// Recursively check the left subtree of the left tree with the right subtree of the right tree
// and the right subtree of the left tree with the left subtree of the right tree

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }
    private boolean isMirror(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null || p.val != q.val) return false;
        return isMirror(p.left,q.right) && isMirror(p.right,q.left);
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(H) where H is the height of the tree

// Approach 2 : Iterative BFS
// Use a queue to perform a level-order traversal of the tree
// At each step, compare the left and right children of the current node
// If both children are null, continue to the next pair
// If one of the children is null or their values are different, return false
// Add the left and right children of both nodes to the queue

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);
        while(!q.isEmpty()){
            TreeNode n1 = q.poll();
            TreeNode n2 = q.poll();
            if(n1 == null && n2 == null) continue;
            if(n1 == null || n2 == null || n1.val != n2.val) return false;
            q.offer(n1.left);
            q.offer(n2.right);
            q.offer(n1.right);
            q.offer(n2.left);
        }
        return true;
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(W) where W is the maximum width of the tree