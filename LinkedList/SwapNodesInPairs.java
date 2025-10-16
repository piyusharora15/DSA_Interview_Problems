// Problem Link: https://leetcode.com/problems/swap-nodes-in-pairs

// Approach 1: Recursive.
// If the list is empty or has only one node, return the head.
// Otherwise, swap the first two nodes and recursively call the function for the rest of the list.
// Time Complexity: O(n), Space Complexity: O(n) due to recursion stack.

// Code:
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }
}

// Approach 2: Iterative.
// Use a dummy node to simplify the swapping process.
// Iterate through the list and swap pairs of nodes.
// Time Complexity: O(n), Space Complexity: O(1).

// Code:
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while(prev.next != null && prev.next.next != null){
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            first.next = second.next;
            second.next = first;
            prev.next = second;
            prev = first;
        }
        return dummy.next;
    }
}