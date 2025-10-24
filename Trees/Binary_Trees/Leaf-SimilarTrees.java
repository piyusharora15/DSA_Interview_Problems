// Problem Link: https://leetcode.com/problems/leaf-similar-trees?envType=problem-list-v2&envId=tree

// Approach 1: Using DFS to collect leaf nodes and compare the sequences.
// Do a DFS traversal of both trees to collect their leaf nodes in a list.
// Finally, compare the two lists to check if they are identical.

// Code:
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        collectLeaves(root1,l1);
        collectLeaves(root2,l2);
        return l1.equals(l2);
    }
    private void collectLeaves(TreeNode node,List<Integer> list){
        if(node == null) return;
        if(node.left == null && node.right == null){
            list.add(node.val);
            return;
        }
        collectLeaves(node.left,list);
        collectLeaves(node.right,list);
    }
}

// Time Complexity: O(N + M), where N and M are the number of nodes in the two trees.
// Space Complexity: O(H1 + H2), where H1 and H2 are the heights of the two trees due to the recursion stack and the leaf lists.

// Approach 2: DFS with String Comparison.
// The approach involves performing an in-order traversal on each tree using a helper method called inOrder. 
// During the traversal, the leaf node values are appended to string/StringBuilder objects in a specific format.
// The resulting strings for both trees are then compared, and the method returns true if the leaf sequences are similar and false otherwise. 

// Code:
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        inOrder(root1,s1);
        inOrder(root2,s2);
        return s1.toString().equals(s2.toString());
    }
    private void inOrder(TreeNode root,StringBuilder s){
        if(root == null) return;
        if(root.left == null && root.right == null){
            s.append(root.val).append("_");
            return;
        }
        inOrder(root.left,s);
        inOrder(root.right,s);
    }
}

// Time Complexity: O(N + M), where N and M are the number of nodes in the two trees.
// Space Complexity: O(H1 + H2), where H1 and H2 are the heights of the two trees due to the recursion stack and the string storage.