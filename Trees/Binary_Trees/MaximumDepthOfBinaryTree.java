// Problem Link: https://leetcode.com/problems/maximum-depth-of-binary-tree?envType=problem-list-v2&envId=tree

// Approach 1: Recursive Depth-First Search (DFS).
// We can find the maximum depth of a binary tree by recursively traversing each node and calculating the depth of its left and right subtrees. The maximum depth at each node is 1 plus the maximum depth of its left and right children.

// Code:
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return 1 + maxDepth(root.right);
        if (root.right == null)
            return 1 + maxDepth(root.left);
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(H), where H is the height of the tree. This space is used by the recursion stack. In the worst case (for a skewed tree), the height can be N, leading to O(N) space complexity. In a balanced tree, the height is log(N), leading to O(log N) space complexity.

// Approach 2: Iterative Breadth-First Search (BFS).
// We can also find the maximum depth of a binary tree using an iterative approach with a queue. We perform a level-order traversal of the tree, counting the number of levels we traverse. The number of levels corresponds to the maximum depth of the tree.

// Code:
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
        }

        return depth;
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(W), where W is the maximum width of the tree. In the worst case, the queue can hold all nodes at the deepest level, leading to O(N) space complexity in a balanced tree.