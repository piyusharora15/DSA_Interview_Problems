// Problem Link: https://leetcode.com/problems/merge-k-sorted-lists?envType=problem-list-v2&envId=linked-list

// Brute Force Approach: The idea is to initialize the resultant linked list as null. Then, we will traverse through each of the k linked lists and merge them one by one into the resultant linked list using the mergeTwoLists function. The mergeTwoLists function takes two sorted linked lists as input and merges them into a single sorted linked list. This process is repeated for all k linked lists.

// Code:
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode mergedList = null;

        for (ListNode list : lists) {
            mergedList = mergeTwoLists(mergedList, list);
        }

        return mergedList;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummy.next;
    }
}

// Time Complexity: O(N*k), where N is the average number of nodes in each linked list and k is the number of linked lists. This is because we are merging k linked lists, and each merge operation takes O(N) time.
// Space Complexity: O(1), as we are using only a constant amount of extra space.

// Optimal Approach: The idea is to use a min-heap (priority queue) to efficiently get the smallest node among the k linked lists. We will initialize the min-heap with the head nodes of all k linked lists. Then, we will repeatedly extract the minimum node from the heap and add it to the merged linked list. If the extracted node has a next node, we will insert that next node into the heap. This process continues until the heap is empty.

// Code:
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for(ListNode head : lists){
            if(head != null) pq.add(head);
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while(!pq.isEmpty()){
            ListNode top = pq.poll();
            tail.next = top;
            tail = top;
            if(tail.next != null){
                pq.add(top.next);
            }
        }
        return dummy.next;
    }
}

// Time Complexity: O(N log k), where N is the total number of nodes across all linked lists and k is the number of linked lists. This is because each insertion and extraction operation from the priority queue takes O(log k) time, and we perform these operations for all N nodes.
// Space Complexity: O(k), as we are storing at most k nodes in the priority queue at any given time.