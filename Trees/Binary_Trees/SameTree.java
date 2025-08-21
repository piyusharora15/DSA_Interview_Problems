// Problem Link : https://leetcode.com/problems/same-tree

// Approach 1 : Recursive DFS :
// If both nodes are null, return true
// If one of the nodes is null, return false
// If the values of the nodes are different, return false
// Recursively check the left and right subtrees

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(H) where H is the height of the tree

// Approach 2 : Iterative BFS
// Use a queue to perform a level-order traversal of both trees
// At each step, compare the current nodes of both trees
// If both nodes are null, continue to the next pair
// If one of the nodes is null or their values are different, return false
// Add the left and right children of both nodes to the queue

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(p);
        queue2.add(q);
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if(node1 == null && node2 == null) continue;
            if(node1 == null || node2 == null || node1.val != node2.val) return false;
            queue1.add(node1.left);
            queue1.add(node1.right);
            queue2.add(node2.left);
            queue2.add(node2.right);
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

// Time Complexity : O(N) where N is the number of nodes in the tree
// Space Complexity : O(W) where W is the maximum width of the tree