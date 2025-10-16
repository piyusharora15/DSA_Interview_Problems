// Problem Link: https://leetcode.com/problems/partition-list?envType=problem-list-v2&envId=linked-list

// Approach:
// 1. Create two dummy nodes to represent the heads of the two partitions: one for nodes less than x and one for nodes greater than or equal to x.
// 2. Traverse the original linked list and append each node to the appropriate partition based on its value.
// 3. After processing all nodes, connect the two partitions together.
// 4. Ensure the last node of the greater than or equal to x partition points to null to avoid cycles.
// 5. Return the head of the new partitioned list, which is the next node of the dummy node for the less than x partition.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        // Dummy nodes to represent the heads of the two partitions
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);
        
        // Pointers to the current end of the two partitions
        ListNode less = lessHead;
        ListNode greater = greaterHead;
        
        // Traverse the original list and partition the nodes
        while (head != null) {
            if (head.val < x) {
                less.next = head;  // Append to the 'less than x' partition
                less = less.next;  // Move the pointer forward
            } else {
                greater.next = head;  // Append to the 'greater than or equal to x' partition
                greater = greater.next;  // Move the pointer forward
            }
            head = head.next;  // Move to the next node in the original list
        }
        
        // Connect the two partitions
        less.next = greaterHead.next;  // Link the end of 'less than x' to the start of 'greater than or equal to x'
        greater.next = null;  // Ensure the last node points to null
        
        return lessHead.next;  // Return the head of the new partitioned list
    }
}
// Time Complexity: O(n) - We traverse the list once.
// Space Complexity: O(1) - We use a constant amount of extra space for the dummy nodes and pointers.