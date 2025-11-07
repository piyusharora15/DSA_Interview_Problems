// Problem Link: https://leetcode.com/problems/merge-two-sorted-lists

// Approach: Iterative Merging of Two Sorted Linked Lists.
// We create a dummy node to simplify the merging process and use two pointers to traverse both lists, appending the smaller node to the merged list at each step.

// Code: 
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                curr.next = list2;
                list2 = list2.next;
            }
            else{
                curr.next = list1;
                list1 = list1.next;
            }
            curr = curr.next;
        }
        curr.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}

// Time Complexity: O(n + m), where n and m are the lengths of the two linked lists.
// Space Complexity: O(1), as we are using only a constant amount of extra space.