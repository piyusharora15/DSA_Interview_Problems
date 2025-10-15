// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii?envType=problem-list-v2&envId=linked-list

// Approach 1: Iterative.
// Using a dummy node to handle edge cases where the head itself might be removed.
// We traverse the list and skip all nodes that have duplicates.
// Time Complexity: O(N), where N is the number of nodes in the list.
// Space Complexity: O(1), since we are not using any extra space.

// Code:
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0,head);
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null){
            if(curr.next != null && curr.val == curr.next.val){
                int dupVal = curr.val;
                while(curr != null && curr.val == dupVal){
                    curr = curr.next;
                }
                prev.next = curr;
            }
            else{
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}

// Approach 2: Recursive.
// We recursively process the list, skipping nodes with duplicates.

// Time Complexity: O(N), where N is the number of nodes in the list.
// Space Complexity: O(N) due to the recursion stack.

// Code:
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            int val = head.val;
            ListNode curr = head;
            while (curr != null && curr.val == val)
                curr = curr.next;
            return deleteDuplicates(curr);
        }
    }
}