// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list

// Approach: Iterative, single pass.
// Maintain a pointer to the current node and compare it with the next node.
// If they are the same, skip the next node by adjusting the current node's next pointer.
// If they are different, move the current pointer to the next node.
// Time Complexity: O(n), where n is the number of nodes in the linked list.
// Space Complexity: O(1), as we are using only a constant amount of extra space

// Code:
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode current = head;
        while(current != null && current.next != null){
            if(current.val == current.next.val){
                current.next = current.next.next;
            }
            else{
                current = current.next;
            }
        }
        return head;
    }
}